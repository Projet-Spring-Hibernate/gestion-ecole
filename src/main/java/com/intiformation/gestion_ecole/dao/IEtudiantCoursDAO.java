package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.EtudiantCours;

public interface IEtudiantCoursDAO extends IGenericDAO<EtudiantCours>{
	
	public List<EtudiantCours> getAllEtudiantCours();

}
