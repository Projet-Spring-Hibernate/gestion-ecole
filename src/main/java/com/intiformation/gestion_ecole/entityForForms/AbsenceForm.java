package com.intiformation.gestion_ecole.entityForForms;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.EtudiantCours;

/**
 * Objet pour recevoir les informations du formulaire d'ajout des absences d'un cours
 * @author IN-MP-018
 *
 */
public class AbsenceForm {

	private List<Etudiant> listeEtudiants;
	
	private List<EtudiantCours> listeEtudiantCours;

	public List<Etudiant> getListeEtudiants() {
		return listeEtudiants;
	}

	public void setListeEtudiants(List<Etudiant> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

	public List<EtudiantCours> getListeEtudiantCours() {
		return listeEtudiantCours;
	}

	public void setListeEtudiantCours(List<EtudiantCours> listeEtudiantCours) {
		this.listeEtudiantCours = listeEtudiantCours;
	}
	
	
	
	
	
}//end class
