package com.intiformation.gestion_ecole.test;

import java.util.ArrayList;
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

import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Personne;
import com.intiformation.gestion_ecole.domain.Promotion;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestCoursDAO extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	@Qualifier("coursDAOImpl")
	private ICoursDAO coursDAO;
	
	@Autowired
	private IMatiereDAO matiereDAO;
	
	@Autowired
	private IPromotionDAO promoDao;
	
	@Autowired
	private IEtudiantDAO etudiantDao;

	/* ==================== AJOUT COURS ================= */
	@Test
	public void testAjoutCours() {

		Cours coursAjout = new Cours("libelle", "date", "duree", "description");

		coursDAO.ajouter(coursAjout);

		// recup de la liste des cours dans bdd
		List<Cours> listeCoursBdd = coursDAO.getAllCours();
		// suppression
		coursDAO.supprimer(listeCoursBdd.get(0));
		coursDAO.getAllCours();

		/// Test si l'ajout a bien été fait

		Assert.assertEquals(coursAjout.getDescription(), listeCoursBdd.get(0).getDescription());

	}// end testAjoutCourst()

	// --------------------------------------------------//
	// -------------- TEST GETBYID ----------------------//
	// --------------------------------------------------//

	@Test
	public void testGetByIdCours() {

		// Création d'une nouveau cours (Sans l'id)
		Cours cours = new Cours("libelle", "date", "duree", "description");

		// Ajout du cours à la bdd
		coursDAO.ajouter(cours);

		// recup de la liste des cours dans bdd
		List<Cours> listeCoursBdd = coursDAO.getAllCours();

		// Recup du cours
		Cours coursRecup = coursDAO.getById(listeCoursBdd.get(0).getIdCours());

		// suppression
		coursDAO.supprimer(listeCoursBdd.get(0));
		coursDAO.getAllCours();
		// -------------------------------------------------------------------------------------

		// Test si la recuperation a bien été faite
		Assert.assertTrue(coursRecup.equals(cours));
	}// end testGetByIdCours()

	/* ==================== MODIF COURS ======================== */
	@Test
	public void testModifierCours() {

		Cours coursAvantModif = new Cours("libelle", "date", "duree", "description");

		// Ajout du cours à la bdd
		coursDAO.ajouter(coursAvantModif);

		// recup de la liste des cours dans bdd
		List<Cours> listeCoursBdd = coursDAO.getAllCours();

		Cours coursModif = listeCoursBdd.get(0);
		coursModif.setDescription("description");

		coursDAO.modifier(coursModif);

		listeCoursBdd = coursDAO.getAllCours();

		// suppression
		coursDAO.supprimer(listeCoursBdd.get(0));
		coursDAO.getAllCours();

		Assert.assertEquals(coursModif.getDescription(), listeCoursBdd.get(0).getDescription());

	}// end testModifierCours()

	// --------------------------------------------------//
	// -------------- TEST SUPPRIMER --------------------//
	// --------------------------------------------------//

	@Test
	public void testSupprimerCours() {
		// Création d'un nouveau cours (Sans l'id)

		Cours cours = new Cours("libelle", "date", "duree", "description");
		coursDAO.ajouter(cours);

		// recup de la liste des cours dans bdd
		List<Cours> listeCoursBdd = coursDAO.getAllCours();

		// suppression
		coursDAO.supprimer(listeCoursBdd.get(0));

		// Recup de la liste des cours dans la bdd après suppression
		listeCoursBdd = coursDAO.getAllCours();

		// Test si la suppression a bien été faite
		Assert.assertEquals(listeCoursBdd.size(), 0);

	}// end testSupprimerCours

	/* ==================== RECUP LISTE COURS ================= */
	@Test
	public void testGetAllCours() {

		Cours cours = new Cours("libelle", "date", "duree", "description");

		// ajout dans bdd
		coursDAO.ajouter(cours);

		// recup de la liste des cours dans bdd
		List<Cours> listeCoursBdd = coursDAO.getAllCours();

		// suppression
		coursDAO.supprimer(listeCoursBdd.get(0));
		coursDAO.getAllCours();

		// Test si la la liste fait la bonne taille
		Assert.assertEquals(listeCoursBdd.size(), 1);
		;

	}// end testGetAllCours
	
	
	
	/*===================== TEST LIAISON AVEC MATIERE ============================*/
	@Test
	public void testAjouterCoursAvecMatiere() {
		

		Matiere matiere = new Matiere("maths");
		matiereDAO.ajouter(matiere);
		List<Matiere> listeMatieres = matiereDAO.listMatiere();
		
		Cours cours = new Cours("maths", "date", "duree", "description");
		cours.setMatiere(matiere);

		coursDAO.ajouter(cours);

		List<Cours> listeCours = coursDAO.getAllCours();

		Cours coursRecup =  coursDAO.getById(listeCours.get(0).getIdCours());

		// récup de la liste des matière du cours
		Matiere matiereRecup = coursRecup.getMatiere();
		// suppression
		coursDAO.supprimer(listeCours.get(0));
		coursDAO.getAllCours();

		matiereDAO.supprimer(listeMatieres.get(0));
		matiereDAO.listMatiere();

		// Test si on recupere bien la matière du cours OK
		Assert.assertTrue(matiereRecup.equals(listeMatieres.get(0)));

	}// end testAjouterCoursAvecMatiere()
	
	
	/*===================== TEST LIAISON AVEC PROMOTION ============================*/
	@Test
	public void testAjouterCoursAvecPromotion() {
		
		Promotion promotion = new Promotion("Sciences");
		promoDao.ajouter(promotion);
		List<Promotion> listePromotions = promoDao.listePromotion();
		
		Cours cours = new Cours("maths", "date", "duree", "description");
		cours.setPromotion(promotion);

		coursDAO.ajouter(cours);

		List<Cours> listeCours = coursDAO.getAllCours();

		Cours coursRecup =  coursDAO.getById(listeCours.get(0).getIdCours());

		// récup de la liste des matière du cours
		Promotion promoRecup = coursRecup.getPromotion();
		// suppression
		coursDAO.supprimer(listeCours.get(0));
		coursDAO.getAllCours();

		promoDao.supprimer(listePromotions.get(0));
		promoDao.listePromotion();

		// Test si on recupere bien la promotion du cours OK
		Assert.assertTrue(promoRecup.equals(listePromotions.get(0)));

	}// end testAjouterCoursAvecPromotion()
	
