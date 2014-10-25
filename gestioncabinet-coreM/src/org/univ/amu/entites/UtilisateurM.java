package org.univ.amu.entites;

import miage.gestioncabinet.api.Utilisateur;

public class UtilisateurM extends PersonneM implements Utilisateur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 208315332371199747L;
	
	private String compte;
	private String motdepasse;
	
	@Override
	public String getNom() {
		
		return super.getNom();
	}

	@Override
	public void setNom(String nom) {
		super.setNom(nom);
		
	}

	@Override
	public String getPrenom() {
		
		return super.getPrenom();
	}

	@Override
	public void setPrenom(String prenom) {
		super.setPrenom(prenom);
		
	}

	@Override
	public String getCompte() {
		
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	
}
