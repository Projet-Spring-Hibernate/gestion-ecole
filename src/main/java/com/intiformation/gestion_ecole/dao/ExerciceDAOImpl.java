package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Exercice;

public class ExerciceDAOImpl extends GeneraleDAOImpl<Exercice> implements IExerciceDAO {

	public ExerciceDAOImpl() {
		super(Exercice.class);
	}

	@Override
	public List<Exercice> listeExercice() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM exercice e").list();

	}
	
	
	

}
