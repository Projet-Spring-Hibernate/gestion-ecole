package com.intiformation.gestion_ecole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Matiere;



@Controller
public class MatiereController {

	@Autowired
	private IMatiereDAO matiereDAO;
	
	@Autowired
	private IEnseignantDAO enseignantDao;

	public void setMatiereDAO(IMatiereDAO matiereDAO) {
		this.matiereDAO = matiereDAO;
	}

	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}
	
	//==================================================================//
	/**
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/matieres/listeAll", method=RequestMethod.GET)
	public String recupListeAllMatiere(ModelMap modele) {
		//1. recup de la liste de tous les etudiants de la bdd
		List<Matiere> listeMatiere = matiereDAO.listMatiere();
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_matiere", listeMatiere);
		
		return "administrateur_listeMatieres";
	}//end recupListeAllEtudiant
	
	
	
	
	
	@RequestMapping(value="/matieres/listeByEnseignant", method=RequestMethod.GET)
	public String recupListeAllMatiereByID(ModelMap modele) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Enseignant enseignant = (Enseignant) enseignantDao.getPersonneByMail(auth.getName());

		
		//1. recup de la liste de tous les etudiants de la bdd
		List<Matiere> listeMatiere = matiereDAO.listematiereEnseignantbyid(enseignant.getIdentifiant());
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_matiere", listeMatiere);
		
		return "enseignant_listeMatieres";
	}//end recupListeAllEtudiant
	
}
