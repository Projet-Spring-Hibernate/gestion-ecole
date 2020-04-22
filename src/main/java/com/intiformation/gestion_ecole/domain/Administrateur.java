package com.intiformation.gestion_ecole.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Marie
 *
 */
@Entity
@Table(name="administrateurs")
public class Administrateur extends Personne {

	/* _______________Constructeur_______________ */
	/**
	 * Ctor vide.
	 */

	public Administrateur() {
		super();
		
	}

	public Administrateur(Long identifiant, String motdepasse, String nom, String prenom, String email) {
		super(identifiant, motdepasse, nom, prenom, email);
		
	}

	public Administrateur(String motdepasse, String nom, String prenom, String email) {
		super(motdepasse, nom, prenom, email);
		
	}

	
	
}//end class
