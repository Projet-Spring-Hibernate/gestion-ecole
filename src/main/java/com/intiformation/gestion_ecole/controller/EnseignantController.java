package com.intiformation.gestion_ecole.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Promotion;
import com.intiformation.springmvc.entity.Employe;

/**
 * Controller pour la gestion des enseignants
 * @author IN-MP-018
 *
 */


@Controller
public class EnseignantController {

	@Autowired
	private IEnseignantDAO enseignantDao;
	
	@Autowired
	private IPromotionDAO promotionDao;
	/**
	 * Setter pour injection spring
	 * @param etudiantDao
	 */
	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}
	
	
	public void setPromotionDao(IPromotionDAO promotionDao) {
		this.promotionDao = promotionDao;
	}

	//==================================================================//


	/**
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/enseignants/listeAll", method=RequestMethod.GET)
	public String recupListeAllEtudiant(ModelMap modele) {
		//1. recup de la liste de tous les etudiants de la bdd
		List<Enseignant> listeEnseignants = enseignantDao.listEnseignant();
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_enseignants", listeEnseignants);
		
		return "administrateur_listeEnseignants";
	}//end recupListeAllEtudiant
	
	//==================================================================//
	
	/**
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/enseignants/afficher/{enseignantID}", method=RequestMethod.GET)
	public String recupEtudiantById(@PathVariable("enseignantID") Long pIdEnseignant,ModelMap modele) {
		
		//1. recup de l'étudiant
		Enseignant enseignant = (Enseignant) enseignantDao.getById(pIdEnseignant);
		
		
		
//		Adresse adresse = adresseDao.getByPersonneId(etudiant.getIdentifiant());
//		System.out.println(adresse);
//		
		List<Promotion> listepromo=promotionDao.getListePromotionByIdEnseignant(enseignant.getIdentifiant());
		System.out.println(listepromo);

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_enseignant", enseignant);
//		modele.addAttribute("attribut_adresse", adresse);
		modele.addAttribute("attribut_listePromo", listepromo);
//		modele.addAttribute("aide_contenu", "Aide pour la page affichage_etudiant");
		
		return "affichage_enseignant";
	}//end recupListeAllEtudiant
	
	
	//==================================================================//
	
	/**
	 * Permet d'afficher le formulaire d'ajout d'un enseignant 
	 * @return
	 */
	@RequestMapping(value="/enseignants/add-enseignant-form", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAjout() {
		
		//1. definition de l'objet de commande à lier aux champs du formulaire d'ajout
		
		//1.1 l'objet
		Enseignant enseignant = new Enseignant();
		
		//1.2 nom de l'objet
		String objetCommandeEnseignant = "enseignantCommand";
		
		//2. envoi de l'objet de commande à la servlet de spring mvc 
		//> données à envoyer vers la servlet 
		Map<String, Object> data = new HashMap<>();
		data.put(objetCommandeEnseignant, enseignant);
		
		//2.1 def du nom logique de la vue
		String viewName="administrateur_ajout_enseignant";
		
		//3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de la vue
		return new ModelAndView(viewName, data);
		
	}//end afficherFormulaireAjout
	
	
	//==================================================================//
	
	/**
	 * permet d'ajouter un enseignant à la bdd
	 * @Validated permet de declencher la validation de l'objet
	 * BindingResult resultat va contenir le resultat du processus de la validation
	 * @return
	 */
	@RequestMapping(value="/enseignants/add", method=RequestMethod.POST)
	public String ajouterEmployeBdd(@ModelAttribute("enseignantCommand") Enseignant pEnseignant, ModelMap modele, BindingResult result) {
		
		
		//Application du validateur sur pEmploye
		//employeValidateur.validate(pEmploye, result);
		
		//if (result.hasErrors()) {
			//le validateur a detecter des erreurs
			// On redirige vers la page du formulaire ajouter-employe.jsp
			//return "ajouter-employe";
		//}else {
			//le validator n'a pas detecté d'erreur
			
			//1. ajout de l'employe à la bdd
			enseignantDao.ajouter(entity);;
			
			//2. recup de la nouvelle liste des employes + redirection vers la page liste-employes.jsp
			
			modele.addAttribute("attribut_liste_employes", employeService.listAllEmploye());
			
			return "redirect:/employes/liste";
		//}//end else

	}//end ajouterEmployeBdd()
}//end class
