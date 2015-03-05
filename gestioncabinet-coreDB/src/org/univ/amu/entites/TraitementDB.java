package org.univ.amu.entites;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
	@SequenceGenerator(name="idTraitementGenerator",sequenceName="t_traitement_c_id_seq",initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="idTraitementGenerator")
	private Long id;
	
	@Column(name="c_posologie")
	private String posologie;
	
	@Embedded
	private ProduitDB produit;

	@Override
	public Produit getProduit() {
		
		return produit;
	}

	@Override
	public void setProduit(Produit produit) {
		this.produit=(ProduitDB) produit;
		
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
