package org.univ.amu.entites;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import miage.gestioncabinet.api.Medecin;

@Entity
@DiscriminatorValue("doctor")
@NamedQueries({
	@NamedQuery(name=MedecinDB.RECHERCHER_MEDECIN, query="select m from MedecinDB m")
})
public class MedecinDB extends UtilisateurDB implements Medecin{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7221137899277955615L;
	public static final String RECHERCHER_MEDECIN = "rechercher_medecin";
	@Column(name="c_rpps")
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
		if(obj instanceof MedecinDB){
			if(((MedecinDB)obj).getNom().equals(this.getNom()) && ((MedecinDB)obj).getPrenom().equals(this.getPrenom())){
				return true;
			}
			return false;
		}
		return false;
	}
	

}
