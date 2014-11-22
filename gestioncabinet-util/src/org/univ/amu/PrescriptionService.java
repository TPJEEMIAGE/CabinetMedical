package org.univ.amu;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import fr.vidal.webservices.interactionservice.InteractionService;
import fr.vidal.webservices.interactionservice.InteractionService_Service;
import fr.vidal.webservices.productservice.ArrayOfInt;
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
				Produit prod = (Produit) Class.forName(appService.getProperty("productClass")).newInstance();
				prod.setCis(p.getCis());
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
		interactionService = new InteractionService_Service().getInteractionServiceHttpPort();
		List<Product> lstProduct = new ArrayList<Product>();
		try{
		
		for (Produit p : produits){
			lstProduct.add(prodService.searchByCis(p.getCis()));			
		}
		List<ArrayOf>
		interactionService.
		
		
		return null;
	}
	
}
