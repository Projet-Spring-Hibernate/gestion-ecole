package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Exercice;

public interface IExerciceDAO extends IGenericDAO<Exercice> {

	public List<Exercice> listeExercice ();
	
}
