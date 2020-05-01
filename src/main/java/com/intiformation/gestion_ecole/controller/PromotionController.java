package com.intiformation.gestion_ecole.controller;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/promotions/listePromotionByIdEtudiant", method = RequestMethod.GET)
	public String recupListePromotionByIdEtudiant(ModelMap modele) {

		// 1. recup de la liste des promotions en fonction de l'ID de l'étudiant de la
		// bdd
		List<Promotion> listePromotionByIdEtudiant = promotionDao.getListePromotionByIdEtudiant(null);

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_promotions", listePromotionByIdEtudiant);

		return "etudiant_listePromotion";
	}//end recupListePromotionByIdEtudiant
	
	/*************************************************************************************************/

	@RequestMapping(value = "/promotions/ListePromotionByIdEnseignant", method = RequestMethod.GET)
	public String recupListePromotionByIdEnseignant(ModelMap modele) {

		// 1. recup de la liste des promotions en fonction de l'ID de l'étudiant de la
		// bdd
		List<Promotion> ListePromotionByIdEnseignant = promotionDao.getListePromotionByIdEnseignant(null);

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_promotions", ListePromotionByIdEnseignant);

		return "enseignants_listePromotion";
	}//end recupListePromotionByIdEnseignant
	
}// end class
