package org.univ.amu.entites;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;

/**
 *  @author regisc
 */

public class InteractionM implements Interaction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4669949486470135740L;
	private Long id;
	private String severite;
	private String risques;
	private String precautions;
	private Produit produitA;
	private Produit produitB;
	

	@Override
	public Produit getProduitA() {
		
		return produitA;
	}

	@Override
	public void setProduitA(Produit produit) {
		this.produitA=produit;
		
	}

	@Override
	public Produit getProduitB() {
		
		return produitB;
	}

	@Override
	public void setProduitB(Produit produit) {
		this.produitB=produit;
		
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getSeverite() {
		
		return severite;
	}

	@Override
	public void setSeverite(String severite) {
		this.severite=severite;
		
	}

	@Override
	public String getRisques() {
		
		return risques;
	}

	@Override
	public void setRisques(String risques) {
		this.risques=risques;
		
	}

	@Override
	public String getPrecautions() {
		
		return precautions;
	}

	@Override
	public void setPrecautions(String precautions) {
		this.precautions=precautions;
		
	}

}
