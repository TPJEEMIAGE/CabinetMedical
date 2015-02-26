package org.univ.amu.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;

/**
 *  @author regisc
 */

@Entity
@Table(name="t_interaction")
public class InteractionDB implements Interaction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4669949486470135740L;
	
	@Id
	@Column(name="c_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="c_severite")
	private String severite;
	
	@Column(name="c_risques")
	private String risques;
	
	@Column(name="c_precautions")
	private String precautions;
	
	@ManyToOne(targetEntity=ProduitDB.class)
	@JoinColumn(name="c_produit_a")
	private Produit produitA;
	
	@ManyToOne(targetEntity=ProduitDB.class)
	@JoinColumn(name="c_produit_b")
	private Produit produitB;
	

	@Override
	public Produit getProduitA() {
		
		return produitA;
	}

	@Override
	public void setProduitA(Produit produit) {
		this.produitA=produit;
		
	}

	@Override
	public Produit getProduitB() {
		
		return produitB;
	}

	@Override
	public void setProduitB(Produit produit) {
		this.produitB=produit;
		
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

	@Override
	public String getSeverite() {
		
		return severite;
	}

	@Override
	public void setSeverite(String severite) {
		this.severite=severite;
		
	}

	@Override
	public String getRisques() {
		
		return risques;
	}

	@Override
	public void setRisques(String risques) {
		this.risques=risques;
		
	}

	@Override
	public String getPrecautions() {
		
		return precautions;
	}

	@Override
	public void setPrecautions(String precautions) {
		this.precautions=precautions;
		
	}

}
