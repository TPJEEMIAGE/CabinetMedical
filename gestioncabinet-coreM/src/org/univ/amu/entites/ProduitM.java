package org.univ.amu.entites;

import miage.gestioncabinet.api.Produit;

/**
 *  @author regisc
 */

public class ProduitM implements Produit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9220204524535368403L;
	private String cis;
	private String nom;

	@Override
	public String getCis() {		
		return cis;
	}

	@Override
	public void setCis(String cis) {
		this.cis=cis;
		
	}

	@Override
	public String getNom() {		
		return nom;	}

	@Override
	public void setNom(String nom) {
		this.nom=nom;
		
	}

}
