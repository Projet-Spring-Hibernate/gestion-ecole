package com.intiformation.gestion_ecole.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.domain.Matiere;

@Component
public class MatiereFormValidator implements Validator  {

	

	@Autowired
	private IMatiereDAO matiereDAO;
	
	
	
	
	public void setMatiereDAO(IMatiereDAO matiereDAO) {
		this.matiereDAO = matiereDAO;
	}
	
	

	@Override
	public boolean supports(Class<?> clazz) {
	return Matiere.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object objetAValider, Errors errors) {
		
		//1.validation du champ libelle
		/**
		 * rejectIfEmptyOrWhitespace() :
		 * 			1er argument :  si champ vide, création d'une erreur de champ (errors) liée au champ
		 * 			2 eme argument : le nom de la propriété
		 * 			3 eme argument : code d'erreur qui sera définit comme clé dans un bundle (.properties)
		 * 			4 eme argument : message d'erreur par défaut
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelle", "required.libelle", "Le champ est obligatoire");
		
	

		/*______________validation objet _______________________*/
		
		//1. récup de l'objet à valider
		Matiere matiere =  (Matiere) objetAValider;
		
		List<Matiere> listematiere = matiereDAO.listMatiere();
		int i=0;
		for (Matiere mat : listematiere) {
			if (mat.getLibelle().equals(matiere.getLibelle())) {
				i++;
			}
		}
		
		//2. validation du salaire
		if (i!=0) {
			//création d'une 
			errors.rejectValue("libelle", "noteallowed.libelle","Cette matière existe deja");
		}
		
	}

}
