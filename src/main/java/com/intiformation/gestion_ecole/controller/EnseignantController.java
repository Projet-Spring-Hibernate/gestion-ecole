package com.intiformation.gestion_ecole.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intiformation.gestion_ecole.dao.IAdresseDao;
import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IEnseignantMatierePromotionDao;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;
import com.intiformation.gestion_ecole.entityForForms.EnseignantForm;
import com.intiformation.gestion_ecole.validator.EnseignantFormValidator;

/**
 * Controller pour la gestion des enseignants
 * 
 * @author Laure
 *
 */
@Controller
public class EnseignantController {

	// ========== DAO =========================//
	@Autowired
	private IEnseignantDAO enseignantDao;

	@Autowired
	private IPromotionDAO promotionDao;

	@Autowired
	private IMatiereDAO matiereDao;

	@Autowired
	private IEnseignantMatierePromotionDao enseignantMatierePromotionDao;

	@Autowired
	private IAdresseDao adresseDao;

	@Autowired
	private IEtudiantDAO etudiantDao;

	@Autowired
	private EnseignantFormValidator enseignantFormValidator;

	// ============ SETTER ====================//
	/**
	 * Setter pour injection spring
	 * 
	 * @param etudiantDao
	 */
	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	public void setMatiereDao(IMatiereDAO matiereDao) {
		this.matiereDao = matiereDao;
	}

	public void setEnseignantMatierePromotionDao(IEnseignantMatierePromotionDao enseignantMatierePromotionDao) {
		this.enseignantMatierePromotionDao = enseignantMatierePromotionDao;
	}

	public void setEnseignantFormValidator(EnseignantFormValidator enseignantFormValidator) {
		this.enseignantFormValidator = enseignantFormValidator;
	}

