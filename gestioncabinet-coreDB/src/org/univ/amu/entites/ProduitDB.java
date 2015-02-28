package org.univ.amu.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import miage.gestioncabinet.api.Produit;

/**
 *  @author regisc
 */

@Entity
@Table(name="t_produit")
@NamedQueries({
	@NamedQuery(name=ProduitDB.RECHERCHER_MEDICAMENT, query="select p from ProduitDB p where p.nom = :keyword")
})
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
