package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Cours;

public interface ICoursDAO extends IGenericDAO<Cours>{
	
	public List<Cours> getAllCours();
	
	public List<Cours> orderCours();
	
	
	
	

}