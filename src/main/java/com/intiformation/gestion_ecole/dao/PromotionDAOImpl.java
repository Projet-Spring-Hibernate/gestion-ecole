package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Promotion;

public class PromotionDAOImpl extends GeneraleDAOImpl<Promotion> implements IPromotionDAO {

	public PromotionDAOImpl() {
		super(Promotion.class);
	}

	@Override
	public List<Promotion> listePromotion() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM promotion p").list();
	}

}
