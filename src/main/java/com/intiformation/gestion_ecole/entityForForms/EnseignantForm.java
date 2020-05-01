package com.intiformation.gestion_ecole.entityForForms;
/**
 * Objet pour recevoir les information du formulaire d'ajout et de modification d'un enseignant
 * @author IN-MP-018
 *
 */

import java.util.List;

import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;

public class EnseignantForm {

	private Enseignant enseignant;
	private Adresse adresse;
	private List<EnseignantMatierePromotion> listeEnseignantMatierePromotion;
	private List<Promotion> listePromotionsExistantes;
	private List<Matiere> listeMatieresExistantes;
	
	
	
	public EnseignantForm() {
		super();
	}
	
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotion() {
		return listeEnseignantMatierePromotion;
	}
	public void setListeEnseignantMatierePromotion(List<EnseignantMatierePromotion> listeEnseignantMatierePromotion) {
		this.listeEnseignantMatierePromotion = listeEnseignantMatierePromotion;
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
	
	
	
}//end class
