package com.intiformation.gestion_ecole.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name="etudiant")
@Table(name="etudiants")
@DiscriminatorValue("etudiant")
public class Etudiant extends Personne{
	
	/*____________________props____________________*/
	@Column
	private String photo;
	@Column
	private String dateDeNaissance;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="etudiant_cours", joinColumns = @JoinColumn(name="Etudiant"), inverseJoinColumns = @JoinColumn(name="Cours"))
	private List<Cours> listeCours;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="etudiant_promotion", joinColumns= @JoinColumn(name="etudiant_id"), inverseJoinColumns= @JoinColumn(name="promotion_id"))
	private List<Promotion> listePromotion;
	
	
	
	/*____________________ctors____________________*/

	
	
	public Etudiant(String photo, String dateDeNaissance) {
		super();
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance;
	}


	public Etudiant(Long identifiant, String motdepasse, String nom, String prenom, String email) {
		super(identifiant, motdepasse, nom, prenom, email);
		// TODO Auto-generated constructor stub
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
		return "Etudiant [photo=" + photo + ", dateDeNaissance=" + dateDeNaissance + "]";
	}
	
	

}
