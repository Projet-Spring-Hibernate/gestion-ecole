package com.intiformation.gestion_ecole.entityForForms;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Promotion;

public class PromotionForm {

	private Promotion promotion;
	private List<Promotion> listePromotionsExistantes;
	private String []  listeIdEtudiants;
	private List<Etudiant> listeAllEtudiants;
	private List<Etudiant> listeEtudiantsAvantModif;
	
	public PromotionForm() {
		super();
	}


	public Promotion getPromotion() {
		return promotion;
	}


	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}


	public List<Promotion> getListePromotionsExistantes() {
		return listePromotionsExistantes;
	}


	public void setListePromotionsExistantes(List<Promotion> listePromotionsExistantes) {
		this.listePromotionsExistantes = listePromotionsExistantes;
	}


	public String[] getListeIdEtudiants() {
		return listeIdEtudiants;
	}


	public void setListeIdEtudiants(String[] listeIdEtudiants) {
		this.listeIdEtudiants = listeIdEtudiants;
	}


	public List<Etudiant> getListeAllEtudiants() {
		return listeAllEtudiants;
	}


	public void setListeAllEtudiants(List<Etudiant> listeAllEtudiants) {
		this.listeAllEtudiants = listeAllEtudiants;
	}


	public List<Etudiant> getListeEtudiantsAvantModif() {
		return listeEtudiantsAvantModif;
	}


	public void setListeEtudiantsAvantModif(List<Etudiant> listeEtudiantsAvantModif) {
		this.listeEtudiantsAvantModif = listeEtudiantsAvantModif;
	}

	
	
	
}//end class
