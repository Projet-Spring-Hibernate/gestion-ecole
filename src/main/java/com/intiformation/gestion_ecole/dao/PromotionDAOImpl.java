package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Promotion;

@Transactional
@Repository("promotionDAOImpl")
public class PromotionDAOImpl extends GeneraleDAOImpl<Promotion> implements IPromotionDAO {

	public PromotionDAOImpl() {
		super(Promotion.class);
	}

	@Override
	public List<Promotion> listePromotion() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM promotion p").list();
	}

}
