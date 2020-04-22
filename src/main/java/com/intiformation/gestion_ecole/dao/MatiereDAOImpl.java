package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Matiere;

@Transactional
@Repository
public class MatiereDAOImpl extends GeneraleDAOImpl<Matiere> implements IMatiereDAO {

	public MatiereDAOImpl() {
		super(Matiere.class);
	}

	@Override
	public List<Matiere> listMatiere() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM matiere m").list();
	}

	@Override
	public List<Matiere> orderMatiere() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM matiere m ORDER BY libelle asc").list();
	}
	
	

}
