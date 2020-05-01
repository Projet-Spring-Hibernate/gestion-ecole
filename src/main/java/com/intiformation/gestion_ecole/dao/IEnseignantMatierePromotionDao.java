package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.EtudiantCours;

public interface IEnseignantMatierePromotionDao extends IGenericDAO<EnseignantMatierePromotion>{

	public List<EnseignantMatierePromotion> getAllEnseignantMatierePromotion();
	
	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotionByEnseignant(Long pIdEnseignant);
	
	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotionByPromotion(Long pIdPromotion);
	
	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotionByMatiere(Long pIdMatiere);
	
	
}//end interface
