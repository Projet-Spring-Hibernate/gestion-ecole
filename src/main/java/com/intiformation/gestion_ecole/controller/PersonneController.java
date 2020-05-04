package com.intiformation.gestion_ecole.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.gestion_ecole.dao.IAdministrateurDao;
import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IEnseignantMatierePromotionDao;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IPersonneDao;
import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Personne;

@Controller
public class PersonneController {

	// ========== DAO =========================//


	@Autowired
	private IEnseignantDAO enseignantDao;
	
	@Autowired
	private IEtudiantDAO etudiantDao;
	
	@Autowired
	private IAdministrateurDao adminDAO;
	// ============ SETTER ====================//

	
	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}

	public void setAdminDAO(IAdministrateurDao adminDAO) {
		this.adminDAO = adminDAO;
	}

	// ===========================================================//
	// =========== Liste personnes by nom ========================//
	// ===========================================================//
	/**
	 * Permet de recuperer la liste de toutes les personnes ayant le même nom.
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/personnes/chercher-par-nom", method=RequestMethod.GET)
	public String recupListeAllEtudiant(@RequestParam("pNom") String pNom, ModelMap modele) {
		
		//================ ETUDIANTS ===============
		
		//1. recup de la liste de toutes les etudiants de la bdd
		List<Etudiant> listeAllEtudiants = etudiantDao.getAllEtudiant();
		
		//2. Init d'une liste vide 
		List<Etudiant> listeEtudiantRecherche = new ArrayList<>();
		
		for(Etudiant etudiant:listeAllEtudiants) {
			if(etudiant.getNom().toLowerCase().contains(pNom.toLowerCase()) || etudiant.getPrenom().toLowerCase().contains(pNom.toLowerCase()) ) {
				listeEtudiantRecherche.add(etudiant);
				
			}//End if
		}//end for
		
		//3. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_etudiants", listeEtudiantRecherche);
		
		//================ ENSEIGNANTS ===============
		
		//1. recup de la liste de tous les enseignants de la bdd
		List<Enseignant> listeAllEnseignants = enseignantDao.listEnseignant();
		
		//2. Init d'une liste vide 
		List<Enseignant> listeEnseignantsRecherche = new ArrayList<>();
		
		for(Enseignant enseignant:listeAllEnseignants) {
			if(enseignant.getNom().toLowerCase().contains(pNom.toLowerCase()) || enseignant.getPrenom().toLowerCase().contains(pNom.toLowerCase()) ) {
				listeEnseignantsRecherche.add(enseignant);
				
			}//End if
		}//end for
		
		//3. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_enseignants", listeEnseignantsRecherche);
		
		
		//================ ADMIN ===============
		
		//1. recup de la liste de tous les admins de la bdd
		List<Administrateur> listeAllAdmins= adminDAO.getAllAdministrateur();
		
		//2. Init d'une liste vide 
		List<Administrateur> listeAdministrateursRecherche = new ArrayList<>();
		
		for(Administrateur admin:listeAllAdmins) {
			if(admin.getNom().toLowerCase().contains(pNom.toLowerCase()) || admin.getPrenom().toLowerCase().contains(pNom.toLowerCase()) ) {
				listeAdministrateursRecherche.add(admin);
				
			}//End if
		}//end for
		
		//3. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_admins", listeAdministrateursRecherche);
		
		
		return "index";
	}//end recupListeAllEtudiant
	
	
	
}//end class
