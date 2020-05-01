package com.intiformation.gestion_ecole.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Promotion;

@Controller
public class CoursController {
	
	@Autowired
	private ICoursDAO coursDao;
	
	@Autowired
	private IEnseignantDAO enseignantDao;
	
	@Autowired
	private IEtudiantDAO etudiantDao;

	public void setCoursDao(ICoursDAO coursDao) {
		this.coursDao = coursDao;
	}

	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}
	
	
	/**
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/cours/listeAll", method=RequestMethod.GET)
	public String recupListeAllCours(ModelMap modele) {
		// 1. récup  de la liste de tous les cours de la bdd
		List<Cours> listeCours = coursDao.getAllCours();
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_cours", listeCours);
		
		return "administrateur_listeCours";
		
	}//end recupListeAllCours()
	
	

	
	/**
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/cours/afficher/{coursID}", method=RequestMethod.GET)
	public String recupCoursById(@PathVariable("coursID") Long pIdCours,ModelMap modele) {
		
		//1. recup du cours
		Cours cours = coursDao.getById(pIdCours);
		

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_cours", cours);
		
		return "affichage_cours";
	}//end recupCoursById
	
	/**
	 * 
	 * @param pIdEtudiant
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/cours/listeByEtudiant", method=RequestMethod.GET)
	public String recupCoursByIdEtudiant(ModelMap modele) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Etudiant etudiant = (Etudiant) etudiantDao.getPersonneByMail(auth.getName());

		
		//1. recup du cours
		List<Cours> listeCours = coursDao.getListeCoursByIdEtudiant(etudiant.getIdentifiant());
		

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_cours_etudiant", listeCours);
		
		return "etudiant_listeCours";
	}//end recupCoursById
	
	
	
	/**
	 * A REVOIR
	 * @return
	 */
	@RequestMapping(value="/cours/add-cours-form", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAjout() {
		
		//1. definition de l'objet de commande à lier aux champs du formulaire d'ajout
		
		//1.1 l'objet
		Cours cours = new Cours();
		
		//1.2 nom de l'objet
		String objetCommandeCours = "coursCommand";
		
		//2. envoi de l'objet de commande à la servlet de spring mvc 
		//> données à envoyer vers la servlet 
		Map<String, Object> data = new HashMap<>();
		data.put(objetCommandeCours, cours);
		
		//2.1 def du nom logique de la vue
		String viewName="administrateur_ajout_cours";
		
		//3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de la vue
		return new ModelAndView(viewName, data);
		
	}//end afficherFormulaireAjout
	
	
	

}
