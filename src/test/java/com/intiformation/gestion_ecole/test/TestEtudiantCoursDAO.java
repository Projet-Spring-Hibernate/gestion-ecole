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

import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantCoursDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.EtudiantCours;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestEtudiantCoursDAO extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	@Qualifier("etudiantCoursDAOImpl")
	private IEtudiantCoursDAO etudiantCoursDAO;

	@Autowired
	private ICoursDAO coursDAO;

	@Autowired
	private IEtudiantDAO etudiantDao;

	/* ==================== AJOUT ETUDCOURS ================= */
	@Test
	public void testAjoutEtudiantCours() {

		EtudiantCours etudCours = new EtudiantCours(true, "motif");

		etudiantCoursDAO.ajouter(etudCours);

		// recup de la liste des cours dans bdd
		List<EtudiantCours> listeEtudCoursBdd = etudiantCoursDAO.getAllEtudiantCours();
		// suppression
		etudiantCoursDAO.supprimer(listeEtudCoursBdd.get(0));
		etudiantCoursDAO.getAllEtudiantCours();

		/// Test si l'ajout a bien été fait

		Assert.assertEquals(etudCours.getMotif(), listeEtudCoursBdd.get(0).getMotif());

	}// end testAjoutEtudiantCours()

	// --------------------------------------------------//
	// -------------- TEST GETBYID ----------------------//
	// --------------------------------------------------//

	@Test
	public void testGetByIdEtudiantCours() {

		// Création d'une nouveau cours (Sans l'id)
		EtudiantCours etudCours = new EtudiantCours(true, "motif");

		// Ajout du Etudcours à la bdd
		etudiantCoursDAO.ajouter(etudCours);

		// recup de la liste des cours dans bdd
		List<EtudiantCours> listeEtudCoursBdd = etudiantCoursDAO.getAllEtudiantCours();

		// Recup du cours
		EtudiantCours etudCoursRecup = etudiantCoursDAO.getById(listeEtudCoursBdd.get(0).getId());

		// suppression
		etudiantCoursDAO.supprimer(listeEtudCoursBdd.get(0));
		etudiantCoursDAO.getAllEtudiantCours();
		// -------------------------------------------------------------------------------------

		// Test si la recuperation a bien été faite
		Assert.assertTrue(etudCoursRecup.equals(etudCours));
	}// end testGetByIdEtudiantCours()

	/* ==================== MODIF ETUDIANTCOURS ======================== */
	@Test
	public void testModifierEtudiantCours() {

		EtudiantCours etudCoursAvantModif = new EtudiantCours(true, "motif");

		// Ajout du Etudcours à la bdd
		etudiantCoursDAO.ajouter(etudCoursAvantModif);

		// recup de la liste des cours dans bdd
		List<EtudiantCours> listeEtudCoursBdd = etudiantCoursDAO.getAllEtudiantCours();

		EtudiantCours etudCoursModif = listeEtudCoursBdd.get(0);
		etudCoursModif.setMotif("motif");

		etudiantCoursDAO.modifier(etudCoursModif);

		listeEtudCoursBdd = etudiantCoursDAO.getAllEtudiantCours();

		// suppression
		etudiantCoursDAO.supprimer(listeEtudCoursBdd.get(0));
		etudiantCoursDAO.getAllEtudiantCours();

		Assert.assertEquals(etudCoursModif.getMotif(), listeEtudCoursBdd.get(0).getMotif());

	}// end testModifierEtudiantCours()

	// --------------------------------------------------//
	// -------------- TEST SUPPRIMER --------------------//
	// --------------------------------------------------//

	@Test
	public void testSupprimerEtudiantCours() {
		// Création d'une nouveau cours (Sans l'id)
		EtudiantCours etudCours = new EtudiantCours(true, "motif");

		// Ajout du Etudcours à la bdd
		etudiantCoursDAO.ajouter(etudCours);

		// recup de la liste des cours dans bdd
		List<EtudiantCours> listeEtudCoursBdd = etudiantCoursDAO.getAllEtudiantCours();

		// suppression
		etudiantCoursDAO.supprimer(listeEtudCoursBdd.get(0));

		// Recup de la liste des cours dans la bdd après suppression
		listeEtudCoursBdd = etudiantCoursDAO.getAllEtudiantCours();

		// Test si la suppression a bien été faite
		Assert.assertEquals(listeEtudCoursBdd.size(), 0);

	}// end testSupprimerEtudiantCours

	/* ==================== RECUP LISTE ETUDIANTCOURS ================= */
	@Test
	public void testGetAllEtudiantCours() {

		// Création d'une nouveau cours (Sans l'id)
		EtudiantCours etudCours = new EtudiantCours(true, "motif");

		// Ajout du Etudcours à la bdd
		etudiantCoursDAO.ajouter(etudCours);

		// recup de la liste des cours dans bdd
		List<EtudiantCours> listeEtudCoursBdd = etudiantCoursDAO.getAllEtudiantCours();

		// suppression
		etudiantCoursDAO.supprimer(listeEtudCoursBdd.get(0));
		etudiantCoursDAO.getAllEtudiantCours();
		;

		// Test si la la liste fait la bonne taille
		Assert.assertEquals(listeEtudCoursBdd.size(), 1);
		;

	}// end testGetAllEtudiantCours

	/*
	 * ===================== TEST LIAISON AVEC ETUDIANT ============================
	 */
	@Test
	public void testAjouterEtudiantCoursAvecEtudiant() {

		Etudiant etudiant = new Etudiant("motdepasse", "nom", "prenom", "email", "photo", "dateDeNaissance");
		etudiantDao.ajouter(etudiant);
		List<Etudiant> listeEtudiants = etudiantDao.getAllEtudiant();

		EtudiantCours etudCours = new EtudiantCours(true, "motif");
		etudCours.setEtudiant(etudiant);
		etudiantCoursDAO.ajouter(etudCours);

		List<EtudiantCours> listeEtudiantCours = etudiantCoursDAO.getAllEtudiantCours();

		EtudiantCours etudCoursRecup = etudiantCoursDAO.getById(listeEtudiantCours.get(0).getId());

		// récup de la liste des étudiants du cours
		Etudiant etudiantRecup = (Etudiant) etudCoursRecup.getEtudiant();
		// suppression
		etudiantCoursDAO.supprimer(listeEtudiantCours.get(0));
		etudiantCoursDAO.getAllEtudiantCours();

		etudiantDao.supprimer(listeEtudiants.get(0));
		etudiantDao.getAllEtudiant();

		// Test si on recupere bien la matière du cours NOT OK
		Assert.assertTrue(etudiantRecup.equals(listeEtudiants.get(0)));

	}// end testAjouterEtudiantCoursAvecEtudiant()

	/* ===================== TEST LIAISON AVEC COURS ============================ */
	@Test
	public void testAjouterEtudiantCoursAvecCours() {

		Cours cours = new Cours("maths", "date", "duree", "description");
		coursDAO.ajouter(cours);
		List<Cours> listeCours = coursDAO.getAllCours();

		EtudiantCours etudCours = new EtudiantCours(true, "motif");
		etudCours.setCours(cours);
		etudiantCoursDAO.ajouter(etudCours);

		List<EtudiantCours> listeEtudiantCours = etudiantCoursDAO.getAllEtudiantCours();

		EtudiantCours etudCoursRecup = etudiantCoursDAO.getById(listeEtudiantCours.get(0).getId());

		// récup de la liste des cours d'etudiant cours
		Cours coursRecup = etudCoursRecup.getCours();
		// suppression
		etudiantCoursDAO.supprimer(listeEtudiantCours.get(0));
		etudiantCoursDAO.getAllEtudiantCours();

		coursDAO.supprimer(listeCours.get(0));
		coursDAO.getAllCours();

		// Test si on recupere bien la matière du cours NOT OK
		Assert.assertTrue(coursRecup.equals(listeCours.get(0)));

	}// end testAjouterEtudiantCoursAvecEtudiant()

}
