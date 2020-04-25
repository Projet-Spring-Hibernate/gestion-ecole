package com.intiformation.gestion_ecole.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe entity pour les administrateurs. Classe fille de la classe Personne.
 * @author Marie
 *
 */

@Entity(name="administrateur")
@DiscriminatorValue("administrateur")
public class Administrateur extends Personne {

	// _______________ Constructeurs _______________ //
	
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

	//_____________ Méthodes _____________//
	@Override
	public String toString() {
		return "Administrateur [identifiant=" + super.getIdentifiant() + ", motdepasse=" + super.getMotdepasse() + ", nom=" + super.getNom() + ", prenom="
				+ super.getPrenom() + ", email=" + super.getEmail() + "]";
	}
	
}//end class
