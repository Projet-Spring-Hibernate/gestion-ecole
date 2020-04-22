package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Adresse;

/**
 * Implémentation de la couche Dao pour Adresse.
 * @author Marie
 *
 */
@Transactional
@Repository("adresseDaoImpl")
public class AdresseDaoImpl extends GeneraleDAOImpl<Adresse> implements IAdresseDao {

		public AdresseDaoImpl() {
			super(Adresse.class);
			
		}


		@Override
		public List<Adresse> getAll(Long adresse_id) {
	
			return this.getSessionFactory().getCurrentSession().createQuery("FROM adresses a").list();
		}

	
	
}//end class
