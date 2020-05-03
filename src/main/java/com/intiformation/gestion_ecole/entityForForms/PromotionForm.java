package com.intiformation.gestion_ecole.entityForForms;

import java.util.List;


import com.intiformation.gestion_ecole.domain.Promotion;

public class PromotionForm {

	private Promotion promotion;
	private List<Promotion> listePromotionsExistantes;
	
	
	public PromotionForm() {
		super();
	}


	public Promotion getPromotion() {
		return promotion;
	}


	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}


	public List<Promotion> getListePromotionsExistantes() {
		return listePromotionsExistantes;
	}


	public void setListePromotionsExistantes(List<Promotion> listePromotionsExistantes) {
		this.listePromotionsExistantes = listePromotionsExistantes;
	}

	
	
	
}//end class
