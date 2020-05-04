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

	public EtudiantCoursDAOImpl() {
		super(EtudiantCours.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EtudiantCours> getAllEtudiantCours() {
		// TODO Auto-generated method stub
		return this.getSessionFactory().getCurrentSession().createQuery("FROM EtudiantCours e").list();
	}


	@Override
	public List<EtudiantCours> getAllAbsenceByEnseignant(Long id) {
	
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//3. Contenu de la requête : 

	
			Query<EtudiantCours> query = session.createQuery("SELECT DISTINCT e FROM EtudiantCours e WHERE e.etudiant IN (SELECT DISTINCT e FROM etudiant e JOIN e.listePromotion l WHERE l IN (SELECT DISTINCT p FROM promotion p JOIN p.listeEnseignantMatierePromotion l WHERE l.enseignant.identifiant = :pIdEnseignant))");
			
			query.setParameter("pIdEnseignant", id);

			List<EtudiantCours> listeetudiantcours= query.getResultList();
			
			
			return listeetudiantcours;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur ....");
			e.printStackTrace();
		}
		
		
		return null;
		}

}
