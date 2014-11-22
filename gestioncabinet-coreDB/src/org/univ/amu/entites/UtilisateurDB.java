package org.univ.amu.entites;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import miage.gestioncabinet.api.Utilisateur;

@Entity
@DiscriminatorValue("simpleUser")
public class UtilisateurDB extends PersonneDB implements Utilisateur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 208315332371199747L;
	
	@Column(name="c_compte")
	private String compte;
	
	@Column(name="c_mdp")
	private String motdepasse;
	
	

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
