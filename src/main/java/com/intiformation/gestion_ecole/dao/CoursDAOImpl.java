package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Personne;
import com.intiformation.gestion_ecole.domain.Promotion;

@Transactional
@Repository("coursDAOImpl")
public class CoursDAOImpl extends GeneraleDAOImpl<Cours> implements ICoursDAO{

	@Autowired
	private IEnseignantDAO enseignantDao;
	
	
	
	
	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

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

//			Enseignant enseignant = new Enseignant();
//			pIdEnseignant= enseignant.getIdentifiant();
																	// à revoir "WHERE -> JOIN
			Query<Cours> query = session.createQuery("SELECT c FROM cours c WHERE c.matiere IN (SELECT m FROM matiere m JOIN m.listeEnseignantMatierePromotion l WHERE l.enseignant.identifiant = :pIdEnseignant)");
			
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

	@Override
	public List<String> getAllLibelle() {
			
			return this.getSessionFactory().getCurrentSession().createQuery("SELECT c.libelle FROM cours c").list();
	}//end getAll

	
	
	
	
	@Override
	public Matiere getMatiereByIdCours(Long idCours) {
		try {
		//1. Recup de la session à partir de la SessionFactory
		Session session = this.getSessionFactory().getCurrentSession();
		
		Query<Matiere> query = session.createQuery("SELECT c.matiere FROM cours c  WHERE c.idCours = :pidCours ");
		//Query<Matiere> query = session.createQuery("SELECT m FROM matiere m JOIN m.listeCours l WHERE l.cours.idCours = :pidCours ");
		
		query.setParameter("pidCours", idCours);

		
		Matiere matiere = query.getSingleResult();
		
		return matiere;
		}catch (PersistenceException e){
			System.out.println("... Erreur dans getMatiereByIdCours ....");
			e.printStackTrace();
		}
		
		return null;
	}//End getMatiereByIdCours

	@Override
	public Promotion getPromotionByIdCours(Long idCours) {
		try {
		//1. Recup de la session à partir de la SessionFactory
		Session session = this.getSessionFactory().getCurrentSession();
		Query<Promotion> query = session.createQuery("SELECT c.promotion FROM cours c WHERE c.idCours = :pidCours ");
		//Query<Promotion> query = session.createQuery("SELECT p FROM promotion p JOIN p.listeCours l WHERE l.cours.idCours = :pidCours ");
		
		query.setParameter("pidCours", idCours);

		
		Promotion promotion = query.getSingleResult();
		
		return promotion;
		}catch (PersistenceException e){
			System.out.println("... Erreur dans getPromotionByIdCours ....");
			e.printStackTrace();
		}
		
		return null;
	}//end getPromotionByIdCours

	
	
	@Override
	public Cours getCoursByLibelle(String plibelle) {
		try {
		//1. Recup de la session à partir de la SessionFactory
		Session session = this.getSessionFactory().getCurrentSession();
		Query<Cours> query = session.createQuery("SELECT c FROM cours c WHERE c.libelle = :plibelle ");
		//Query<Promotion> query = session.createQuery("SELECT p FROM promotion p JOIN p.listeCours l WHERE l.cours.idCours = :pidCours ");
		
		query.setParameter("plibelle", plibelle);

		
		Cours cours = query.getSingleResult();
		
		return cours;
		}catch (PersistenceException e){
			System.out.println("... Erreur dans getCoursByLibelle ....");
			e.printStackTrace();
		}
		
		return null;
	}//end getCoursByLibelle
	

}//end class
