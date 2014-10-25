package org.univ.amu.entites;

import miage.gestioncabinet.api.Medecin;

public class MedecinM extends UtilisateurM implements Medecin{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7221137899277955615L;
	private String RPPS;
	
	@Override
	public String getRPPS() {
		
		return RPPS;
	}
	
	

}
