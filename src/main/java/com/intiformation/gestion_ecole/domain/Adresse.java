package com.intiformation.gestion_ecole.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe entity pour les adresses. Reliée à la classe Personne par une relation OneToONe
 * 
 * @author Marie
 *
 */
@Entity
@Table(name = "adresses")
public class Adresse {

	/* _________________Propriétés_______________ */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="adresse_id")
	private Long adresse_id;
	
	@Column(name="rue")
	private String rue;

	@Column(name="codePostal")
	private String codePostal;

	@Column(name="ville")
	private String ville;
	
	// Association avec Personne
	@OneToOne(mappedBy="adresse")
	private Personne personne;


	/* _______________Constructeurs_______________ */
	/**
	 * Ctor vide.
	 */
	public Adresse() {
		super();
	}
	
	public Adresse(Long adresse_id, String rue, String codePostal, String ville, Personne personne) {
		super();
		this.adresse_id = adresse_id;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.personne = personne;
	}

	public Adresse(String rue, String codePostal, String ville, Personne personne) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.personne = personne;
	}


	/* _______________getter Setter_______________ */

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}


	public Long getAdresse_id() {
		return adresse_id;
	}

	public void setAdresse_id(Long adresse_id) {
		this.adresse_id = adresse_id;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	@Override
	public String toString() {
		return "Adresse [personne_id=" + adresse_id + ", rue=" + rue + ", codePostal=" + codePostal + ", ville="
				+ ville + ", personne=" + personne + "]";
	}


	
	
	
}// end classe
