package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Personne;
import com.intiformation.gestion_ecole.domain.Promotion;

@Transactional
@Repository("promotionDAOImpl")
public class PromotionDAOImpl extends GeneraleDAOImpl<Promotion> implements IPromotionDAO {

	public PromotionDAOImpl() {
		super(Promotion.class);
	}

	@Override
	public List<Promotion> listePromotion() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM promotion p").list();
	}

	
	/**
	 * Recupere la liste des promotions d'un étudiant
	 */
	@Override
	public List<Promotion> getListePromotionByIdEtudiant(Long pIdEtudiant) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//3. Contenu de la requête : 

	
			Query<Promotion> query = session.createQuery("SELECT p FROM promotion p JOIN p.listeEtudiant e WHERE e.identifiant = :pIdEtudiant");
			
			query.setParameter("pIdEtudiant", pIdEtudiant);

			List<Promotion> listepromotion= query.getResultList();
			
			
			return listepromotion;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur ....");
			e.printStackTrace();
		}
		return null;
	}

	
	
	/**
	 * Recupere la liste des promotions d'un enseignant
	 */
	@Override
	public List<Promotion> getListePromotionByIdEnseignant(Long pIdEnseignant) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//3. Contenu de la requête : 

	
			Query<Promotion> query = session.createQuery("SELECT DISTINCT p FROM promotion p JOIN p.listeEnseignantMatierePromotion l WHERE l.enseignant.identifiant = :pIdEnseignant");
			
			query.setParameter("pIdEnseignant", pIdEnseignant);

			List<Promotion> listepromotion= query.getResultList();
			
			System.out.println(listepromotion);
			
			return listepromotion;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur ....");
			e.printStackTrace();
		}
		return null;
	}

}//end class
