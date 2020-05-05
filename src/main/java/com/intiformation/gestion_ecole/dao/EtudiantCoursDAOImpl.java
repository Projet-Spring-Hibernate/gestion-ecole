package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.domain.EtudiantCours;
import com.intiformation.gestion_ecole.domain.Matiere;

@Transactional
@Repository("etudiantCoursDAOImpl")
public class EtudiantCoursDAOImpl extends GeneraleDAOImpl<EtudiantCours> implements IEtudiantCoursDAO {

	//============== CTOR ===================//
	public EtudiantCoursDAOImpl() {
		super(EtudiantCours.class);
	}
	
	//============ METHODES =================//

	// --------------------------------------------------//
	// -------------- GET ALL ---------------------------//
	// --------------------------------------------------//
	/**
	 * Recupere la liste de tous les EtudiantsCours (absences) de la bdd
	 */
	@Override
	public List<EtudiantCours> getAllEtudiantCours() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM EtudiantCours e").list();
	}//End getAllEtudiantCours

	// --------------------------------------------------//
	// -------------- GET BY ENSEIGNANT -----------------//
	// --------------------------------------------------//
	/**
	 * Recupere la liste des EtudiantsCours (absences) pour un enseignant (toutes les absences de ses etudiants)
	 */
	@Override
	public List<EtudiantCours> getAllAbsenceByEnseignant(Long id) {

		try {
			// 1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();

			// 2. Contenu de la requête :

			Query<EtudiantCours> query = session.createQuery(
					"SELECT DISTINCT e FROM EtudiantCours e WHERE e.etudiant IN (SELECT DISTINCT e FROM etudiant e JOIN e.listePromotion l WHERE l IN (SELECT DISTINCT p FROM promotion p JOIN p.listeEnseignantMatierePromotion l WHERE l.enseignant.identifiant = :pIdEnseignant))");

			query.setParameter("pIdEnseignant", id);

			// 3. recup du resultat
			
			List<EtudiantCours> listeetudiantcours = query.getResultList();

			return listeetudiantcours;

		} catch (PersistenceException e) {
			System.out.println("... Erreur dans getAllAbsenceByEnseignant....");
			e.printStackTrace();
		}//end catch

		return null;
	}//end getAllAbsenceByEnseignant

	
	// --------------------------------------------------//
	// -------------- GET BY COURS ----------------------//
	// --------------------------------------------------//
	
	/**
	 * Recupere la liste des EtudiantsCours (absences) de la bdd pour un cours
	 */
	@Override
	public List<EtudiantCours> getAbsenceByCours(Long pidCours) {
		try {
			// 1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();

			// 2. Contenu de la requête :

			Query<EtudiantCours> query = session.createQuery(
					"SELECT DISTINCT e FROM EtudiantCours e WHERE e.cours.idCours = :pidCours");

			query.setParameter("pidCours", pidCours);

			// 3. recup du resultat
			
			List<EtudiantCours> listeetudiantcours = query.getResultList();

			return listeetudiantcours;

		} catch (PersistenceException e) {
			System.out.println("... Erreur dans getAbsenceByCours....");
			e.printStackTrace();
		}//end catch
		return null;
	}//End getAbsenceByCours

	
	// --------------------------------------------------//
	// -------------- GET BY ETUDIANTS ------------------//
	// --------------------------------------------------//
	
	/**
	 * Recupere la liste des EtudiantsCours (absences) de la bdd pour un etudiants
	 */
	@Override
	public List<EtudiantCours> getAbsenceByEtudiant(Long pidEtudiants) {
		try {
			// 1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();

			// 2. Contenu de la requête :

			Query<EtudiantCours> query = session.createQuery(
					"SELECT DISTINCT e FROM EtudiantCours e WHERE e.etudiant.identifiant = :pidEtudiants");

			query.setParameter("pidEtudiants", pidEtudiants);
			
			// 3. recup du resultat
			List<EtudiantCours> listeetudiantcours = query.getResultList();

			return listeetudiantcours;

		} catch (PersistenceException e) {
			System.out.println("... Erreur dans getAbsenceByEtudiant....");
			e.printStackTrace();
		}//end catch
		return null;
	}//End getAbsenceByEtudiant

}// end class
