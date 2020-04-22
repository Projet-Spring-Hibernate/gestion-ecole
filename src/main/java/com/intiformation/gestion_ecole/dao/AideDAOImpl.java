package com.intiformation.gestion_ecole.dao;
/**
 * 
 */
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Aide;
@Repository
public class AideDAOImpl extends GeneraleDAOImpl<Aide> implements IAideDAO{

	@Transactional(readOnly=true)
	@Override
	public Aide getAideByPage(long pId_page) {
		// TODO Auto-generated method stub
		return entityManager.find(Aide.class, pId_page);
	}//fin getAideByPage

	@Transactional(readOnly=true)
	@Override
	public List<Aide> getAllAide() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("").getResultList();
	}//fin getAllAide
	
	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	//constructeur AideDAOImpl avec super(Aide.class)
	
	//méthodes avec hibernate (query...)

}//fin classe
