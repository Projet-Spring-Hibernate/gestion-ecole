package com.intiformation.gestion_ecole.domain;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
@DiscriminatorValue("etudiants")
public class Etudiant extends Personne{
	
	/*____________________props____________________*/
	@Column(name="photo")
	private String photo;
	
	@Column(name="dateDeNaissance")
	private String dateDeNaissance;
	
//	@ManyToMany
//	@Cascade(CascadeType.SAVE_UPDATE)
//	@JoinTable(name="etudiant_cours", 
//	joinColumns =@JoinColumn(name="ETUDIANT_ID",referencedColumnName="identifiant"), 
//	inverseJoinColumns =  @JoinColumn(name="COURS_ID", referencedColumnName="ID_COURS"))
//	private List<Cours> listeCours=new ArrayList<>();
	
	@ManyToMany
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinTable(name="etudiant_promotion", 
	joinColumns= @JoinColumn(name="etudiant_id", referencedColumnName="identifiant"), 
	inverseJoinColumns= @JoinColumn(name="promotion_id", referencedColumnName="ID_PROMOTION"))
	private List<Promotion> listePromotion=new ArrayList<>();
	
	@OneToMany(mappedBy="etudiant")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<EtudiantCours> listeEtudiantCours =new ArrayList<>();
	
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

	

//	public List<Cours> getListeCours() {
//		return listeCours;
//	}
//
//
//	public void setListeCours(List<Cours> listeCours) {
//		this.listeCours = listeCours;
//	}
//	


	public List<EtudiantCours> getListeEtudiantCours() {
		return listeEtudiantCours;
	}


	public void setListeEtudiantCours(List<EtudiantCours> listeEtudiantCours) {
		this.listeEtudiantCours = listeEtudiantCours;
	}


	public List<Promotion> getListePromotion() {
		return listePromotion;
	}


	public void setListePromotion(List<Promotion> listePromotion) {
		this.listePromotion = listePromotion;
	}

	// ================== Méthodes ==========================//

	/**
	 * Ajoute une promotion à la liste des promotions de l'étudiant + ajoute l'étudiant à la liste des étudiants de la promotion
	 * @param promotion
	 */
	public void addPromotion(Promotion promotion) {
		this.listePromotion.add(promotion);
		promotion.getListeEtudiant().add(this);
	}
	
//	/**
//	 * Ajoute un cours à la liste des cours de l'étudiant+ ajoute l'étudiant à la liste des etudiants du cours
//	 * @param cours
//	 */
//	public void addCours(Cours cours) {
//		this.listeCours.add(cours);
//		cours.getListeEtudiant().add(this);
//	}
	
	/**
	 * Ajoute un etudiantCours (table de jointure ) à la liste des etudiantCours de l'étudiant + attribut l'étudiant à la propriété etudiant de l'étudiantCours
	 * @param etudiantCours
	 */
	public void addEtudiantCours(EtudiantCours etudiantCours) {
		this.listeEtudiantCours.add(etudiantCours);
		etudiantCours.setEtudiant(this);
	}
	
	
	
	@Override
	public String toString() {
		return "Etudiant [identifiant=" + super.getIdentifiant() + ", motdepasse=" + super.getMotdepasse() + ", nom=" + super.getNom() + ", prenom="
				+ super.getPrenom() + ", email=" + super.getEmail() + ", photo=" + photo + ", dateDeNaissance=" + dateDeNaissance + ", adresse="+super.getAdresse()+"]";
	}

}
