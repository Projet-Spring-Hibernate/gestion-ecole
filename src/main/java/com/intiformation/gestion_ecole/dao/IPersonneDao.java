package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Personne;

/**
 * Interface de la classe Personne.
 * @author Marie
 *
 */
public interface IPersonneDao extends IGenericDAO<Personne>{
	
	/***********************Méthodes*********************************/
	
	/**
	 * Permet de déterminer si la Personne existe ou non selon son nom et son identifiant.
	 * @param nom
	 * @param id_personne
	 * @return
	 */
	public boolean isPersonneExists(String email, String mot_de_passe);
	
	public Personne getPersonneByMailMDP(String email, String mot_de_passe);
	
	/**
	 * Permet de récupérer la liste de toute les Personnes.
	 * @param id_personne
	 * @return
	 */
	public List<Personne> getAll(Long id_personne);
	

}//end interface
