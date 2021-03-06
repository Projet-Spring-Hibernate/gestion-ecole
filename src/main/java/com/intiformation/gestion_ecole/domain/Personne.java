package com.intiformation.gestion_ecole.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Classe Entité de Personne. Classe mère de Administrateur, Enseignant et Etudiant
 * Relation OneToOne avec Adresse
 * @author Marie
 *
 */
@Entity(name="personne")
@Table(name="personnes")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role", discriminatorType=DiscriminatorType.STRING)
public class Personne implements Serializable {

	/*_______________Propriétés_______________*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="identifiant")
	private Long identifiant;
	
	@Column(name="password")
	private String motdepasse;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="username")
	private String email;
	
	// Association avec Adresse
	@OneToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "adresse_id", referencedColumnName = "adresse_id")
	@JsonIgnore
	private Adresse adresse;
	
	
	/*_______________Constructeurs_______________*/

	/**
	 * Ctor vide.
	 */
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

	
	/*_______________getter Setter_______________*/

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


	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	//_______________________Méthodes ___________________//
	
	/**
	 * Attribut une adresse à la propriété adresse de la personne + attribut la personne à l'attribut personne de l'adresse
	 * @param adresse
	 */
	public void addAdresse(Adresse adresse) {
		this.adresse=adresse;
		adresse.setPersonne(this);
	}
	
	@Override
	public String toString() {
		return "Personne [identifiant=" + identifiant + ", motdepasse=" + motdepasse + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", adresse="+adresse+"]";
	}
}//end class
