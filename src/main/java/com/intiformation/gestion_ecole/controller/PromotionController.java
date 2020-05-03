package com.intiformation.gestion_ecole.controller;

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
	private PromotionFormValidator promotionFormValidator;
	
	// Setter pour l'injection Spring de PromotionFormValidator
	public void setPromotionFormValidator(PromotionFormValidator promotionFormValidator) {
		this.promotionFormValidator = promotionFormValidator;
	}

	/*********************************LISTE PROMOTION************************************************/

	@RequestMapping(value = "/promotions/listeAll", method = RequestMethod.GET)
	public String recupListeAllPromotion(ModelMap modele) {
		// 1. recup de la liste de toutes les promotions de la bdd
		List<Promotion> listePromotion = promotionDao.listePromotion();

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_promotions", listePromotion);

		return "administrateur_listePromotion";
	}// end recupListeAllPromotion

	/******************************LISTE PROMOTION BY ID*********************************************/
	@RequestMapping(value="/promotions/afficher/{promotionID}", method=RequestMethod.GET)
	public String recupPromotionById(@PathVariable("promotionID") Long pIdPromotion,ModelMap modele) {
		
		//1. recup de l'étudiant
		Promotion promotion = promotionDao.getById(pIdPromotion);
	
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_promotion", promotion);
		
		return "affichage_promotion";
	}//end recupPromoById
	
	/*********************************SUPPRIMER UNR PROMOTION***********************************************/
	@RequestMapping(value="/promotions/delete/{promotionID}", method=RequestMethod.GET)
	public String suppressionPromotionsById(@PathVariable("promotionID") Long pIdPromotion, ModelMap modele) {
		
		//1. recup de la promo
		Promotion promotion = promotionDao.getById(pIdPromotion);
		promotionDao.supprimer(promotion);

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_promotion", promotion);
		
		return "redirect:/promotions/listeAll";
	}//end delete
	
	/*************************************************************************************************/
	
	@RequestMapping(value="/promotions/add-promotion-formEnseignant", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAjoutPromotionEnseignant() {
		
		//1. definition de l'objet de commande à lier aux champs du formulaire d'ajout
		
		//1.1 l'objet
		Promotion promotion = new Promotion();
		
		//1.2 nom de l'objet
		String objetCommandePromotion = "promotionsEnseignantCommand";
		
		//2. envoi de l'objet de commande à la servlet de spring mvc 
		//> données à envoyer vers la servlet 
		Map<String, Object> data = new HashMap<>();
		data.put(objetCommandePromotion, promotion);
		
		//2.1 def du nom logique de la vue
		String viewName="enseignant_ajout_promotion";
		
		//3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de la vue
		return new ModelAndView(viewName, data);
		
	}//end afficherFormulaireAjoutPromotionEnseignant
	
	/*************************************************************************************************/
	
	@RequestMapping(value = "/promotion/add-enseignant", method = RequestMethod.POST)
	public String ajouterPromotionEnseignant(@ModelAttribute("promotionCommand") @Validated Promotion pPromotions,
			ModelMap modele, BindingResult result) {

			// =================== 1. Validateur ========================//

			// 1.1 Application du validateur sur pPromotionform
			
			promotionFormValidator.validate(pPromotions, result);

			// 1.2 Test des erreurs
			if (result.hasErrors()) {

				// redirection vers le formulaire d'ajout

				return "redirect:/promotion/add-promotions-formEnseignant";
				
			} else {
				// ==> le validateur n'a pas detecté d'erreur

				// =================== 2. Recup et traitement de la promo par id de l'enseignant
				// ========================//

				// On recupere l'enseignants à partie de l'objet EnseignantFrom
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				Enseignant enseignant = (Enseignant) enseignantDao.getPersonneByMail(auth.getName());
				
				List<Promotion> listePromotion = promotionDao.getListePromotionByIdEnseignant(enseignant.getIdentifiant());
				System.out.println("dans le controleur");
				promotionDao.ajouter(pPromotions);
				//2. def des données à afficher dans la vue
				modele.addAttribute("attribut_prmotion_enseignant", listePromotion);
				
				return "redirect:/promotion/listeByEnseignant";

			}//end else
	}
	
	/*************************************************************************************************/
	
	@RequestMapping(value = "/promotions/add", method = RequestMethod.POST)
	public String ajouterPromotionsBdd(@ModelAttribute("promotionform") @Validated PromotionForm promotionform,
			ModelMap modele, BindingResult result, RedirectAttributes redirectAttributes) {

			// =================== 1. Validateur ========================//

			// 1.1 Application du validateur sur pEnseignantform
			
			promotionFormValidator.validate(promotionform, result);

			// 1.2 Test des erreurs
			if (result.hasErrors()) {

				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.promotionform", result);
				redirectAttributes.addFlashAttribute("promotionform", promotionform);
				
				// redirection vers le formulaire d'ajout

				return "redirect:/promotions/add-promotion-form";
				
			} else {
				// ==> le validateur n'a pas detecté d'erreur

				// =================== 2. Recup et traitement de l'Enseignant
				// ========================//

				// On recupere l'enseignants à partie de l'objet EnseignantFrom
				Promotion promotion = promotionform.getPromotion();

				// On ajoute la promotion à la bdd. 
				promotionDao.ajouter(promotion);
				System.out.println("\n\n Promotion Ajouté !");
				
				// On recupere à nouveau la promotion id
				// son id
				promotion = promotionDao.getById(promotion.getIdPromotion());
				
				modele.addAttribute("attribut_liste_promotions", promotionDao.getAllPromo());
				
				return "redirect:/promotions/listeAll";
				

			}//end else
	}
	
	/*************************************************************************************************/
	
	@RequestMapping(value="/promotions/update-promotion-form", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdate(@RequestParam("idpromotion") Long pPromotionID) {
		
		// 1. récup de del a promo à modif dans la bdd
		Promotion promotionModif = promotionDao.getById(pPromotionID);
		
		// 2. déf du modèle de données (objet de commande = employeModif) + déf du nom logique de la vue
		//		=> ajout dans un objet ModelAndView
		
		return new ModelAndView("administrateur_modif_promotion", "promotionModifCommand", promotionModif);
		
		
	}//end afficherFormulaireUpdate()
	

	/*************************************************************************************************/
	
	@RequestMapping(value="/promotions/update", method=RequestMethod.POST)
	public String modifierPromotionBdd(@ModelAttribute("promotionModifCommand") Promotion pPromotion, ModelMap modele)  {
		
		// 1. modif de l'employé dans la bdd
		promotionDao.modifier(pPromotion);
		
		// 2. récup de la nouvelle liste des promos + envoi de la liste vers la servlet de spring mvc
		modele.addAttribute("attribut_liste_promotions", promotionDao.getAllPromo());
				
		
		
		return "redirect:/promotions/listeAll";
	}//end modifierPromotionsBdd
	
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
	
	
	/*************************************************************************************************/

	
}// end class
