package com.intiformation.gestion_ecole.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.gestion_ecole.dao.IAdresseDao;
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
		modele.addAttribute("aide_contenu", "Aide pour la page affichage_etudiant");
		
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
		modele.addAttribute("aide_contenu", "Aide pour la page affichage_etudiant");
		
		return "affichage_enseignant";
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

			
			List<String>listeLibellePromoExistantes = new ArrayList<>();
			
			for(Promotion promo : promotionDao.listePromotion()) {
				listeLibellePromoExistantes.add(promo.getLibelle());
			};
			
			etudiantForm.setListeLibellePromoExistantes(listeLibellePromoExistantes);
			
			
			
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
		return "administrateur_ajout_etudiant";

	}// end afficherFormulaireAjout

	
}//end class