	public void setAdresseDao(IAdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}

	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}

	public void setPromotionDao(IPromotionDAO promotionDao) {
		this.promotionDao = promotionDao;
	}

	// ========== METHODES ========================================//

	// ===========================================================//
	// =========== Liste ALL Enseignants =========================//
	// ===========================================================//
	/**
	 * Permet de recuperer la liste de tous les enseignants. Invoqué au click du
	 * bouton "Enseignants" de l'entête admin. Renvoie vers la page
	 * administrateur_ListeEnseignants.jsp
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/enseignants/listeAll", method = RequestMethod.GET)
	public String recupListeAllEnseignant(ModelMap modele) {
		// 1. recup de la liste de tous les enseignants de la bdd
		List<Enseignant> listeEnseignants = enseignantDao.listEnseignant();

		// 2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_enseignants", listeEnseignants);

		return "administrateur_listeEnseignants";
	}// end recupListeAllEtudiant

	
	
	// ===========================================================//
	// =========== Liste Enseignants d'un etudiant ===============//
	// ===========================================================//
	/**
	 * Permet de recuperer la liste des enseignants de l'étudiant connecté. Invoqué
	 * au click du bouton "Mes enseignants" de l'entête etudiant. Renvoie vers la
	 * page etudiant_ListeEnseignants.jsp
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/enseignants/listeByEtudiant", method = RequestMethod.GET)
	public String recupListeEnseignantByEtudiant(ModelMap modele) {

		// 1. recup de l'etudiant connecté

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Etudiant etudiantConnecte = (Etudiant) etudiantDao.getPersonneByMail(auth.getName());

		// 2. recup de la liste des enseignants de l'etudiants
		List<Enseignant> listeEnseignants = enseignantDao.getlistEnseignantByIdEtudiant(etudiantConnecte.getIdentifiant());

		System.out.println("\n" + listeEnseignants + "\n");

		// 3. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_enseignants", listeEnseignants);

		return "etudiant_listeEnseignants";
	}// end recupListeAllEtudiant

	
	
	
	
	// ===========================================================//
	// =========== Affichage enseignant à partie d'un tableau ====//
	// ===========================================================//
	/**
	 * Permet d'afficher les caractéristiques d'un enseingnant. Invoqué au click du
	 * bouton "Afficher" des pages etudiant_ListeEnseignants.jsp et
	 * administrateur_ListeEnseignants.jsp. Renvoie vers la page
	 * affichage_enseignant.jsp
	 * 
	 * @param pIdEnseignant
	 *            : identifiant de l'enseignant que l'on veut afficher
	 * @return
	 */

	@RequestMapping(value = "/enseignants/afficher/{enseignantID}", method = RequestMethod.GET)
	public String recupEnseignantById(@PathVariable("enseignantID") Long pIdEnseignant, ModelMap modele) {

		// 1. recup de l'enseignant
		Enseignant enseignant = (Enseignant) enseignantDao.getById(pIdEnseignant);

		// 2. recup de l'adresse de l'enseignant
		Adresse adresse = adresseDao.getByPersonneId(enseignant.getIdentifiant());
		System.out.println(adresse);

		// 3. recup de la liste des combinaison EnseignantMatierePromotion
		List<EnseignantMatierePromotion> listeCombinaisons = enseignantMatierePromotionDao
				.getListeEnseignantMatierePromotionByEnseignant(pIdEnseignant);

		// 4. def des données à afficher dans la vue
		modele.addAttribute("attribut_enseignant", enseignant);
		modele.addAttribute("attribut_adresse", adresse);
		modele.addAttribute("attribut_combinaison", listeCombinaisons);
		// modele.addAttribute("aide_contenu", "Aide pour la page affichage_etudiant");

		return "affichage_enseignant";
	}// end recupEnseignantById

	
	
	
	
	
	// ===========================================================//
	// =========== Affichage enseignant connecté =================//
	// ===========================================================//
	/**
	 * Permet d'afficher les caractéristiques de l'enseignant connecté. Invoqué au
	 * click du bouton "Mon compte" de l'entête enseignant. Renvoie vers la page
	 * affichage_enseignant.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/enseignants/affiche", method = RequestMethod.GET)
	public String recupEnseignantConnecte(ModelMap modele) {

		// 1. recup de l'enseignant connecté

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Enseignant enseignantConnecte = (Enseignant) enseignantDao.getPersonneByMail(auth.getName());

		// 2. recup de l'adresse de l'enseignant
		Adresse adresse = adresseDao.getByPersonneId(enseignantConnecte.getIdentifiant());

		// 3. recup de la liste des combinaison EnseignantMatierePromotion
		List<EnseignantMatierePromotion> listeCombinaisons = enseignantMatierePromotionDao
				.getListeEnseignantMatierePromotionByEnseignant(enseignantConnecte.getIdentifiant());

		// 4. def des données à afficher dans la vue
		modele.addAttribute("attribut_enseignant", enseignantConnecte);
		modele.addAttribute("attribut_adresse", adresse);
		modele.addAttribute("attribut_combinaison", listeCombinaisons);
		// modele.addAttribute("aide_contenu", "Aide pour la page affichage_etudiant");

		return "affichage_enseignant";
	}// end recupEnseignantConnecte

	
	
	
	
	
	
	// ===========================================================//
	// =========== Affichage formulaire ajout enseignant =========//
	// ===========================================================//
	/**
	 * Permet d'afficher le formulaire d'ajout d'un enseignant. Invoqué au click du
	 * bouton "Ajouter un enseignant" de la page administrateur_listeEnseignant.jsp.
	 * Renvoie vers la page administrateur_ajout_enseignant.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/enseignants/add-enseignant-form", method = RequestMethod.GET)
	public String afficherFormulaireAjout(ModelMap modele) {

		System.out.println("Je suis dans afficherFormulaireAjout ");

		if (!modele.containsAttribute("enseignantform")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet
			EnseignantForm enseignantform = new EnseignantForm();

			// 1.2 On recupere la liste des promos et des matières existantes pour affichage
			// dans des menus deroulants
			enseignantform.setListeMatieresExistantes(matiereDao.listMatiere());
			enseignantform.setListePromotionsExistantes(promotionDao.listePromotion());

			// 1.3 creation d'une liste avec 5 elements vides
			List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = new ArrayList<>();
			Enseignant enseignantVide = new Enseignant();
			Matiere matiereVide = new Matiere();
			Promotion promotionVide = new Promotion();
			EnseignantMatierePromotion enseignantMatierePromoVide = new EnseignantMatierePromotion();
			enseignantMatierePromoVide.setEnseignant(enseignantVide);
			enseignantMatierePromoVide.setMatiere(matiereVide);
			enseignantMatierePromoVide.setPromotion(promotionVide);
			listeEnseignantMatierePromotion.add(enseignantMatierePromoVide);
			listeEnseignantMatierePromotion.add(enseignantMatierePromoVide);
			listeEnseignantMatierePromotion.add(enseignantMatierePromoVide);
			listeEnseignantMatierePromotion.add(enseignantMatierePromoVide);
			listeEnseignantMatierePromotion.add(enseignantMatierePromoVide);

			enseignantform.setListeEnseignantMatierePromotion(listeEnseignantMatierePromotion);

			System.out.println("Liste promo existantes : " + enseignantform.getListePromotionsExistantes());
			System.out.println("Liste matieres existantes : " + enseignantform.getListeMatieresExistantes());
			System.out.println("Liste vide : " + enseignantform.getListeEnseignantMatierePromotion().size());

			// 2. envoi de l'objet de commande à la servlet de spring mvc
			// > données à envoyer vers la servlet

			modele.addAttribute("enseignantform", enseignantform);
		} else {
			EnseignantForm enseignantForm = (EnseignantForm) modele.getAttribute("enseignantform");
			// 1.2 On recupere la liste des promos et des matières existantes pour affichage
			// dans des menus deroulants
			enseignantForm.setListeMatieresExistantes(matiereDao.listMatiere());
			enseignantForm.setListePromotionsExistantes(promotionDao.listePromotion());
			modele.addAttribute("enseignantform", enseignantForm);

		} // end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue
		return "administrateur_ajout_enseignant";

	}// end afficherFormulaireAjout

	
	
	
	
	// ===========================================================//
	// =========== Ajout enseignant à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet d'ajouter un enseignant à la bdd <br/>
	 * Invoqué au click du bouton "Ajouter" de la page
	 * administrateur_ajout_enseignant.jsp. <br/>
	 * Renvoie vers la page administrateur_listeEnseignants.jsp
	 * 
	 * @return
	 */

	@RequestMapping(value = "/enseignants/add", method = RequestMethod.POST)
	public String ajouterEnseignantBdd(@ModelAttribute("enseignantform") EnseignantForm enseignantform, ModelMap modele,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur pEnseignantform
		enseignantFormValidator.validate(enseignantform, result);

		// 1.2 Test des erreurs
		if (result.hasErrors()) {

			// ==> le validateur a detecté des erreurs
			// On redirige vers la page du formulaire administrateur_ajout_enseignant.jsp
			// addFlashAttribute : permet de renvoyer les erreurs et les informations déjà
			// entrer dans le formulaire

			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.enseignantform", result);
			redirectAttributes.addFlashAttribute("enseignantform", enseignantform);

			return "redirect:/enseignants/add-enseignant-form";

		} else {
			// ==> le validateur n'a pas detecté d'erreur

			// ======= 2. Recup et traitement de l'Enseignant===============//

			// On recupere l'enseignants à partie de l'objet EnseignantFrom
			Enseignant enseignant = enseignantform.getEnseignant();

			// objet pour le cryptage
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// cryptage du mdp avec la methode encode
			String hashedMotDePasse = passwordEncoder.encode(enseignant.getMotdepasse());
			enseignant.setMotdepasse(hashedMotDePasse);

			// ======= 3. Recup et traitement de l'Adresse===================//

			// On recupere l'adresse à partie de l'objet EnseignantFrom
			Adresse adresse = enseignantform.getAdresse();

			// Test si les champs d'adresse sont vide
			if (adresse.getRue() != "" && adresse.getCodePostal() != "" && adresse.getVille() != "") {
				// => Ils ne sont pas vides

				// On ajoute l'adresse à l'enseignant et vice versa par la méthode 'add'
				enseignant.addAdresse(adresse);

				System.out.println("Adresse : " + adresse);
			} else {
				// => Ils sont vides
				System.out.println("adresse vide");
			}

			System.out.println("Enseignant : " + enseignant);

			// ====== 4. Ajout et recup de l'enseignant===============//

			// On ajoute l'enseignant à la bdd. Grace à la cascade l'adresse aussi est
			// ajoutée
			enseignantDao.ajouter(enseignant);

			System.out.println("\n\n Enseignant Ajouté !");

			// On recupere à nouveau l'enseignant par son mail (unique) de la bdd pour avoir
			// son id
			enseignant = (Enseignant) enseignantDao.getPersonneByMail(enseignant.getEmail());

			// ======== 5. Recup et traitement des combinaisons===================//

			// On récupere la liste des combinaison EnseignantMatierePromotion à partir des
			// champs du formulaire
			// => à ce stade, les seules infos de chaque objet sont les identifiants. les
			// autres champs sont vides.

			List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = enseignantform
					.getListeEnseignantMatierePromotion();

			// ----------- Suppression des doublons ---------------//

			// Création d'une liste vide dans laquelle on va transferrer les objets distinct
			List<EnseignantMatierePromotion> listeEnseignantMatierePromotionDISTINCT = new ArrayList<>();

			// On parcourt la liste des combinaisons
			for (EnseignantMatierePromotion combinaison : listeEnseignantMatierePromotion) {

				// Test si les id sont de promotion et matiere sont differents de 0 (0=valeur
				// par defaut)
				if (combinaison.getPromotion().getIdPromotion() != 0 && combinaison.getMatiere().getIdMatiere() != 0) {
					// => Ils sont different de 0

					// Creation d'un compteur
					int i = 0;

					// On parcourt la liste distinct
					for (EnseignantMatierePromotion combinaisonDistinct : listeEnseignantMatierePromotionDISTINCT) {

						// test si la combinaison est déjà presente dans la liste distinct
						if (combinaisonDistinct.getPromotion().getIdPromotion() == combinaison.getPromotion()
								.getIdPromotion()
								&& combinaisonDistinct.getMatiere().getIdMatiere() == combinaison.getMatiere()
										.getIdMatiere()) {
							// => elle est déjà presente => On incremente le compteur
							i++;
						} // end if
					} // end for

					// Test si le compteur==0 cad si la combinaison n'est pas déjà presente dans la
					// liste distinct
					if (i == 0) {
						// => Elle n'est pas déjà presente => on l'ajoute
						listeEnseignantMatierePromotionDISTINCT.add(combinaison);
					} // end if
				} // end if

			} // end for

			System.out.println(
					"\n\n listeEnseignantMatierePromotionDISTINCT " + listeEnseignantMatierePromotionDISTINCT.size());

			// ----- Recup des infos des matieres et promotions+ajout-------------------//

			// On parcourt la liste des combinaisons
			Promotion promotion;
			Matiere matiere;
			EnseignantMatierePromotion enseignantMatierePromotion = new EnseignantMatierePromotion();
			List<EnseignantMatierePromotion> newlisteEnseignantMatierePromotion = new ArrayList<>();

			for (EnseignantMatierePromotion combinaison : listeEnseignantMatierePromotionDISTINCT) {

				// pour chaque combianaison, on recupere l'objet promo et matiere de la base de
				// donnée s pour avoir toutes leurs infos
				promotion = promotionDao.getById(combinaison.getPromotion().getIdPromotion());
				matiere = matiereDao.getById(combinaison.getMatiere().getIdMatiere());

				// On recupere aussi leurs listes de combinaison (remplace le fetchtype.EAGER)
				promotion.setListeEnseignantMatierePromotion(enseignantMatierePromotionDao
						.getListeEnseignantMatierePromotionByMatiere(matiere.getIdMatiere()));
				matiere.setListeEnseignantMatierePromotion(enseignantMatierePromotionDao
						.getListeEnseignantMatierePromotionByPromotion(promotion.getIdPromotion()));
				enseignant.setListeEnseignantMatierePromotion(enseignantMatierePromotionDao
						.getListeEnseignantMatierePromotionByEnseignant(enseignant.getIdentifiant()));

				// On crée un objet combiaison avec toutes les infos collectées

				// ajoute la combinaison vide
				enseignantMatierePromotion = new EnseignantMatierePromotion();
				enseignantMatierePromotionDao.ajouter(enseignantMatierePromotion);

				// on le recupere avec son id
				List<EnseignantMatierePromotion> listeCombinaisonExistante = enseignantMatierePromotionDao
						.getAllEnseignantMatierePromotion();
				Long idDernierAjout = listeCombinaisonExistante.get((listeCombinaisonExistante.size() - 1)).getId();
				System.out.println("ID dernier ajout :" + idDernierAjout);
				enseignantMatierePromotion = listeCombinaisonExistante.get((listeCombinaisonExistante.size() - 1));

				enseignantMatierePromotion.addMatiere(matiere);
				enseignantMatierePromotion.addPromotion(promotion);
				enseignantMatierePromotion.addEnseignant(enseignant);

				enseignantMatierePromotionDao.modifier(enseignantMatierePromotion);

				// on l'ajoute à une nouvelle liste
				newlisteEnseignantMatierePromotion.add(enseignantMatierePromotion);

			} // end for

			// 2. recup de la nouvelle liste des enseignants + redirection vers la page
			// administrateur_listeEnseignats.jsp

			return "redirect:/enseignants/listeAll";
		} // end else

	}// end ajouterEmployeBdd()

	

	// ===========================================================//
	// ======== Affichage formulaire de modif enseignant =========//
	// ===========================================================//
	/**
	 * Permet d'afficher le formulaire de modif d'un enseignant. <br/>
	 * Invoqué au click du bouton "Modifier" de la page affichage_enseignant.jsp.
	 * <br/>
	 * Renvoie vers la page administrateur_modif_enseignant.jsp. <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enseignants/update-form/{idEnseignant}", method = RequestMethod.GET)
	public String afficherFormulaireModif(@PathVariable("idEnseignant") Long idEnseignant, ModelMap modele) {

		System.out.println("Je suis dans afficherFormulaireModif ");

		if (!modele.containsAttribute("enseignantform")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet
			EnseignantForm enseignantform = new EnseignantForm();

			// 1.2 Attribution de l'enseignant à modifier à l'attribut enseignant de
			// l'enseignantForm
			Enseignant enseignant = (Enseignant) enseignantDao.getById(idEnseignant);
			enseignantform.setEnseignant(enseignant);

			// 1.3 Attribution de l'adresse de l'enseignant à modifier à l'attribut adresse
			// de l'enseignantForm

			enseignantform.setAdresse(adresseDao.getByPersonneId(idEnseignant));

			// 1.4 Recup de la liste des EnseignantMatierePromotion
			List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = enseignantMatierePromotionDao
					.getListeEnseignantMatierePromotionByEnseignant(idEnseignant);

			Matiere matiereVide = new Matiere();
			matiereVide.setLibelle("-- Choisir --");
			matiereVide.setIdMatiere(0L);
			Promotion promotionVide = new Promotion();
			promotionVide.setLibelle("-- Choisir --");
			promotionVide.setIdPromotion(0L);
			EnseignantMatierePromotion enseignantMatierePromoVide = new EnseignantMatierePromotion();
			enseignantMatierePromoVide.setEnseignant(enseignant);
			enseignantMatierePromoVide.setMatiere(matiereVide);
			enseignantMatierePromoVide.setPromotion(promotionVide);
			while (listeEnseignantMatierePromotion.size() < 5) {
				listeEnseignantMatierePromotion.add(enseignantMatierePromoVide);
			} // end while

			enseignantform.setListeEnseignantMatierePromotion(listeEnseignantMatierePromotion);

			// 1.5 On recupere la liste des promos et des matières existantes pour affichage
			// dans des menus deroulants
			enseignantform.setListeMatieresExistantes(matiereDao.listMatiere());
			enseignantform.setListePromotionsExistantes(promotionDao.listePromotion());

			// 2. envoi de l'objet de commande à la servlet de spring mvc
			// > données à envoyer vers la servlet
			// Map<String, Object> data = new HashMap<>();
			// data.put("enseignantCommand", enseignantform);
			modele.addAttribute("enseignantform", enseignantform);

		} else {
			EnseignantForm enseignantForm = (EnseignantForm) modele.getAttribute("enseignantform");
			// 1.2 On recupere la liste des promos et des matières existantes pour affichage
			// dans des menus deroulants
			enseignantForm.setListeMatieresExistantes(matiereDao.listMatiere());
			enseignantForm.setListePromotionsExistantes(promotionDao.listePromotion());
			modele.addAttribute("enseignantform", enseignantForm);

		} // end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue
		return "administrateur_modif_enseignant";

	}// end afficherFormulaireModif

	
	
	
	// ===========================================================//
	// =========== Modif enseignant à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet de modifier un enseignant de la bdd <br/>
	 * Invoqué au click du bouton "Modifer" de la page
	 * administrateur_modif_enseignant.jsp. <br/>
	 * Renvoie vers la page affichage_enseignant.jsp
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enseignants/update", method = RequestMethod.POST)
	public String modifierEnseignantBdd(@ModelAttribute("enseignantform") EnseignantForm enseignantform,
			ModelMap modele, BindingResult result, RedirectAttributes redirectAttributes) {

		// =================== 1. Validateur =======================//

		// 1.1 Application du validateur sur pEnseignantform
		enseignantFormValidator.validate(enseignantform, result);

		// 1.2 Test des erreurs
		if (result.hasErrors()) {
			System.out.println("\nLe validateur a detecter des erreurs\n");
			System.out.println(enseignantform.getEnseignant().getIdentifiant());
			System.out.println(enseignantform.getEnseignant().getNom());
			// ==> le validateur a detecté des erreurs
			// On redirige vers la page du formulaire administrateur_ajout_enseignant.jsp

			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.enseignantform", result);
			redirectAttributes.addFlashAttribute("enseignantform", enseignantform);

			return "redirect:/enseignants/update-form/" + enseignantform.getEnseignant().getIdentifiant();
			// return "administrateur_ajout_enseignant";
		} else {
			// ==> le validateur n'a pas detecté d'erreur

			// ========= 2. Recup et traitement de l'Enseignant=================//

			// On recupere l'enseignants à partie de l'objet EnseignantFrom
			Enseignant enseignant = enseignantform.getEnseignant();


			// ============= 3. Recup et traitement de l'Adresse===============//

			// On recupere l'adresse à partie de l'objet EnseignantFrom
			Adresse adresse = enseignantform.getAdresse();

			// Test si les champs d'adresse sont vide
			if (adresse.getRue() != "" && adresse.getCodePostal() != "" && adresse.getVille() != "") {
				// => Ils ne sont pas vides

				// On ajoute l'adresse à l'enseignant et vice versa par la méthode 'add'
				enseignant.addAdresse(adresse);

				System.out.println("Adresse : " + adresse);
			} else {
				// => Ils sont vides
				System.out.println("adresse vide");
			}

			System.out.println("Enseignant : " + enseignant);

			// ========== 4. Ajout et recup de l'enseignant===================//

			// On ajoute l'enseignant à la bdd. Grace à la cascade l'adresse aussi est
			// ajoutée
			enseignantDao.modifier(enseignant);

			System.out.println("\n\n Enseignant Ajouté !");

			// On recupere à nouveau l'enseignant par son mail (unique) de la bdd pour avoir
			// son id
			enseignant = (Enseignant) enseignantDao.getPersonneByMail(enseignant.getEmail());

			// ====== 5. Recup et traitement des combinaisons=================//

			// On récupere la liste des combinaison EnseignantMatierePromotion à partir des
			// champs du formulaire
			// => à ce stade, les seules infos de chaque objet sont les identifiants. les
			// autres champs sont vides.

			List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = enseignantform
					.getListeEnseignantMatierePromotion();

			// ----------- Suppression des doublons ---------------//

			// Création d'une liste vide dans laquelle on va transferrer les objets distinct
			List<EnseignantMatierePromotion> listeEnseignantMatierePromotionDISTINCT = new ArrayList<>();

			// On parcourt la liste des combinaisons
			for (EnseignantMatierePromotion combinaison : listeEnseignantMatierePromotion) {

				// Test si les id sont de promotion et matiere sont differents de 0 (0=valeur
				// par defaut)
				if (combinaison.getPromotion().getIdPromotion() != 0 && combinaison.getMatiere().getIdMatiere() != 0) {
					// => Ils sont different de 0

					// Creation d'un compteur
					int i = 0;

					// On parcourt la liste distinct
					for (EnseignantMatierePromotion combinaisonDistinct : listeEnseignantMatierePromotionDISTINCT) {

						// test si la combinaison est déjà presente dans la liste distinct
						if (combinaisonDistinct.getPromotion().getIdPromotion() == combinaison.getPromotion()
								.getIdPromotion()
								&& combinaisonDistinct.getMatiere().getIdMatiere() == combinaison.getMatiere()
										.getIdMatiere()) {
							// => elle est déjà presente => On incremente le compteur
							i++;
						} // end if
					} // end for

					// Test si le compteur==0 cad si la combinaison n'est pas déjà presente dans la
					// liste distinct
					if (i == 0) {
						// => Elle n'est pas déjà presente => on l'ajoute
						listeEnseignantMatierePromotionDISTINCT.add(combinaison);
					} // end if
				} // end if

			} // end for

			System.out.println(
					"\n\n listeEnseignantMatierePromotionDISTINCT " + listeEnseignantMatierePromotionDISTINCT.size());

			// ------ Recup des infos des matieres et promotion+modif-------------------//

			// On parcourt la liste des combinaisons
			Promotion promotion;
			Matiere matiere;
			EnseignantMatierePromotion enseignantMatierePromotion = new EnseignantMatierePromotion();
			List<EnseignantMatierePromotion> newlisteEnseignantMatierePromotion = new ArrayList<>();

			for (EnseignantMatierePromotion combinaison : listeEnseignantMatierePromotionDISTINCT) {

				// pour chaque combianaison, on recupere l'objet promo et matiere de la base de
				// donnée s pour avoir toutes leurs infos
				promotion = promotionDao.getById(combinaison.getPromotion().getIdPromotion());
				matiere = matiereDao.getById(combinaison.getMatiere().getIdMatiere());

				// On recupere aussi leurs listes de combinaison (remplace le fetchtype.EAGER)
				promotion.setListeEnseignantMatierePromotion(enseignantMatierePromotionDao
						.getListeEnseignantMatierePromotionByMatiere(matiere.getIdMatiere()));
				matiere.setListeEnseignantMatierePromotion(enseignantMatierePromotionDao
						.getListeEnseignantMatierePromotionByPromotion(promotion.getIdPromotion()));
				enseignant.setListeEnseignantMatierePromotion(enseignantMatierePromotionDao
						.getListeEnseignantMatierePromotionByEnseignant(enseignant.getIdentifiant()));

				// On crée un objet combiaison avec toutes les infos collectées

				// ajoute la combinaison vide
				enseignantMatierePromotion = new EnseignantMatierePromotion();
				enseignantMatierePromotionDao.ajouter(enseignantMatierePromotion);

				// on le recupere avec son id
				List<EnseignantMatierePromotion> listeCombinaisonExistante = enseignantMatierePromotionDao
						.getAllEnseignantMatierePromotion();
				Long idDernierAjout = listeCombinaisonExistante.get((listeCombinaisonExistante.size() - 1)).getId();
				System.out.println("ID dernier ajout :" + idDernierAjout);
				enseignantMatierePromotion = listeCombinaisonExistante.get((listeCombinaisonExistante.size() - 1));

				enseignantMatierePromotion.addMatiere(matiere);
				enseignantMatierePromotion.addPromotion(promotion);
				enseignantMatierePromotion.addEnseignant(enseignant);

				enseignantMatierePromotionDao.modifier(enseignantMatierePromotion);

				// on l'ajoute à une nouvelle liste
				newlisteEnseignantMatierePromotion.add(enseignantMatierePromotion);

			} // end for


			// 2. recup de la nouvelle liste des enseignants + redirection vers la page
			// administrateur_listeEnseignats.jsp


			return "redirect:/enseignants/afficher/" + enseignant.getIdentifiant();
		} // end else

	}// end ajouterEmployeBdd()

	
	
	
	
	
	// ===========================================================//
	// =========== Delete enseignant à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet de supprimer un enseignant de la bdd <br/>
	 * Invoqué au click du bouton "Supprimer" de la page
	 * affichage_enseignant.jsp. <br/>
	 * Renvoie vers la page administrateur_listeEnseignant.jsp
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/enseignants/delete/{enseignantId}", method = RequestMethod.GET)
	public String supprimerEnseignant(@PathVariable("enseignantId") Long pIdEnseignant, ModelMap modele) {

		// ========== Recup des objets à supprimer ============

		// 1. recup de l'enseignant par son id
		Enseignant enseignant = (Enseignant) enseignantDao.getById(pIdEnseignant);

		// 2. Recup de son adresse
		Adresse adresse = adresseDao.getByPersonneId(pIdEnseignant);

		// 3. recup de la liste de ses combinaison EnseignantMatierePromotion
		List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = enseignantMatierePromotionDao
				.getListeEnseignantMatierePromotionByEnseignant(pIdEnseignant);

		// ========== Suppression ===================

		// 1. suppression de l'adresse
		adresseDao.supprimer(adresse);

		// 2. suppression des combinaison EnseignantMatierePromotion
		for (EnseignantMatierePromotion combinaison : listeEnseignantMatierePromotion) {
			enseignantMatierePromotionDao.supprimer(combinaison);
		} // End for

		// 3. suppression de l'enseignant
		enseignantDao.supprimer(enseignant);

		return "redirect:/enseignants/listeAll";

	}// end supprimerEnseignant
	
	
	
}// end class
