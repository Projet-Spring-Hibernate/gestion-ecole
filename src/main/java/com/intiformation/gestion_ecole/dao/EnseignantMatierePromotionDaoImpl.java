package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.EtudiantCours;
import com.intiformation.gestion_ecole.domain.Promotion;
/**
 * Implementation concrete de la DAO de la table de jointure triple EnseignantMatierePromotion.
 * Implemente l'interface IEnseignantMatierePromotionDao pour les fonctions specifiques et herit de GeneraleDAOImpl avec un type EnseignantMatierePromotion pour les fonctions generales (CRUD)
 * @author IN-MP-018
 *
 */
@Transactional
@Repository("enseignantMatierePromotionDaoImpl")
public class EnseignantMatierePromotionDaoImpl extends GeneraleDAOImpl<EnseignantMatierePromotion> implements IEnseignantMatierePromotionDao {

	// --------------------------------------------------//
	// -------------- GET ALL ----------------------------//
	// --------------------------------------------------//
	
	/**
	 * Recupere la liste de toutes les combinaisons EnseignantMatierePromotion
	 */
	@Override
	public List<EnseignantMatierePromotion> getAllEnseignantMatierePromotion() {
		
		return this.getSessionFactory().getCurrentSession().createQuery("FROM enseignantMatierePromotion e").list();
	}
	
	// --------------------------------------------------//
	// -------------- GET BY ENSEIGNANT------------------//
	// --------------------------------------------------//

	/**
	 * Recupere la liste des combinaisons EnseignantMatierePromotion par l'id de l'enseignant
	 */
	@Override
	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotionByEnseignant(Long pIdEnseignant) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//2. Contenu de la requête : 

			Query<EnseignantMatierePromotion> query = session.createQuery("SELECT DISTINCT e FROM enseignantMatierePromotion e WHERE e.enseignant.identifiant = :pIdEnseignant");
			
			query.setParameter("pIdEnseignant", pIdEnseignant);

			//3. Recup du resultat
			List<EnseignantMatierePromotion> listeEnseignantMatierePromotion= query.getResultList();
			
			
			return listeEnseignantMatierePromotion;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur getListeEnseignantMatierePromotionByEnseignant ....");
			e.printStackTrace();
		}//end catch
		return null;
	}//End getListeEnseignantMatierePromotionByEnseignant

	// --------------------------------------------------//
	// -------------- GET BY PROMO ----------------------//
	// --------------------------------------------------//
	
	/**
	 * Recupere la liste des combinaisons EnseignantMatierePromotion par l'id de la promotion
	 */
	@Override
	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotionByPromotion(Long pIdPromotion) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//2. Contenu de la requête : 

			Query<EnseignantMatierePromotion> query = session.createQuery("SELECT DISTINCT e FROM enseignantMatierePromotion e WHERE e.promotion.idPromotion = :pIdPromotion");
			
			query.setParameter("pIdPromotion", pIdPromotion);

			//3. Recup du resultat
			List<EnseignantMatierePromotion> listeEnseignantMatierePromotion= query.getResultList();
			
			
			return listeEnseignantMatierePromotion;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur getListeEnseignantMatierePromotionByPromotion....");
			e.printStackTrace();
		}//end catch
		return null;
	}//End getListeEnseignantMatierePromotionByPromotion

	// --------------------------------------------------//
	// -------------- GET BY MATIERE --------------------//
	// --------------------------------------------------//
	/**
	 * Recupere la liste des combinaisons EnseignantMatierePromotion par l'id de la matiere
	 */
	@Override
	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotionByMatiere(Long pIdMatiere) {
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//2. Contenu de la requête : 

			Query<EnseignantMatierePromotion> query = session.createQuery("SELECT  DISTINCT e FROM enseignantMatierePromotion e WHERE e.matiere.idMatiere = :pIdMatiere");
			
			query.setParameter("pIdMatiere", pIdMatiere);

			//3. Recup du resultat
			List<EnseignantMatierePromotion> listeEnseignantMatierePromotion= query.getResultList();
			
			
			return listeEnseignantMatierePromotion;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur getListeEnseignantMatierePromotionByMatiere ....");
			e.printStackTrace();
		}//end catch
		return null;
	}//end getListeEnseignantMatierePromotionByPromotion

}//end class
