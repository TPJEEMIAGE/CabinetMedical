package org.univ.amu.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import miage.gestioncabinet.api.Produit;

/**
 *  @author regisc
 */

@Entity
@Table(name="t_produit")
public class ProduitDB implements Produit {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 9220204524535368403L;
	
	public static final String RECHERCHER_MEDICAMENT = "rechercher_medicament";
	
	@Id
	@Column(name="c_cis")
	private String cis;
	
	@Column(name="c_nom")
	private String nom;
	
	@Column(name="c_vidal_id")
	private Integer vidal_id;

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

	/**
	 * @return the vidal_id
	 */
	public Integer getVidal_id() {
		return vidal_id;
	}

	/**
	 * @param vidal_id the vidal_id to set
	 */
	public void setVidal_id(Integer vidal_id) {
		this.vidal_id = vidal_id;
	} 

}
