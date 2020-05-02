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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	/*************************************************************************************************/

	
	
	@RequestMapping(value = "/promotions/add", method = RequestMethod.POST)
	public String ajouterPromotionBdd(@ModelAttribute("promotionsform") PromotionFormValidator promotionForm,
			ModelMap modele, BindingResult result, RedirectAttributes redirectAttributes) {

		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur promotionForm
		
		PromotionFormValidator.validate(promotionForm, result);//a def dans validator

		// 1.2 Test des erreurs
		if (result.hasErrors()) {

			// ==> le validateur a detecté des erreurs
			// On redirige vers la page du formulaire administrateur_ajout_promotion.jsp

			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.promotionform",
					result);
			redirectAttributes.addFlashAttribute("promotionform", promotionForm);
			return "redirect:/promotions/add-promotions-form";
			return "administrateur_ajout_promotion";
		} else {
			// ==> le validateur n'a pas detecté d'erreur

			// =================== 2. Recup et traitement de l'Enseignant
			// ========================//

			// On recupere la promotion à partie de l'objet PromotionFrom
			// promotion = promotionForm.getPromotion();//a mettre dans validator

			
			// 2. recup de la nouvelle liste des promotions + redirection vers la page
			// administrateur_listePromotion.jsp

			// modele.addAttribute("attribut_liste_promotions", promotionDao.getAll(); //a def dans la Dao

			return "redirect:/promotions/listeAll";
		}// end else
	}// end ajouterPromotionsBdd()
	
}// end class
