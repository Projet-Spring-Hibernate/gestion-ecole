package com.intiformation.gestion_ecole.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.entityForForms.EnseignantForm;
import com.intiformation.gestion_ecole.entityForForms.EtudiantForm;

/**
 * Validateur pour le formulaire d'ajout et de modif d'un etudiant
 * @author Laure
 *
 */
@Component //declare la classe comme validateur(bean spring)
public class EtudiantFormValidator  implements Validator{
	
	@Autowired
	//@Qualifier("etudiantDAOImpl")
	private IEtudiantDAO etudiantDao;
	
	/**
	 * Setter pour injection string
	 * @param personneDao
	 */
	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}


	/**
	 * permet de definir l'instance à valider 
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		
		return EtudiantForm.class.isAssignableFrom(clazz);
	}//end support



	/**
	 * Permet d'implementer la logique de validation
	 * @param objetAValider : objet à valider
	 * @param errors : pour la gestion des erreurs de validation
	 */
	@Override
	public void validate(Object objetAValider, Errors errors) {
		/*_______________ Validation des champs _______________*/
		/**
		 * rejectIfEmptyOrWhiteSpace() :
		 * 	-1er argument : si champ vide, creation d'une erreur de champ (errors) liée au champ
		 * 	-2e argument : le nom de la proprieté
		 * 	-3e argument :code d'erreur sui serat definit comme clé dans un bundle (.properties)
		 * 	-4e argument=message d'erreur par defaut
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "etudiant.nom", "required.nom", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "etudiant.prenom", "required.prenom", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "etudiant.email", "required.email", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "etudiant.motdepasse", "required.motdepasse", "Le champ est obligatoires");

		
		/*_______________ Validation objet _______________*/
		
		//1. recup de l'objet à valider
		EtudiantForm etudiantform = (EtudiantForm) objetAValider;
		
		//2. Validation de l'email : l'email doit être unique
		List<String> listeEmailExistants = etudiantDao.getAllMail();
		
		if(listeEmailExistants.contains(etudiantform.getEtudiant().getEmail())) {
			Etudiant etudiantExistant = (Etudiant) etudiantDao.getPersonneByMail(etudiantform.getEtudiant().getEmail());
			if(etudiantExistant.getIdentifiant()!=etudiantform.getEtudiant().getIdentifiant()) {
				//creation d'une erreur
				errors.rejectValue("etudiant.email", "notallowed.email", "Cet email appartient à une autre personne. Merci d'en choisir un autre.");
			}// end if
		}//end if
		
	}//End validate
}//end class
