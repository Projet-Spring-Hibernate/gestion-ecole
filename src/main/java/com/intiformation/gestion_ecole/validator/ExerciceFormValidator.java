package com.intiformation.gestion_ecole.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestion_ecole.dao.IExerciceDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Exercice;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.entityForForms.ExerciceForm;

@Component
public class ExerciceFormValidator implements Validator{
	
	@Autowired
	private IExerciceDAO exerciceDao;
	
	

	public void setExerciceDao(IExerciceDAO exerciceDao) {
		this.exerciceDao = exerciceDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ExerciceForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object objetAValider, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "exercice.libelle", "required.libelle",
				"Le champ est obligatoire");
		
		/* _______________ Validation objet _______________ */
		ExerciceForm exoForm = (ExerciceForm) objetAValider;
//	
		List<String> listeExercicesExistant =  exerciceDao.getAllLibelle();
		if (listeExercicesExistant.contains(exoForm.getExercice().getLibelle())) {

			Exercice exoExistant = exerciceDao.getExerciceByLibelle(exoForm.getExercice().getLibelle());

			if (exoExistant.getIdExercice() != exoForm.getExercice().getIdExercice()) {
				errors.rejectValue("exercice.libelle", "notallowed.libelle", "Cet exercice existe déjà.");
			}
		} // End if
		
		
		
		
	}//end validate

}
