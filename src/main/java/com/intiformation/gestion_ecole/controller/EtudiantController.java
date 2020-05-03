package com.intiformation.gestion_ecole.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intiformation.gestion_ecole.dao.AideDAOImpl;
import com.intiformation.gestion_ecole.dao.IAdresseDao;
import com.intiformation.gestion_ecole.dao.IAideDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IPersonneDao;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;
import com.intiformation.gestion_ecole.entityForForms.EnseignantForm;
import com.intiformation.gestion_ecole.entityForForms.EtudiantForm;
import com.intiformation.gestion_ecole.validator.EtudiantFormValidator;



/**
 * Controller pour la gestion des etudiants
 * @author IN-MP-018
 *
 */


@Controller
public class EtudiantController {

	// ========== DAO =========================//
	
	@Autowired
	private IEtudiantDAO etudiantDao;

	@Autowired
	private IAdresseDao adresseDao;
	
	@Autowired
	private IPromotionDAO promotionDao;
	
	@Autowired
	private EtudiantFormValidator etudiantFormValidator;
	
	@Autowired
	private IAideDAO aideDao;
	
	
	// ============ SETTER ====================//
	
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


	public void setEtudiantFormValidator(EtudiantFormValidator etudiantFormValidator) {
		this.etudiantFormValidator = etudiantFormValidator;
	}


	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}



	public void setAdresseDao(IAdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}
	
	
	// ========== METHODES ========================================//	


	// ===========================================================//
	// =========== Liste ALL Etudiants ===========================//
	// ===========================================================//
	/**
	 * Permet de recuperer la liste de tous les etudiants. Invoqué au click du
	 * bouton "Etudiants" de l'entête admin. Renvoie vers la page
	 * administrateur_ListeEtudiants.jsp
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
		
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_listeEtudiants"));
		return "administrateur_listeEtudiants";
	}//end recupListeAllEtudiant
	
	
	
	
	// ===========================================================//
	// =========== Liste Etudiants d'un enseignant ===============//
	// ===========================================================//
	
	/**
	 * Permet de recuperer la liste des etudiants de l'enseignant connecté. Invoqué
	 * au click du bouton "Mes etudiants" de l'entête enseignant. Renvoie vers la
	 * page enseignant_listeEnseignant.jsp
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/etudiants/listeByEnseignant", method = RequestMethod.GET)
	public String recupListeEtudiantsByEnseignant(ModelMap modele) {

		// 1. recup de l'enseignant connecté

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Enseignant enseignantConnecte = (Enseignant) etudiantDao.getPersonneByMail(auth.getName());

		// 2. recup de la liste des etudiants de l'enseignant
		List<Etudiant> listeEtudiants = etudiantDao.getlistEtudiantByIdEnseignant(enseignantConnecte.getIdentifiant());

		System.out.println("\n" + listeEtudiants + "\n");

		// 3. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_etudiants", listeEtudiants);

		modele.addAttribute("aide_contenu", aideDao.getAideByPage("enseignant_listeEtudiants"));
		return "enseignant_listeEtudiants";
	}// end recupListeAllEtudiant
	
	
	// ===========================================================//
	// =========== Affichage etudiant à partie d'un tableau ====//
	// ===========================================================//
	/**
	 * Permet d'afficher les caractéristiques d'un etudiant. Invoqué au click du
	 * bouton "Afficher" des pages enseignant_ListeEtudiants et
	 * administrateur_ListeEtudiantss.jsp. Renvoie vers la page
	 * affichage_etudiant.jsp
	 * 
	 * @param pIdEtudiant
	 *            : identifiant de l'etudiant que l'on veut afficher
	 * @return
	 */
	@RequestMapping(value="/etudiants/afficher/{etudiantID}", method=RequestMethod.GET)
	public String recupEtudiantById(@PathVariable("etudiantID") Long pIdEtudiant,ModelMap modele) {
		
		//1. recup de l'étudiant
		Etudiant etudiant = (Etudiant) etudiantDao.getById(pIdEtudiant);
		
		// 2. recup de l'adresse de l'etudiant
		Adresse adresse = adresseDao.getByPersonneId(etudiant.getIdentifiant());
		System.out.println(adresse);
		
		// 3. recup de la liste des promotions
		List<Promotion> listepromo=promotionDao.getListePromotionByIdEtudiant(etudiant.getIdentifiant());

		//4. def des données à afficher dans la vue
		modele.addAttribute("attribut_etudiant", etudiant);
		modele.addAttribute("attribut_adresse", adresse);
		modele.addAttribute("attribut_listePromo", listepromo);

		modele.addAttribute("aide_contenu", aideDao.getAideByPage("affichage_etudiant"));
		return "affichage_etudiant";
	}//end recupListeAllEtudiant
	
	
	// ===========================================================//
	// =========== Affichage etudiant connecté =================//
	// ===========================================================//
	/**
	 * Permet d'afficher les caractéristiques de l'etudiant connecté. Invoqué au
	 * click du bouton "Mon compte" de l'entête etudiant. Renvoie vers la page
	 * affichage_etudiant.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/etudiants/affiche", method = RequestMethod.GET)
	public String recupEtudiantConnecte(ModelMap modele) {

		// 1. recup de l'etudiant connecté

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Etudiant etudiantConnecte = (Etudiant) etudiantDao.getPersonneByMail(auth.getName());

		// 2. recup de l'adresse de l'etudiant
		Adresse adresse = adresseDao.getByPersonneId(etudiantConnecte.getIdentifiant());

		// 3. recup de la liste des promotions
		List<Promotion> listepromo=promotionDao.getListePromotionByIdEtudiant(etudiantConnecte.getIdentifiant());

		//4. def des données à afficher dans la vue
		modele.addAttribute("attribut_etudiant", etudiantConnecte);
		modele.addAttribute("attribut_adresse", adresse);
		modele.addAttribute("attribut_listePromo", listepromo);

		
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("affichage_etudiant"));
		return "affichage_etudiant";
	}// end recupEnseignantConnecte
	
	
	
	

	
	
	
	
	
	// ===========================================================//
	// =========== Affichage formulaire ajout etudiant ===========//
	// ===========================================================//
	/**
	 * Permet d'afficher le formulaire d'ajout d'un etudiant. Invoqué au click du
	 * bouton "Ajouter un etudiant" de la page administrateur_listeEtudiants.jsp.
	 * Renvoie vers la page administrateur_ajout_etudiant.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/etudiants/add-etudiant-form", method = RequestMethod.GET)
	public String afficherFormulaireAjout(ModelMap modele) {

		System.out.println("Je suis dans afficherFormulaireAjout ");

		if (!modele.containsAttribute("etudiantForm")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet
			EtudiantForm etudiantForm = new EtudiantForm();

			// 1.2 On recupere la liste des promos existantes

			etudiantForm.setListePromotionsExistantes(promotionDao.listePromotion());
			
			
			
			// 2. envoi de l'objet de commande à la servlet de spring mvc
			// > données à envoyer vers la servlet

			modele.addAttribute("etudiantForm", etudiantForm);
			
		} else {
			EtudiantForm etudiantForm = (EtudiantForm) modele.getAttribute("etudiantForm");
			// 1.2 On recupere la liste des promos existantes

			etudiantForm.setListePromotionsExistantes(promotionDao.listePromotion());

			modele.addAttribute("etudiantForm", etudiantForm);

		} // end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue
		
		
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_ajout_etudiant"));
		return "administrateur_ajout_etudiant";

	}// end afficherFormulaireAjout

	// ===========================================================//
	// =========== Ajout etudiant à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet d'ajouter un etudiant à la bdd <br/>
	 * Invoqué au click du bouton "Ajouter" de la page
	 * administrateur_ajout_etudiant.jsp. <br/>
	 * Renvoie vers la page administrateur_listeEtudiants.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/etudiants/add", method = RequestMethod.POST)
	public String ajouterEtudiantBdd(@ModelAttribute("etudiantForm") EtudiantForm etudiantForm, ModelMap modele,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur pEnseignantform
		etudiantFormValidator.validate(etudiantForm, result);

		// 1.2 Test des erreurs
		if (result.hasErrors()) {

			// ==> le validateur a detecté des erreurs
			// On redirige vers la page du formulaire administrateur_ajout_etudiant.jsp
			// addFlashAttribute : permet de renvoyer les erreurs et les informations déjà
			// entrées dans le formulaire

			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.etudiantForm", result);
			redirectAttributes.addFlashAttribute("etudiantForm", etudiantForm);

			return "redirect:/etudiants/add-etudiant-form";

		} else {
			// ==> le validateur n'a pas detecté d'erreur

			// ======= 2. Recup et traitement de l'etudiant===============//

			// On recupere l'etudiant à partie de l'objet etudiantForm
			Etudiant etudiant = etudiantForm.getEtudiant();

			// objet pour le cryptage
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// cryptage du mdp avec la methode encode
			String hashedMotDePasse = passwordEncoder.encode(etudiant.getMotdepasse());
			etudiant.setMotdepasse(hashedMotDePasse);

			// ======= 3. Recup et traitement de l'Adresse===================//

			// On recupere l'adresse à partie de l'objet EtudiantForm
			Adresse adresse = etudiantForm.getAdresse();

			// Test si les champs d'adresse sont vide
			if (adresse.getRue() != "" && adresse.getCodePostal() != "" && adresse.getVille() != "") {
				// => Ils ne sont pas vides

				// On ajoute l'adresse à l'etudiant et vice versa par la méthode 'add'
				etudiant.addAdresse(adresse);

				System.out.println("Adresse : " + adresse);
			} else {
				// => Ils sont vides
				System.out.println("adresse vide");
			}

			System.out.println("\n \n etudiant : " + etudiant);

			
			// ====== 4. Recup de la liste des promos===============//
			
			
			System.out.println("\n"+etudiantForm.getListeIdPromotions()[0]+"\n");
			
			//Recuperation de la liste des promos
			List<Promotion> listePromotion = new ArrayList<>();
			Promotion promotion;
			Long idPromotion;
			for(int i = 0; i<etudiantForm.getListeIdPromotions().length; i++) {
				idPromotion = Long.parseLong(etudiantForm.getListeIdPromotions()[i]);
				promotion = promotionDao.getById(idPromotion);
				listePromotion.add(promotion);
				
			}//end for
			
			etudiant.setListePromotion(listePromotion);
			
			// ====== 5. Ajout l'etudiant===============//

			// On ajoute l'etudiant à la bdd. Grace à la cascade l'adresse aussi est
			// ajoutée
			etudiantDao.ajouter(etudiant);

			System.out.println("\n\n Etudiant Ajouté !");

		

			// 6. edirection vers la page administrateur_listeEtudiants.jsp


			return "redirect:/etudiants/listeAll";
		} // end else

	}// end ajouterEtudiantBdd()
	
	
	
	// ===========================================================//
	// ======== Affichage formulaire de modif etudiant =========//
	// ===========================================================//
	/**
	 * Permet d'afficher le formulaire de modif d'un etudiant. <br/>
	 * Invoqué au click du bouton "Modifier" de la page affichage_etudiant.jsp.
	 * <br/>
	 * Renvoie vers la page administrateur_modif_etudiant.jsp. <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/etudiant/update-form/{idEtudiant}", method = RequestMethod.GET)
	public String afficherFormulaireModif(@PathVariable("idEtudiant") Long idEtudiant, ModelMap modele) {

		System.out.println("Je suis dans afficherFormulaireModif ");

		if (!modele.containsAttribute("etudiantForm")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet
			EtudiantForm etudiantForm = new EtudiantForm();

			// 1.2 Attribution de l'etudiant à modifier à l'attribut etudiant de
			// etudiantForm
			Etudiant etudiant = (Etudiant) etudiantDao.getById(idEtudiant);
			etudiantForm.setEtudiant(etudiant);

			// 1.3 Attribution de l'adresse de l'etudiant à modifier à l'attribut adresse
			// de l'etudiantForm

			etudiantForm.setAdresse(adresseDao.getByPersonneId(idEtudiant));

			// 1.4 On recupere la liste des promos existantes

			etudiantForm.setListePromotionsExistantes(promotionDao.listePromotion());

			
			//1.5 on recupere la liste des promos de l'etudiant
			
			List<Promotion> listePromoEtudiant = promotionDao.getListePromotionByIdEtudiant(idEtudiant);
			int nbPromo= listePromoEtudiant.size();
			String[] listeIdPromotions = new String[nbPromo];
			
			for(int i=0; i<nbPromo; i++) {
				listeIdPromotions[i] = String.valueOf(listePromoEtudiant.get(i).getIdPromotion());
			}//end for
			
			etudiantForm.setListeIdPromotions(listeIdPromotions);
			
			// 2. envoi de l'objet de commande à la servlet de spring mvc
			// > données à envoyer vers la servlet

			modele.addAttribute("etudiantForm", etudiantForm);

		} else {
			EtudiantForm etudiantForm = (EtudiantForm) modele.getAttribute("etudiantForm");
			// 1.2 On recupere la liste des promos  existantes pour affichage
			// dans des menus deroulants
			etudiantForm.setListePromotionsExistantes(promotionDao.listePromotion());
			modele.addAttribute("etudiantForm", etudiantForm);

		} // end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_modif_etudiant"));

		return "administrateur_modif_etudiant";

	}// end afficherFormulaireModif

	
	
	
	// ===========================================================//
	// =========== Modif etudiant à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet de modifier un etudiant de la bdd <br/>
	 * Invoqué au click du bouton "Modifer" de la page
	 * administrateur_modif_etudiant.jsp. <br/>
	 * Renvoie vers la page affichage_etudiant.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/etudiants/update", method = RequestMethod.POST)
	public String modifierEtudiantBdd(@ModelAttribute("etudiantForm") EtudiantForm etudiantForm, ModelMap modele,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur pEnseignantform
		etudiantFormValidator.validate(etudiantForm, result);

		// 1.2 Test des erreurs
		if (result.hasErrors()) {

			// ==> le validateur a detecté des erreurs
			// On redirige vers la page du formulaire administrateur_ajout_etudiant.jsp
			// addFlashAttribute : permet de renvoyer les erreurs et les informations déjà
			// entrées dans le formulaire

			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.etudiantForm", result);
			redirectAttributes.addFlashAttribute("etudiantForm", etudiantForm);

			return "redirect:/etudiants/update-form/"+etudiantForm.getEtudiant().getIdentifiant();

		} else {
			// ==> le validateur n'a pas detecté d'erreur

			// ======= 2. Recup et traitement de l'etudiant===============//

			// On recupere l'etudiant à partie de l'objet etudiantForm
			Etudiant etudiant = etudiantForm.getEtudiant();


			// ======= 3. Recup et traitement de l'Adresse===================//

			// On recupere l'adresse à partie de l'objet EtudiantForm
			Adresse adresse = etudiantForm.getAdresse();

			// Test si les champs d'adresse sont vide
			if (adresse.getRue() != "" && adresse.getCodePostal() != "" && adresse.getVille() != "") {
				// => Ils ne sont pas vides

				// On ajoute l'adresse à l'etudiant et vice versa par la méthode 'add'
				etudiant.addAdresse(adresse);

				System.out.println("Adresse : " + adresse);
			} else {
				// => Ils sont vides
				System.out.println("adresse vide");
			}

			System.out.println("\n \n etudiant : " + etudiant);

			
			// ====== 4. Recup de la liste des promos===============//
			
			
			System.out.println("\n"+etudiantForm.getListeIdPromotions()[0]+"\n");
			
			//Recuperation de la liste des promos
			List<Promotion> listePromotion = new ArrayList<>();
			Promotion promotion;
			Long idPromotion;
			for(int i = 0; i<etudiantForm.getListeIdPromotions().length; i++) {
				idPromotion = Long.parseLong(etudiantForm.getListeIdPromotions()[i]);
				promotion = promotionDao.getById(idPromotion);
				listePromotion.add(promotion);
				
			}//end for
			
			etudiant.setListePromotion(listePromotion);
			
			// ====== 5. Ajout l'etudiant===============//

			// On ajoute l'etudiant à la bdd. Grace à la cascade l'adresse aussi est
			// ajoutée
			etudiantDao.modifier(etudiant);

			System.out.println("\n\n Etudiant Modifier !");

		

			// 2. edirection vers la page administrateur_listeEtudiants.jsp

			return "redirect:/etudiants/afficher/" + etudiant.getIdentifiant();
		} // end else

	}// end ajouterEtudiantBdd()
	
	// ===========================================================//
	// =========== Delete etudiant à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet de supprimer un etudiant de la bdd <br/>
	 * Invoqué au click du bouton "Supprimer" de la page
	 * affichage_etudiant.jsp. <br/>
	 * Renvoie vers la page administrateur_listeEtudiants.jsp
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/etudiants/delete/{etudiantId}", method = RequestMethod.GET)
	public String supprimerEnseignant(@PathVariable("etudiantId") Long pIdEtudiant, ModelMap modele) {

		// ========== Recup des objets à supprimer ============

		// 1. recup de l'enseignant par son id
		Etudiant etudiant = (Etudiant) etudiantDao.getById(pIdEtudiant);

		// 2. Recup de son adresse
		Adresse adresse = adresseDao.getByPersonneId(pIdEtudiant);


		// ========== Suppression ===================

		// 1. suppression de l'adresse
		adresseDao.supprimer(adresse);


		// 2. suppression de l'enseignant
		etudiantDao.supprimer(etudiant);

		return "redirect:/etudiants/listeAll";

	}// end supprimerEnseignant
}//end class
