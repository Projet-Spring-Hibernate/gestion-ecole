package com.intiformation.gestion_ecole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestion_ecole.dao.IAdministrateurDao;
import com.intiformation.gestion_ecole.dao.IEtudiantCoursDAO;
import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.domain.EtudiantCours;
import com.intiformation.gestion_ecole.domain.Matiere;


@Controller
public class AbsenceController {

	@Autowired
	private IEtudiantCoursDAO etudiantcoursDAO;

	
	
	
	public void setEtudiantcoursDAO(IEtudiantCoursDAO etudiantcoursDAO) {
		this.etudiantcoursDAO = etudiantcoursDAO;
	}




	@RequestMapping(value="/etudiantCours/listeAll", method=RequestMethod.GET)
	public String recupListeAllMatiere(ModelMap modele) {
		//1. recup de la liste de tous les etudiants de la bdd
		System.out.println("dans le getall");
		List<EtudiantCours> listeabsence = etudiantcoursDAO.getAllEtudiantCours();
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_absence", listeabsence);
		//modele.addAttribute("attribut_liste_etudiant", listeabsence);

		
		return "administrateur_listeAbsence";
	}//end recupListeAllEtudiant
	
	
}
