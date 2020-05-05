package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Etudiant;

/**
 * Implementation concrete de la dao pour les etudiants. Herite de
 * PersonneDaoImpl pour les fonctions generales et definie les fonctions
 * specifiques aux etudiants
 * 
 * @author IN-MP-018
 *
 */
@Transactional
@Repository("etudiantDAOImpl")
public class EtudiantDAOImpl extends PersonneDaoImpl implements IEtudiantDAO{

	// --------------------------------------------------//
	// -------------- GET ALL ---------------------------//
	// --------------------------------------------------//
	@Override
	public List<Etudiant> getAllEtudiant() {
		
		return this.getSessionFactory().getCurrentSession().createQuery("FROM etudiant e ").list();
	}//end getAllEtudiant
	
	// --------------------------------------------------//
	// -------------- GET BY ENSEIGNANT --------------------//
	// --------------------------------------------------//
	@Override
	public List<Etudiant> getlistEtudiantByIdEnseignant(Long pIdEnseignant){
		try {
			// 1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();

			// 2. Contenu de la requête :
			
			Query<Etudiant> query = session.createQuery(
					"SELECT DISTINCT e FROM etudiant e JOIN e.listePromotion l WHERE l IN (SELECT DISTINCT p FROM promotion p JOIN p.listeEnseignantMatierePromotion l WHERE l.enseignant.identifiant = :pIdEnseignant)" );

			query.setParameter("pIdEnseignant", pIdEnseignant);

			// 3. recup du resultat
			List<Etudiant> listeEtudiants = query.getResultList();

			System.out.println(listeEtudiants);

			return listeEtudiants;

		} catch (PersistenceException e) {
			System.out.println("... Erreur dans getlistEtudiantByIdEnseignant ....");
			e.printStackTrace();
		}
		return null;
	}//end getlistEtudiantByIdEnseignant

	
	// --------------------------------------------------//
	// -------------- GET BY PROMOTION --------------------//
	// --------------------------------------------------//
	@Override
	public List<Etudiant> getlistEtudiantsByIdPromotion(Long pIdPromotion) {
		try {
			// 1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();

			// 2. Contenu de la requête :
			
			Query<Etudiant> query = session.createQuery(
					"SELECT DISTINCT e FROM etudiant e JOIN e.listePromotion l WHERE l.idPromotion = :pIdPromotion" );

			query.setParameter("pIdPromotion", pIdPromotion);

			// 3. recup du resultat
			List<Etudiant> listeEtudiants = query.getResultList();

			System.out.println(listeEtudiants);

			return listeEtudiants;

		} catch (PersistenceException e) {
			System.out.println("... Erreur dans getlistEtudiantsByIdPromotion ....");
			e.printStackTrace();
		}
		return null;
	}//End getlistEtudiantsByIdPromotion
	
	
	

}//end class
