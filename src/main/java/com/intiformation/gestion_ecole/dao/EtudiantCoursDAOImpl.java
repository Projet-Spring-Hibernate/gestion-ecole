package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.EtudiantCours;

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


	

}
