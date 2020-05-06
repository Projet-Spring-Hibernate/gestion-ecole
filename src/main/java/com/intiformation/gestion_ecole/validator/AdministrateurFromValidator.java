package com.intiformation.gestion_ecole.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestion_ecole.dao.IAdministrateurDao;
import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.entityForForms.AdministrateurForm;

@Component
public class AdministrateurFromValidator implements Validator {

	@Autowired
	private IAdministrateurDao adminDAO;
	
	
	
	public void setAdminDAO(IAdministrateurDao adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Administrateur.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object objetAValider, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "administrateur.nom", "required.nom", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "administrateur.prenom", "required.prenom", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "administrateur.email", "required.email", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "administrateur.motdepasse", "required.motdepasse", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adresse.rue", "required.rue", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adresse.codePostal", "required.codePostal", "Le champ est obligatoires");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adresse.ville", "required.ville", "Le champ est obligatoires");

		
		/*_______________ Validation objet _______________*/
		
		//1. recup de l'objet à valider
		AdministrateurForm administrateurform =  (AdministrateurForm) objetAValider;
		
		//2. Validation de l'email : l'email doit être unique
		List<String> listeEmailExistants = adminDAO.getAllMail();
		
		if(listeEmailExistants.contains(administrateurform.getAdministrateur().getEmail())) {
			Administrateur adminExistant =  (Administrateur) adminDAO.getPersonneByMail(administrateurform.getAdministrateur().getEmail());
			if(adminExistant.getIdentifiant()!=administrateurform.getAdministrateur().getIdentifiant()) {
				//creation d'une erreur
				errors.rejectValue("administrateur.email", "notallowed.email", "Cet email appartient à une autre personne. Merci d'en choisir un autre.");
			}// end if
		}//end if
	}

	
	
	
}
