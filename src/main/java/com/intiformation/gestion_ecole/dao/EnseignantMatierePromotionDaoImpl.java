package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.EtudiantCours;

@Transactional
@Repository("enseignantMatierePromotionDaoImpl")
public class EnseignantMatierePromotionDaoImpl extends GeneraleDAOImpl<EnseignantMatierePromotion> implements IEnseignantMatierePromotionDao {

	@Override
	public List<EtudiantCours> getAllEnseignantMatierePromotion() {
		
		return this.getSessionFactory().getCurrentSession().createQuery("FROM enseignantMatierePromotion e").list();
	}

}//end class
