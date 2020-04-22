package com.intiformation.gestion_ecole.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.domain.Enseignant;

@Transactional
@Repository("enseignantDAOImpl")
public class EnseignantDAOImpl extends PersonneDaoImpl implements IEnseignantDAO {

	public EnseignantDAOImpl() {
	}
	
	@Override
	public List<Enseignant> listEnseignant() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM personne p WHERE type(p)='enseignant'").list();
	}
	
	


}