//	/*===================== TEST LIAISON AVEC ETUDIANT (NOT OK) ============================*/
//	@Test
//	public void testAjouterCoursAvecEtudiant() {
//		
//		Etudiant etudiant = new Etudiant("motdepasse", "nom", "prenom", "email", "photo", "dateDeNaissance");
//		etudiantDao.ajouter(etudiant);
//		List<Etudiant> listeEtudiants = etudiantDao.getAllEtudiant();
//		
//		
//		Cours cours = new Cours("maths", "date", "duree", "description");
//		cours.setListeEtudiant((List<Etudiant>) etudiant); // devrait être un objet étudiant
//
//		coursDAO.ajouter(cours);
//
//		List<Cours> listeCours = coursDAO.getAllCours();
//
//		Cours coursRecup =  coursDAO.getById(listeCours.get(0).getIdCours());
//
//		// récup de la liste des étudiants du cours
//		Etudiant etudiantRecup = (Etudiant) coursRecup.getListeEtudiant();
//		// suppression
//		coursDAO.supprimer(listeCours.get(0));
//		coursDAO.getAllCours();
//
//		etudiantDao.supprimer(listeEtudiants.get(0));
//		etudiantDao.getAllEtudiant();
//
//		// Test si on recupere bien l'étudiant du cours NOT OK
//		Assert.assertTrue(etudiantRecup.equals(listeEtudiants.get(0)));
//
//	}// end testAjouterCoursAvecEtudiant()
	
	
	/*===================== TEST LIAISON AVEC EXERCICES ============================*/

}
