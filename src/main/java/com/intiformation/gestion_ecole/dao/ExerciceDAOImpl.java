package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Exercice;

@Transactional
@Repository("exerciceDAOImpl")
public class ExerciceDAOImpl extends GeneraleDAOImpl<Exercice> implements IExerciceDAO {

	public ExerciceDAOImpl() {
		super(Exercice.class);
	}

	@Override
	public List<Exercice> listeExercice() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM exercice e").list();

	}

	@Override
	public List<Exercice> getExerciceByIdCours(Long pIdCours) {

		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//3. Contenu de la requête : 

	
			Query<Exercice> query = session.createQuery("SELECT e FROM exercice e  WHERE e.cours.idCours = :pIdCours");
			
			query.setParameter("pIdCours", pIdCours);

			List<Exercice> listeExo= query.getResultList();
			
			
			return listeExo;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur ....");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<String> getAllLibelle() {
			
			return this.getSessionFactory().getCurrentSession().createQuery("SELECT e.libelle FROM exercice e").list();
	}//end getAll

	@Override
	public Exercice getExerciceByLibelle(String plibelle) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			Query<Exercice> query = session.createQuery("SELECT e FROM exercice e WHERE e.libelle = :plibelle ");
			
			
			query.setParameter("plibelle", plibelle);

			
			Exercice exercice = query.getSingleResult();
			
			return exercice;
			}catch (PersistenceException e){
				System.out.println("...  Erreur dans getExerciceByLibelle ....");
				e.printStackTrace();
			}
		return null;
	}
	
	
	

}
