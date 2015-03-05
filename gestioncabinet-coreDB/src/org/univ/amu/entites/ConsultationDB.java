package org.univ.amu.entites;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Entity
@Table(name="t_consultation")
@NamedQuery(name=ConsultationDB.RECHERCHER_CONSULTATIONS,query="select c From ConsultationDB c where c.dateRdv > ?1 and c.dateRdv < ?2")
public class ConsultationDB implements Consultation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -278148284796775331L;
	
	public static final String RECHERCHER_CONSULTATIONS = "rechConsult";
	
	@ManyToOne(targetEntity=PatientDB.class,cascade={CascadeType.MERGE})
	@JoinColumn(name="c_patient")
	private Patient patient;
	
	@ManyToOne(targetEntity=MedecinDB.class,cascade={CascadeType.MERGE})
	@JoinColumn(name="c_medecin")
	private Medecin medecin;
	
	@Id
	@Column(name="c_id")
	@SequenceGenerator(name="idConsultationGenerator",sequenceName="t_consultation_c_id_seq",initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="idConsultationGenerator")
	private Long id;
	
	@Column(name="c_daterdv")
	@Temporal(TemporalType.DATE)
	private Calendar dateRdv;

	@Column(name="c_hdebut")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar heureDebut;
	
	@Column(name="c_hfin")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar heurefin;
	
	@Column(name="c_compterendu")
	private String compteRendu;
	
	@OneToMany(targetEntity=TraitementDB.class,cascade={CascadeType.ALL})
	@JoinColumn(name="c_consultation")
	private List<Traitement> traitement;
	
	@OneToMany(targetEntity=InteractionDB.class,cascade={CascadeType.ALL})
	@JoinColumn(name="c_consultation")
	private List<Interaction> interaction;
	

	@Override
	public int compareTo(Consultation arg0) {
		int compare = 0;
		if(this.getDebut() != null){
			compare = this.getDebut().compareTo(arg0.getDebut());
			if(compare == 0){
				if(this.getFin() != null){
					compare = this.getFin().compareTo(arg0.getFin());
				}
			}
		}
		return compare;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateRdv == null) ? 0 : dateRdv.hashCode());
		result = prime * result + ((medecin == null) ? 0 : medecin.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [patient=" + patient + ", medecin=" + medecin
				+ "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultationDB other = (ConsultationDB) obj;
		if (dateRdv == null) {
			if (other.dateRdv != null)
				return false;
		} else if (!dateRdv.equals(other.dateRdv))
			return false;
		if (medecin == null) {
			if (other.medecin != null)
				return false;
		} else if (!medecin.equals(other.medecin))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}

	@Override
	public Patient getPatient() {
		if(this.patient == null)
			patient = new PatientDB();
		return patient;
	}

	@Override
	public void setPatient(Patient patient) {
		
		this.patient = patient;
	}

	@Override
	public Medecin getMedecin() {
		
		return medecin;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		
		this.medecin = medecin;
	}

	@Override
	public Calendar getDebut() {
		
		return heureDebut;
	}

	@Override
	public void setDebut(Calendar date) {
		
		this.heureDebut = date;
	}

	@Override
	public Calendar getFin() {
		
		return heurefin;
	}

	@Override
	public void setFin(Calendar date) {
		this.heurefin=date;		
	}

	@Override
	public String getCompteRendu() {
		
		return compteRendu;
	}

	@Override
	public void setCompteRendu(String texte) {
		this.compteRendu = texte;
		
	}

	@Override
	public List<Traitement> getPrescription() {
		if(this.traitement == null)
			this.traitement = new ArrayList<Traitement>();
		return traitement;
	}

	@Override
	public Boolean ajouterTraitement(Produit produit) {
		
		try{
			Traitement traitement2 = new TraitementDB();
			traitement2.setProduit(produit);			
			this.getPrescription().add(traitement2);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean supprimerTraitement(Traitement medicament) {
		
		
		return traitement.remove(medicament);
	}

	@Override
	public List<Interaction> getInteractions() {
		if(this.interaction == null)
			this.interaction = new ArrayList<Interaction>();
		return interaction;
	}

	@Override
	public void setInteractions(List<Interaction> interactions) {
		
		this.interaction = interactions ;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
