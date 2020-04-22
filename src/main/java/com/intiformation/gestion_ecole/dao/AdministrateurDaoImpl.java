package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Administrateur;

/**
 * Implémentation de la Dao pour la classe Administrateur.
 * @author Marie
 *
 */
public class AdministrateurDaoImpl extends GeneraleDAOImpl<Administrateur> implements IAdministrateurDao{

	public AdministrateurDaoImpl() {
		super(Administrateur.class);
		
	}

	@Override
	public List<Administrateur> getAll() {
		
		return this.getSessionFactory().getCurrentSession().createQuery("FROM administrateur p").list();
	}

	
	
}//end class
