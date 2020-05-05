package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.EtudiantCours;

/**
 * Interface de la dao pour la table EtudiantCours (absence/presence)
 * 
 * @author IN-MP-018
 *
 */
public interface IEtudiantCoursDAO extends IGenericDAO<EtudiantCours>{
	
	public List<EtudiantCours> getAllEtudiantCours();

	public List<EtudiantCours> getAllAbsenceByEnseignant(Long id);

	public List<EtudiantCours> getAbsenceByCours(Long pidCours);
	public List<EtudiantCours> getAbsenceByEtudiant(Long pidEtudiants);
}//end interface
