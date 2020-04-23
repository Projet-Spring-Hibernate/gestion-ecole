package com.intiformation.gestion_ecole.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.dao.IAdministrateurDao;
import com.intiformation.gestion_ecole.dao.IPersonneDao;
import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.domain.Personne;

/**
 * Classe test pour les Administrateurs;
 * @author Marie
 *
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestAdministrateurDao extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	@Qualifier("administrateurDaoImpl")
	private IAdministrateurDao administrateurDao;
	
	// ============ Méthodes ====================//

		// --------------------------------------------------//
		// -------------- TEST AJOUTER ----------------------//
		// --------------------------------------------------// 
	
	@Test
	public void testAjouterAdministrateur() {
		
		//Création d'une nouvel administrateur (Sans l'id)
		Administrateur administrateurAjout = new Administrateur("mdp", "nom", "prenom", "emailAdministrateur1");
		
		//Ajout de l'administrateur à la bdd
		administrateurDao.ajouter(administrateurAjout);
		
		//Recup de la liste des administrateurs dans la bdd
		List<Administrateur> listeAdministrateurs = administrateurDao.getAllAdministrateur();

		//------------ Suppression de l'administrateur pour ne pas gener les autres tests -------
		administrateurDao.supprimer(listeAdministrateurs.get(0));
		administrateurDao.getAll();
		//-------------------------------------------------------------------------------------
		
		//Test si l'ajout a bien été fait 
		Assert.assertEquals(administrateurAjout.getEmail(), listeAdministrateurs.get(0).getEmail());
	
	}//end testAjouterAdministrateur
	
	    // --------------------------------------------------//
		// -------------- TEST MODIFIER ---------------------//
		// --------------------------------------------------//
		@Test
		public void testModifierAdministrateur() {
			
			//Création d'une nouvel administrateur (Sans l'id)
			Administrateur adminsitrateurAvantModif = new Administrateur("mdp", "nom", "prenom", "email");
			
			//Ajout de l'administrateur à la bdd
			administrateurDao.ajouter(adminsitrateurAvantModif);
			
			//Recup de la liste des personnes dans la bdd
			List<Administrateur> listeAdministrateurs = administrateurDao.getAllAdministrateur();

			//Recup de la personne à modifier (avec son id) et modification de l'un de ses attribut
			Personne administrateurModif = listeAdministrateurs.get(0);
			administrateurModif.setEmail("EmailModif");
			
			//Modif de l'administrateur dans la bdd
			administrateurDao.modifier(administrateurModif);
			
			//Recup de la liste des administrateur dans la bdd après modification
			listeAdministrateurs = administrateurDao.getAllAdministrateur();

			
			//------------ Suppression de l'administrateur pour ne pas gener les autres tests -------
			administrateurDao.supprimer(listeAdministrateurs.get(0));
			administrateurDao.getAllAdministrateur();
			//-------------------------------------------------------------------------------------
			
			//Test si la modif a bien été faite 
			Assert.assertEquals(administrateurModif.getEmail(), listeAdministrateurs.get(0).getEmail());
		
		}//end testModifierAdministrateur
		
		// --------------------------------------------------//
		// -------------- TEST SUPPRIMER --------------------//
		// --------------------------------------------------//
		
		@Test
		public void testSupprimerAdministrateur() {
			//Création d'une nouvel administrateur (Sans l'id)
			Administrateur administrateurSuppr = new Administrateur("mdp", "nom", "prenom", "email");
			
			//Ajout de l'administrateur à la bdd
			administrateurDao.ajouter(administrateurSuppr);
			
			//Recup de la liste des administrateurs dans la bdd
			List<Administrateur> listeAdministrateurs = administrateurDao.getAllAdministrateur();
			
			//Suppression de l'administrateur
			administrateurDao.supprimer(listeAdministrateurs.get(0));
			
			//Recup de la liste des administrateurs dans la bdd après suppression
			listeAdministrateurs = administrateurDao.getAllAdministrateur();

			//Test si la suppression a bien été faite 
			Assert.assertEquals(listeAdministrateurs.size(),0);
			
		}//end testSupprimerAdministrateur
	
		// --------------------------------------------------//
		// -------------- TEST GETBYID ----------------------//
		// --------------------------------------------------//
		
		@Test
		public void testGetByIdAdministrateur() {
			
			//Création d'une nouvel administrateur (Sans l'id)
			Administrateur administrateur = new Administrateur("mdp", "nom", "prenom", "email");
			
			//Ajout de l'administrateur à la bdd
			administrateurDao.ajouter(administrateur);
			
			//Recup de la liste des personnes dans la bdd
			List<Administrateur> listeAdministrateurs = administrateurDao.getAllAdministrateur();
			
			//Recup de l'administrateur
			Administrateur administrateurRecup = (Administrateur) administrateurDao.getById(listeAdministrateurs.get(0).getIdentifiant());

			//------------ Suppression de l'admin pour ne pas gener les autres tests -------
			administrateurDao.supprimer(listeAdministrateurs.get(0));
			administrateurDao.getAllAdministrateur();
			//-------------------------------------------------------------------------------------
			

			//Test si la recuperation a bien été faite 
			Assert.assertTrue(administrateurRecup.equals(administrateur));

		}//end testGetByIdAdministrateur
		
		// --------------------------------------------------//
		// -------------- TEST getAll -----------------------//
		// --------------------------------------------------// 
		
		@Test
		public void testGetAllAdministrateur() {
			
			//Création d'une nouvel Administrateur (Sans l'id)
			Administrateur administrateur = new Administrateur("mdp", "nom", "prenom", "email");
			
			//Ajout de la administrateur à la bdd
			administrateurDao.ajouter(administrateur);
			
			//Recup de la liste des personnes dans la bdd
			List<Administrateur> listeAdministrateursGetAll = administrateurDao.getAllAdministrateur();
			
			//------------ Suppression de l'administrateur pour ne pas gener les autres tests -------
			administrateurDao.supprimer(listeAdministrateursGetAll.get(0));
			administrateurDao.getAllAdministrateur();
			//-------------------------------------------------------------------------------------
			

			//Test si la la liste fait la bonne taille
			Assert.assertEquals(listeAdministrateursGetAll.size(), 1);;

		}//end testGetAllAdministrateur
	
	
}//end class
