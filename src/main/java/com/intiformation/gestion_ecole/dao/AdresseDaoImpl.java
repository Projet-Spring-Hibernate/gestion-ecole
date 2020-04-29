package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		public List<Adresse> getAll() {


			return this.getSessionFactory().getCurrentSession().createQuery("FROM adresse a").list();
		}//end getAll


		/**
		 * Permet de récuperer l'adresse d'une personne par son id
		 */
		@Override
		public Adresse getByPersonneId(Long pIdPersonne) {

			System.out.println("Je suis dans getByPersonneId");
			System.out.println("id = "+pIdPersonne);
			Session session = null;
			Adresse adresse = null;
			try {
				System.out.println("CHECKPOINT 1");

				// 1. recup de la session à partir de la session factory
				session = this.getSessionFactory().getCurrentSession();

				// 2. definition du contenu de la requete HQL
				/**
				 * Utilisation de la clause WHERE 
				 */
				//======= Notation par nom de parametre ==========
				String requeteGetByPersonneID = "SELECT a FROM adresse a WHERE a.personne.identifiant = :pIdPersonne";

				// 3. construction de la requete avec la session (il faut prendre l'import
				// org.hibernate.query)
				Query<Adresse> getByIDPersonneQUERY = session.createQuery(requeteGetByPersonneID);

				// passage de param
				getByIDPersonneQUERY.setParameter("pIdPersonne", pIdPersonne);
				
				
				// 4. execution et recup du resultat de la requete
				adresse = getByIDPersonneQUERY.uniqueResult();

				System.out.println("Adresse ="+adresse);
				// 5. renvoi de la liste
				return adresse;

			} catch (HibernateException e) {

				System.out.println("... Erreur lors de la recuperation de l'adresse par l'id de la personne (HQL) de la bdd...");
				e.printStackTrace();

			} 
			
			return null;
		}//end getByPersonneId
	
	
}//end class
