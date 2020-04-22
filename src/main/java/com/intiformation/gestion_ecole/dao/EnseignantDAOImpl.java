package com.intiformation.gestion_ecole.dao;

import java.util.List;



import com.intiformation.gestion_ecole.domain.Enseignant;

public class EnseignantDAOImpl extends GeneraleDAOImpl<Enseignant> implements IEnseignantDAO {

	public EnseignantDAOImpl() {
		super(Enseignant.class);
	}
	
	@Override
	public List<Enseignant> listEnseignant() {
		return this.getSessionFactory().getCurrentSession().createQuery("FROM personne p WHERE type(p)='enseignant'").list();
	}
	
	


}
