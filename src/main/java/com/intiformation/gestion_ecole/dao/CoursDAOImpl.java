package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Promotion;

@Transactional
@Repository("coursDAOImpl")
public class CoursDAOImpl extends GeneraleDAOImpl<Cours> implements ICoursDAO{

	
	
	public CoursDAOImpl() {
		super(Cours.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cours> getAllCours() {
		// TODO Auto-generated method stub
		return this.getSessionFactory().getCurrentSession().createQuery("FROM cours c").list();
	}

	@Override
	public List<Cours> orderCours() {
		// TODO Auto-generated method stub
		return this.getSessionFactory().getCurrentSession().createQuery("FROM cours c ORDER BY libelle asc").list();
	}

	@Override
	public List<Cours> getListeCoursByIdEnseignant(Long pIdEnseignant) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//3. Contenu de la requête : 

																	// à revoir
			Query<Cours> query = session.createQuery("SELECT c FROM cours c WHERE c.matiere IN (SELECT m FROM matiere m WHERE m.listeEnseignantMatierePromotion l WHERE l.enseignant.identifiant = :pIdEnseignant)");
			
			query.setParameter("pIdEnseignant", pIdEnseignant);

			List<Cours> listecours= query.getResultList();
			
			
			return listecours;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur ....");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cours> getListeCoursByIdEtudiant(Long pIdEtudiant) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//3. Contenu de la requête : 

	
			Query<Cours> query = session.createQuery("SELECT c FROM cours c JOIN c.listeEtudiantCours l WHERE l.etudiant.identifiant = :pIdEtudiant");
			
			query.setParameter("pIdEtudiant", pIdEtudiant);

			List<Cours> listecours= query.getResultList();
			
			
			return listecours;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur ....");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Cours affichageCours(Cours cours) {
		try {
		//1. Recup de la session à partir de la SessionFactory
		Session session = this.getSessionFactory().getCurrentSession();
		Cours cours1 = this.getById(cours.getIdCours());
		
		Query<Cours> query = session.createQuery("SELECT ens FROM enseignant ens JOIN ens.listeEnseignantMatierePromotion l WHERE l.matiere.idMatiere = :pIdMatiere AND l.promotion.idPromotion = :pIdPromotion");
		query.setParameter("pIdMatiere", cours1.getMatiere().getIdMatiere());
		query.setParameter("pIdPromotion", cours1.getPromotion().getIdPromotion());
		
		cours1 = query.getSingleResult();
		
		return cours1;
		}catch (PersistenceException e){
			System.out.println("... Erreur ....");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	

}
