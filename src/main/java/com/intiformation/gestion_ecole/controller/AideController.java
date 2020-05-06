package com.intiformation.gestion_ecole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intiformation.gestion_ecole.dao.IAideDAO;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Aide;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Promotion;
import com.intiformation.gestion_ecole.entityForForms.EtudiantForm;

/**
 * Controller dédié à la gestion de la rubrique Aide
 * 
 * @author IN-DF-019
 *
 */
@Controller
public class AideController {

	// ========== DAO =========================//

	@Autowired
	private IAideDAO aideDao;

	// ============ SETTER ====================//
	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}

	// ========== METHODES ========================================//

	// ===========================================================//
	// =========== Liste ALL Aides ===========================//
	// ===========================================================//

	/**
	 * Permet de recuperer la liste de toutes les aides. Invoqué au click du bouton
	 * "Aides" de l'entête admin. Renvoie vers la page administrateur_ListeAides.jsp
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/aides/listeAll", method = RequestMethod.GET)
	public String recupListeAllAides(ModelMap modele) {
		// 1.récupération de la liste de toutes les pages d'aide présentes dans la BDD
		List<Aide> listeAide = aideDao.getAllAide();
		System.out.println("\n" + listeAide + "\n");

		// 2.définition des données à afficher dans la vue
		modele.addAttribute("attribut_liste_aide", listeAide);

		modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_listeAides"));
		return "administrateur_listeAides";
	}// end recupListeAllAide

	// ===========================================================//
	// =========== Affichage aide à partie d'un tableau ==========//
	// ===========================================================//
	/**
	 * Permet d'afficher les caractéristiques d'une aide. Invoqué au click du bouton
	 * "Afficher" de la page administrateur_listeAides. Renvoie vers la page
	 * affichage_aide.jsp
	 * 
	 * @param pId_aide
	 *            : identifiant de l'aide que l'on veut afficher
	 * @return
	 */
	@RequestMapping(value = "/aides/afficher/{id_aide}", method = RequestMethod.GET)
	public String recupAideById(@PathVariable("id_aide") Long pId_aide, ModelMap modele) {

		// 1. recup de l'aide
		Aide aide = aideDao.getById(pId_aide);

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_aide", aide);

		modele.addAttribute("aide_contenu", aideDao.getAideByPage("affichage_aide"));
		return "affichage_aide";
	}// end recupListeAllEtudiant

	// ===========================================================//
	// ======== Affichage formulaire de modif aide ===============//
	// ===========================================================//
	/**
	 * Permet d'afficher le formulaire de modif d'une aide. <br/>
	 * Invoqué au click du bouton "Modifier" de la page affichage_aide.jsp. <br/>
	 * Renvoie vers la page administrateur_modif_aide.jsp. <br/>
	 * 
	 * @return
	 */

	@RequestMapping(value = "/aides/update-form/{id_aide}", method = RequestMethod.GET)
	public String afficherFormulaireModif(@PathVariable("id_aide") Long idAide, ModelMap modele) {

		Aide aide = aideDao.getById(idAide);

		modele.addAttribute("aide", aide);

		modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_modif_aide"));

		return "administrateur_modif_aide";
	}// End afficherFormulaireModif

	// ===========================================================//
	// =========== Modif aide à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet de modifier un aide de la bdd <br/>
	 * Invoqué au click du bouton "Modifer" de la page
	 * administrateur_modif_aide.jsp. <br/>
	 * Renvoie vers la page affichage_aide.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/aide/update", method = RequestMethod.POST)
	public String modifierAideBdd(@ModelAttribute("aide") Aide aide, ModelMap modele,
			RedirectAttributes redirectAttributes) {

		aideDao.modifier(aide);

		redirectAttributes.addFlashAttribute("message",
				"L'aide pour la page " + aide.getPage() + " a bien été modifiée.");
		redirectAttributes.addFlashAttribute("reussiteOperation", "true");
		return "redirect:/aides/afficher/" + aide.getId_aide();
	}// end modifierAideBdd

}// end class
