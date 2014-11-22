package org.univ.amu.entites;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import miage.gestioncabinet.api.Personne;

/**
 * Implementation de personne
 * @author cregis
 *
 */
@Entity
@Table(name="t_personne")
@DiscriminatorColumn(name="c_discriminator",discriminatorType=DiscriminatorType.STRING)
public abstract class PersonneDB implements Personne {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3658658016809680990L;
	
	@Column(name="c_id")
	@Id
	private Long id;
	
	@Column(name="c_nom")
	private String nom;
	
	@Column(name="c_prenom")
	private String prenom;
	
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
	public String getNom() {
		return nom;
	}

	@Override
	public void setNom(String nom) {		
		this.nom =nom;				
	}

	@Override
	public String getPrenom() {
		return prenom;
	}

	@Override
	public void setPrenom(String prenom) {
		this.prenom=prenom;				
	}
	
	

}
