package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Matiere;


public interface IMatiereDAO extends IGenericDAO<Matiere> {

	public List<Matiere> listMatiere();

	public List<Matiere> orderMatiere();
	
}
