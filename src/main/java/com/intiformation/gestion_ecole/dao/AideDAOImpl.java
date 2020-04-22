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
	public Aide getAideByPage(long pId_page) {
		

			//1. Recup de la session à partir de la SessionFactory
			Session session = (Session) this.getSessionFactory();
			
			Query<Aide> query = session.createQuery("SELECT a FROM aide a WHERE a.id_page = :pIdpage");
			
			query.setParameter("pIdpage", pId_page);
			
			
			return query.getSingleResult();
			

	}//fin getAideByPage

	@Override
	public List<Aide> getAllAide() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM aide a").list();
	}//fin getAllAide


}//fin classe
