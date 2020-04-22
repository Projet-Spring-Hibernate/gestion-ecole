package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("generaleDAOImpl")
public class GeneraleDAOImpl<T> implements IGenericDAO<T> {

	// ============ Propriétées ====================//

	protected Class<T> entityClass;

	// déclaration de la session factory d'hibernate
	@Autowired
	private SessionFactory sessionFactory;

	// ============ Setter ====================//
	/**
	 * setter de la session factory pour injection spring
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	//============ Constructeurs ====================//
	/**
	 * Constructeur prenant en parametre la classe de l'entity
	 */
	public GeneraleDAOImpl(Class<T> entityClass) {
		
		this.entityClass = entityClass;
		
	}//end ctor
	
	

	public GeneraleDAOImpl() {
		super();
	}


	// ============ Méthodes ====================//

	// --------------------------------------------------//
	// -------------- AJOUTER --------------------------//
	// --------------------------------------------------//
	/**
	 * Ajout d'une entité à la base de donnée.
	 * 
	 * @param entity
	 *            à ajouter à la bdd
	 */
	@Override
	public void ajouter(T entity) {
		try {
			sessionFactory.getCurrentSession().save(entity);
		} catch (HibernateException e) {
			System.out.println("Erreur lors de l'ajout de l'entité");
			throw e;
		} // end catch
	}// end ajouter

	// --------------------------------------------------//
	// -------------- MODIFIER -------------------------//
	// --------------------------------------------------//

	/**
	 * Modification d'une entité dans la base de donnée
	 * 
	 * @param entity
	 *            à modifier
	 */
	@Override
	public void modifier(T entity) {
		try {
			sessionFactory.getCurrentSession().merge(entity);
		} catch (HibernateException e) {
			System.out.println("Erreur lors de la modification de l'entité");
			throw e;
		} // end catch

	}// end modifier

	// --------------------------------------------------//
	// -------------- SUPPRIMER ------------------------//
	// --------------------------------------------------//

	/**
	 * Suppression d'une entité dans la bdd
	 * 
	 * @param id
	 *            (clé primaire) de l'entité à supprimer
	 */
	@Override
	public void supprimer(T entity) {
		try {
			sessionFactory.getCurrentSession().delete(entity);

		} catch (HibernateException e) {
			System.out.println("Erreur lors de la suppression de l'entité");
			throw e;
		} // end catch

	}// End ajouter

	// --------------------------------------------------//
	// -------------- GETBYID --------------------------//
	// --------------------------------------------------//
	/**
	 * Récupération d'une entité par son identifiant (clé primaire)
	 * 
	 * @param id
	 *            de l'entité à récuperer
	 * @return l'entité cherchée
	 */
	@Override
	public T getById(Long id) {

		return sessionFactory.getCurrentSession().get(entityClass, id);
	}// end getById

}// end class
