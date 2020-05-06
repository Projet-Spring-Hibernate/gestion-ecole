package com.intiformation.gestion_ecole.entityForForms;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Exercice;

public class ExerciceForm {
	
	private Exercice exercice;
	
	private List<Cours> listeCoursExistants;
	
	private Cours cours;
	
	private List<Exercice> listeExercice;

	public ExerciceForm() {
		super();
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public List<Exercice> getListeExercice() {
		return listeExercice;
	}

	public void setListeExercice(List<Exercice> listeExercice) {
		this.listeExercice = listeExercice;
	}

	public Exercice getExercice() {
		return exercice;
	}

	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}

	public List<Cours> getListeCoursExistants() {
		return listeCoursExistants;
	}

	public void setListeCoursExistants(List<Cours> listeCoursExistants) {
		this.listeCoursExistants = listeCoursExistants;
	}
	
	


	
	

}
