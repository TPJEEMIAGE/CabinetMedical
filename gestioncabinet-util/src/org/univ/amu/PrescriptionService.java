package org.univ.amu;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.univ.amu.entites.InteractionDB;
import org.univ.amu.entites.ProduitDB;

import fr.vidal.webservices.interactionservice.ArrayOfProduct;
import fr.vidal.webservices.interactionservice.InteractionCouple;
import fr.vidal.webservices.interactionservice.InteractionResult;
import fr.vidal.webservices.interactionservice.InteractionService;
import fr.vidal.webservices.interactionservice.InteractionService_Service;
import fr.vidal.webservices.interactionservice.ArrayOfInt;
import fr.vidal.webservices.interactionservice.InteractionSeverityType;
import fr.vidal.webservices.productservice.Product;
import fr.vidal.webservices.productservice.ProductService;
import fr.vidal.webservices.productservice.ProductService_Service;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;

@Stateless
@LocalBean
public class PrescriptionService {

	@EJB
	private ApplicationService appService;
	
	private ProductService prodService;
	
	private InteractionService interactionService;
	
	public List<Produit> findProduits(String keyword){
		prodService = new ProductService_Service().getProductServiceHttpPort();
		List<Produit> lstRetour = new ArrayList<Produit>();
		try{
			List<Product> lstProduct = prodService.directSearchByName(keyword).getProduct();
			for(Product p : lstProduct){
				Produit prod = (Produit) new ProduitDB();//Class.forName(appService.getProperty("productClass")).newInstance();
				if(p.getCis() != null)
					prod.setCis(p.getCis());
				else
					prod.setCis(String.valueOf(p.getId()));
				prod.setNom(p.getName());
				lstRetour.add(prod);
			}
			return lstRetour;
		}
		catch(Exception e){
			appService.getLogger().error("Erreur lors de la recherche de produit avec le mot clé : "+keyword,e);
			return new ArrayList<Produit>();
		}
	}
	
	public List<Interaction> findInteractions(List<Produit> produits){
		interactionService = new InteractionService_Service().getInteractionServiceHttpPort(); // On recupère le service web interaction vidal
		
		List<Product> lstProduct = new ArrayList<Product>();

		/* On doit retransformer notre liste de produit en product pour pouvoir les donner au service vidal*/
		
		try{
		
			/* Pour chaque produit, on récupère son equivalent product par une recherche par cis */
			for (Produit p : produits){
				lstProduct.add(prodService.searchByCis(p.getCis()));			
			}
			
			//On prépare un arrayOfINt qui contiendra les ID des product
			//On en aura besoin pour la recherche d'interaction
			ArrayOfInt intArray = new ArrayOfInt();
			
			//On le remplit des IDs des product
			for(Product p : lstProduct){
				intArray.getInt().add(p.getId());
			}
			
			//On prépare une liste pour stocker les résultats de nos requetes pour les interactions
			List<InteractionResult> intResult = new ArrayList<InteractionResult>();
			
			//La méthode getInteractionCouplesForProductIds prend en paramètre un ArrayOfInt que l'on a, et un InteractionseverityType
			//La méthode ne spécifie pas quel type utiliser donc on boucle sur les valeurs de l'enumeration InteractionseverityType
			//Pour toutes les récuperer
			for(InteractionSeverityType type : InteractionSeverityType.values())
				intResult.add(interactionService.getInteractionCouplesForProductIds(intArray,type));
			
			//On prepare la liste d'interaction que l'on retourne
			List<Interaction> lstReturn = new ArrayList<Interaction>();
			
			//Pour tous les resultats de la méthode getInteractionCouplesForProductIds
			for(InteractionResult ir : intResult){
				//Et pour chaque couple d'intéractions trouvé entre 2 produits de la liste fournie
				for(InteractionCouple ic : ir.getInteractionCoupleList().getInteractionCouple())
				{
					//On instancie nos objets interactions et produits (Ceux qui seront stockées dans l'interaction)
					Interaction inter = (Interaction) new InteractionDB();//Class.forName(appService.getProperty("interactionClass")).newInstance();
					Produit prodA = (Produit) new ProduitDB();//Class.forName(appService.getProperty("productClass")).newInstance();
					Produit prodB = (Produit) new ProduitDB();//Class.forName(appService.getProperty("productClass")).newInstance();
					
					//On renseigne les produits avec les données des product dans l'interactionCouple
					prodA.setCis(ic.getProductA().getCis());
					prodB.setCis(ic.getProductB().getCis());
					prodA.setNom(ic.getProductA().getName());
					prodB.setNom(ic.getProductB().getName());
					
					//On renseigne l'interaction avec les données de l'interactionCouple
					inter.setPrecautions(ic.getPrecautionComment());
					inter.setProduitA(prodA);
					inter.setProduitB(prodB);
					inter.setRisques(ic.getRiskComment());
					inter.setSeverite(ic.getSeverity().value());
					
					//On l'ajoute a la liste de retour
					lstReturn.add(inter);
				}
			}
			
			return lstReturn;
		}
		catch(Exception e){
			//Si il y a un problème, on logge l'erreur et on renvoie un liste vide
			this.appService.getLogger().error("Une erreur est survenue lors de la recherche d'interactions pour la liste de produits : "+produits,e);
			return new ArrayList<Interaction>();
		}
		
		
		
		
	
	}
}

