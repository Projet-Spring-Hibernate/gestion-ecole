package com.intiformation.gestion_ecole.controller;

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

import com.intiformation.gestion_ecole.dao.IAideDAO;
import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IEnseignantMatierePromotionDao;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;
import com.intiformation.gestion_ecole.validator.MatiereFormValidator;



@Controller
public class MatiereController {

	@Autowired
	private IMatiereDAO matiereDAO;
	
	@Autowired
	private IEnseignantDAO enseignantDao;
	
	@Autowired
	private IEtudiantDAO etudiantDao;
	
	@Autowired
	private IEnseignantMatierePromotionDao enseignantmatierepromoda;
	
	@Autowired //injection du bean du validator dans employevalidateur 
	private MatiereFormValidator matiereValidator;
	
	@Autowired
	private IAideDAO aideDao;

	public void setMatiereDAO(IMatiereDAO matiereDAO) {
		this.matiereDAO = matiereDAO;
	}

	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}
	
	
	
	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}
	
	

	public void setEnseignantmatierepromoda(IEnseignantMatierePromotionDao enseignantmatierepromoda) {
		this.enseignantmatierepromoda = enseignantmatierepromoda;
	}
	
	

	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}

	//==================================================================//
	/**
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value="/matieres/listeAll", method=RequestMethod.GET)
	public String recupListeAllMatiere(ModelMap modele) {
		//1. recup de la liste de tous les etudiants de la bdd
		List<Matiere> listeMatiere = matiereDAO.listMatiere();
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_matiere", listeMatiere);
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("administrateur_listeMatieres"));
		return "administrateur_listeMatieres";
	}//end recupListeAllEtudiant
	
	
	
	
	
	@RequestMapping(value="/matieres/listeByEnseignant", method=RequestMethod.GET)
	public String recupListeAllMatiereByIDEnseignant(ModelMap modele) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Enseignant enseignant = (Enseignant) enseignantDao.getPersonneByMail(auth.getName());

		System.out.println("dans le controleur");

		List<Matiere> listeMatiere = matiereDAO.listematiereEnseignantbyid(enseignant.getIdentifiant());
		System.out.println("dans le controleur");
		modele.addAttribute("attribut_liste_matiere", listeMatiere);
		System.out.println(listeMatiere);
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("enseignant_listeMatieres"));
		return "enseignant_listeMatieres";
	}//end recupListeAllMatiereByID
	
	@RequestMapping(value="/matieres/listeByEtudiant", method=RequestMethod.GET)
	public String recupListeAllMatiereByIDEtudiant(ModelMap modele) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Etudiant etudiant = (Etudiant) etudiantDao.getPersonneByMail(auth.getName());

		System.out.println("dans le controleur");

		List<Matiere> listeMatiere = matiereDAO.listematiereEtudiantbyid(etudiant.getIdentifiant());
		System.out.println("dans le controleur");
		modele.addAttribute("attribut_liste_matiere", listeMatiere);
		System.out.println(listeMatiere);
		modele.addAttribute("aide_contenu", aideDao.getAideByPage("etudiant_listeMatieres"));
		return "etudiant_listeMatieres";
	}//end recupListeAllMatiereByID
	
	
	@RequestMapping(value="/matieres/afficher/{matiereID}", method=RequestMethod.GET)
	public String recupEtudiantById(@PathVariable("matiereID") Long pIdMatiere,ModelMap modele) {
		
		//1. recup de l'étudiant
		Matiere matiere = matiereDAO.getById(pIdMatiere);
		
		
		
		List<Enseignant> enseignant =  enseignantDao.getlistEnseignantByIdMatiere(pIdMatiere);
		//System.out.println(enseignant);
		
		//List<Matiere> listematiere=matiereDao.getListePromotionByIdEtudiant(etudiant.getIdentifiant());
		//System.out.println(listepromo);

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_matiere", matiere);
		modele.addAttribute("attribut_enseignant", enseignant);
		//modele.addAttribute("attribut_listePromo", listepromo);
		modele.addAttribute("aide_contenu", "Aide pour la page affichage_matiere");

		return "affichage_matiere";
	}//end recupListeAllEtudiant
	
	@RequestMapping(value="/matieres/add-form", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAjout() {
		
		
		//1.1 l'objet
		Matiere matiere = new Matiere();
		
		//1.2 nom de l'objet
		String objetCommandeMatiere = "matiereCommand";
		
		//2. envoi de l'objet de commande à la servlet de spring mvc 
		//> données à envoyer vers la servlet 
		Map<String, Object> data = new HashMap<>();
		data.put(objetCommandeMatiere, matiere);
		
		//2.1 def du nom logique de la vue
		String viewName="administrateur_ajout_matiere";
		
		//3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de la vue
		return new ModelAndView(viewName, data);
		
	}//end afficherFormulaireAjout
	
	@RequestMapping(value="/matieres/add", method=RequestMethod.POST)
	public String ajouterEmployeBdd(@ModelAttribute("matiereCommand") @Validated Matiere pMatiere, ModelMap modele, BindingResult result) {
		//application du validateur sur l'objet pEmploye
		matiereValidator.validate(pMatiere, result);
		
		//validation 
		if (result.hasErrors()) {
			/*_____le validator à detecter des erreurs_____*/
			
			//redirection vers la page du formulaore (ajouter-employe.jsp)
			return "administrateur_ajout_matiere";
		} else {

			/*_____le validator  n'à pas detecter des erreurs_____*/

			//ajout de l'employé 
			
			matiereDAO.ajouter(pMatiere);

			modele.addAttribute("attribut_liste_matiere", matiereDAO.listMatiere());
			
			
			return "redirect:/matieres/listeAll";
			
		}//end else
		
	}//end ajoter
	
	@RequestMapping(value= {"/matieres/delete/{matiereId}"}, method=RequestMethod.GET)
	public String supprimerMatierebdd(@PathVariable("matiereId") Long idMatiere, ModelMap modele) {
		
		//1.suppression de l'employe dans la bdd
		Matiere matiere = matiereDAO.getById(idMatiere);
		//List<EnseignantMatierePromotion> listeenseignant =  enseignantmatierepromoda.getListeEnseignantMatierePromotionByMatiere(idMatiere);
	
			matiereDAO.supprimer(matiere);
		
				
		
		//2. récup de la nouvelle liste des employés dans la bdd
		List<Matiere> listeMatiereBdd = matiereDAO.listMatiere();
		
		//3.données à afficher de la vue
		modele.addAttribute("attribut_liste_matiere", listeMatiereBdd);
		
		//4. redirection vers la page liste-employes.jsp
		return "redirect:/matieres/listeAll";
	}//end supprimer
	
	/**
	 * permet d'afficher le formulaire modif de l'employe
	 * invoqué avec une requete http GET ()
	 * avec passa du param 'idemploye'
	 * @return
	 */
	@RequestMapping(value="/matieres/update-form",method=RequestMethod.GET)
	public ModelAndView afficherFormulaireUpdate(@RequestParam("idMatiere") Long id) {
		System.out.println("dans le get");
		//1. récup de l'employé à modifier dans la bdd 
		
		Matiere matiereModif = matiereDAO.getById(id);
		System.out.println(matiereModif);
		//2. déf du modele de données (objet de commande = employeModif) + déf du nom logique de la vue
		// => ajout dans un objet ModelAndView
		
		return new ModelAndView("administrateur_modif_matiere","matiereModifCommand",matiereModif);
		
	}//end afficherformulaireupdate()
	
	/**
	 * permet de modifier un employé dans la BDD
	 * invoquée à la soumission du formulaire de modifier-employe.jsp
	 * @return
	 */
	@RequestMapping(value="/matieres/update", method=RequestMethod.POST)
	public String modifierEmployeBdd(@ModelAttribute("matiereModifCommand") Matiere id, ModelMap modele) {
		
		//1. modif de l'employé dans la bdd
		matiereDAO.modifier(id);
		
System.out.println(id);
		//2. récup la nouvelle liste des employés + envoi de la liste vers la servlet de spring mvc
		modele.addAttribute("attribut_liste_matiere", matiereDAO.listMatiere());
		
		//4. redirection vers la page liste-employes.jsp
		return "redirect:/matieres/listeAll";
		
	}//end modifierEmployeBdd
	
	
	
}
	
	
	
	

