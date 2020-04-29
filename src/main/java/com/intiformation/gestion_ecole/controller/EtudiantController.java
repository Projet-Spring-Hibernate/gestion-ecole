package com.intiformation.gestion_ecole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestion_ecole.dao.IAdresseDao;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IPersonneDao;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Promotion;



/**
 * Controller pour la gestion des etudiants
 * @author IN-MP-018
 *
 */


@Controller
public class EtudiantController {

	@Autowired
	private IEtudiantDAO etudiantDao;

	@Autowired
	private IAdresseDao adresseDao;
	
	@Autowired
	private IPromotionDAO promotionDao;
	
	/**
	 * Setter pour injection spring
	 * @param etudiantDao
	 */
	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}
	


	public void setPromotionDao(IPromotionDAO promotionDao) {
		this.promotionDao = promotionDao;
	}



	public void setAdresseDao(IAdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}
	//==================================================================//
	


	/**
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/etudiants/listeAll", method=RequestMethod.GET)
	public String recupListeAllEtudiant(ModelMap modele) {
		//1. recup de la liste de tous les etudiants de la bdd
		List<Etudiant> listeEtudiants = etudiantDao.getAllEtudiant();
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_etudiants", listeEtudiants);
		
		return "administrateur_listeEtudiants";
	}//end recupListeAllEtudiant
	
	//==================================================================//
	
	/**
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/etudiants/afficher/{etudiantID}", method=RequestMethod.GET)
	public String recupEtudiantById(@PathVariable("etudiantID") Long pIdEtudiant,ModelMap modele) {
		
		//1. recup de l'étudiant
		Etudiant etudiant = (Etudiant) etudiantDao.getById(pIdEtudiant);
		
		
		
		Adresse adresse = adresseDao.getByPersonneId(etudiant.getIdentifiant());
		System.out.println(adresse);
		
		List<Promotion> listepromo=promotionDao.getListePromotionByIdEtudiant(etudiant.getIdentifiant());
		System.out.println(listepromo);

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_etudiant", etudiant);
		modele.addAttribute("attribut_adresse", adresse);
		modele.addAttribute("attribut_listePromo", listepromo);
		modele.addAttribute("aide_contenu", "Aide pour la page affichage_etudiant");
		
		return "affichage_etudiant";
	}//end recupListeAllEtudiant
	
}//end class
