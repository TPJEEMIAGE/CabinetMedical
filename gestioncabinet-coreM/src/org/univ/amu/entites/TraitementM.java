package org.univ.amu.entites;

import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

/**
 *  @author regisc
 */

public class TraitementM implements Traitement {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7108804426854126604L;
	private Long id;
	private String posologie;
	private Produit produit;

	@Override
	public Produit getProduit() {
		
		return produit;
	}

	@Override
	public void setProduit(Produit produit) {
		this.produit=produit;
		
	}

	@Override
	public String getPosologie() {
		
		return posologie;
	}

	@Override
	public void setPosologie(String posologie) {
		this.posologie=posologie;
		
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

}
