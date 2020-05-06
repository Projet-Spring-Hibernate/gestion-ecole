package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Exercice;

public interface IExerciceDAO extends IGenericDAO<Exercice> {

	public List<Exercice> listeExercice ();
	
	public List<Exercice> getExerciceByIdCours(Long pIdCours);
	
	public List<String> getAllLibelle();
	
	public Exercice getExerciceByLibelle(String plibelle);
	
}
