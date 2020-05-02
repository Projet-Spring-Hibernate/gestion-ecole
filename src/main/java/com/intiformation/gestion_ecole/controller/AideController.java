package com.intiformation.gestion_ecole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestion_ecole.dao.IAideDAO;
import com.intiformation.gestion_ecole.domain.Aide;

/**
 * Controller dédié à la gestion de la rubrique Aide
 * @author IN-DF-019
 *
 */
@Controller
public class AideController {
	
	@Autowired
	private IAideDAO aideDao;

	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}
	
	/**
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/aides/listeAll", method=RequestMethod.GET)
	public String recupListeAllAide(ModelMap modele) {
		//1.récupération de la liste de toutes les pages d'aide présentes dans la BDD
		List<Aide> listeAide = aideDao.getAllAide();
		//2.définition des données à afficher dans la vue
		modele.addAttribute("attribut_liste_aide", listeAide);
		return "administrateur_listeAides";
	}//end recupListeAllAide
	
	@RequestMapping(value="/aides/")
	public String modifierAide() {
		
		return "administrateur_modif_aide";
	}//end modifierAide
	

}//end class
