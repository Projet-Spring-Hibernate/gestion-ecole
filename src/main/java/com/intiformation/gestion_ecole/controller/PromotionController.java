package com.intiformation.gestion_ecole.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intiformation.gestion_ecole.dao.IAideDAO;
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
import com.intiformation.gestion_ecole.entityForForms.PromotionForm;
import com.intiformation.gestion_ecole.validator.CoursValidator;
import com.intiformation.gestion_ecole.validator.PromotionFormValidator;

/**
 * Controller pour la gestion des promotions
 * 
 * @author Marie
 *
 */
@Controller
public class PromotionController {

	// ========== DAO et SETTER =========================//
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

	@Autowired
	private IAideDAO aideDao;

	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}

	@Autowired
	private PromotionFormValidator promotionFormValidator;

	// Setter pour l'injection Spring de PromotionFormValidator
	public void setPromotionFormValidator(PromotionFormValidator promotionFormValidator) {
		this.promotionFormValidator = promotionFormValidator;
	}

	// ========== METHODES ========================================//

	// ===========================================================//
	// =========== Liste ALL Promotions ===========================//
	// ===========================================================//

	/**
	 * Permet de recuperer la liste de toutes les promotions. Invoqué au click du
	 * bouton "Promotions" de l'entête admin. Renvoie vers la page
	 * administrateur_ListePromotions.jsp
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/promotions/listeAll", method = RequestMethod.GET)
	public String recupListeAllPromotion(ModelMap modele) {
		// 1. recup de la liste de toutes les promotions de la bdd
		List<Promotion> listePromotion = promotionDao.listePromotion();

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_promotions", listePromotion);

		// modele.addAttribute("aide_contenu",
		// aideDao.getAideByPage("administrateur_listePromotion"));

		return "administrateur_listePromotion";
	}// end recupListeAllPromotion

	// ===========================================================//
	// =========== Affichage promotion par id ===================//
	// ===========================================================//
	/**
	 * Permet d'afficher les caractéristiques d'une promotion. Invoqué au click du
	 * bouton "Afficher" Renvoie vers la page affichage_promotion.jsp
	 * 
	 * @param pIdEtudiant
	 *            : identifiant de l'etudiant que l'on veut afficher
	 * @return
	 */
	@RequestMapping(value = "/promotions/afficher/{promotionID}", method = RequestMethod.GET)
	public String recupPromotionById(@PathVariable("promotionID") Long pIdPromotion, ModelMap modele) {

		// 1. recup de la promotion
		Promotion promotion = promotionDao.getById(pIdPromotion);

		// 2. Recup de la liste des etudiants de la promo
		List<Etudiant> listeEtudiant = etudiantDao.getlistEtudiantsByIdPromotion(pIdPromotion);

		promotion.setListeEtudiant(listeEtudiant);

		// 3. def des données à afficher dans la vue
		modele.addAttribute("attribut_promotion", promotion);

		return "affichage_promotion";
	}// end recupPromoById

	// ===========================================================//
	// =========== Delete promo à la bdd =========================//
	// ===========================================================//
	/**
	 * Permet de supprimer une promotion de la bdd <br/>
	 * Invoqué au click du bouton "Supprimer" de la page affichage_promotion.jsp.
	 * <br/>
	 * Renvoie vers la page administrateur_listePromotions.jsp
	 * 
	 * @return
	 */
	@RequestMapping(value = "/promotions/delete/{promotionID}", method = RequestMethod.GET)
	public String suppressionPromotionsById(@PathVariable("promotionID") Long pIdPromotion, ModelMap modele) {

		// 1. recup de la promo
		Promotion promotion = promotionDao.getById(pIdPromotion);
		promotionDao.supprimer(promotion);

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_promotion", promotion);

		return "redirect:/promotions/listeAll";
	}// end delete

	// ===========================================================//
	// =========== Affichage formulaire ajout promotion ===========//
	// ===========================================================//
	/**
	 * Permet d'afficher le formulaire d'ajout d'une promotion. Invoqué au click du
	 * bouton "Ajouter une promotion" de la page administrateur_listePromotion.jsp.
	 * Renvoie vers la page administrateur_ajout_promotion.jsp
	 * 
	 * @return
	 */
	@RequestMapping(value = "/promotions/add-promotion-form", method = RequestMethod.GET)
	public String ajouterPromoForm(ModelMap modele) {

		if (!modele.containsAttribute("promotionForm")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet

			PromotionForm promotionForm = new PromotionForm();

			// 1.2 recuperer les etudiants existants
			List<Etudiant> listeEtudiants = etudiantDao.getAllEtudiant();

			promotionForm.setListeAllEtudiants(listeEtudiants);

			// 2. envoi de l'objet de commande à la servlet de spring mvc
			// > données à envoyer vers la servlet

			modele.addAttribute("promotionForm", promotionForm);

		} else {

			PromotionForm promotionForm = (PromotionForm) modele.getAttribute("promotionForm");

			List<Etudiant> listeEtudiants = etudiantDao.getAllEtudiant();

			System.out.println("\n\nlisteEtudiants =" + listeEtudiants);
			promotionForm.setListeAllEtudiants(listeEtudiants);

			modele.addAttribute("promotionForm", promotionForm);
		} // end
			// modele.addAttribute("aide_contenu",
			// aideDao.getAideByPage("administrateur_ajout_promotion"));

		return "administrateur_ajout_promotion";
	}// End ajouterPromoForm

	// ===========================================================//
	// =========== Ajout promotion à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet d'ajouter une promotion à la bdd <br/>
	 * Invoqué au click du bouton "Ajouter" de la page
	 * administrateur_ajout_promotion.jsp. <br/>
	 * Renvoie vers la page administrateur_listePromotions.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/promotions/add", method = RequestMethod.POST)
	public String ajouterPromotionsBdd(@ModelAttribute("promotionform") PromotionForm promotionForm, ModelMap modele,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur promotionForm

		promotionFormValidator.validate(promotionForm, result);

		// 1.2 Test des erreurs
		if (result.hasErrors()) {

			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.promotionForm", result);
			redirectAttributes.addFlashAttribute("promotionForm", promotionForm);

			// redirection vers le formulaire d'ajout

			return "redirect:/promotions/add-promotion-form";

		} else {
			// ==> le validateur n'a pas detecté d'erreur

			// ======= 2. Recup et traitement de la promotion ===============//

			// On recupere la promotion à partie de l'objet promotionForm
			Promotion promotion = promotionForm.getPromotion();

			// On ajoute la promotion à la bdd.
			promotionDao.ajouter(promotion);
			System.out.println("\n\n Promotion Ajouté !");

			// On recupere à nouveau la promotion par son id

			promotion = promotionDao.listePromotion().get(promotionDao.listePromotion().size() - 1);

			// ======= 3. Recup de la liste des etudiants de la promo ====//

			List<Etudiant> listeEtudiants = new ArrayList<>();
			Etudiant etudiant;
			Long idEtudiant;
			List<Promotion> listePromoDelEtudiant;
			for (int i = 0; i < promotionForm.getListeIdEtudiants().length; i++) {
				idEtudiant = Long.parseLong(promotionForm.getListeIdEtudiants()[i]);
				etudiant = (Etudiant) etudiantDao.getById(idEtudiant);

				listePromoDelEtudiant = promotionDao.getListePromotionByIdEtudiant(idEtudiant);
				listePromoDelEtudiant.add(promotion);

				etudiant.setListePromotion(listePromoDelEtudiant);

				etudiantDao.modifier(etudiant);

				listeEtudiants.add(etudiant);
			} // end for

			return "redirect:/promotions/listeAll";

		} // end else
	}// End ajouterPromotionsBdd

	// ===========================================================//
	// ======== Affichage formulaire de modif promotion =========//
	// ===========================================================//
	/**
	 * Permet d'afficher le formulaire de modif d'une promotion. <br/>
	 * Invoqué au click du bouton "Modifier" de la page affichage_promotion.jsp.
	 * <br/>
	 * Renvoie vers la page administrateur_modif_promotion.jsp. <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/promotions/update-promotion-form/{idPromotion}", method = RequestMethod.GET)
	public String afficherFormulaireUpdate(@PathVariable("idPromotion") Long pPromotionID, ModelMap modele) {

		if (!modele.containsAttribute("promotionForm")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet

			PromotionForm promotionForm = new PromotionForm();

			// 1.2 recuperer les etudiants existants
			List<Etudiant> listeEtudiants = etudiantDao.getAllEtudiant();

			promotionForm.setListeAllEtudiants(listeEtudiants);

			// 1.3 recup de la promo à modifier
			Promotion promotion = promotionDao.getById(pPromotionID);

			promotionForm.setPromotion(promotion);

			// 1.4 Recup de la liste des etudiants de la promo

			List<Etudiant> listeEtudiantDeLaPromo = etudiantDao.getlistEtudiantsByIdPromotion(pPromotionID);

			promotionForm.setListeEtudiantsAvantModif(listeEtudiantDeLaPromo);

			int nbEtudiant = listeEtudiantDeLaPromo.size();
			String[] listeIdEtudiants = new String[nbEtudiant];

			for (int i = 0; i < nbEtudiant; i++) {
				listeIdEtudiants[i] = String.valueOf(listeEtudiantDeLaPromo.get(i).getIdentifiant());
			} // end for

			promotionForm.setListeIdEtudiants(listeIdEtudiants);

			// 2. envoi de l'objet de commande à la servlet de spring mvc
			// > données à envoyer vers la servlet

			modele.addAttribute("promotionForm", promotionForm);

		} else {

			PromotionForm promotionForm = (PromotionForm) modele.getAttribute("promotionForm");

			List<Etudiant> listeEtudiants = etudiantDao.getAllEtudiant();

			System.out.println("\n\nlisteEtudiants =" + listeEtudiants);
			promotionForm.setListeAllEtudiants(listeEtudiants);

			modele.addAttribute("promotionForm", promotionForm);
		} // end
			// modele.addAttribute("aide_contenu",
			// aideDao.getAideByPage("administrateur_ajout_promotion"));

		return "administrateur_modif_promotion";

	}// end afficherFormulaireUpdate()

	// ===========================================================//
	// =========== Modif promotion à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet de modifier une promotion de la bdd <br/>
	 * Invoqué au click du bouton "Modifer" de la page
	 * administrateur_modif_promotion.jsp. <br/>
	 * Renvoie vers la page affichage_promotion.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/promotions/update", method = RequestMethod.POST)
	public String modifierPromotionBdd(@ModelAttribute("promotionform") PromotionForm promotionForm, ModelMap modele,
			BindingResult result, RedirectAttributes redirectAttributes) {

		System.out.println("///////////////////////////////////////////////////");
		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur promotionForm

		promotionFormValidator.validate(promotionForm, result);

		// 1.2 Test des erreurs
		if (result.hasErrors()) {
			System.out.println("\n Il y a une erreur de validation \n");

			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.promotionForm", result);
			redirectAttributes.addFlashAttribute("promotionForm", promotionForm);

			// redirection vers le formulaire d'ajout

			return "redirect:/promotions/update-promotion-form/" + promotionForm.getPromotion().getIdPromotion();

		} else {
			System.out.println("\n Il y a pas d'erreur de validation \n");
			// ==> le validateur n'a pas detecté d'erreur

			List<Etudiant> listeEtudiantDeLaPromoAvantModif = etudiantDao
					.getlistEtudiantsByIdPromotion(promotionForm.getPromotion().getIdPromotion());

			// ======= 2. Recup et traitement de la promotion ===============//

			// On recupere la promotion à partie de l'objet promotionForm
			Promotion promotion = promotionForm.getPromotion();

			// On ajoute la promotion à la bdd.
			promotionDao.modifier(promotion);
			System.out.println("\n\n Promotion modifiée !");

			// ======= 3. Recup de la liste des etudiants de la promo ====//

			List<Etudiant> listeEtudiants = new ArrayList<>();
			Etudiant etudiant;
			Long idEtudiant;
			List<Promotion> listePromoDelEtudiant;
			List<Promotion> listePromoDelEtudiantNEW=new ArrayList<>();
			for (int i = 0; i < promotionForm.getListeIdEtudiants().length; i++) {
				idEtudiant = Long.parseLong(promotionForm.getListeIdEtudiants()[i]);
				etudiant = (Etudiant) etudiantDao.getById(idEtudiant);

				listePromoDelEtudiant = promotionDao.getListePromotionByIdEtudiant(idEtudiant);
				listePromoDelEtudiant.add(promotion);

				etudiant.setListePromotion(listePromoDelEtudiant);

				etudiantDao.modifier(etudiant);

				listeEtudiants.add(etudiant);
			} // end for

			boolean estToujoursDansListe = false;
			for (Etudiant etuAvM : listeEtudiantDeLaPromoAvantModif) {
				estToujoursDansListe = false;
				System.out.println("\t"+etuAvM);
				for (Etudiant etuApM : listeEtudiants) {
					if (etuAvM.getIdentifiant() == etuApM.getIdentifiant()) {
						estToujoursDansListe = true;
					} // end if
				} // end for

				if (estToujoursDansListe == false) {

					listePromoDelEtudiant = promotionDao.getListePromotionByIdEtudiant(etuAvM.getIdentifiant());
					
					for(Promotion promo : listePromoDelEtudiant) {
						if(promo.getIdPromotion()!=promotion.getIdPromotion()) {
							listePromoDelEtudiantNEW.add(promo);
						}//end if
					}//End for
					etuAvM.setListePromotion(listePromoDelEtudiantNEW);
					etudiantDao.modifier(etuAvM);
				} // End if
			} // end for

			return "redirect:/promotions/listeAll";

		} // end else
	}// end modifierPromotionsBdd

	// ===========================================================//
	// =========== Liste promos d'un etudiant ====================//
	// ===========================================================//
	
	/**
	 * Permet de recuperer la liste des promos de l'etudiant connecté. Invoqué
	 * au click du bouton "Mes promos" de l'entête etudiant. Renvoie vers la
	 * page etudiant_listePromo.jsp
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/promotions/listeByEtudiant", method = RequestMethod.GET)
	public String recupPromotionsByIdEtudiant(ModelMap modele) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Etudiant etudiant = (Etudiant) etudiantDao.getPersonneByMail(auth.getName());

		// 1. recup du cours
		List<Promotion> listePromotions = promotionDao.getListePromotionByIdEtudiant(etudiant.getIdentifiant());

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_promotion_etudiant", listePromotions);

		return "etudiant_listePromotion";
	}// end recupPromotionsByIdEtudiant

	
	
	// ===========================================================//
	// =========== Liste promos d'un enseignant ==================//
	// ===========================================================//
	
	/**
	 * Permet de recuperer la liste des promos de l'enseignant connecté. Invoqué
	 * au click du bouton "Mes promos" de l'entête enseignant. Renvoie vers la
	 * page enseignant_listePromo.jsp
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "promotions/listeByEnseignant", method = RequestMethod.GET)
	public String recupPromotionsByIdEnseignant(ModelMap modele) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Enseignant enseignant = (Enseignant) enseignantDao.getPersonneByMail(auth.getName());

		// 1. recup de la liste des promotions
		List<Promotion> ListePromotions = promotionDao.getListePromotionByIdEnseignant(enseignant.getIdentifiant());

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_promotion_enseignant", ListePromotions);

		return "enseignants_listePromotion";
	}// end recupPromotionByIdEnseignant



}// end class
