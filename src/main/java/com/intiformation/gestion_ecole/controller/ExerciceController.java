package com.intiformation.gestion_ecole.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intiformation.gestion_ecole.dao.IAideDAO;
import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.dao.IExerciceDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.EtudiantCours;
import com.intiformation.gestion_ecole.domain.Exercice;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;
import com.intiformation.gestion_ecole.entityForForms.AbsenceForm;
import com.intiformation.gestion_ecole.entityForForms.CoursForm;
import com.intiformation.gestion_ecole.entityForForms.ExerciceForm;
import com.intiformation.gestion_ecole.validator.ExerciceFormValidator;

@Controller
public class ExerciceController {

	// ========== DAO =========================//

	@Autowired
	private IExerciceDAO exerciceDao;

	@Autowired
	private ICoursDAO coursDao;

	@Autowired
	private IAideDAO aideDao;

	@Autowired
	private ExerciceFormValidator exerciceFormValidator;

	// ============ SETTER ====================//
	public void setExerciceDao(IExerciceDAO exerciceDao) {
		this.exerciceDao = exerciceDao;
	}

	public void setCoursDao(ICoursDAO coursDao) {
		this.coursDao = coursDao;
	}

	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}

	public void setExerciceFormValidator(ExerciceFormValidator exerciceFormValidator) {
		this.exerciceFormValidator = exerciceFormValidator;
	}

	// ===========================================================//
	// =========== Liste des exercices d'un cours ================//
	// ===========================================================//
	@RequestMapping(value = "/exercice/listeExo/{idCours}", method = RequestMethod.GET)
	public String recupExoByIdCours(@PathVariable("idCours") Long idCours, ModelMap modele) {

		// 1. recup du cours
		List<Exercice> listeExo = exerciceDao.getExerciceByIdCours(idCours);

		Cours cours = coursDao.getById(idCours);

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_exercice", listeExo);
		modele.addAttribute("attribut_cours", cours);
		// modele.addAttribute("aide_contenu",
		// aideDao.getAideByPage("attribut_liste_exercice"));

		return "affichage_liste_exercice";
	}// end recupCoursById

	// ===========================================================//
	// =========== Affichage du formulaire d'ajout ===============//
	// ===========================================================//
	@RequestMapping(value = "/exercice/add-form/{idCours}", method = RequestMethod.GET)
	public String afficherFormulaireAjout(@PathVariable("idCours") Long idCours, ModelMap modele) {

		if (!modele.containsAttribute("exoform")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet
			ExerciceForm exoform = new ExerciceForm();

			// 1.2 Initialisation de l'objet exercice form
			Exercice exerciceVide = new Exercice();
			Cours cours = coursDao.getById(idCours);
			// Cours coursVide = new Cours();

			exerciceVide.setCours(cours);

			exoform.setExercice(exerciceVide);

			exoform.setListeCoursExistants(coursDao.getAllCours());
			System.out.println("exercice vide : " + exerciceVide);

			modele.addAttribute("exoform", exoform);
			modele.addAttribute("attribut_cours", cours);

		} else {
			ExerciceForm exoForm = (ExerciceForm) modele.getAttribute("exoform");
			Cours cours = coursDao.getById(idCours);
			modele.addAttribute("exoform", exoForm);
			modele.addAttribute("attribut_cours", cours);

		} // end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue

		// modele.addAttribute("aide_contenu",
		// aideDao.getAideByPage("administrateur_ajout_cours"));
		return "enseignant_ajout_exercice";

	}// end afficherFormulaireAjout

	// ===========================================================//
	// ================== Ajout des exercices=====================//
	// ===========================================================//
	/**
	 * permet d'ajouter un cours à la bdd
	 * 
	 * @Validated permet de declencher la validation de l'objet BindingResult
	 *            resultat va contenir le resultat du processus de la validation
	 * @return
	 */
	@RequestMapping(value = "/exercice/add/{idCours}", method = RequestMethod.POST)
	public String ajouterExerciceBdd(@ModelAttribute("exoform") ExerciceForm exoform,
			@PathVariable("idCours") Long idCours, ModelMap modele, BindingResult result,
			RedirectAttributes redirectAttributes) {
		System.out.println("dans méthode add");

		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur pCoursform

		exerciceFormValidator.validate(exoform, result);

		// 1.2 Test des erreurs
		if (result.hasErrors()) {
			System.out.println("erreurs");
			// redirection vers le formulaire d'ajout
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exoform", result);
			redirectAttributes.addFlashAttribute("exoform", exoform);

			return "redirect:/exercice/add-form/" +idCours;

		} else {
			Exercice exercice = exoform.getExercice();

			System.out.println("Cours lié à l'exo = " + exercice.getCours());

			Cours cours = coursDao.getById(exoform.getExercice().getCours().getIdCours());

			System.out.println("Cours du exerciceForm = " + exoform.getExercice().getCours());

			exercice.setCours(null);
			exerciceDao.ajouter(exercice);
			exercice = exerciceDao.listeExercice().get(exerciceDao.listeExercice().size() - 1);

			exercice.setCours(cours);

			System.out.println("Cours de l'exo = " + exercice.getCours());

			exerciceDao.modifier(exercice);

			System.out.println("Cours : " + cours);

			System.out.println("Exercice : " + exercice);

			System.out.println("\n\n Exercice Ajouté !");

			return "redirect:/exercice/listeExo/" + exercice.getCours().getIdCours();
		} // end else

	}// end ajouterExerciceBdd()

	// ===========================================================//
	// =========== affichage formulaire modif ====================//
	// ===========================================================//

	/**
	 * Permet de recuperer d'afficher le formulaire de modif d'un exo Invoquée au
	 * click du bouton "modifier" de la page affichage_liste_exercice.jsp.
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/exercice/update-form/{idExo}", method = RequestMethod.GET)
	public String formModifExoPourUnCours(@PathVariable("idExo") Long idExo, ModelMap modele) {

		if (!modele.containsAttribute("exoform")) {

			// 1. definition de l'objet à lier aux champs du formulaire de modification

			// 1.1 l'objet
			ExerciceForm exoform = new ExerciceForm();

			Exercice exercice = exerciceDao.getById(idExo);
			exercice.setCours(coursDao.getCoursByIdExercice(exercice.getIdExercice()));

			System.out.println("Cours lié à l'exo = " + exercice.getCours());

			exoform.setExercice(exercice);

			modele.addAttribute("exoform", exoform);
		} else {
			ExerciceForm exoForm = (ExerciceForm) modele.getAttribute("exoform");
			Cours cours = coursDao.getById(exoForm.getExercice().getCours().getIdCours());

			exoForm.getExercice().setCours(cours);
			modele.addAttribute("exoform", exoForm);

		} // end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue

		return "enseignant_modif_exercice";
	}// End formModifExoPourUnCours

	// ===========================================================//
	// =========== Modification des exercices=====================//
	// ===========================================================//

	@RequestMapping(value = "/exercice/update", method = RequestMethod.POST)
	public String modifierExercice(@ModelAttribute("exoform") ExerciceForm exoform, ModelMap modele,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur pCoursform

		exerciceFormValidator.validate(exoform, result);

		// 1.2 Test des erreurs
		if (result.hasErrors()) {
			System.out.println("erreurs");
			// redirection vers le formulaire d'ajout
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exoform", result);
			redirectAttributes.addFlashAttribute("exoform", exoform);

			return "redirect:/exercice/update-form/" + exoform.getExercice().getIdExercice();

		} else {
			Exercice exercice = exoform.getExercice();

			System.out.println("Cours lié à l'exo = " + exercice.getCours());

			Cours cours = coursDao.getById(exoform.getExercice().getCours().getIdCours());

			System.out.println("Cours du exerciceForm = " + exoform.getExercice().getCours());

			exercice.setCours(cours);

			System.out.println("Cours de l'exo = " + exercice.getCours());

			exerciceDao.modifier(exercice);

			System.out.println("Cours : " + cours);

			System.out.println("Exercice : " + exercice);

			System.out.println("\n\n Exercice Ajouté !");

			return "redirect:/exercice/listeExo/" + exoform.getExercice().getCours().getIdCours();
		}
	}// end modifierExercice
	

	// ===========================================================//
	// =========== Suppression des exercices=====================//
	// ===========================================================//

	@RequestMapping(value = "/exercice/delete/{exoID}", method = RequestMethod.GET)
	public String suppressionExerciceById(@PathVariable("exoID") Long pIdExo, ModelMap modele) {

		// 1. recup du cours
		Exercice exercice = exerciceDao.getById(pIdExo);
		exerciceDao.supprimer(exercice);

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_exercice", exercice);

		return "redirect:/exercice/listeExo/" + exercice.getCours().getIdCours();
	}// end suppressionExerciceById

}// end class
