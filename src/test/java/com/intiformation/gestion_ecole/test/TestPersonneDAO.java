package com.intiformation.gestion_ecole.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.dao.AdresseDaoImpl;
import com.intiformation.gestion_ecole.dao.IAdresseDao;
import com.intiformation.gestion_ecole.dao.IPersonneDao;
import com.intiformation.gestion_ecole.dao.PersonneDaoImpl;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Personne;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestPersonneDAO extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	@Qualifier("personneDaoImpl")
	private IPersonneDao personneDao;
	
	@Autowired
	private IAdresseDao adresseDao;
	
	// ============ Méthodes ====================//

	// --------------------------------------------------//
	// -------------- TEST AJOUTER ----------------------//
	// --------------------------------------------------// 
	@Test
	public void testAjouterPersonne() {
		
		//Création d'une nouvelle personne (Sans l'id)
		Personne personneAjout = new Personne("mdp", "nom", "prenom", "emailPersonne1");
		
		//Ajout de la personne à la bdd
		personneDao.ajouter(personneAjout);
		
		//Recup de la liste des personnes dans la bdd
		List<Personne> listePersonnes = personneDao.getAll();

		//------------ Suppression de la personne pour ne pas gener les autres tests -------
		personneDao.supprimer(listePersonnes.get(0));
		personneDao.getAll();
		//-------------------------------------------------------------------------------------
		
		//Test si l'ajout a bien été fait 
		Assert.assertEquals(personneAjout.getEmail(), listePersonnes.get(0).getEmail());
	
	}//end testAjouterPersonne
	
	// --------------------------------------------------//
	// -------------- TEST MODIFIER ---------------------//
	// --------------------------------------------------//
	@Test
	public void testModifierPersonne() {
		
		//Création d'une nouvelle personne (Sans l'id)
		Personne personneAvantModif = new Personne("mdp", "nom", "prenom", "email");
		
		//Ajout de la personne à la bdd
		personneDao.ajouter(personneAvantModif);
		
		//Recup de la liste des personnes dans la bdd
		List<Personne> listePersonnes = personneDao.getAll();

		//Recup de la personne à modifier (avec son id) et modification de l'un de ses attribut
		Personne personneModif = listePersonnes.get(0);
		personneModif.setEmail("EmailModif");
		
		//Modif de la personne dans la bdd
		personneDao.modifier(personneModif);
		
		//Recup de la liste des personnes dans la bdd après modification
		listePersonnes = personneDao.getAll();

		
		//------------ Suppression de la personne pour ne pas gener les autres tests -------
		personneDao.supprimer(listePersonnes.get(0));
		personneDao.getAll();
		//-------------------------------------------------------------------------------------
		
		//Test si la modif a bien été faite 
		Assert.assertEquals(personneModif.getEmail(), listePersonnes.get(0).getEmail());
	
	}//end testModifierPersonne
	
	
	// --------------------------------------------------//
	// -------------- TEST SUPPRIMER --------------------//
	// --------------------------------------------------//
	
	@Test
	public void testSupprimerPersonne() {
		//Création d'une nouvelle personne (Sans l'id)
		Personne personneSuppr = new Personne("mdp", "nom", "prenom", "email");
		
		//Ajout de la personne à la bdd
		personneDao.ajouter(personneSuppr);
		
		//Recup de la liste des personnes dans la bdd
		List<Personne> listePersonnes = personneDao.getAll();
		
		//Suppression de la personne
		personneDao.supprimer(listePersonnes.get(0));
		
		//Recup de la liste des personnes dans la bdd après suppression
		listePersonnes = personneDao.getAll();

		//Test si la suppression a bien été faite 
		Assert.assertEquals(listePersonnes.size(),0);
		
	}//end testSupprimerPersonne
	
	// --------------------------------------------------//
	// -------------- TEST GETBYID ----------------------//
	// --------------------------------------------------//
	
	@Test
	public void testGetByIdPersonne() {
		
		//Création d'une nouvelle personne (Sans l'id)
		Personne personne = new Personne("mdp", "nom", "prenom", "email");
		
		//Ajout de la personne à la bdd
		personneDao.ajouter(personne);
		
		//Recup de la liste des personnes dans la bdd
		List<Personne> listePersonnes = personneDao.getAll();
		
		//Recup de la personne
		Personne personneRecup = personneDao.getById(listePersonnes.get(0).getIdentifiant());

		//------------ Suppression de la personne pour ne pas gener les autres tests -------
		personneDao.supprimer(listePersonnes.get(0));
		personneDao.getAll();
		//-------------------------------------------------------------------------------------
		

		//Test si la recuperation a bien été faite 
		Assert.assertTrue(personneRecup.equals(personne));

	}//end testGetByIdPersonne
	
	
	
	// --------------------------------------------------//
	// -------------- TEST getAll -----------------------//
	// --------------------------------------------------// 
	
	@Test
	public void testGetAllPersonne() {
		
		//Création d'une nouvelle personne (Sans l'id)
		Personne personne = new Personne("mdp", "nom", "prenom", "email");
		
		//Ajout de la personne à la bdd
		personneDao.ajouter(personne);
		
		//Recup de la liste des personnes dans la bdd
		List<Personne> listePersonnesGetAll = personneDao.getAll();
		
		//------------ Suppression de la personne pour ne pas gener les autres tests -------
		personneDao.supprimer(listePersonnesGetAll.get(0));
		personneDao.getAll();
		//-------------------------------------------------------------------------------------
		

		//Test si la la liste fait la bonne taille
		Assert.assertEquals(listePersonnesGetAll.size(), 1);;

	}//end testGetAllPersonne
	
	// --------------------------------------------------//
	// -------------- TEST ISPERSONNEEXISTS -------------//
	// --------------------------------------------------// 
	
	@Test
	public void testIsPersonneExistes() {
		
		//Création d'une nouvelle personne (Sans l'id)
		Personne personne = new Personne("mdp", "nom", "prenom", "email");
		
		//Ajout de la personne à la bdd
		personneDao.ajouter(personne);
		
		//Recup de la liste des personnes dans la bdd
		List<Personne> listePersonnes = personneDao.getAll();
		
		//On test si la personne existe
		boolean testIsExists = personneDao.isPersonneExists("email", "mdp");
		
		//------------ Suppression de la personne pour ne pas gener les autres tests -------
		personneDao.supprimer(listePersonnes.get(0));
		personneDao.getAll();
		//-------------------------------------------------------------------------------------


		//Test si la fonction renvoie true
		Assert.assertTrue(testIsExists);

	}//end testIsPersonneExistes
	
	// --------------------------------------------------//
	// -------------- TEST getPersonneByMailMDP ---------//
	// --------------------------------------------------// 
	
	@Test
	public void testgetPersonneByMailMDP() {
		
		//Création d'une nouvelle personne (Sans l'id)
		Personne personne = new Personne("mdp", "nom", "prenom", "email");
		
		//Ajout de la personne à la bdd
		personneDao.ajouter(personne);
		
		//Recup de la liste des personnes dans la bdd
		List<Personne> listePersonnes = personneDao.getAll();
		
		//Recup de la personne par son mail et mot de passe
		Personne personneRecup = personneDao.getPersonneByMailMDP("email", "mdp");

		//------------ Suppression de la personne pour ne pas gener les autres tests -------
		personneDao.supprimer(listePersonnes.get(0));
		personneDao.getAll();
		//-------------------------------------------------------------------------------------
		

		//Test si la recuperation a bien été faite 
		Assert.assertTrue(personneRecup.equals(personne));

	}//end testgetPersonneByMailMDP
	
	
	// --------------------------------------------------//
	// -------------- TEST liaison avec Adresse---------//
	// --------------------------------------------------// 
	@Test
	public void testAjouterPersonneAvecAdresse() {

//		//Création d'une adresse
//		Adresse adresse= new Adresse("rue", "codePostal", "ville");
//		
//		// Création d'une nouvelle personne (Sans l'id)
//		Personne personne = new Personne("mdp", "nom", "prenom", "email");
//		
//		// Recuperation de l'adresse et attribution de l'adresse à la personne
//		personne.setAdresse(adresse);
//		
//		// Ajout de la personne à la bdd
//		personneDao.ajouter(personne);
		
		//========================================
		
		//Création d'une adresse
		Adresse adresse= new Adresse("rue", "codePostal", "ville");
		
		//Ajout de l'adresse à la bdd
		adresseDao.ajouter(adresse);
		
		// Création d'une nouvelle personne (Sans l'id)
		Personne personne = new Personne("mdp", "nom", "prenom", "email");

		// Recuperation de l'adresse (pour avoir son id) et attribution de l'adresse à la personne
		personne.setAdresse(adresseDao.getAll().get(0));

		// Ajout de la personne à la bdd
		personneDao.ajouter(personne);

		// Recup de la liste des personnes dans la bdd
		List<Personne> listePersonnes = personneDao.getAll();

		// Recup de la liste des adresses dans la bdd
		List<Adresse> listeAdresse = adresseDao.getAll();
		
		//Recup de l'adresse de la personne 
		Personne personneRecup = personneDao.getById(listePersonnes.get(0).getIdentifiant());
		
		Adresse adresseRecup = personneRecup.getAdresse();
		
		//------------ Suppression de la personne et de l'adresse pour ne pas gener les autres tests -------
		personneDao.supprimer(listePersonnes.get(0));
		personneDao.getAll();
		adresseDao.supprimer(listeAdresse.get(0));
		adresseDao.getAll();
		//-------------------------------------------------------------------------------------
		
		//Test si on recupere bien l'adresse de la personne
		Assert.assertTrue(adresseRecup.equals(listeAdresse.get(0)));
	
	}//end testAjouterPersonne
}//end class
