package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Cours;

@Transactional
@Repository
public class CoursDAOImpl extends GeneraleDAOImpl<Cours> implements ICoursDAO{

	
	
	public CoursDAOImpl() {
		super(Cours.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cours> getAllCours() {
		// TODO Auto-generated method stub
		return this.getSessionFactory().getCurrentSession().createQuery("FROM cours c").list();
	}

	@Override
	public List<Cours> orderCours() {
		// TODO Auto-generated method stub
		return this.getSessionFactory().getCurrentSession().createQuery("FROM cours c ORDER BY libelle asc").list();
	}
	
	

}
