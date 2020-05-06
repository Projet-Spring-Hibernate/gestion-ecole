package com.intiformation.gestion_ecole.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

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
import com.intiformation.gestion_ecole.dao.IPersonneDao;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.domain.Adresse;
import com.intiformation.gestion_ecole.domain.Aide;
import com.intiformation.gestion_ecole.domain.Cours;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;
import com.intiformation.gestion_ecole.domain.Etudiant;
import com.intiformation.gestion_ecole.domain.EtudiantCours;
import com.intiformation.gestion_ecole.domain.Exercice;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Personne;
import com.intiformation.gestion_ecole.domain.Promotion;
/**
 * Controleur pour initialiser la bdd à chaque ouverture du site
 * @author IN-MP-018
 *
 */
@Controller
public class WelcomeController {
	
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
	@Qualifier("personneDaoImpl")
	private IPersonneDao personneDao;
	
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
	
	@RequestMapping(value = "/bienvenue", method = RequestMethod.GET)
	public String afficherPageAccueil(ModelMap modele) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Personne personneConnecte = personneDao.getPersonneByMail(auth.getName());
		System.out.println("Personne connecté "+personneConnecte);
		modele.addAttribute("attribut_personneConnecte", personneConnecte);
		
		return "index";
	}//end afficherPageAccueil
	
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public View remplirlaBdd() {
		
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
		
		Enseignant enseignant1 = new Enseignant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", "Rogue", "Severus", "rogue@gmail.com");
		Enseignant enseignant2 = new Enseignant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", "McGonagall", "Minerva", "mcGonagall@gmail.com");
		Enseignant enseignant3 = new Enseignant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", "Chourave", "Pomora", "chourave@gmail.com");
		Enseignant enseignant4 = new Enseignant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", "Flitwick", "Filius", "flitwick@gmail.com");

		Etudiant etudiant1 = new Etudiant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", " Potter", "Harry", "potter@gmail.com", "etudiant1.jpg", "15/02/1993");
		Etudiant etudiant2 = new Etudiant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", " Granger", "Hermione", "granger@gmail.com", "etudiant2.jpg", "20/04/1993");
		Etudiant etudiant3 = new Etudiant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", " Malefoy", "Drago", "malefoy@gmail.com", "etudiant3.jpg", "25/12/1993");
		Etudiant etudiant4 = new Etudiant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", " Lestrange", "Bellatrix", "lestrange@gmail.com", "etudiant4.jpg", "14/07/1993");
		Etudiant etudiant5 = new Etudiant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", " Geignarde", "Mimi", "geignarde@gmail.com", "etudiant5.jpg", "01/01/1993");
		Etudiant etudiant6 = new Etudiant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", " cho", "Chang", "cho@gmail.com", "etudiant6.jpg", "16/08/1993");
		Etudiant etudiant7 = new Etudiant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", " Diggory", "Cedric", "diggory@gmail.com", "etudiant7.jpg", "22/10/1993");
		Etudiant etudiant8 = new Etudiant("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", " Tonks", "Nymphadora", "tonks@gmail.com", "etudiant8.jpg", "26/09/1993");
		

		Administrateur admin1 = new Administrateur("$2a$10$6Pb4872xfcnKiHuTr5GHZebyH5ZtQm0wZMgrO83BbHgITWXpyjoSi", " Dumbledore", "Albus", "dumbledore@gmail.com");
		
		//Creation des adresses (1 par personne)

		Adresse adresse1= new Adresse("10 Rue du Chaudron", "75015", "Pré-Au-lard");
		Adresse adresse2= new Adresse("26 Rue du Hibou", "75002", "Azkaban");
		Adresse adresse3= new Adresse("11 Rue du Phoenix", "75013", "Private Drive");
		Adresse adresse4= new Adresse("20 Rue de la Magie", "75004", "Chemin de Traverse");
		Adresse adresse5= new Adresse("1 Rue de la Baguette", "75018", "Little Whinging");
		Adresse adresse6= new Adresse("5 Rue du Nifleur", "75006", "Bristol");
		Adresse adresse7= new Adresse("45 Rue de la Coupe", "75017", "Poudlard");
		Adresse adresse8= new Adresse("6 Rue du Dragon", "75008", "Londres");
		Adresse adresse9= new Adresse("3 Rue du Horcrux", "75009", "Godric");
		Adresse adresse10= new Adresse("6 Rue de Gloucester", "75010", "Londres");
		Adresse adresse11= new Adresse("4 Rue du Balais", "75003", "Bristol");
		Adresse adresse12= new Adresse("25 Rue du vif d'or", "75014", "Pré-au-lard");
		Adresse adresse13= new Adresse("3 Rue Picadilly", "75009", "Londres");

		
		//Creation des promotions
		
		Promotion promotion1 = new Promotion("Serpentard");
		Promotion promotion2 = new Promotion("Serdaigle");
		Promotion promotion3 = new Promotion("Poufsouffle");
		Promotion promotion4 = new Promotion("Gryffondor");
		
		
		//Creation des matières
		Matiere matiere1 = new Matiere("Défense contre les forces du Mal");
		Matiere matiere2 = new Matiere("Potion");
		Matiere matiere3 = new Matiere("Histoire de la Magie");

	
		//Creation des cours 

		Cours cours1 = new Cours("cours1", "lundi", "3h", "cours de Défense contre les forces du Mal Serpentard");
		Cours cours2 = new Cours("cours2", "mardi", "3h", "cours de Potion Serdaigle");
		Cours cours3 = new Cours("cours3", "mercredi", "3h", "cours d'Histoire de la Magie Poufsouffle");
		Cours cours4 = new Cours("cours4", "jeudi", "3h", "cours de Potion Poufsouffle");
		Cours cours5 = new Cours("cours5", "vendredi", "3h", "cours d'Histoire de la Magie Serpentard");
		Cours cours6 = new Cours("cours6", "samedi", "3h", "cours de Défense contre les forces du Mal Gryffondor");
		
		//Creation d'exercices
		
		Exercice exercice1 = new Exercice("cours de Potion Gryffondor Filtre d'amour "); //pour le cours 1
		Exercice exercice2 = new Exercice("cours d'Histoire de la Magie Serpentard Les Sorts"); //pour le cours 1
		Exercice exercice3 = new Exercice("cours de Défense contre les forces du Mal Gryffondor Endoloris "); //pour le cours 3
		Exercice exercice4 = new Exercice("cours d'Histoire de la Magie Serpentard Botanique"); //pour le cours 6
		Exercice exercice5 = new Exercice("cours de Potion Serdaigle Métamorphose"); //pour le cours 6
		Exercice exercice6 = new Exercice("cours de Potion Poufsouffle Potion de Chance"); //pour le cours 5
		Exercice exercice7 = new Exercice("cours de Défense contre les forces du Mal Serpentard Avada Kedavra");//pour le cours 5
		
		
		//Creation d'etudiantCours (presence/absence au cours)
		
		EtudiantCours presence1 = new EtudiantCours(true, "motif1");
		EtudiantCours presence2 = new EtudiantCours(false, "/");
		EtudiantCours presence3 = new EtudiantCours(true, "motif2");
		EtudiantCours presence4 = new EtudiantCours(false, "/");
		EtudiantCours presence5 = new EtudiantCours(false, "/");
		EtudiantCours presence6 = new EtudiantCours(true, "motif3");
		EtudiantCours presence7 = new EtudiantCours(false, "/");

		
		
		//creation jointures EnseignantMatierePromotion
		
		EnseignantMatierePromotion enseignantMatierePromotion1 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion2 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion3 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion4 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion5 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion6 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion7 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion8 = new EnseignantMatierePromotion();
		EnseignantMatierePromotion enseignantMatierePromotion9 = new EnseignantMatierePromotion();
		
		//======================================================================================================================//
		
		
		// Attribution des adresses aux personnes (vis versa automatique)
		
		enseignant1.addAdresse(adresse1);
		enseignant2.addAdresse(adresse2);
		enseignant3.addAdresse(adresse3);
		enseignant4.addAdresse(adresse12);
		
		etudiant1.addAdresse(adresse4);
		etudiant2.addAdresse(adresse5);
		etudiant3.addAdresse(adresse6);
		etudiant4.addAdresse(adresse7);
		etudiant5.addAdresse(adresse8);
		etudiant6.addAdresse(adresse9);
		etudiant7.addAdresse(adresse10);
		etudiant8.addAdresse(adresse11);
		
		admin1.addAdresse(adresse13);
		
		
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
		promotion1.addEtudiant(etudiant3);
		promotion1.addEtudiant(etudiant4); 
		
		promotion2.addEtudiant(etudiant2);
		promotion2.addEtudiant(etudiant5);
		promotion2.addEtudiant(etudiant6);
		
		promotion3.addEtudiant(etudiant7);
		promotion3.addEtudiant(etudiant8);
		
		promotion4.addEtudiant(etudiant1);
		promotion4.addEtudiant(etudiant2);
		promotion4.addEtudiant(etudiant8);
		
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
		enseignantMatierePromotion5.addPromotion(promotion3);
		enseignantMatierePromotion5.addMatiere(matiere1);
		
		enseignantMatierePromotion6.addEnseignant(enseignant2);
		enseignantMatierePromotion6.addPromotion(promotion4);
		enseignantMatierePromotion6.addMatiere(matiere2);
		
		enseignantMatierePromotion7.addEnseignant(enseignant3);
		enseignantMatierePromotion7.addPromotion(promotion1);
		enseignantMatierePromotion7.addMatiere(matiere3);
		
		enseignantMatierePromotion8.addEnseignant(enseignant4);
		enseignantMatierePromotion8.addPromotion(promotion3);
		enseignantMatierePromotion8.addMatiere(matiere3);
		
		enseignantMatierePromotion9.addEnseignant(enseignant4);
		enseignantMatierePromotion9.addPromotion(promotion1);
		enseignantMatierePromotion9.addMatiere(matiere3);
		

		
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

		
		presence1.setCours(cours1Recup);
		presence2.setCours(cours1Recup);
		presence3.setCours(cours1Recup);
		presence4.setCours(cours2Recup);
		presence5.setCours(cours2Recup);
		presence6.setCours(cours2Recup);


		
		presence1.setEtudiant(etudiant1Recup);
		presence2.setEtudiant(etudiant2Recup);
		presence3.setEtudiant(etudiant3Recup);
		presence4.setEtudiant(etudiant1Recup);
		presence5.setEtudiant(etudiant2Recup);
		presence6.setEtudiant(etudiant3Recup);
		presence7.setEtudiant(etudiant4Recup);


	
		etudiantCoursDao.ajouter(presence1);
		etudiantCoursDao.ajouter(presence2);
		etudiantCoursDao.ajouter(presence3);
		etudiantCoursDao.ajouter(presence4);
		etudiantCoursDao.ajouter(presence5);
		etudiantCoursDao.ajouter(presence6);
		etudiantCoursDao.ajouter(presence7);

		

		//==================================================================================
		
		// ----------- Suppression des doublons ---------------//

		List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = enseignantMatierePromotionDao.getAllEnseignantMatierePromotion();
		
		// Création d'une liste vide dans laquelle on va transferrer les objets  distincts
		List<EnseignantMatierePromotion> listeEnseignantMatierePromotionDISTINCT = new ArrayList<>();
		
		// On parcourt la liste des combinaisons
		for (EnseignantMatierePromotion combinaison : listeEnseignantMatierePromotion) {
				// Creation d'un compteur
				int i = 0;
				// On parcourt la liste distinct
				for (EnseignantMatierePromotion combinaisonDistinct : listeEnseignantMatierePromotionDISTINCT) {
					// test si la combinaison est déjà presente dans la liste distinct
					if (combinaisonDistinct.getPromotion().getIdPromotion() == combinaison.getPromotion().getIdPromotion()
							&& combinaisonDistinct.getMatiere().getIdMatiere() == combinaison.getMatiere().getIdMatiere()) {
						// => elle est déjà presente => On incremente le compteur
						i++;
					} // end if
				} // end for

				// Test si le compteur==0 cad si la combinaison n'est pas déjà presente dans la
				// liste distinct
				if (i == 0) {
					// => Elle n'est pas déjà presente => on l'ajoute
					listeEnseignantMatierePromotionDISTINCT.add(combinaison);
				}else {
					//=> Elle est deja dans la liste => on la supprime
					enseignantMatierePromotionDao.supprimer(combinaison);
				}

				
		} // end for
		
		//===================================================================================
		
		aideDao.ajouter(new Aide("admin_welcome", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_listeEnseignants", "Liste de tous les enseignants de la base de données."));
		aideDao.ajouter(new Aide("administrateur_listeEtudiants", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_listePromotion", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_listeMatieres", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_listeCours", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_listeAbsence", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_listeAides", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_ajout_enseignant", "Formulaire d'ajout d'un enseignant dans la base de donnés."));
		aideDao.ajouter(new Aide("administrateur_ajout_etudiant", "Formulaire d'ajout d'un etudiant dans la base de donnés."));
		aideDao.ajouter(new Aide("administrateur_ajout_promotion", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur _ajout_matiere", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_ajout_cours", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_modif_enseignant", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_modif_etudiant", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_modif_promotion", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur _modif_matiere", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_modif_cours", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_modif_aide", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_listeEtudiants", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_listeAbsence", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_listePromotion", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_listeMatieres", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_listeCours", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_ajout_exercice", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_ajout_cours", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_ajout_absence", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_modif_exercice", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_modif_cours", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("enseignant_ajout_modif_liste_absence", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("etudiant_listePromotion", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("etudiant_listeAbsence", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("etudiant_listeMatieres", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("etudiant_listeCours", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("etudiant_listeEnseignants", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_exercice", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_promotion", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_matiere", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_cours", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_enseignant", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_etudiant", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_aide", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_listeAbsence_Cours", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("index", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_ajout_administrateur", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_listeAdministrateur", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("administrateur_modif_administrateur", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_administrateur", "Pas d'aide disponible"));
		aideDao.ajouter(new Aide("affichage_liste_exercice", "Pas d'aide disponible"));

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


		return new InternalResourceView("/login.jsp");
		
	}//end methode
	
	
	// ============ Setter ====================//


	public void setEnseignatDao(IEnseignantDAO enseignatDao) {
		this.enseignatDao = enseignatDao;
	}


	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}


	public void setAdminitrateurDao(IAdministrateurDao adminitrateurDao) {
		this.adminitrateurDao = adminitrateurDao;
	}


	public void setAdresseDao(IAdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}


	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}


	public void setCoursDao(ICoursDAO coursDao) {
		this.coursDao = coursDao;
	}


	public void setEtudiantCoursDao(IEtudiantCoursDAO etudiantCoursDao) {
		this.etudiantCoursDao = etudiantCoursDao;
	}


	public void setExerciceDao(IExerciceDAO exerciceDao) {
		this.exerciceDao = exerciceDao;
	}


	public void setMatiereDao(IMatiereDAO matiereDao) {
		this.matiereDao = matiereDao;
	}


	public void setPromotionDao(IPromotionDAO promotionDao) {
		this.promotionDao = promotionDao;
	}


	public void setPersonneDao(IPersonneDao personneDao) {
		this.personneDao = personneDao;
	}


	public void setEnseignantMatierePromotionDao(IEnseignantMatierePromotionDao enseignantMatierePromotionDao) {
		this.enseignantMatierePromotionDao = enseignantMatierePromotionDao;
	}
}//end class
