package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Personne;



/**
 * Implémentation de la couche Dao pour Personne.
 * @author Marie
 *
 */
@Transactional
@Repository("personneDaoImpl")
public class PersonneDaoImpl extends GeneraleDAOImpl<Personne> implements IPersonneDao {


	
	public PersonneDaoImpl() {
		super(Personne.class);
		
	}

	/**
	 * Savoir si la Personne est présente dans la BDD.
	 */
	@Override
	public boolean isPersonneExists(String email, String mot_de_passe) {
		
		try {

			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			Query<Long> query = session.createQuery("SELECT COUNT(p) FROM personne p WHERE p.email = :pEmail AND p.motdepasse = :pMotDePasse");
			
			query.setParameter("pEmail", email);
			query.setParameter("pMotDePasse", mot_de_passe);
			
			Long nbPersonne = query.getSingleResult();
			
			return (nbPersonne ==1);
			
		} catch (HibernateException ex) {
			
				System.out.println("------------ Erreur ----------");
				ex.printStackTrace();
			}//end catch
		
		return false; 
		
	}//end isPersonneExists

	
	/**
	 * recupere le personne par son mot de passe et email
	 */
	@Override
	public Personne getPersonneByMailMDP(String email, String mot_de_passe) {
		
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//3. Contenu de la requête : 
			
			Query<Personne> query = session.createQuery("SELECT p FROM personne p WHERE p.email= :pEmail AND p.motdepasse= :pMdp");
			
			query.setParameter("pEmail", email);
			query.setParameter("pMdp", mot_de_passe);
			
			Personne personne = query.getSingleResult();
			
			return personne;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur ....");
			e.printStackTrace();
		}
		return null;
	}//end getPersonneByMailMDP
	
	
	@Override
	public List<Personne> getAll() {
	
		return this.getSessionFactory().getCurrentSession().createQuery("FROM personne p").list();
	}//end getAll
	
	

}//end class
