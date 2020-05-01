package com.intiformation.gestion_ecole.controller;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Promotion;

/**
 * Controller pour la gestion des promotions
 * 
 * @author Marie
 *
 */
@Controller
public class PromotionController {

	@Autowired
	private IPromotionDAO promotionDao;

	// Setter pour l'injection Spring de IPromotionDAO
	public void setPromotionDao(IPromotionDAO promotionDao) {
		this.promotionDao = promotionDao;
	}

	@Autowired
	private IEnseignantDAO enseignantDao;

	// Setter pour l'injection Spring de IEnseignantDAO
	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	@Autowired
	private IEtudiantDAO etudiantDao;

	// Setter pour l'injection Spring de IEtudiantDAO
	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}

	@Autowired
	private ICoursDAO coursDao;

	// Setter pour l'injection Spring de ICoursDAO
	public void setCoursDao(ICoursDAO coursDao) {
		this.coursDao = coursDao;
	}

	@Autowired
	private IMatiereDAO matiereDao;

	// Setter pour l'injection Spring de IMatiereDAO
	public void setMatiereDao(IMatiereDAO matiereDao) {
		this.matiereDao = matiereDao;
	}

	/*************************************************************************************************/

	@RequestMapping(value = "/promotions/listeAll", method = RequestMethod.GET)
	public String recupListeAllPromotion(ModelMap modele) {
		// 1. recup de la liste de toutes les promotions de la bdd
		List<Promotion> listePromotion = promotionDao.listePromotion();

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_promotions", listePromotion);

		return "administrateur_listePromotion";
	}// end recupListeAllPromotion

	/*************************************************************************************************/
	@RequestMapping(value="/promotions/afficher/{promotionID}", method=RequestMethod.GET)
	public String recupPromotionById(@PathVariable("promotionID") Long pIdPromotion,ModelMap modele) {
		
		//1. recup de l'étudiant
		Promotion promotion = promotionDao.getById(pIdPromotion);
	
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_promotion", promotion);
		
		return "affichage_promotion";
	}//end recupPromoById
	
	/*************************************************************************************************/

	@RequestMapping(value="/promotions/listeByEtudiant", method=RequestMethod.GET)
	public String recupPromotionsByIdEtudiant(ModelMap modele) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Etudiant etudiant = (Etudiant) etudiantDao.getPersonneByMail(auth.getName());

		
		//1. recup du cours
		List<Promotion> listePromotions = promotionDao.getListePromotionByIdEtudiant(etudiant.getIdentifiant());
				
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_promotion_etudiant", listePromotions);
		
		return "etudiant_listePromotion";
	}//end recupPromotionsByIdEtudiant
	
	/*************************************************************************************************/

	@RequestMapping(value = "/promotions/listeByIdEnseignant", method = RequestMethod.GET)
	public String recupPromotionsByIdEnseignant(ModelMap modele) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Enseignant enseignant = (Enseignant) enseignantDao.getPersonneByMail(auth.getName());
		
		// 1. recup de la liste des promotions 
		List<Promotion> ListePromotions  = promotionDao.getListePromotionByIdEnseignant(enseignant.getIdentifiant());

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_promotion_enseignant", ListePromotions);

		return "enseignants_listePromotion";
	}//end recupPromotionByIdEnseignant
	
}// end class
