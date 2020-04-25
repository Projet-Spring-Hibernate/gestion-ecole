package com.intiformation.gestion_ecole.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Personne;
import com.intiformation.gestion_ecole.domain.Promotion;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestEtudiantDAO {

	@Autowired
	@Qualifier("etudiantDAOImpl")
	private IEtudiantDAO etudiantDao;

	@Autowired
	private IPromotionDAO promoDao;
	
	@Autowired
	private ICoursDAO coursDao;

	/* ==================== AJOUT ETUDIANT ================= */
	@Test
	public void testAjoutEtudiant() {

		Etudiant etudiantAjout = new Etudiant("mdp", "nom", "prenom", "email", "photo", "dateDeNaissance1");

		etudiantDao.ajouter(etudiantAjout);

		// recup de la liste des etudiant dans bdd
		List<Etudiant> listeEtudiantsBd = etudiantDao.getAllEtudiant();
		// suppression
		etudiantDao.supprimer(listeEtudiantsBd.get(0));
		etudiantDao.getAllEtudiant();

	}// end testAjoutEtudiant()

	/*
	 * @Test public void testModifierPersonne() {
	 * 
	 * //Création d'une nouvelle personne (Sans l'id) Personne personneAvantModif =
	 * new Personne("mdp", "nom", "prenom", "email");
	 * 
	 * //Ajout de la personne à la bdd personneDao.ajouter(personneAvantModif);
	 * 
	 * //Recup de la liste des personnes dans la bdd List<Personne> listePersonnes =
	 * personneDao.getAll();
	 * 
	 * //Recup de la personne à modifier (avec son id) et modification de l'un de
	 * ses attribut Personne personneModif = listePersonnes.get(0);
	 * personneModif.setEmail("EmailModif");
	 * 
	 * //Modif de la personne dans la bdd personneDao.modifier(personneModif);
	 * 
	 * //Recup de la liste des personnes dans la bdd après modification
	 * listePersonnes = personneDao.getAll();
	 * 
	 * 
	 * //------------ Suppression de la personne pour ne pas gener les autres tests
	 * ------- personneDao.supprimer(listePersonnes.get(0)); personneDao.getAll();
	 * //---------------------------------------------------------------------------
	 * ----------
	 * 
	 * //Test si la modif a bien été faite
	 * Assert.assertEquals(personneModif.getEmail(),
	 * listePersonnes.get(0).getEmail());
	 * 
	 * }//end testModifierPersonne
	 * 
	 */

	/* ==================== MODIF ETUDIANT ======================== */
	@Test
	public void testModifierEtudiant() {

		Etudiant etudiantAvantModif = new Etudiant("mdp", "nom", "prenom", "email", "photo", "dateDeNaissance");

		// ajout de l'étudiant dans la bdd
		etudiantDao.ajouter(etudiantAvantModif);

		// Recup de la liste des etudiants dans la bdd
		List<Etudiant> listeEtudiantsBdd = etudiantDao.getAllEtudiant();

		Etudiant etudiantModif = listeEtudiantsBdd.get(0);
		etudiantModif.setDateDeNaissance("date");

		etudiantDao.modifier(etudiantModif);

		listeEtudiantsBdd = etudiantDao.getAllEtudiant();

		// suppression
		etudiantDao.supprimer(listeEtudiantsBdd.get(0));
		etudiantDao.getAllEtudiant();

		Assert.assertEquals(etudiantModif.getDateDeNaissance(), listeEtudiantsBdd.get(0).getDateDeNaissance());

	}// end testModifierEtudiant()

	/* ==================== RECUP LISTE ETUDIANTS ================= */
	@Test
	public void testGetAllEtudiant() {

		Etudiant etudiant = new Etudiant("mdp", "nom", "prenom", "email", "photo", "dateDeNaissance");

		// ajout dans bdd
		etudiantDao.ajouter(etudiant);

		List<Etudiant> listeEtudiantBdd = etudiantDao.getAllEtudiant();

		// suppression
		etudiantDao.supprimer(listeEtudiantBdd.get(0));
		etudiantDao.getAllEtudiant();
	}

	@Test
	public void testAjouterEtudiantAvecPromotion() {
		Promotion promo = new Promotion("sciences du vivant");

		promoDao.ajouter(promo);
		List<Promotion> listePromotion = promoDao.listePromotion();

		Etudiant etudiant = new Etudiant("mdp", "nom", "prenom", "email", "photo", "dateDeNaissance");
		etudiant.setListePromotion(listePromotion);

		etudiantDao.ajouter(etudiant);

		List<Etudiant> listeEtudiant = etudiantDao.getAllEtudiant();

		Etudiant etudiantRecup = (Etudiant) etudiantDao.getById(listeEtudiant.get(0).getIdentifiant());

		List<Promotion> promoRecup = etudiantRecup.getListePromotion();
		// suppression
		etudiantDao.supprimer(listeEtudiant.get(0));
		etudiantDao.getAllEtudiant();

		promoDao.supprimer(listePromotion.get(0));
		promoDao.listePromotion();

		// Test si on recupere bien l'adresse de la personne
		Assert.assertTrue(promoRecup.equals(listePromotion.get(0)));

	}// end testAjouterEtudiantAvecPromotion()
	
	
	@Test
	public void testAjouterEtudiantAvecCours() {
//		Cours cours = new Cours("maths", "date", "duree","description");
//
//		coursDao.ajouter(cours);
//		List<Cours> listeCours = coursDao.getAllCours();
//
//		Etudiant etudiant = new Etudiant("mdp", "nom", "prenom", "email", "photo", "dateDeNaissance");
//		etudiant.setListeCours(listeCours);
//
//		etudiantDao.ajouter(etudiant);
//
//		List<Etudiant> listeEtudiant = etudiantDao.getAllEtudiant();
//
//		Etudiant etudiantRecup = (Etudiant) etudiantDao.getById(listeEtudiant.get(0).getIdentifiant());
//
//		List<Cours> coursRecup = etudiantRecup.getListeCours();
//		// suppression
//		etudiantDao.supprimer(listeEtudiant.get(0));
//		etudiantDao.getAllEtudiant();
//
//		coursDao.supprimer(listeCours.get(0));
//		coursDao.getAllCours();
//
//		// Test si on recupere bien l'adresse de la personne
//		Assert.assertTrue(coursRecup.equals(listeCours.get(0)));

	}// end testAjouterEtudiantAvecPromotion()

}
