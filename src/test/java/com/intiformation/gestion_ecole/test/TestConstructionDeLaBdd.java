package com.intiformation.gestion_ecole.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.dao.IAdministrateurDao;
import com.intiformation.gestion_ecole.dao.IAdresseDao;
import com.intiformation.gestion_ecole.dao.IAideDAO;
import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IEnseignantMatierePromotionDao;
import com.intiformation.gestion_ecole.dao.IEtudiantCoursDAO;
import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.dao.IExerciceDAO;
import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.EtudiantCours;
import com.intiformation.gestion_ecole.domain.Exercice;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestConstructionDeLaBdd {

	@Autowired
	@Qualifier("enseignantDAOImpl")
	private IEnseignantDAO enseignatDao;
	
	@Autowired
	@Qualifier("etudiantDAOImpl")
	private IEtudiantDAO etudiantDao;
	
	@Autowired
	@Qualifier("administrateurDaoImpl")
	private IAdministrateurDao adminitrateurDao;
	
	@Autowired
	private IAdresseDao adresseDao;
	
	@Autowired
	private IAideDAO aideDao;
	
	@Autowired
	private ICoursDAO coursDao;
	
	@Autowired
	private IEtudiantCoursDAO etudiantCoursDao;
	
	@Autowired
	private IExerciceDAO exerciceDao;
	
	@Autowired
	private IMatiereDAO matiereDao;
	
	@Autowired
	private IPromotionDAO promotionDao;
	
	@Autowired
	private IEnseignantMatierePromotionDao enseignantMatierePromotionDao;
	
	@Test
	public void testJointure() {
		
		//Verifiaction que les tables sont vides
		
		System.out.println("\n Liste des administrateurs : "+ adminitrateurDao.getAllAdministrateur()+"\n");
		System.out.println("\n Liste des etudiants : "+ etudiantDao.getAllEtudiant()+"\n");
		System.out.println("\n Liste des enseignants : "+ enseignatDao.listEnseignant()+"\n");
		System.out.println("\n Liste des adresses : "+ adresseDao.getAll()+"\n");
		System.out.println("\n Liste des cours : "+ coursDao.getAllCours()+"\n");
		System.out.println("\n Liste des etudiantsCours : "+ etudiantCoursDao.getAllEtudiantCours()+"\n");
		System.out.println("\n Liste des exercices : "+ exerciceDao.listeExercice()+"\n");
		System.out.println("\n Liste des promotions : "+ promotionDao.listePromotion()+"\n");
		System.out.println("\n Liste des matières : "+ matiereDao.listMatiere()+"\n");
		
		
		//Creation des personnes
		
		Enseignant enseignant1 = new Enseignant("0000", "Prof1", "1", "prof1@gmail.com");
		Enseignant enseignant2 = new Enseignant("0000", "Prof2", "2", "prof2@gmail.com");
		Enseignant enseignant3 = new Enseignant("0000", "Prof3", "3", "prof3@gmail.com");


		Etudiant etudiant1 = new Etudiant("0000", "etudiant1", "1", "etudiant1@gmail.com", "etudiant1.jpg", "01/01/2000");
		Etudiant etudiant2 = new Etudiant("0000", "etudiant2", "2", "etudiant2@gmail.com", "etudiant2.jpg", "01/01/2000");
		Etudiant etudiant3 = new Etudiant("0000", "etudiant3", "3", "etudiant3@gmail.com", "etudiant3.jpg", "01/01/2000");
		Etudiant etudiant4 = new Etudiant("0000", "etudiant4", "4", "etudiant4@gmail.com", "etudiant4.jpg", "01/01/2000");
		Etudiant etudiant5 = new Etudiant("0000", "etudiant5", "5", "etudiant5@gmail.com", "etudiant5.jpg", "01/01/2000");

		Administrateur admin1 = new Administrateur("0000", "admin1", "1", "admin@gmail.com");
		
		//Creation des adresses (1 par personne)

		Adresse adresse1= new Adresse("rue1", "75000", "Paris");
		Adresse adresse2= new Adresse("rue2", "75002", "Paris");
		Adresse adresse3= new Adresse("rue3", "75003", "Paris");
		Adresse adresse4= new Adresse("rue4", "75004", "Paris");
		Adresse adresse5= new Adresse("rue5", "75005", "Paris");
		Adresse adresse6= new Adresse("rue6", "75006", "Paris");
		Adresse adresse7= new Adresse("rue7", "75007", "Paris");
		Adresse adresse8= new Adresse("rue8", "75008", "Paris");
		Adresse adresse9= new Adresse("rue9", "75009", "Paris");

		
		//Creation des promotions
		
		Promotion promotion1 = new Promotion("Promo1");
		Promotion promotion2 = new Promotion("Promo2");
		
		
		//Creation des matières
		Matiere matiere1 = new Matiere("Math");
		Matiere matiere2 = new Matiere("Chimie");
		Matiere matiere3 = new Matiere("Meca");

	
		//Creation des cours 

		Cours cours1 = new Cours("cours1", "lundi", "3h", "cours de Math promo1");
		Cours cours2 = new Cours("cours2", "mardi", "3h", "cours de Chimie promo1");
		Cours cours3 = new Cours("cours3", "mercredi", "3h", "cours de Meca promo1");
		Cours cours4 = new Cours("cours4", "jeudi", "3h", "cours de Math promo2");
		Cours cours5 = new Cours("cours5", "vendredi", "3h", "cours de Chimie promo2");
		Cours cours6 = new Cours("cours6", "samedi", "3h", "cours de Meca promo2");
		
		//Creation d'exercices
		
		Exercice exercice1 = new Exercice("cours de Math promo1 exo1 "); //pour le cours 1
		Exercice exercice2 = new Exercice("cours de Math promo1 exo2"); //pour le cours 1
		Exercice exercice3 = new Exercice("cours de Meca promo1 exo1 "); //pour le cours 3
		Exercice exercice4 = new Exercice("cours de Meca promo2 exo1"); //pour le cours 6
		Exercice exercice5 = new Exercice("cours de Meca promo2 exo2"); //pour le cours 6
		Exercice exercice6 = new Exercice("cours de Chimie promo2 exo1"); //pour le cours 5
		Exercice exercice7 = new Exercice("cours de Chimie promo2 exo2");//pour le cours 5
		
		
		//Creation d'etudiantCours (presence/absence au cours)
		
		EtudiantCours presence1 = new EtudiantCours(true, "motif1");
		EtudiantCours presence2 = new EtudiantCours(false, "/");
		EtudiantCours presence3 = new EtudiantCours(true, "motif2");
		EtudiantCours presence4 = new EtudiantCours(false, "/");
		EtudiantCours presence5 = new EtudiantCours(false, "/");
		EtudiantCours presence6 = new EtudiantCours(true, "motif3");
		EtudiantCours presence7 = new EtudiantCours(false, "/");
		EtudiantCours presence8 = new EtudiantCours(true, "motif4");
		EtudiantCours presence9 = new EtudiantCours(false, "/");
		EtudiantCours presence10 = new EtudiantCours(true, "motif5");
		
		
		//creation jointures EnseignantMatierePromotion
		
		EnseignantMatierePromotion enseignantMatierePromotion1 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion2 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion3 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion4 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion5 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion6 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion7 = new EnseignantMatierePromotion();
		
		
		//======================================================================================================================//
		
		
		// Attribution des adresses aux personnes (vis versa automatique)
		
		enseignant1.addAdresse(adresse1);
		enseignant2.addAdresse(adresse2);
		enseignant3.addAdresse(adresse3);
		
		etudiant1.addAdresse(adresse4);
		etudiant2.addAdresse(adresse5);
		etudiant3.addAdresse(adresse6);
		etudiant4.addAdresse(adresse7);
		etudiant5.addAdresse(adresse8);
		
		admin1.addAdresse(adresse9);
		
		
		// L'admin n'est relié à rien d'autre qu'à l'adresse. On peut l'ajouter maintenant à la bd
		adminitrateurDao.ajouter(admin1);
		
		//test de la recuperation de l'admin et de son adresse
		
		Administrateur admin1Recup = (Administrateur) adminitrateurDao.getById(1L);
		
		System.out.println("Admin1 après recuperation : " + admin1Recup);
		System.out.println("Adresse de l'admin 1 : " + admin1Recup.getAdresse());
		
		
		System.out.println("adresse 1 après recup : "+ adresseDao.getById(1L));
		System.out.println("Propriétaire de l'adresse 1 : "+ adresseDao.getById(1L).getPersonne());
		
		
		//attribution des Matières et promotions aux enseignants
		
		
		cours1.addExercice(exercice1);
		cours1.addExercice(exercice2);
		
		cours3.addExercice(exercice3);
		
		cours6.addExercice(exercice4);
		cours6.addExercice(exercice5);
		
		cours5.addExercice(exercice6);
		cours5.addExercice(exercice7);
		
		promotion1.addCours(cours1);
		promotion1.addCours(cours2);
		promotion1.addCours(cours3);
		
		promotion2.addCours(cours4);
		promotion2.addCours(cours5);
		promotion2.addCours(cours6);
		
		promotion1.addEtudiant(etudiant1);
		promotion1.addEtudiant(etudiant2);
		promotion1.addEtudiant(etudiant3); //l'étudiant 3 est dans 2 promos
		promotion2.addEtudiant(etudiant3);
		promotion2.addEtudiant(etudiant4);
		promotion2.addEtudiant(etudiant5);
		
		enseignantMatierePromotion1.addEnseignant(enseignant1);
		enseignantMatierePromotion1.addPromotion(promotion2);
		enseignantMatierePromotion1.addMatiere(matiere1);
		
		enseignantMatierePromotion2.addEnseignant(enseignant1);
		enseignantMatierePromotion2.addPromotion(promotion2);
		enseignantMatierePromotion2.addMatiere(matiere2);
		
		enseignantMatierePromotion3.addEnseignant(enseignant1);
		enseignantMatierePromotion3.addPromotion(promotion1);
		enseignantMatierePromotion3.addMatiere(matiere1);
		
		enseignantMatierePromotion4.addEnseignant(enseignant1);
		enseignantMatierePromotion4.addPromotion(promotion1);
		enseignantMatierePromotion4.addMatiere(matiere2);
		
		enseignantMatierePromotion5.addEnseignant(enseignant2);
		enseignantMatierePromotion5.addPromotion(promotion2);
		enseignantMatierePromotion5.addMatiere(matiere2);
		
		enseignantMatierePromotion6.addEnseignant(enseignant2);
		enseignantMatierePromotion6.addPromotion(promotion2);
		enseignantMatierePromotion6.addMatiere(matiere3);
		
		enseignantMatierePromotion7.addEnseignant(enseignant3);
		enseignantMatierePromotion7.addPromotion(promotion1);
		enseignantMatierePromotion7.addMatiere(matiere3);
		

		
		enseignantMatierePromotionDao.ajouter(enseignantMatierePromotion1);
		enseignantMatierePromotionDao.ajouter(enseignantMatierePromotion2);
		enseignantMatierePromotionDao.ajouter(enseignantMatierePromotion3);
		enseignantMatierePromotionDao.ajouter(enseignantMatierePromotion4);
		enseignantMatierePromotionDao.ajouter(enseignantMatierePromotion5);
		enseignantMatierePromotionDao.ajouter(enseignantMatierePromotion6);
		enseignantMatierePromotionDao.ajouter(enseignantMatierePromotion7);
		
		
		Matiere matiere1Recup=matiereDao.getById(1L);
		Matiere matiere2Recup=matiereDao.getById(2L);
		Matiere matiere3Recup=matiereDao.getById(3L);
		
		Cours cours1Recup = coursDao.getById(1L);
		Cours cours2Recup = coursDao.getById(2L);
		Cours cours3Recup = coursDao.getById(3L);
		Cours cours4Recup = coursDao.getById(4L);
		Cours cours5Recup = coursDao.getById(5L);
		Cours cours6Recup = coursDao.getById(6L);
		
		
		matiere1Recup.addCours(cours1Recup);
		matiere1Recup.addCours(cours4Recup);
		
		matiere2Recup.addCours(cours2Recup);
		matiere2Recup.addCours(cours5Recup);
		
		matiere3Recup.addCours(cours3Recup);
		matiere3Recup.addCours(cours6Recup);		

		
		matiereDao.modifier(matiere1Recup);
		matiereDao.modifier(matiere2Recup);
		matiereDao.modifier(matiere3Recup);
		
		
		Etudiant etudiant1Recup= (Etudiant) etudiantDao.getById(5L);
		Etudiant etudiant2Recup= (Etudiant) etudiantDao.getById(6L);
		Etudiant etudiant3Recup= (Etudiant) etudiantDao.getById(7L);
		Etudiant etudiant4Recup= (Etudiant) etudiantDao.getById(8L);
		Etudiant etudiant5Recup= (Etudiant) etudiantDao.getById(9L);
		
		presence1.setCours(cours1Recup);
		presence2.setCours(cours1Recup);
		presence3.setCours(cours1Recup);
		presence4.setCours(cours2Recup);
		presence5.setCours(cours2Recup);
		presence6.setCours(cours2Recup);
		presence7.setCours(cours5Recup);
		presence8.setCours(cours5Recup);
		presence9.setCours(cours6Recup);
		presence10.setCours(cours6Recup);
		
		presence1.setEtudiant(etudiant1Recup);
		presence2.setEtudiant(etudiant2Recup);
		presence3.setEtudiant(etudiant3Recup);
		presence4.setEtudiant(etudiant1Recup);
		presence5.setEtudiant(etudiant2Recup);
		presence6.setEtudiant(etudiant3Recup);
		presence7.setEtudiant(etudiant4Recup);
		presence8.setEtudiant(etudiant5Recup);
		presence9.setEtudiant(etudiant4Recup);
		presence10.setEtudiant(etudiant5Recup);
	
		etudiantCoursDao.ajouter(presence1);
		etudiantCoursDao.ajouter(presence2);
		etudiantCoursDao.ajouter(presence3);
		etudiantCoursDao.ajouter(presence4);
		etudiantCoursDao.ajouter(presence5);
		etudiantCoursDao.ajouter(presence6);
		etudiantCoursDao.ajouter(presence7);
		etudiantCoursDao.ajouter(presence8);
		etudiantCoursDao.ajouter(presence9);
		etudiantCoursDao.ajouter(presence10);
		
//		enseignatDao.ajouter(enseignant1);
//		enseignatDao.ajouter(enseignant2);
//		enseignatDao.ajouter(enseignant3);
//		
//		
//		etudiantDao.ajouter(etudiant1);
//		etudiantDao.ajouter(etudiant2);
//		etudiantDao.ajouter(etudiant3);
//		etudiantDao.ajouter(etudiant4);
//		etudiantDao.ajouter(etudiant5);
//		
//		
//		promotionDao.ajouter(promotion1);
//		promotionDao.ajouter(promotion2);
//		
//		matiereDao.ajouter(matiere1);
//		matiereDao.ajouter(matiere2);
//		matiereDao.ajouter(matiere3);
//		
//		
//		coursDao.ajouter(cours1);
//		coursDao.ajouter(cours2);
//		coursDao.ajouter(cours3);
//		coursDao.ajouter(cours4);
//		coursDao.ajouter(cours5);
//		coursDao.ajouter(cours6);
		
		
//		etudiantCoursDao.ajouter(presence1);
//		etudiantCoursDao.ajouter(presence2);
//		etudiantCoursDao.ajouter(presence3);
//		etudiantCoursDao.ajouter(presence4);
//		etudiantCoursDao.ajouter(presence5);
//		etudiantCoursDao.ajouter(presence6);
//		etudiantCoursDao.ajouter(presence7);
//		etudiantCoursDao.ajouter(presence8);
//		etudiantCoursDao.ajouter(presence9);
//		etudiantCoursDao.ajouter(presence10);
		
//		exerciceDao.ajouter(exercice1);
//		exerciceDao.ajouter(exercice2);
//		exerciceDao.ajouter(exercice3);
//		exerciceDao.ajouter(exercice4);
//		exerciceDao.ajouter(exercice5);
//		exerciceDao.ajouter(exercice6);
//		
//		cours1.addExercice(exercice1);
//		cours1.addExercice(exercice2);
//		
//		cours3.addExercice(exercice3);
//		
//		cours6.addExercice(exercice4);
//		cours6.addExercice(exercice5);
//		
//		cours5.addExercice(exercice6);
//		cours5.addExercice(exercice7);
//		
//		promotion1.addCours(cours1);
//		promotion1.addCours(cours2);
//		promotion1.addCours(cours3);
//		
//		promotion2.addCours(cours4);
//		promotion2.addCours(cours5);
//		promotion2.addCours(cours6);
//		
//		
//		promotion1.addEtudiant(etudiant1);
//		promotion1.addEtudiant(etudiant2);
//		promotion1.addEtudiant(etudiant3); //l'étudiant 3 est dans 2 promos
//		promotion2.addEtudiant(etudiant3);
//		promotion2.addEtudiant(etudiant4);
//		promotion2.addEtudiant(etudiant5);
		
//		enseignant1.addMatiere(matiere1);
//		enseignant1.addMatiere(matiere2);
//		enseignant2.addMatiere(matiere2);
//		enseignant2.addMatiere(matiere3);
//		enseignant3.addMatiere(matiere3);
//		
//		
//		enseignant1.addPromotion(promotion1);
//		enseignant3.addPromotion(promotion1);
//		
//		enseignant1.addPromotion(promotion2);
//		enseignant2.addPromotion(promotion2);
		

//		
//		enseignatDao.ajouter(enseignant1);
//		enseignatDao.ajouter(enseignant2);
//		enseignatDao.ajouter(enseignant3);
//		
//		
//		Cours cours1Recup =coursDao.getById(1L);
//		Cours cours2Recup =coursDao.getById(2L);
//		Cours cours3Recup =coursDao.getById(3L);
//		Cours cours4Recup =coursDao.getById(4L);
//		Cours cours5Recup =coursDao.getById(5L);
//		Cours cours6Recup =coursDao.getById(6L);
//		
//		System.out.println("\n Cours 1 recup : "+ cours1Recup);
//		System.out.println("\n Cours 1 recup Promotion: "+ cours1Recup.getPromotion());
//		
//		
//		
//		Etudiant etudiant1Recup= (Etudiant) etudiantDao.getById(5L);
//		Etudiant etudiant2Recup= (Etudiant) etudiantDao.getById(6L);
//		Etudiant etudiant3Recup= (Etudiant) etudiantDao.getById(7L);
//		Etudiant etudiant4Recup= (Etudiant) etudiantDao.getById(8L);
//		Etudiant etudiant5Recup= (Etudiant) etudiantDao.getById(9L);
//		
//		presence1.setCours(cours1Recup);
//		presence2.setCours(cours1Recup);
//		presence3.setCours(cours1Recup);
//		presence4.setCours(cours2Recup);
//		presence5.setCours(cours2Recup);
//		presence6.setCours(cours2Recup);
//		presence7.setCours(cours5Recup);
//		presence8.setCours(cours5Recup);
//		presence9.setCours(cours6Recup);
//		presence10.setCours(cours6Recup);
//		
//		presence1.setEtudiant(etudiant1Recup);
//		presence2.setEtudiant(etudiant2Recup);
//		presence3.setEtudiant(etudiant3Recup);
//		presence4.setEtudiant(etudiant1Recup);
//		presence5.setEtudiant(etudiant2Recup);
//		presence6.setEtudiant(etudiant3Recup);
//		presence7.setEtudiant(etudiant4Recup);
//		presence8.setEtudiant(etudiant5Recup);
//		presence9.setEtudiant(etudiant4Recup);
//		presence10.setEtudiant(etudiant5Recup);
//	
//		etudiantCoursDao.ajouter(presence1);
//		etudiantCoursDao.ajouter(presence2);
//		etudiantCoursDao.ajouter(presence3);
//		etudiantCoursDao.ajouter(presence4);
//		etudiantCoursDao.ajouter(presence5);
//		etudiantCoursDao.ajouter(presence6);
//		etudiantCoursDao.ajouter(presence7);
//		etudiantCoursDao.ajouter(presence8);
//		etudiantCoursDao.ajouter(presence9);
//		etudiantCoursDao.ajouter(presence10);
		
//		etudiantDao.ajouter(etudiant1);
//		etudiantDao.ajouter(etudiant2);
//		etudiantDao.ajouter(etudiant3);
//		etudiantDao.ajouter(etudiant4);
//		etudiantDao.ajouter(etudiant5);
		
		//===================================================================================
		
		System.out.println("\n Liste des administrateurs : "+ adminitrateurDao.getAllAdministrateur()+"\n");
		
		System.out.println("\n Liste des etudiants : "+ etudiantDao.getAllEtudiant()+"\n");
		
		System.out.println("\n Liste des enseignants : "+ enseignatDao.listEnseignant()+"\n");
		
		System.out.println("\n Liste des adresses : "+ adresseDao.getAll()+"\n");
		
		System.out.println("\n Liste des cours : "+ coursDao.getAllCours()+"\n");
		
		System.out.println("\n Liste des etudiantsCours : "+ etudiantCoursDao.getAllEtudiantCours()+"\n");
		
		System.out.println("\n Liste des exercices : "+ exerciceDao.listeExercice()+"\n");
		
		System.out.println("\n Liste des promotions : "+ promotionDao.listePromotion()+"\n");
		
		System.out.println("\n Liste des matières : "+ matiereDao.listMatiere()+"\n");
		
		System.out.println("\n Cours 1 Recup :"+coursDao.getById(1L));
		System.out.println("\n Cours 1 Recup promotion :"+coursDao.getById(1L).getPromotion());
		
		System.out.println("\n exercice 1 Recup :"+ exerciceDao.getById(1L));
		System.out.println("\n exercice 1 Recup cours :"+ exerciceDao.getById(1L).getCours());
		
//		
//		//Ajout des liste aux promotions et ajout des promotions à la bdd
//		
//		enseignant1.addMatiere(matiere1);
//		enseignant1.addMatiere(matiere2);
//		
//		enseignant2.addMatiere(matiere2);
//		enseignant2.addMatiere(matiere3);
//		
//		promotion1.addEnseignant(enseignant1);
//		promotion1.addMatiere(matiere1);
//		promotion1.addMatiere(matiere2);
//		
//		promotion2.addEnseignant(enseignant2);
//		promotion2.addMatiere(matiere2);
//		promotion2.addMatiere(matiere3);
//		
//		
//		promotionDao.ajouter(promotion1);
//		promotionDao.ajouter(promotion2);
//		
//
//		System.out.println(promotionDao.listePromotion());
//		Promotion promo1Recup = promotionDao.getById(1L);
//		Promotion promo2Recup = promotionDao.getById(2L);
//		
//		Matiere matiere1Recup = matiereDao.getById(1L);
//		Matiere matiere2Recup = matiereDao.getById(2L);
//		Matiere matiere3Recup = matiereDao.getById(3L);
//		
//		Enseignant enseignant1Recup = (Enseignant) enseignatDao.getById(1L);
//		Enseignant enseignant2Recup = (Enseignant) enseignatDao.getById(2L);
//		
//		System.out.println("\n Promo 1 :"+ promo1Recup);
//		System.out.println("Liste des matières de la promo 1 "+promo1Recup.getListeMatiere());
//		System.out.println("Liste des enseignants de la promo 1 "+promo1Recup.getListeEnseignant());
//		
//		System.out.println("\n Promo 2 :"+ promo2Recup);
//		System.out.println("Liste des matières de la promo 2 "+promo2Recup.getListeMatiere());
//		System.out.println("Liste des enseignants de la promo 2 "+promo2Recup.getListeEnseignant());
//		
//		System.out.println("\n Matière 1 :"+ matiere1Recup);
//		System.out.println("Liste des promos de la matière 1 (maths) "+matiere1Recup.getListePromotion());
//		System.out.println("Liste des enseignants de la  matière 1 (maths) "+matiere1Recup.getListeEnseignant());
//		
//		System.out.println("\n Matière 2 :"+ matiere2Recup);
//		System.out.println("Liste des promos de la matière 2 (chimie) "+matiere2Recup.getListePromotion());
//		System.out.println("Liste des enseignants de la  matière 2 (maths) "+matiere2Recup.getListeEnseignant());
//		
//		System.out.println("\n Matière 3 :"+ matiere3Recup);
//		System.out.println("Liste des promos de la matière 3 (meca) "+matiere3Recup.getListePromotion());	
//		System.out.println("Liste des enseignants de la  matière 3 (maths) "+matiere3Recup.getListeEnseignant());
//		
//		System.out.println("\n Enseignant 1 :"+ enseignant1Recup);
//		System.out.println("Liste des matières de l'enseignant 1 "+enseignant1Recup.getListeMatiere());
//		System.out.println("Liste des promos de l'enseignant 1 "+enseignant1Recup.getListePromotion());
//		
//		System.out.println("\n Enseignant 2 :"+ enseignant2Recup);
//		System.out.println("Liste des matières de l'enseignant 2 "+enseignant2Recup.getListeMatiere());
//		System.out.println("Liste des promos de l'enseignant 2 "+enseignant2Recup.getListePromotion());
//		
//
//		System.out.println("\n Liste des enseignants :" +enseignatDao.getAll());
//		System.out.println("\n Liste des matières :" +matiereDao.listMatiere());
//		System.out.println("\n Liste des promo :"+ promotionDao.listePromotion());
	}//end methode
	
	
}//end class
