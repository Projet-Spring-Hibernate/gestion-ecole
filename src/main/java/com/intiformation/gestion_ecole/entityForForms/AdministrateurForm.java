package com.intiformation.gestion_ecole.entityForForms;

import java.util.List;

import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.domain.Adresse;


public class AdministrateurForm {

	private Administrateur administrateur;
	private Adresse adresse;
	
	

	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Administrateur getAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	
	
	
	
}
