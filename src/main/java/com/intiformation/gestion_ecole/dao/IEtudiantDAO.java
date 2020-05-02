package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Etudiant;

/**
 * Interface de la DAO d'etudiant. Herite de l'interface de IPersonneDao pour les fonctions générales. Définie les fonctions specifiques aux etudiants
 * @author IN-MP-018
 *
 */
public interface IEtudiantDAO extends IPersonneDao{
	
	public List<Etudiant> getAllEtudiant();
	
	public List<Etudiant> getlistEtudiantByIdEnseignant(Long pIdEnseignant);

}//end interface
