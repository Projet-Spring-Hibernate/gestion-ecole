package com.intiformation.gestion_ecole.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Promotion;

/**
 * 
 * @author Marie
 *
 */
@Component
public class PromotionFormValidator implements Validator {

	@Autowired
	private IPromotionDAO promotionDao;

	// Setter pour injection
	public void setPromotionDao(IPromotionDAO promotionDao) {
		this.promotionDao = promotionDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object objetAValider, Errors errors) {

		/* _______________ Validation des champs _______________ */
		/**
		 * rejectIfEmptyOrWhiteSpace() : -1er argument : si champ vide, creation d'une
		 * erreur de champ (errors) liée au champ -2e argument : le nom de la proprieté
		 * -3e argument :code d'erreur sui serat definit comme clé dans un bundle
		 * (.properties) -4e argument=message d'erreur par defaut
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelle", "required.libelle", "Le champ est obligatoire");

		/* _______________ Validation objet _______________ */

		// 1. recup de l'objet à valider
		Promotion promotion = (Promotion) objetAValider;

		List<Promotion> listePromotions = promotionDao.getAllPromo(); 
		int i = 0;
		for (Promotion promotion1 : listePromotions) {
			if (promotion1.getLibelle().equals(promotion.getLibelle())) {
				i++;
			} // end if
		}

		if (i != 0) {
			errors.rejectValue("libelle", "notallowed.libelle", "Ce cours existe déjà");

		}

	}// End validate

}// end class
