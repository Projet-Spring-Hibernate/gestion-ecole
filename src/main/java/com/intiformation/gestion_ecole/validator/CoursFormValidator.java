package com.intiformation.gestion_ecole.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.entityForForms.CoursForm;

@Component
public class CoursFormValidator implements Validator{
	
	@Autowired
	private ICoursDAO coursDao;
	
	
	

	public void setCoursDao(ICoursDAO coursDao) {
		this.coursDao = coursDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CoursForm.class.isAssignableFrom(clazz);
	}//end support

	@Override
	public void validate(Object objetAValider, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours.libelle", "required.libelle", "Le champ est obligatoire");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours.date", "required.date", "Le champ est obligatoire");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours.duree", "required.duree", "Le champ est obligatoire");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours.description", "required.description", "Le champ est obligatoire");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matiere.libelle", "required.libelle", "Le champ est obligatoire");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "promotion.libelle", "required.libelle", "Le champ est obligatoire");
		

		/*_______________ Validation objet _______________*/
		
		//1. recup de l'objet à valider
		CoursForm coursForm =  (CoursForm) objetAValider;
		
		List<String> listeCoursExistant = coursDao.getAllLibelle();
		if (listeCoursExistant.contains(coursForm.getCours().getLibelle())) {
			errors.rejectValue("cours.libelle", "notallowed.libelle", "Ce cours existe déjà.");
		}
		
		
		
	}

}
