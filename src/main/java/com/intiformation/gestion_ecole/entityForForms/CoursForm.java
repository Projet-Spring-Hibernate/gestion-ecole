package com.intiformation.gestion_ecole.entityForForms;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;

public class CoursForm {
	
	private Cours cours;
	private List<Promotion> listePromotionsExistantes;
	private List<Matiere> listeMatieresExistantes;
	
	private Matiere matiere;
	private Promotion promotion;
	
	
	public CoursForm() {
		super();
	}
	
	


	public List<Promotion> getListePromotionsExistantes() {
		return listePromotionsExistantes;
	}


	public void setListePromotionsExistantes(List<Promotion> listePromotionsExistantes) {
		this.listePromotionsExistantes = listePromotionsExistantes;
	}


	public List<Matiere> getListeMatieresExistantes() {
		return listeMatieresExistantes;
	}


	public void setListeMatieresExistantes(List<Matiere> listeMatieresExistantes) {
		this.listeMatieresExistantes = listeMatieresExistantes;
	}


	public Cours getCours() {
		return cours;
	}


	public void setCours(Cours cours) {
		this.cours = cours;
	}




	public Matiere getMatiere() {
		return matiere;
	}




	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}




	public Promotion getPromotion() {
		return promotion;
	}




	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
