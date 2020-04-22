package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Administrateur;

/**
 * Implémentation de la Dao pour la classe Administrateur.
 * @author Marie
 *
 */

@Repository("administrateurDaoImpl")
@Transactional
public class AdministrateurDaoImpl extends PersonneDaoImpl implements IAdministrateurDao{

	public AdministrateurDaoImpl() {
		
	}

	@Override
	public List<Administrateur> getAllAdministrateur() {
		
		return this.getSessionFactory().getCurrentSession().createQuery("FROM administrateur p").list();
	}

	
	
}//end class
