package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Exercice;

@Transactional
@Repository
public class ExerciceDAOImpl extends GeneraleDAOImpl<Exercice> implements IExerciceDAO {

	public ExerciceDAOImpl() {
		super(Exercice.class);
	}

	@Override
	public List<Exercice> listeExercice() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM exercice e").list();

	}
	
	
	

}
