package com.intiformation.gestion_ecole.dao;
/**

 * 
 */
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.intiformation.gestion_ecole.domain.Aide;

@Transactional
@Repository("aideDAOImpl")
public class AideDAOImpl extends GeneraleDAOImpl<Aide> implements IAideDAO{

	public AideDAOImpl() {
		super(Aide.class);
	}

	@Override
	public String getAideByPage(String pNom_Page) {
		

			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			Query<String> query = session.createQuery("SELECT a.contenu FROM aide a WHERE a.page = :pNom_Page");
			
			query.setParameter("pNom_Page", pNom_Page);
			
			
			return query.getSingleResult();
			

	}//fin getAideByPage

	@Override
	public List<Aide> getAllAide() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM aide a").list();
	}//fin getAllAide


}//fin classe
