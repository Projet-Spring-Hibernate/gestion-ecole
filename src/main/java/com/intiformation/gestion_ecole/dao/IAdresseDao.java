package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Adresse;

/**
 * Interface de Adresse.
 * @author Marie
 *
 */
public interface IAdresseDao extends IGenericDAO<Adresse> {

	/***********************Méthodes*********************************/
	
	/**
	 * Permet de récupérer la liste de toute les Adresses.
	 * @param adresse_id
	 * @return
	 */
	public List<Adresse> getAll();
	
	public Adresse getByPersonneId(Long pIdPersonne);
	
}//end interface
