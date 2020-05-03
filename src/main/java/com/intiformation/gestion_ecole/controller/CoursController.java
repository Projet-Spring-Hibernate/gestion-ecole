package com.intiformation.gestion_ecole.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;
import com.intiformation.gestion_ecole.entityForForms.CoursForm;
import com.intiformation.gestion_ecole.validator.CoursFormValidator;
import com.intiformation.gestion_ecole.validator.CoursValidator;

@Controller
public class CoursController {
	
	// ========== DAO =========================//
	@Autowired
	private ICoursDAO coursDao;
	
	@Autowired
	private IEnseignantDAO enseignantDao;
	
	@Autowired
	private IEtudiantDAO etudiantDao;
	
	@Autowired
	private CoursFormValidator coursFormValidator;
	
	@Autowired
	private IMatiereDAO matiereDao;
	
	@Autowired
	private IPromotionDAO promotionDao;
	

	// ============ SETTER ====================//
	public void setCoursDao(ICoursDAO coursDao) {
		this.coursDao = coursDao;
	}

	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}
	
	
	public void setCoursFormValidator(CoursFormValidator coursFormValidator) {
		this.coursFormValidator = coursFormValidator;
	}

	public void setMatiereDao(IMatiereDAO matiereDao) {
		this.matiereDao = matiereDao;
	}

	public void setPromotionDao(IPromotionDAO promotionDao) {
		this.promotionDao = promotionDao;
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
	public String recupCoursById(@PathVariable("coursID") Long pIdCours, ModelMap modele) {
		
		//1. recup du cours
		Cours cours = coursDao.getById(pIdCours);
		
		//Cours cours1 = coursDao.affichageCours(cours);
		

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_cours", cours);
		
		return "affichage_cours";
	}//end recupCoursById
	
	@RequestMapping(value="/cours/delete/{coursID}", method=RequestMethod.GET)
	public String suppressionCoursById(@PathVariable("coursID") Long pIdCours, ModelMap modele) {
		
		//1. recup du cours
		Cours cours = coursDao.getById(pIdCours);
		coursDao.supprimer(cours);
		
		//Cours cours1 = coursDao.affichageCours(cours);
		

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_cours", cours);
		
		return "redirect:/cours/listeAll";
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
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/cours/listeByEnseignant", method=RequestMethod.GET)
	public String recupCoursByIdEnseignant(ModelMap modele) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Enseignant enseignant = (Enseignant) enseignantDao.getPersonneByMail(auth.getName());

		
		//1. recup du cours
		List<Cours> listeCours = coursDao.getListeCoursByIdEnseignant(enseignant.getIdentifiant());
		System.out.println("dans le controleur");

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_cours_enseignant", listeCours);
		System.out.println(listeCours);
		
		return "enseignant_listeCours";
	}//end recupCoursByIdEnseignant
	
	
	
	
	/**
	 * A REVOIR
	 * @return
	 */
//	@RequestMapping(value="/cours/add-cours-form", method=RequestMethod.GET)
//	public ModelAndView afficherFormulaireAjout() {
//		
//		//1. definition de l'objet de commande à lier aux champs du formulaire d'ajout
//		
//		//1.1 l'objet
//		Cours cours = new Cours();
//		
//		//1.2 nom de l'objet
//		String objetCommandeCours = "coursCommand";
//		
//		//2. envoi de l'objet de commande à la servlet de spring mvc 
//		//> données à envoyer vers la servlet 
//		Map<String, Object> data = new HashMap<>();
//		data.put(objetCommandeCours, cours);
//		
//		//2.1 def du nom logique de la vue
//		String viewName="administrateur_ajout_cours";
//		
//		//3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de la vue
//		return new ModelAndView(viewName, data);
//		
//	}//end afficherFormulaireAjout
	
	
	@RequestMapping(value="/cours/add-cours-formEnseignant", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAjoutEnseignant() {
		
		//1. definition de l'objet de commande à lier aux champs du formulaire d'ajout
		
		//1.1 l'objet
		Cours cours = new Cours();
		
		//1.2 nom de l'objet
		String objetCommandeCours = "coursEnseignantCommand";
		
		//2. envoi de l'objet de commande à la servlet de spring mvc 
		//> données à envoyer vers la servlet 
		Map<String, Object> data = new HashMap<>();
		data.put(objetCommandeCours, cours);
		
		//2.1 def du nom logique de la vue
		String viewName="enseignant_ajout_cours";
		
		//3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de la vue
		return new ModelAndView(viewName, data);
		
	}//end afficherFormulaireAjout
	
	
	@RequestMapping(value = "/cours/add-enseignant", method = RequestMethod.POST)
	public String ajouterCoursEnseignant(@ModelAttribute("coursCommand") @Validated Cours pCours,
			ModelMap modele, BindingResult result) {

			// =================== 1. Validateur ========================//

			// 1.1 Application du validateur sur pEnseignantform
			
			coursFormValidator.validate(pCours, result);

			// 1.2 Test des erreurs
			if (result.hasErrors()) {

				// redirection vers le formulaire d'ajout

				return "redirect:/cours/add-cours-formEnseignant";
				// return "administrateur_ajout_enseignant";
			} else {
				// ==> le validateur n'a pas detecté d'erreur

				// =================== 2. Recup et traitement des cours par id de l'enseignant
				// ========================//

				// On recupere l'enseignants à partie de l'objet EnseignantFrom
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				Enseignant enseignant = (Enseignant) enseignantDao.getPersonneByMail(auth.getName());
				
				List<Cours> listeCours = coursDao.getListeCoursByIdEnseignant(enseignant.getIdentifiant());
				System.out.println("dans le controleur");
				coursDao.ajouter(pCours);
				//2. def des données à afficher dans la vue
				modele.addAttribute("attribut_cours_enseignant", listeCours);
				
				return "redirect:/cours/listeByEnseignant";
				

			}//end else
	}
	
	
	@RequestMapping(value="/cours/add-cours-form", method=RequestMethod.GET)
	public String afficherFormulaireAjout(ModelMap modele) {

		if (!modele.containsAttribute("coursform")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet
			CoursForm coursform = new CoursForm();

			// 1.2 On recupere la liste des promos et des matières existantes pour affichage
			// dans des menus deroulants
			coursform.setListeMatieresExistantes(matiereDao.listMatiere());
			coursform.setListePromotionsExistantes(promotionDao.listePromotion());

			// 1.3 creation d'une liste avec 5 elements vides
			List<Cours> listeCours = coursDao.getAllCours();
			//List<Matiere> listeMatiere = matiereDao.listMatiere();
			//List<Promotion> listePromotion = promotionDao.listePromotion();
			Cours coursVide = new Cours();
			Matiere matiereVide = new Matiere();
			//Matiere matiereVide = coursVide.getMatiere();
			
			Promotion promotionVide = new Promotion();
			//Promotion promotionVide = coursVide.getPromotion();
			
			// A REVOIR
			coursVide.setMatiere(matiereVide);
			coursVide.setPromotion(promotionVide);
			
			listeCours.add(coursVide);
			// IMPOSSIBLE
			//listeCours.add(matiereVide);
			//listeMatiere.add(matiereVide);
			//listePromotion.add(promotionVide);
//			listeCours.addAll((Collection<? extends Cours>) matiereVide);
//			listeCours.addAll((Collection<? extends Cours>) promotionVide);
			
			coursform.setListeCours(listeCours);
			
			//coursform.setListePromotionsExistantes(listeCours);
			//coursform.setListeMatieresExistantes(listeCours);
			
			//coursform.setListeMatieresExistantes(listeMatiere);
			//coursform.setListePromotionsExistantes(listePromotion);
			

			System.out.println("Liste promo existantes : " + coursform.getListePromotionsExistantes());
			System.out.println("Liste matieres existantes : " + coursform.getListeMatieresExistantes());
			System.out.println("Liste vide : " + coursform.getListeCours().size());
		
			

			
			// 2. envoi de l'objet de commande à la servlet de spring mvc
			// > données à envoyer vers la servlet
			// Map<String, Object> data = new HashMap<>();
			// data.put("enseignantCommand", enseignantform);
			modele.addAttribute("coursform", coursform);
		}else {
			CoursForm coursForm = (CoursForm) modele.getAttribute("coursform");
			coursForm.setListeMatieresExistantes(matiereDao.listMatiere());
			coursForm.setListePromotionsExistantes(promotionDao.listePromotion());
			modele.addAttribute("coursform", coursForm);
			
		}//end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue
		return "administrateur_ajout_cours";
		
	}//end afficherFormulaireAjout
	
	
	

	/**
	 * permet d'ajouter un enseignant à la bdd
	 * 
	 * @Validated permet de declencher la validation de l'objet BindingResult
	 *            resultat va contenir le resultat du processus de la validation
	 * @return
	 */
	@RequestMapping(value = "/cours/add", method = RequestMethod.POST)
	public String ajouterCoursBdd(@ModelAttribute("coursform") CoursForm coursform,
		ModelMap modele, BindingResult result, RedirectAttributes redirectAttributes) {
		System.out.println("dans méthode add");

		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur pEnseignantform
		
		coursFormValidator.validate(coursform, result);
		
		Cours cours = coursform.getCours();
		Matiere matiere = coursform.getMatiere();
		Promotion promotion = coursform.getPromotion();

		// 1.2 Test des erreurs
		if (result.hasErrors()) {
			System.out.println("erreurs");
			// redirection vers le formulaire d'ajout
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.coursform", result);
			redirectAttributes.addFlashAttribute("coursform", coursform);

			return "redirect:/cours/add-cours-form";
			
		}else if (cours.getMatiere().getLibelle()!="" && cours.getPromotion().getLibelle()!=""){
			cours.addMatiere(matiere);
			cours.addPromotion(promotion);
			
			System.out.println("Matiere : "+matiere);
			System.out.println("Promotion : "+promotion);

		}else {
			System.out.println("matière et promo vides");
			
			
		}//end else
		System.out.println("Cours : "+cours);
		
		// Ajout et récup du cours
		coursDao.ajouter(cours);
		
		System.out.println("\n\n Cours Ajouté !");
		
		cours = coursDao.getById(cours.getIdCours());

		
		modele.addAttribute("administrateur_listeCours", coursDao.getAllCours());
		return "redirect:/cours/listeAll";

	}// end ajouterCoursBdd()
	
	
	/**
	 * MODIF à partir du lien de liste cours
	 * @param idcours
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/cours/update-cours-form", method=RequestMethod.GET)
	public String afficherFormulaireUpdate(@RequestParam("idcours") Long idcours, ModelMap modele) {
		

		if (!modele.containsAttribute("coursform")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet
			CoursForm coursform = new CoursForm();

			Cours cours = coursDao.getById(idcours);
			coursform.setCours(cours);
			
			coursform.setMatiere(cours.getMatiere());
			
			coursform.setListeMatieresExistantes(matiereDao.listMatiere());
			coursform.setListePromotionsExistantes(promotionDao.listePromotion());

			// 1.3 creation d'une liste avec 5 elements vides
			List<Cours> listeCours = coursDao.getAllCours();
			//List<Matiere> listeMatiere = matiereDao.listMatiere();
			//List<Promotion> listePromotion = promotionDao.listePromotion();
			Cours coursVide = new Cours();
			Matiere matiereVide = new Matiere();
			matiereVide.setLibelle("--Choisir--");
			matiereVide.setIdMatiere(0L);
			
			Promotion promotionVide = new Promotion();
			promotionVide.setLibelle("--Choisir--");
			promotionVide.setIdPromotion(0L);
			
			// A REVOIR
			coursVide.setMatiere(matiereVide);
			coursVide.setPromotion(promotionVide);
			
			coursform.setMatiere(matiereVide);
			coursform.setCours(coursVide);
			coursform.setPromotion(promotionVide);
			
			listeCours.add(coursVide);
			
			coursform.setListeCours(listeCours);
			coursform.setListeMatieresExistantes(matiereDao.listMatiere());
			coursform.setListePromotionsExistantes(promotionDao.listePromotion());

			System.out.println("Liste promo existantes : " + coursform.getListePromotionsExistantes());
			System.out.println("Liste matieres existantes : " + coursform.getListeMatieresExistantes());
			System.out.println("Liste vide : " + coursform.getListeCours().size());
		
			

			modele.addAttribute("coursform", coursform);
		}else {
			CoursForm coursForm = (CoursForm) modele.getAttribute("coursform");
			coursForm.setListeMatieresExistantes(matiereDao.listMatiere());
			coursForm.setListePromotionsExistantes(promotionDao.listePromotion());
			modele.addAttribute("coursform", coursForm);
			
		}//end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue
		return "administrateur_modif_cours";
		
//		// 1. récup de l'empl à modif dans la bdd
//		Cours coursModif = coursDao.getById(pCoursID);
//		
//		// 2. déf du modèle de données (objet de commande = employeModif) + déf du nom logique de la vue
//		//		=> ajout dans un objet ModelAndView
//		
//		return new ModelAndView("administrateur_modif_cours", "coursModifCommand", coursModif);
//		//new ModelAndView(viewName, modelName, modelObject);
//		
//		/**
//		 * résolution de la vue par le view resolver
//		 * 	modifier-employe ======> /WEB-INF/views/modifier-employe.jsp
//		 * 	
//		 */
		
	}//end afficherFormulaireUpdate()
	
	/**
	 * MODIF A PARTIR DE L'AFFICHAGE
	 * @param idcours
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/cours/update-cours-form/{idcours}", method=RequestMethod.GET)
	public String afficherFormulaireUpdateBouton(@PathVariable("idcours") Long idcours, ModelMap modele) {
		

		if (!modele.containsAttribute("coursform")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet
			CoursForm coursform = new CoursForm();

			Cours cours = coursDao.getById(idcours);
			coursform.setCours(cours);
			
			coursform.setMatiere(cours.getMatiere());
			
			coursform.setListeMatieresExistantes(matiereDao.listMatiere());
			coursform.setListePromotionsExistantes(promotionDao.listePromotion());

			// 1.3 creation d'une liste avec 5 elements vides
			List<Cours> listeCours = coursDao.getAllCours();
			//List<Matiere> listeMatiere = matiereDao.listMatiere();
			//List<Promotion> listePromotion = promotionDao.listePromotion();
			Cours coursVide = new Cours();
			Matiere matiereVide = new Matiere();
			matiereVide.setLibelle("--Choisir--");
			matiereVide.setIdMatiere(0L);
			
			Promotion promotionVide = new Promotion();
			promotionVide.setLibelle("--Choisir--");
			promotionVide.setIdPromotion(0L);
			
			// A REVOIR
			coursVide.setMatiere(matiereVide);
			coursVide.setPromotion(promotionVide);
			
			coursform.setMatiere(matiereVide);
			coursform.setCours(coursVide);
			coursform.setPromotion(promotionVide);
			
			listeCours.add(coursVide);
			
			coursform.setListeCours(listeCours);
			coursform.setListeMatieresExistantes(matiereDao.listMatiere());
			coursform.setListePromotionsExistantes(promotionDao.listePromotion());

			System.out.println("Liste promo existantes : " + coursform.getListePromotionsExistantes());
			System.out.println("Liste matieres existantes : " + coursform.getListeMatieresExistantes());
			System.out.println("Liste vide : " + coursform.getListeCours().size());
		
			

			modele.addAttribute("coursform", coursform);
		}else {
			CoursForm coursForm = (CoursForm) modele.getAttribute("coursform");
			coursForm.setListeMatieresExistantes(matiereDao.listMatiere());
			coursForm.setListePromotionsExistantes(promotionDao.listePromotion());
			modele.addAttribute("coursform", coursForm);
			
		}//end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue
		return "administrateur_modif_cours";

		
	}//end afficherFormulaireUpdate()
	
	
	
	@RequestMapping(value="/cours/update", method=RequestMethod.POST)
	public String modifierEmployeBdd(@ModelAttribute("coursform") Cours pCours, ModelMap modele)  {
		
		// 1. modif de l'employé dans la bdd
		coursDao.modifier(pCours);
		
		// 2. récup de la nouvelle liste des employés + envoi de la liste vers la servlet de spring mvc
		modele.addAttribute("attribut_liste_cours", coursDao.getAllCours());
				
		
		
		return "redirect:/cours/listeAll";
	}//end modifierEmployeBdd
	


}//end class
