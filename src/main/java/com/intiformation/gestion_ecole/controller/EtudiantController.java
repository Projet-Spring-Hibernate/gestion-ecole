package com.intiformation.gestion_ecole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.domain.Etudiant;



/**
 * Controller pour la gestion des etudiants
 * @author IN-MP-018
 *
 */


@Controller
public class EtudiantController {

	@Autowired
	private IEtudiantDAO etudiantDao;

	/**
	 * Setter pour injection spring
	 * @param etudiantDao
	 */
	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}
	
	//==================================================================//
	
	@RequestMapping(value="/etudiants/listeAll", method=RequestMethod.GET)
	public String recupListeAllEtudiant(ModelMap modele) {
		//1. recup de la liste de tous les etudiants de la bdd
		List<Etudiant> listeEtudiants = etudiantDao.getAllEtudiant();
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_etudiants", listeEtudiants);
		
		return "administrateur_listeEtudiants";
	}
	
}//end class
