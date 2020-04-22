package com.intiformation.gestion_ecole.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personne implements Serializable {

	/*_______________prop_______________*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long identifiant;
	private String motdepasse;
	private String nom;
	private String prenom;
	private String email;
	
	
	/*_______________ctor_______________*/

	public Personne() {
	}

	public Personne(Long identifiant, String motdepasse, String nom, String prenom, String email) {
		super();
		this.identifiant = identifiant;
		this.motdepasse = motdepasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Personne(String motdepasse, String nom, String prenom, String email) {
		super();
		this.motdepasse = motdepasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	
	/*_______________get/set_______________*/

	public Long getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(Long identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Personne [identifiant=" + identifiant + ", motdepasse=" + motdepasse + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + "]";
	}
	
	
	
	
	
	
	
	
	
}//end class
