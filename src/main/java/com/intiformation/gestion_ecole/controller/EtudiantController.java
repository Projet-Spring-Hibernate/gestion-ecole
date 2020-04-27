package com.intiformation.gestion_ecole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
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

	/**
	 * Setter pour injection spring
	 * @param etudiantDao
	 */
	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
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
		
//		//2. Recup de son adresse
//		Adresse adresse = etudiant.getAdresse();
//		System.out.println("adresse" + adresse );
//		//3. Recup de ses promos
//		List<Promotion> listePromos = etudiant.getListePromotion();
//		System.out.println("promo :" + listePromos);
		
		//4. Recup des enseignant de la premiere promo
		
//		List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = listePromos.get(0).getListeEnseignantMatierePromotion();
//		System.out.println("EnseignantMatierePromotion : " + listeEnseignantMatierePromotion);
//		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_etudiant", etudiant);
		
		
		
		
		return "affichage_etudiant";
	}//end recupListeAllEtudiant
	
}//end class
