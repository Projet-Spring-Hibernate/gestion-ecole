package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Aide;


public interface IAideDAO extends IGenericDAO<Aide>{
	
	public String getAideByPage(String pId_page);
	public List<Aide> getAllAide(); 

}//fin classe
