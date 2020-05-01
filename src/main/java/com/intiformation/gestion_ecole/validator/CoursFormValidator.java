package com.intiformation.gestion_ecole.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.entityForForms.CoursForm;
import com.intiformation.gestion_ecole.entityForForms.EnseignantForm;

@Component
public class CoursFormValidator implements Validator{
	
	@Autowired
	@Qualifier("coursDAOImpl")
	private ICoursDAO coursDao;
	
	
	/**
	 * setter pour injection
	 * @param coursDao
	 */
	public void setCoursDao(ICoursDAO coursDao) {
		this.coursDao = coursDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Cours.class.isAssignableFrom(clazz);
	}//end support
	

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours.libelle", "required.libelle", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours.date", "required.date", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours.duree", "required.duree", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours.description", "required.description", "Le champ est obligatoires");

		
		/*_______________ Validation objet _______________*/
		
		//1. recup de l'objet à valider
		Cours cours = (Cours) objetAValider;
		
		
		
	}//End validate
	
	

}
