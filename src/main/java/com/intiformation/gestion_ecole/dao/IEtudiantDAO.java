package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Etudiant;

public interface IEtudiantDAO extends IGenericDAO<Etudiant>{
	
	public List<Etudiant> getAllEtudiant();

}
