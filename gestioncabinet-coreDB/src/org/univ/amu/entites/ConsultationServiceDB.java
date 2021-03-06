/**
 * 
 */
package org.univ.amu.entites;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

import org.univ.amu.PrescriptionService;

import fr.vidal.webservices.interactionservice.ArrayOfInt;
import fr.vidal.webservices.interactionservice.InteractionCouple;
import fr.vidal.webservices.interactionservice.InteractionResult;
import fr.vidal.webservices.interactionservice.InteractionService;
import fr.vidal.webservices.interactionservice.InteractionService_Service;
import fr.vidal.webservices.interactionservice.InteractionSeverityType;
import fr.vidal.webservices.productservice.Product;

/**
 * @author Moe1
 *
 */
@Stateful
@Startup
@Remote(ConsultationRemoteService.class)
public class ConsultationServiceDB implements ConsultationRemoteService {

	@PersistenceContext(unitName = "TPBDD")
	private EntityManager entityManager;
	
	@EJB
	private PrescriptionService prescriptionService;
	
	private static InteractionService interactionService;

	private Consultation consultation;
	
	@PostConstruct
	public void initialization(){
		if(ConsultationServiceDB.interactionService == null)
			ConsultationServiceDB.interactionService = new InteractionService_Service().getInteractionServiceHttpPort();
	}

	@Override
	public Consultation getConsultation() {
		return this.consultation;
	}

	@Override
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		Consultation consultation = null;
		try{
			consultation = new ConsultationDB();
			consultation.setDebut(date);
			Calendar dateFin = (Calendar)date.clone();
			dateFin.add(Calendar.MINUTE, 30);
			consultation.setFin(dateFin);
			this.setConsultation(consultation);
			this.enregistrer();
		}catch(Exception e){
			System.err.println(this + " n'a pas réussi à créer un rendez-vous pour " + date);
			e.printStackTrace();
		}
		return this.getConsultation();
	}

	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		List<Produit> produits = new ArrayList<Produit>();
		for(Product p : prescriptionService.findProduits(keyword)){
			Produit prod = (Produit) new ProduitDB();//Class.forName(appService.getProperty("productClass")).newInstance();
			if(p.getCis() != null)
				prod.setCis(p.getCis());
			else
				prod.setCis(String.valueOf(p.getId()));
			prod.setNom(p.getName());
			produits.add(prod);
		}
		return produits;
	}

	//TODO A implémenter avec le Interaction Service
	@Override
	public void analyserPrescription() throws GestionCabinetException {
		ArrayOfInt ints = new ArrayOfInt();
		try{
			this.getConsultation().getInteractions().clear();
			Set<Produit> prodSet = new HashSet<Produit>();
			for(Traitement traitement : this.getConsultation().getPrescription()){
				prodSet.add(traitement.getProduit());
			}
			for(Traitement traitement : this.getConsultation().getPrescription()){
				Produit produit = traitement.getProduit();
				if(produit instanceof ProduitDB){
					ProduitDB produitDB = (ProduitDB) produit;
					ints.getInt().add(produitDB.getVidalId());
				}
			}
			InteractionResult vidalInteractions = ConsultationServiceDB.interactionService.getInteractionCouplesForProductIds(ints, InteractionSeverityType.CONTRAINDICATIONS);
			for(InteractionCouple vidalInteraction : vidalInteractions.getInteractionCoupleList().getInteractionCouple()){
				Interaction interaction = new InteractionDB();
				Produit produitA = null;
				Produit produitB = null;
				Iterator<Traitement> itTraitement = this.getConsultation().getPrescription().iterator();
				while(itTraitement.hasNext() || (produitA != null && produitB != null)){
					Produit produit = itTraitement.next().getProduit();
					if(produit.getCis().equals(vidalInteraction.getProductA().getCis())){
						produitA = produit;
					}else if(produit.getCis().equals(vidalInteraction.getProductB().getCis())){
						produitB = produit;
					}
				}
				interaction.setProduitA(produitA);
				interaction.setProduitB(produitB);
				interaction.setSeverite(vidalInteraction.getSeverity().value());
				this.getConsultation().getInteractions().add(interaction);
			}
			this.entityManager.persist(this.getConsultation());
		}catch(Exception e){
			System.err.println(this + " n'a pas réussi à analyser " + this.getConsultation());
			e.printStackTrace();
		}
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		try{
			this.entityManager.persist(this.getConsultation());
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(this + " n'a pas réussi à enregistrer " + this.getConsultation());
		}

		return this.getConsultation();
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		try{
			this.entityManager.remove(this.getConsultation());
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(this + " n'a pas réussi à supprimer " + this.getConsultation());
		}


	}

	
	

}
