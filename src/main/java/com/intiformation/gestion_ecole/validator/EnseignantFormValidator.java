package com.intiformation.gestion_ecole.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.intiformation.gestion_ecole.dao.IPersonneDao;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.entityForForms.EnseignantForm;


/**
 * Validateur pour le formulaire d'ajout et de modif d'un l'enseignant
 * @author Laure
 *
 */
@Component //declare la classe comme validateur(bean spring)
public class EnseignantFormValidator implements Validator {

	@Autowired
	@Qualifier("personneDaoImpl")
	private IPersonneDao personneDao;
	
	/**
	 * Setter pour injection string
	 * @param personneDao
	 */
	public void setPersonneDao(IPersonneDao personneDao) {
		this.personneDao = personneDao;
	}


	/**
	 * permet de definir l'instance à valider 
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		
		return EnseignantForm.class.isAssignableFrom(clazz);
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enseignant.nom", "required.nom", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enseignant.prenom", "required.prenom", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enseignant.email", "required.email", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enseignant.motdepasse", "required.motdepasse", "Le champ est obligatoires");

		
		/*_______________ Validation objet _______________*/
		
		//1. recup de l'objet à valider
		EnseignantForm enseignantform = (EnseignantForm) objetAValider;
		
		//2. Validation de l'email : l'email doit être unique
		List<String> listeEmailExistants = personneDao.getAllMail();
		
		if(listeEmailExistants.contains(enseignantform.getEnseignant().getEmail())) {
			Enseignant enseignantExistant = (Enseignant) personneDao.getPersonneByMail(enseignantform.getEnseignant().getEmail());
			if(enseignantExistant.getIdentifiant()!=enseignantform.getEnseignant().getIdentifiant()) {
				//creation d'une erreur
				errors.rejectValue("enseignant.email", "notallowed.email", "Cet email appartient à une autre personne. Merci d'en choisir un autre.");
			}// end if
		}//end if
		
	}//End validate
	
}//end class
