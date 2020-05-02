package com.intiformation.gestion_ecole.dao;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Enseignant;
/**
 * Interface de la dao pour les enseignants. Herite de IPersonneDao pour les fonctions generales et definie les fonctions specifiques aux enseignants
 * @author IN-MP-018
 *
 */
public interface IEnseignantDAO extends IPersonneDao{
	
	public List<Enseignant> listEnseignant();

	public List<Enseignant> getlistEnseignantByIdMatiere(Long pIdMatiere);
	
	public List<Enseignant> getlistEnseignantByIdPromotion(Long pIdPromotion);
	
	public List<Enseignant> getlistEnseignantByIdEtudiant(Long pIdEtudiant);
	
	public Enseignant getEnseignantByIdPromotionetIdMatiere(Long pIdPromotion, Long pIdMatiere );
	
}//end classe
