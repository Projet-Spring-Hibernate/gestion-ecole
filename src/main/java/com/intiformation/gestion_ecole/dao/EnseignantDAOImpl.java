package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Promotion;
/**
 * Implementation concrete de la dao pour les enseignants. Herite de IPersonneDao pour les fonctions generales et definie les fonctions specifiques aux enseignants
 * @author IN-MP-018
 *
 */
@Transactional
@Repository("enseignantDAOImpl")
public class EnseignantDAOImpl extends PersonneDaoImpl implements IEnseignantDAO {

	// --------------------------------------------------//
	// -------------- GET ALL ---------------------------//
	// --------------------------------------------------// 
	
	/**
	 * Recupere la liste de tous les enseignants de la bdd
	 */
	@Override
	public List<Enseignant> listEnseignant() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM enseignant e").list();
	}//end listEnseignant

	
	// --------------------------------------------------//
	// -------------- GET BY MATIERE --------------------//
	// --------------------------------------------------// 
	
	/**
	 * Recupere la liste des enseignants qui enseignent une matiere par l'id de la matiere
	 */
	@Override
	public List<Enseignant> getlistEnseignantByIdMatiere(Long pIdMatiere) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//2. Contenu de la requête : 
			Query<Enseignant> query = session.createQuery("SELECT DISTINCT e FROM enseignant e JOIN e.listeEnseignantMatierePromotion l WHERE l.matiere.idMatiere = :pIdMatiere");
			
			query.setParameter("pIdMatiere", pIdMatiere);

			//3. recup du resultat
			List<Enseignant> listeEnseignants= query.getResultList();
			
			System.out.println(listeEnseignants);
			
			return listeEnseignants;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur dans getlistEnseignantByIdMatiere ....");
			e.printStackTrace();
		}
		return null;
	}//end getlistEnseignantByIdMatiere

	
	
	// --------------------------------------------------//
	// -------------- GET BY PROMOTION ------------------//
	// --------------------------------------------------// 
	
	/**
	 * Recupere la liste des enseignants qui enseignent  à une promo par l'id de la promo
	 */
	@Override
	public List<Enseignant> getlistEnseignantByIdPromotion(Long pIdPromotion) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//2. Contenu de la requête : 
			Query<Enseignant> query = session.createQuery("SELECT DISTINCT e FROM enseignant e JOIN e.listeEnseignantMatierePromotion l WHERE l.promotion.idPromotion = :pIdPromotion");
			
			query.setParameter("pIdPromotion", pIdPromotion);

			//3. recup du resultat
			List<Enseignant> listeEnseignants= query.getResultList();
			
			System.out.println(listeEnseignants);
			
			return listeEnseignants;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur dans getlistEnseignantByIdPromotion ....");
			e.printStackTrace();
		}
		return null;
	}//end getlistEnseignantByIdPromotion

	
	
	// --------------------------------------------------//
	// -------------- GET BY PROMO et MATIERE -----------//
	// --------------------------------------------------// 
	
	
	/**
	 * Recup d'un enseignant par l'id d'une matiere et d'une promo (caracteristique d'un cours)
	 */
	@Override
	public Enseignant getEnseignantByIdPromotionetIdMatiere(Long pIdPromotion, Long pIdMatiere ) {
		try {
			
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//2. Contenu de la requête : 
			Query<Enseignant> query = session.createQuery("SELECT DISTINCT e FROM enseignant e JOIN e.listeEnseignantMatierePromotion l WHERE l.matiere.idMatiere = :pIdMatiere AND l.promotion.idPromotion = :IdPromotion");
			
			query.setParameter("pIdPromotion", pIdPromotion);
			query.setParameter("pIdMatiere", pIdMatiere);
			//3. recup du resultat
			Enseignant enseignant= query.getSingleResult();
			
			System.out.println(enseignant);
			
			return enseignant;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur dans getEnseignantByIdPromotionetIdMatiere ....");
			e.printStackTrace();
		}
		return null;
	}//End getEnseignantByIdCours
	
	


}//end class
