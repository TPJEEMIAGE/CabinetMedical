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

	@Override
	public String toString() {
		return "MedecinM [getNom()=" + getNom() + ", getPrenom()="
				+ getPrenom() + "]";
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MedecinM){
			if(((MedecinM)obj).getNom().equals(this.getNom()) && ((MedecinM)obj).getPrenom().equals(this.getPrenom())){
				return true;
			}
			return false;
		}
		return false;
	}
	

}
