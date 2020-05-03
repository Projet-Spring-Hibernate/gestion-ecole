package com.intiformation.gestion_ecole.entityForForms;


import java.util.List;

import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Promotion;
/**
 * Objet pour recevoir les information du formulaire d'ajout et de modification d'un etudiant
 * @author Laure
 *
 */
public class EtudiantForm {

	private Etudiant etudiant;
	private Adresse adresse;
	private String []  listeIdPromotions;
	
	private List<Promotion> listePromotionsExistantes;
	
	//private List<String> listeLibellePromoExistantes;
	
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public List<Promotion> getListePromotionsExistantes() {
		return listePromotionsExistantes;
	}
	public void setListePromotionsExistantes(List<Promotion> listePromotionsExistantes) {
		this.listePromotionsExistantes = listePromotionsExistantes;
	}
	public String[] getListeIdPromotions() {
		return listeIdPromotions;
	}
	public void setListeIdPromotions(String[] listeIdPromotions) {
		this.listeIdPromotions = listeIdPromotions;
	}
//	public List<String> getListeLibellePromoExistantes() {
//		return listeLibellePromoExistantes;
//	}
//	public void setListeLibellePromoExistantes(List<String> listeLibellePromoExistantes) {
//		this.listeLibellePromoExistantes = listeLibellePromoExistantes;
//	}
	
	
	
}//end class
