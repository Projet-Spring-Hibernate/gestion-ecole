package com.intiformation.gestion_ecole.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.intiformation.gestion_ecole.dao.IEtudiantCoursDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.EtudiantCours;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;
import com.intiformation.gestion_ecole.entityForForms.AbsenceForm;
import com.intiformation.gestion_ecole.entityForForms.EnseignantForm;

@Controller
public class AbsenceController {

	// ========== DAO =========================//
	@Autowired
	private IEtudiantCoursDAO etudiantcoursDAO;

	@Autowired
	private IEnseignantDAO enseignantDAO;

	@Autowired
	private ICoursDAO coursDao;

	@Autowired
	private IEtudiantDAO etudiantDao;

	@Autowired
	private IMatiereDAO matiereDao;

	@Autowired
	private IAideDAO aideDao;

	// ============ SETTER ====================//
	public void setEtudiantcoursDAO(IEtudiantCoursDAO etudiantcoursDAO) {
		this.etudiantcoursDAO = etudiantcoursDAO;
	}

	public void setCoursDao(ICoursDAO coursDao) {
		this.coursDao = coursDao;
	}

	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}

	public void setMatiereDao(IMatiereDAO matiereDao) {
		this.matiereDao = matiereDao;
	}

	public void setEnseignantDAO(IEnseignantDAO enseignantDAO) {
		this.enseignantDAO = enseignantDAO;
	}

	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}

	// ========== METHODES ========================================//

	// ===========================================================//
	// =========== Liste ALL absences =========================//
	// ===========================================================//

	/**
	 * Permet de recuperer la liste des etudiantsCours. Invoquée au click du bouton
	 * "absences" de l'entete admin. Renvoie vers la page
	 * administrateur_listeAbsence
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/etudiantCours/listeAll", method = RequestMethod.GET)
	public String recupListeAllAbsence(ModelMap modele) {

		// 1. recup de la liste de toutes les absences/presence de la bdd
		List<EtudiantCours> listepresenceEtabsence = etudiantcoursDAO.getAllEtudiantCours();

		// 2. Tris pour n'avoir que les absences
		List<EtudiantCours> listeabsence = new ArrayList<>();
		for (EtudiantCours presenceAbsence : listepresenceEtabsence) {
			if (presenceAbsence.isAbsence()) {
				listeabsence.add(presenceAbsence);
			} // end if
		} // end for

		// 3. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_absence", listeabsence);
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_listeAbsence"));

		return "administrateur_listeAbsence";
	}// end recupListeAll

	// ===========================================================//
	// =========== Liste absences d'un enseignant ===============//
	// ===========================================================//

	/**
	 * Permet de recuperer la liste des etudiantsCours d'un prof. Invoquée au click
	 * du bouton "absences" de l'entete enseignant. Renvoie vers la page
	 * enseignant_listeAbsence
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/etudiantCours/listeByEnseignant", method = RequestMethod.GET)
	public String recupListeAllabsencebyenseignant(ModelMap modele) {

		// 1. Recup de l'enseignant connecté
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Enseignant enseignant = (Enseignant) enseignantDAO.getPersonneByMail(auth.getName());

		// 2. Recup de la liste des matieres de l'enseignant et la liste des ids
		List<Matiere> listeMatiereDeEnseignant = matiereDao.listematiereEnseignantbyid(enseignant.getIdentifiant());

		List<Long> listeIdMatiere = new ArrayList<>();
		for (Matiere matiere : listeMatiereDeEnseignant) {
			listeIdMatiere.add(matiere.getIdMatiere());
		} // end for

		// 3. recup de la liste de toutes les absences/presence des promos de
		// l'enseignant
		List<EtudiantCours> listepresenceEtabsence = etudiantcoursDAO
				.getAllAbsenceByEnseignant(enseignant.getIdentifiant());

		// 4. Tris pour n'avoir que les absences + On ne garde que celles qui concernent
		// ses matieres
		List<EtudiantCours> listeabsence = new ArrayList<>();
		for (EtudiantCours presenceAbsence : listepresenceEtabsence) {
			Matiere matiere = coursDao.getMatiereByIdCours(presenceAbsence.getCours().getIdCours());
			if (presenceAbsence.isAbsence() && listeIdMatiere.contains(matiere.getIdMatiere())) {
				listeabsence.add(presenceAbsence);
			} // end if
		} // end for

		// 5. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_absence", listeabsence);

		modele.addAttribute("aide_contenu", aideDao.getAideByPage("enseignant_listeAbsence"));
		return "enseignant_listeAbsence";

	}// end recupListeAllabsencebyenseignant

	// ===========================================================//
	// =========== Liste absences d'un enseignant ===============//
	// ===========================================================//

	/**
	 * Permet de recuperer la liste des etudiantsCours d'un etudiant. Invoquée au
	 * click du bouton "absences" de l'entete etudiants. Renvoie vers la page
	 * etudiant_listeAbsence
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/etudiantCours/listeByEtudiant", method = RequestMethod.GET)
	public String recupListeAllabsencebyetudiant(ModelMap modele) {

		// 1. Recup de l'etudiant connecté
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Etudiant etudiant = (Etudiant) etudiantDao.getPersonneByMail(auth.getName());

		// 2. recup de la liste de toutes les absences/presence de l'etudiant
		List<EtudiantCours> listepresenceEtabsence = etudiantcoursDAO.getAbsenceByEtudiant(etudiant.getIdentifiant());

		// 3. Tris pour n'avoir que les absences + On ne garde que celles qui concernent
		// ses matieres
		List<EtudiantCours> listeabsence = new ArrayList<>();
		for (EtudiantCours presenceAbsence : listepresenceEtabsence) {
			if (presenceAbsence.isAbsence()) {
				listeabsence.add(presenceAbsence);
			} // end if
		} // end for

		// 4. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_absence", listeabsence);
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("etudiant_listeAbsence"));

		return "etudiant_listeAbsence";

	}// end recupListeAllabsencebyetudiant

	// ===========================================================//
	// =========== Liste absences d'un cours =====================//
	// ===========================================================//

	/**
	 * Permet de recuperer la liste des etudiantsCours d'un cours. Invoquée au click
	 * du bouton "Feuille de presence" de la page affichage_cours.jsp. Renvoie vers
	 * la page affichage_listeAbsence_cours.jsp
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/absences/afficher/{idCours}", method = RequestMethod.GET)
	public String affichagelisteEtudiantCoursPourUnCours(@PathVariable("idCours") Long idCours, ModelMap modele) {

		List<EtudiantCours> listeEtudiantCours = etudiantcoursDAO.getAbsenceByCours(idCours);

		Cours cours = coursDao.getById(idCours);

		modele.addAttribute("attribut_liste_absences", listeEtudiantCours);
		modele.addAttribute("attribut_cours", cours);
		
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("affichage_listeAbsence_Cours"));

		return "affichage_listeAbsence_Cours";
	}// End creationlisteEtudiantCoursPourUnCours

	// ===========================================================//
	// =========== affichage formulaire modif =====================//
	// ===========================================================//

	/**
	 * Permet de recuperer d'afficher le formulaire de modif d'une feuille de
	 * presence Invoquée au click du bouton "Ajouter/modifier" de la page
	 * affichage_absence.jsp.
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/absences/update-form/{idCours}", method = RequestMethod.GET)
	public String formModifFeuillePresencePourUnCours(@PathVariable("idCours") Long idCours, ModelMap modele) {

		// Recup du cours, de sa promo et de la liste des etudiants dans la promo
		Cours cours = coursDao.getById(idCours);
		Promotion promotion = coursDao.getPromotionByIdCours(idCours);
		List<Etudiant> listeEtudiant = etudiantDao.getlistEtudiantsByIdPromotion(promotion.getIdPromotion());

		// Recup des absences existantes déjà dans la dbb
		List<EtudiantCours> listeEtudiantCours = etudiantcoursDAO.getAbsenceByCours(idCours);

		// On ajoute les absences qui n'existent pas dans la bdd
		EtudiantCours absence;
		boolean existeDeja = false;

		for (Etudiant etudiant : listeEtudiant) {
			existeDeja = false;
			for (EtudiantCours absenceExistante : listeEtudiantCours) {
				if (absenceExistante.getEtudiant().getIdentifiant() == etudiant.getIdentifiant()) {
					existeDeja = true;
				} // end if
			} // end for

			if (existeDeja == false) {
				absence = new EtudiantCours(false, "");
				absence.setCours(cours);
				absence.setEtudiant(etudiant);
				etudiantcoursDAO.ajouter(absence);
			} // end if
		} // end for

		// On recupere la liste des absences
		List<EtudiantCours> listeAbsence = etudiantcoursDAO.getAbsenceByCours(idCours);

		// creation d'un objet AbsenceForm
		AbsenceForm absenceForm = new AbsenceForm();
		absenceForm.setListeEtudiantCours(listeAbsence);

		modele.addAttribute("absenceForm", absenceForm);
		modele.addAttribute("attribut_cours", cours);

		modele.addAttribute("aide_contenu", aideDao.getAideByPage("enseignant_ajout_modif_liste_absence"));

		return "enseignant_ajout_modif_liste_absence";
	}// End modifFeuillePresencePourUnCours

	// ===========================================================//
	// =========== Modification des absences=====================//
	// ===========================================================//

	/**
	 * Permet de recuperer de modif les absences à partir du formulaire de modif
	 * d'une feuille de presence
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/absences/update/{idCours}", method = RequestMethod.POST)
	public String modifFeuillePresencePourUnCours(@ModelAttribute("absenceForm") AbsenceForm absenceForm,
			@PathVariable("idCours") Long idCours, ModelMap modele, RedirectAttributes redirectAttributes) {

		List<EtudiantCours> listeAbsence = absenceForm.getListeEtudiantCours();

		Cours cours;
		Etudiant etudiant;
		for (EtudiantCours absence : listeAbsence) {

			cours = coursDao.getById(absence.getCours().getIdCours());
			etudiant = (Etudiant) etudiantDao.getById(absence.getEtudiant().getIdentifiant());

			absence.setEtudiant(etudiant);
			absence.setCours(cours);

			System.out.println(absence);
			etudiantcoursDAO.modifier(absence);
		} // end for
		redirectAttributes.addFlashAttribute("message", "Les modifications de la feuille de présence ont bien été pris en compte");
		redirectAttributes.addFlashAttribute("reussiteOperation", "true");
		return "redirect:/absences/afficher/" + idCours;
	}// end modifFeuillePresencePourUnCours
}// end class
