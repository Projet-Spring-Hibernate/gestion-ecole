package com.intiformation.gestion_ecole.dao;

import java.util.List;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Etudiant;

@Transactional
@Repository("etudiantDAOImpl")
public class EtudiantDAOImpl extends PersonneDaoImpl implements IEtudiantDAO{

	public EtudiantDAOImpl() {
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Etudiant> getAllEtudiant() {
		// TODO Auto-generated method stub
		return this.getSessionFactory().getCurrentSession().createQuery("FROM personne p WHERE type(p)='etudiant'").list();
	}
	
	

}
