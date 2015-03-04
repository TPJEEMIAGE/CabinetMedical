package org.univ.amu.entites;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
	private Integer vidalId;

	public Integer getVidalId() {
		return vidalId;
	}

	public void setVidalId(Integer vidalId) {
		this.vidalId = vidalId;
	}

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
