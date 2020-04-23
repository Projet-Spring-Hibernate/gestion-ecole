package com.intiformation.gestion_ecole.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Classe entity pour les administrateurs. Classe fille de la classe Personne.
 * Relation ManyToMany avec Cours
 * Relation ManyToMany avec Promotion ===> A VERIFIER 
 * @author Lise
 *
 */
@Entity(name="etudiant")
@DiscriminatorValue("etudiant")
public class Etudiant extends Personne{
	
	/*____________________props____________________*/
	@Column(name="photo")
	private String photo;
	
	@Column(name="dateDeNaissance")
	private String dateDeNaissance;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="etudiant_cours", joinColumns = @JoinColumn(name="Etudiant"), inverseJoinColumns = @JoinColumn(name="Cours"))
	private List<Cours> listeCours;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="etudiant_promotion", joinColumns= @JoinColumn(name="etudiant_id"), inverseJoinColumns= @JoinColumn(name="promotion_id"))
	private List<Promotion> listePromotion;
	
	
	
	/*____________________ctors____________________*/

	
	
	public Etudiant(String photo, String dateDeNaissance) {
		super();
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance;
	}


	public Etudiant(Long identifiant, String motdepasse, String nom, String prenom, String email,String photo, String dateDeNaissance) {
		super(identifiant, motdepasse, nom, prenom, email);
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance;
		// TODO Auto-generated constructor stub
	}
	
	
	
	





	public Etudiant(String motdepasse, String nom, String prenom, String email, String photo, String dateDeNaissance) {
		super(motdepasse, nom, prenom, email);
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance;
	}


	public Etudiant() {
		super();
	}


	
	
	/*________________getters/setter____________________*/
	
	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getDateDeNaissance() {
		return dateDeNaissance;
	}


	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	

	public List<Cours> getListeCours() {
		return listeCours;
	}


	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}
	
	
	


	public List<Promotion> getListePromotion() {
		return listePromotion;
	}


	public void setListePromotion(List<Promotion> listePromotion) {
		this.listePromotion = listePromotion;
	}


	
	@Override
	public String toString() {
		return "Etudiant [identifiant=" + super.getIdentifiant() + ", motdepasse=" + super.getMotdepasse() + ", nom=" + super.getNom() + ", prenom="
				+ super.getPrenom() + ", email=" + super.getEmail() + ", photo=" + photo + ", dateDeNaissance=" + dateDeNaissance + ", adresse="+super.getAdresse()+"]";
	}

}
