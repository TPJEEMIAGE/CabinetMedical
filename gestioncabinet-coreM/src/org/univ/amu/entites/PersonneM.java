package org.univ.amu.entites;

import miage.gestioncabinet.api.Personne;

/**
 * Implementation de personne
 * @author cregis
 *
 */
public abstract class PersonneM implements Personne {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3658658016809680990L;
	
	private Long id;
	private String nom;
	private String prenom;
	
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
	public String getNom() {
		return nom;
	}

	@Override
	public void setNom(String nom) {		
		this.nom =nom;				
	}

	@Override
	public String getPrenom() {
		return prenom;
	}

	@Override
	public void setPrenom(String prenom) {
		this.prenom=prenom;				
	}
	
	

}
