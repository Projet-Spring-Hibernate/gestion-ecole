package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;

public interface ICoursDAO extends IGenericDAO<Cours>{
	
	public List<Cours> getAllCours();
	
	public List<Cours> orderCours();
	
	public List<Cours> getListeCoursByIdEnseignant(Long pIdEnseignant);
	
	public Cours affichageCours(Cours cours);
	
	
	
	public List<Cours> getListeCoursByIdEtudiant(Long pIdEtudiant);
	
	public List<String> getAllLibelle();
	
	
	public Matiere getMatiereByIdCours(Long idCours);
	
	public Promotion getPromotionByIdCours(Long idCours);
	
	public Cours getCoursByLibelle(String plibelle);
	
	

}//end interface
