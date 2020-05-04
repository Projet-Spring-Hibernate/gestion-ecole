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

import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantCoursDAO;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.EtudiantCours;
import com.intiformation.gestion_ecole.domain.Matiere;


@Controller
public class AbsenceController {

	@Autowired
	private IEtudiantCoursDAO etudiantcoursDAO;

	@Autowired
	private IEnseignantDAO enseignantDAO;
	
	
	public void setEtudiantcoursDAO(IEtudiantCoursDAO etudiantcoursDAO) {
		this.etudiantcoursDAO = etudiantcoursDAO;
	}
	
	




	public void setEnseignantDAO(IEnseignantDAO enseignantDAO) {
		this.enseignantDAO = enseignantDAO;
	}






	@RequestMapping(value="/etudiantCours/listeAll", method=RequestMethod.GET)
	public String recupListeAllAbsence(ModelMap modele) {
		//1. recup de la liste de tous les etudiants de la bdd
		System.out.println("dans le getall");
		List<EtudiantCours> listeabsence = etudiantcoursDAO.getAllEtudiantCours();
		
		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_liste_absence", listeabsence);

		
		return "administrateur_listeAbsence";
	}//end recupListeAll
	
	
	@RequestMapping(value="/etudiantCours/listeByEnseignant", method=RequestMethod.GET)
	public String recupListeAllabsencebyenseignant(ModelMap modele) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Enseignant enseignant = (Enseignant) enseignantDAO.getPersonneByMail(auth.getName());

		System.out.println("dans le controleur");

	//	List<EtudiantCours> listetudiant = etudiantcoursDAO.getAllAbsenceByEnseignant(enseignant.getIdentifiant());
		System.out.println("dans le controleur");
		//modele.addAttribute("attribut_liste_absence", listetudiant);
	//	System.out.println(listetudiant);
		return "enseignant_listeAbsence";
		
}//end recupListeAllabsencebyenseignant
	
	@RequestMapping(value="/etudiantCours/afficher/{etudiantCoursID}", method=RequestMethod.GET)
	public String recupEtudiantById(@PathVariable("etudiantCoursID") Long pId,ModelMap modele) {
		
		//1. recup de l'étudiant
		EtudiantCours etudiantcours = etudiantcoursDAO.getById(pId);

		
		
		//List<Enseignant> enseignant =  enseignantDao.getlistEnseignantByIdMatiere(pIdMatiere);
		//System.out.println(enseignant);
		
		//List<Matiere> listematiere=matiereDao.getListePromotionByIdEtudiant(etudiant.getIdentifiant());
		//System.out.println(listepromo);

		//2. def des données à afficher dans la vue
		modele.addAttribute("attribut_matiere", etudiantcours);
		//modele.addAttribute("attribut_enseignant", enseignant);
		//modele.addAttribute("attribut_listePromo", listepromo);
		//modele.addAttribute("aide_contenu", "Aide pour la page affichage_etudiant");
		
		return "affichage_absence";
	}//end recupListeAllEtudiant
	
	@RequestMapping(value="/etudiantCours/add-form", method=RequestMethod.GET)
	public ModelAndView afficherFormulaireAjout() {
		
		
		//1.1 l'objet
		EtudiantCours etudint = new EtudiantCours();
		
		//1.2 nom de l'objet
		String objetCommandeMatiere = "etudiantCoursCommand";
		
		//2. envoi de l'objet de commande à la servlet de spring mvc 
		//> données à envoyer vers la servlet 
		Map<String, Object> data = new HashMap<>();
		data.put(objetCommandeMatiere, etudint);
		
		//2.1 def du nom logique de la vue
		String viewName="administrateur_ajout_etudiantCours";
		
		//3. envoi de l'objet ModelAndView à la servlet contenant l'objet et le nom de la vue
		return new ModelAndView(viewName, data);
		
	}//end afficherFormulaireAjout
	
	@RequestMapping(value="/etudiantCours/add", method=RequestMethod.POST)
	public String ajouterEmployeBdd(@ModelAttribute("etudiantCoursCommand") @Validated EtudiantCours pId, ModelMap modele, BindingResult result) {
		//application du validateur sur l'objet pEmploye
	//	matiereValidator.validate(pMatiere, result);
		
		//validation 
		if (result.hasErrors()) {
			/*_____le validator à detecter des erreurs_____*/
			
			//redirection vers la page du formulaore (ajouter-employe.jsp)
			return "administrateur_ajout_matiere";
		} else {

			/*_____le validator  n'à pas detecter des erreurs_____*/

			//ajout de l'employé 
			
		//	matiereDAO.ajouter(pMatiere);

		//	modele.addAttribute("attribut_liste_matiere", matiereDAO.listMatiere());
			
			return "redirect:/matieres/listeAll";
			
		}//end else
		
	}//end ajoter
	
	@RequestMapping(value= {"/etudiantCours/delete/{etudiantCoursId}"}, method=RequestMethod.GET)
	public String supprimerMatierebdd(@PathVariable("etudiantCoursId") Long id, ModelMap modele) {
		
		//1.suppression de l'employe dans la bdd
		EtudiantCours absence = etudiantcoursDAO.getById(id);
		//List<EnseignantMatierePromotion> listeenseignant =  enseignantmatierepromoda.getListeEnseignantMatierePromotionByMatiere(idMatiere);
	
			etudiantcoursDAO.supprimer(absence);
		
				
		//	List<EtudiantCours> listetudiant = etudiantcoursDAO.getAllAbsenceByEnseignant(enseignant.getIdentifiant());
			//modele.addAttribute("attribut_liste_absence", listetudiant);
			//System.out.println(listetudiant);
			return "enseignant_listeAbsence";
	}//end supprimer
	
	/**
	 * permet d'afficher le formulaire modif de l'employe
	 * invoqué avec une requete http GET ()
	 * avec passa du param 'idemploye'
	 * @return
	 */
	//@RequestMapping(value="/etudiantCours/update-form",method=RequestMethod.GET)
	//public ModelAndView afficherFormulaireUpdate(@RequestParam("idMatiere") Long id) {
		
	//	Matiere matiereModif = matiereDAO.getById(id);
		//System.out.println(matiereModif);
		//2. déf du modele de données (objet de commande = employeModif) + déf du nom logique de la vue
		// => ajout dans un objet ModelAndView
		
		//return new ModelAndView("administrateur_modif_matiere","matiereModifCommand",matiereModif);
		
//	}//end afficherformulaireupdate()
	
//	/**
//	 * permet de modifier un employé dans la BDD
//	 * invoquée à la soumission du formulaire de modifier-employe.jsp
//	 * @return
//	 */
//	@RequestMapping(value="/matieres/update", method=RequestMethod.POST)
//	public String modifierEmployeBdd(@ModelAttribute("matiereModifCommand") Matiere id, ModelMap modele) {
//		
//		//1. modif de l'employé dans la bdd
//	//	matiereDAO.modifier(id);
//		
//System.out.println(id);
//		//2. récup la nouvelle liste des employés + envoi de la liste vers la servlet de spring mvc
//		//modele.addAttribute("attribut_liste_matiere", matiereDAO.listMatiere());
//		
//		//4. redirection vers la page liste-employes.jsp
//		return "redirect:/matieres/listeAll";
//		
//	}//end modifierEmployeBdd
	
	
	
	
	
}
