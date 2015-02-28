package org.univ.amu.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

/**
 *  @author regisc
 */
@Entity
@Table(name="t_traitement")
public class TraitementDB implements Traitement {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7108804426854126604L;
	
	@Id
	@Column(name="c_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="c_posologie")
	private String posologie;
	
	@ManyToOne(targetEntity=ProduitDB.class)
	@JoinColumn(name="c_produit")
	private Produit produit;

	@Override
	public Produit getProduit() {
		
		return produit;
	}

	@Override
	public void setProduit(Produit produit) {
		this.produit=produit;
		
	}

	@Override
	public String getPosologie() {
		
		return posologie;
	}

	@Override
	public void setPosologie(String posologie) {
		this.posologie=posologie;
		
	}

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

}
