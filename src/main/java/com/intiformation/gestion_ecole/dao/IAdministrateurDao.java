package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Administrateur;

/**
 * Interface de la classe Administrateur.
 * @author Marie
 *
 */
public interface IAdministrateurDao extends IPersonneDao {

	/***********************Méthodes*********************************/
	
	/**
	 * Permet de récupérer la liste de toute les Administrateurs.
	 * @param adresse_id
	 * @return
	 */
	public List<Administrateur> getAll();
	
}//end interface
