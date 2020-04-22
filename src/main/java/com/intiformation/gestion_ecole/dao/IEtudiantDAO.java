package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Etudiant;

public interface IEtudiantDAO extends IPersonneDao{
	
	public List<Etudiant> getAllEtudiant();

}
