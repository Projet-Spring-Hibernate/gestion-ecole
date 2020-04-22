package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Enseignant;

public interface IEnseignantDAO extends IGenericDAO<Enseignant> {

	
	public List<Enseignant> listEnseignant();

	
	
}
