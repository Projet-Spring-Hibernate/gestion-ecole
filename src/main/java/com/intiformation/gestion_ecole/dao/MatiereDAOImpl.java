package com.intiformation.gestion_ecole.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Matiere;

@Transactional
@Repository("matiereDAOImpl")
public class MatiereDAOImpl extends GeneraleDAOImpl<Matiere> implements IMatiereDAO {

	public MatiereDAOImpl() {
		super(Matiere.class);
	}

	@Override
	public List<Matiere> listMatiere() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM matiere m").list();
	}

	@Override
	public List<Matiere> orderMatiere() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM matiere m ORDER BY libelle asc").list();
	}

	@Override
	public List<Matiere> listematiereEtudiantbyid(Long id) {
		return null;
	}

	@Override
	public List<Matiere> listematiereEnseignantbyid(Long id) {
		
		try {
			//1. Recup de la session à partir de la SessionFactory
			Session session = this.getSessionFactory().getCurrentSession();
			
			//3. Contenu de la requête : 

	
			Query<Matiere> query = session.createQuery("SELECT DISTINCT m FROM matiere m JOIN m.listeEnseignantMatierePromotion e WHERE e.enseignant.identifiant = :pIdEnseignant");
			
			query.setParameter("pIdEnseignant", id);

			List<Matiere> listematiere= query.getResultList();
			
			
			return listematiere;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur ....");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	

}
