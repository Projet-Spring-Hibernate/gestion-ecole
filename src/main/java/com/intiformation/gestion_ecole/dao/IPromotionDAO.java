package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Promotion;

public interface IPromotionDAO extends IGenericDAO<Promotion> {

		public List<Promotion> listePromotion();

		public List<Promotion> getListePromotionByIdEtudiant(Long pIdEtudiant);
		
		public List<Promotion> getListePromotionByIdEnseignant(Long pIdEnseignant);
		
		
}//end class

