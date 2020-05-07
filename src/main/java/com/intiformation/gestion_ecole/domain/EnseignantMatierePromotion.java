package com.intiformation.gestion_ecole.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Table de jointure triple entre Enseignant, promotion et matière
 * @author IN-MP-018
 *
 */

@Entity(name="enseignantMatierePromotion")
@Table(name="enseignant_matiere_promotion")
public class EnseignantMatierePromotion {

	/*_______________prop_______________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="MATIERE_ID", referencedColumnName="ID_MATIERE")
	//@JsonManagedReference
	@JsonIgnore
	private Matiere matiere;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="ENSEIGNANT_ID", referencedColumnName="identifiant")
	//@JsonManagedReference
	@JsonIgnore
	private Enseignant enseignant;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="PROMOTION_ID", referencedColumnName="ID_PROMOTION")
	//@JsonManagedReference
	@JsonIgnore
	private Promotion promotion;
	
	/*_______________Constructeurs_______________*/
	
	public EnseignantMatierePromotion() {

	}
	

	/*_______________getter Setter_______________*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}


	//_______________________Méthodes ___________________//
	
	
	public void addPromotion(Promotion promotion) {
		this.promotion=promotion;
		promotion.getListeEnseignantMatierePromotion().add(this);
	}
	
	
	public void addMatiere(Matiere matiere) {
		this.matiere=matiere;
		matiere.getListeEnseignantMatierePromotion().add(this);
	}
	
	public void addEnseignant(Enseignant enseignant) {
		this.enseignant=enseignant;
		enseignant.getListeEnseignantMatierePromotion().add(this);
	}
	
	@Override
	public String toString() {
		return "EnseignantMatierePromotion [id=" + id + ", matiere=" + matiere.getIdMatiere() + ", enseignant=" + enseignant.getIdentifiant()
				+ ", promotion=" + promotion.getIdPromotion() + "]";
	}


	
	
	
}//end class
