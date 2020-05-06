package com.intiformation.gestion_ecole.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intiformation.gestion_ecole.dao.IAdministrateurDao;
import com.intiformation.gestion_ecole.dao.IAdresseDao;
import com.intiformation.gestion_ecole.dao.IAideDAO;
import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Promotion;
import com.intiformation.gestion_ecole.entityForForms.AdministrateurForm;
import com.intiformation.gestion_ecole.entityForForms.EtudiantForm;
import com.intiformation.gestion_ecole.validator.AdministrateurFromValidator;


@Controller
public class AdministrateurController {

	@Autowired
	private IAdministrateurDao administrateurtDAO;
	
	@Autowired
	private IAideDAO aideDao;
	
	@Autowired
	private IAdresseDao adresseDao;
	
	@Autowired
	private AdministrateurFromValidator adminvalidator;
	
	
	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}

	public void setAdministrateurtDAO(IAdministrateurDao administrateurtDAO) {
		this.administrateurtDAO = administrateurtDAO;
	}
	
	
	
	
	// ========== METHODES ========================================//	


	public void setAdresseDao(IAdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}

	public void setAdminvalidator(AdministrateurFromValidator adminvalidator) {
		this.adminvalidator = adminvalidator;
	}

	// ===========================================================//
	// =========== Liste ALL Administrateurs ===========================//
	// ===========================================================//
	/**
	 * Permet de recuperer la liste de tous les administrateurs. 
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/administrateurs/listeAll", method=RequestMethod.GET)
	public String recupListeAllEtudiant(ModelMap modele) {
		//1. recup de la liste de tous les etudiants de la bdd
		List<Administrateur> listeAdministrateur = administrateurtDAO.getAllAdministrateur();
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_administrateur", listeAdministrateur);
		
		//modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_listeAdministrateur"));
		return "administrateur_listeAdministrateur";
	}//end recupListeAllEtudiant
	
	
	
	
	
	// ===========================================================//
	// =========== Affichage Administrateurs à partir d'un tableau ====//
	// ===========================================================//
	/**
	 * Permet d'afficher les caractéristiques d'un administrateur
	 * 
	 * @param pIdEtudiant
	 *            : identifiant de l'etudiant que l'on veut afficher
	 * @return
	 */
	@RequestMapping(value="/administrateurs/afficher/{administrateurID}", method=RequestMethod.GET)
	public String recupEtudiantById(@PathVariable("administrateurID") Long pId,ModelMap modele) {
		
		//1. recup des admins
		Administrateur admin = (Administrateur) administrateurtDAO.getById(pId);
		Adresse adresse = adresseDao.getByPersonneId(admin.getIdentifiant());


		//4. def des données à afficher dans la vue
		modele.addAttribute("attribut_administrateur", admin);
		modele.addAttribute("attribut_adresse", adresse);



		//modele.addAttribute("aide_contenu", aideDao.getAideByPage("affichage_administrateur"));
		return "affichage_administrateur";
	}//end recupListeAll
	
	
	
	
	
	// ===========================================================//
	// =========== Affichage formulaire ajout admin ===========//
	// ===========================================================//
	/**
	 * Permet d'afficher le formulaire d'ajout d'un admin
	 * 
	 * @return
	 */

	@RequestMapping(value = "/administrateurs/add-administrateur-form", method = RequestMethod.GET)
	public String afficherFormulaireAjout(ModelMap modele) {

		System.out.println("Je suis dans afficherFormulaireAjout ");

		if (!modele.containsAttribute("administrateurForm")) {

			// 1. definition de l'objet à lier aux champs du formulaire d'ajout

			// 1.1 l'objet
			AdministrateurForm adminForm = new AdministrateurForm();

		
			
			// 2. envoi de l'objet de commande à la servlet de spring mvc
			// > données à envoyer vers la servlet

			modele.addAttribute("administrateurForm", adminForm);
			
		} else {
			AdministrateurForm adminForm =  (AdministrateurForm) modele.getAttribute("administrateurForm");
		
			

			modele.addAttribute("administrateurForm", adminForm);

		} // end else

		// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
		// la vue
		
		
		//modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_ajout_administrateur"));
		return "administrateur_ajout_administrateur";

	}// end afficherFormulaireAjout

	// ===========================================================//
	// =========== Ajout admin à la bdd =====================//
	// ===========================================================//
	/**
	 * Permet d'ajouter un administrateur à la bdd <br/>
	 * 
	 * @return
	 */

	@RequestMapping(value = "/administrateurs/add", method = RequestMethod.POST)
	public String ajouterEtudiantBdd(@ModelAttribute("administrateurForm") AdministrateurForm admin, ModelMap modele,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// =================== 1. Validateur ========================//

		// 1.1 Application du validateur sur pEnseignantform
		adminvalidator.validate(admin, result);

		// 1.2 Test des erreurs
		if (result.hasErrors()) {

			// ==> le validateur a detecté des erreurs
			// On redirige vers la page du formulaire administrateur_ajout_etudiant.jsp
			// addFlashAttribute : permet de renvoyer les erreurs et les informations déjà
			// entrées dans le formulaire

			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.administrateurForm", result);
			redirectAttributes.addFlashAttribute("administrateurForm", admin);

			return "redirect:/administrateurs/add-administrateur-form";

		} else {
			// ==> le validateur n'a pas detecté d'erreur

			// ======= 2. Recup et traitement des admins===============//

			// On recupere l'admin à partie de l'objet etudiantForm
			
			Administrateur adminf = admin.getAdministrateur();

			// objet pour le cryptage
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// cryptage du mdp avec la methode encode
			String hashedMotDePasse = passwordEncoder.encode(adminf.getMotdepasse());
			adminf.setMotdepasse(hashedMotDePasse);

			// ======= 3. Recup et traitement de l'Adresse===================//

			// On recupere l'adresse à partie de l'objet EtudiantForm
			Adresse adresse = admin.getAdresse();

				adminf.addAdresse(adresse);

			
			// ====== 4. Ajout de l'admin===============//

		
			
			administrateurtDAO.ajouter(adminf);

			System.out.println("\n\n Administrateur Ajouté !");


			return "redirect:/administrateurs/listeAll";
		} // end else

	}// end ajouterAdministrateurBdd()
	
	// ===========================================================//
		// ======== Affichage formulaire de modif etudiant =========//
		// ===========================================================//
		/**
		 * Permet d'afficher le formulaire de modif d'un administrateur. <br/>
	
		 * @return
		 */
		@RequestMapping(value = "/administrateurs/update-form/{idAdministrateur}", method = RequestMethod.GET)
		public String afficherFormulaireModif(@PathVariable("idAdministrateur") Long id, ModelMap modele) {


			if (!modele.containsAttribute("administrateurForm")) {

				// 1. definition de l'objet à lier aux champs du formulaire d'ajout

				// 1.1 l'objet
				AdministrateurForm adminForm = new AdministrateurForm();

				// 1.2 Attribution de l'admin à modifier
				Administrateur admin = (Administrateur) administrateurtDAO.getById(id);
				adminForm.setAdministrateur(admin);
			

				// 1.3 Attribution de l'adresse de l'admin à modifier
				adminForm.setAdresse(adresseDao.getByPersonneId(id));

				
				
				// 2. envoi de l'objet de commande à la servlet de spring mvc
				// > données à envoyer vers la servlet

				modele.addAttribute("administrateurForm", adminForm);

			}

			// 3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de
			// la vue
		//	modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_modif_administrateur"));

			return "administrateur_modif_administrateur";

		}// end afficherFormulaireModif

		
		
		
		// ===========================================================//
		// =========== Modif etudiant à la bdd =====================//
		// ===========================================================//
		/**
		 * Permet de modifier un administrateur de la bdd <br/>

		 * 
		 * @return
		 */

		@RequestMapping(value = "/administrateurs/update", method = RequestMethod.POST)
		public String modifierEtudiantBdd(@ModelAttribute("administrateurForm") AdministrateurForm adminForm, ModelMap modele,
				BindingResult result, RedirectAttributes redirectAttributes) {

			// =================== 1. Validateur ========================//

			// 1.1 Application du validateur sur pEnseignantform
			adminvalidator.validate(adminForm, result);

			// 1.2 Test des erreurs
			if (result.hasErrors()) {

				// ==> le validateur a detecté des erreurs
				// On redirige vers la page du formulaire administrateur_ajout_administrateur.jsp
				// addFlashAttribute : permet de renvoyer les erreurs et les informations déjà
				// entrées dans le formulaire

				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.administrateurForm", result);
				redirectAttributes.addFlashAttribute("administrateurForm", adminForm);

				return "redirect:/administrateurs/update-form/"+adminForm.getAdministrateur().getIdentifiant();

			} else {
				// ==> le validateur n'a pas detecté d'erreur

				// ======= 2. Recup et traitement de l'etudiant===============//

				// On recupere l'admin à partie de l'objet adminForm
				Administrateur adminf = adminForm.getAdministrateur();

				// ======= 3. Recup et traitement de l'Adresse===================//

				// On recupere l'adresse à partie de l'objet EtudiantForm
				Adresse adresse = adminForm.getAdresse();
					// On ajoute l'adresse à l'administrateur et vice versa par la méthode 'add'
					adminf.addAdresse(adresse);

			
					
				System.out.println("\n \n administrateur : " + adminf);

		
				
				// ====== 5. Ajout l'admin===============//

				// On ajoute l'admin à la bdd. Grace à la cascade l'adresse aussi est
				// ajoutée
				administrateurtDAO.modifier(adminf);
				adresseDao.modifier(adresse);

				System.out.println("\n\n Administrateur Modifier !");

			

				// 2. edirection vers la page administrateur_listeEtudiants.jsp

				return "redirect:/administrateurs/afficher/" + adminf.getIdentifiant();
			} // end else

		}// end modifierBdd()
	
	
	// ===========================================================//
	// =========== Delete admin à la bdd =========================//
	// ===========================================================//
	/**
	 * Permet de supprimer un administrateur de la bdd <br/>
	
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/administrateurs/delete/{administrateurId}", method = RequestMethod.GET)
	public String supprimerEnseignant(@PathVariable("administrateurId") Long pId, ModelMap modele) {

		// ========== Recup des objets à supprimer ============

		// 1. recup de l'admin par son id
		
		Administrateur admin = (Administrateur) administrateurtDAO.getById(pId);

	

		// ========== Suppression ===================

		// 1. suppression de l'admin
		administrateurtDAO.supprimer(admin);

	

		return "redirect:/administrateurs/listeAll";

	}// end supprimerEnseignant
	
	
}//end class
