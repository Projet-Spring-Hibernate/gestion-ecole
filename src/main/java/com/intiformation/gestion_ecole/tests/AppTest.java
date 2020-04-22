package com.intiformation.gestion_ecole.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intiformation.gestion_ecole.dao.AdministrateurDaoImpl;
import com.intiformation.gestion_ecole.dao.IAdministrateurDao;
import com.intiformation.gestion_ecole.dao.IPersonneDao;
import com.intiformation.gestion_ecole.dao.PersonneDaoImpl;
import com.intiformation.gestion_ecole.domain.Administrateur;
import com.intiformation.gestion_ecole.domain.Personne;

public class AppTest {

	public static void main(String[] args) {
		//===========================================================================//
		//==================== Recup du contexte de l'application ===================//
		//===========================================================================//
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});

		
		//===========================================================================//
		//==================== Test DAO Administrateur  =============================//
		//===========================================================================//
		
		//Recup de la dao de l'administrateur
		IAdministrateurDao adminDAO =(IAdministrateurDao) context.getBean("administrateurDaoImpl");
		

		//Ajout d'un administrateur
		Administrateur admin = new Administrateur("123", "a", "a", "a");
		adminDAO.ajouter(admin);
		
		//Recup de la liste des administrateurs et affichage
		List<Administrateur> listeAdmin = adminDAO.getAllAdministrateur();
		System.out.println("Liste des admins après 1 ajout : ");
		System.out.println(listeAdmin);

		//Modification d'un administrateur
		admin.setIdentifiant(1L);
		admin.setEmail("TestModif");
		adminDAO.modifier(admin);
		
		//Recup de la liste des administrateurs et affichage
		listeAdmin = adminDAO.getAllAdministrateur();
		System.out.println("Liste des admins après 1 modif : ");
		System.out.println(listeAdmin);
		
		//Recup d'un admin par son id
		Personne adminRecup = adminDAO.getById(1L);
		System.out.println("Admin n°1 après recup : ");
		System.out.println(adminRecup);
		
		//Suppression d'un admin 
		adminDAO.supprimer(adminRecup);
		
		//Recup de la liste des administrateurs et affichage
		listeAdmin = adminDAO.getAllAdministrateur();
		System.out.println("Liste des admins après 1 suppression : ");
		System.out.println(listeAdmin);
		
		//===========================================================================//
		//==================== Test DAO Personne  ===================================//
		//===========================================================================//
		
		//Recup de la dao de la personne
		IPersonneDao personneDAO =(IPersonneDao) context.getBean("personneDaoImpl");
		

		//Ajout d'un personne
		Personne personne = new Personne("123", "a", "a", "a");
		personneDAO.ajouter(personne);
		
		//Recup de la liste des personnes et affichage
		List<Personne> listePersonnes = personneDAO.getAll();
		System.out.println("Liste des personnes après 1 ajout : ");
		System.out.println(listePersonnes);

		//Modification d'une personne
		personne.setIdentifiant(2L);
		personne.setEmail("email");
		personneDAO.modifier(personne);
		
		//Recup de la liste des personnes et affichage
		listePersonnes = personneDAO.getAll();
		System.out.println("Liste des personnes après 1 modif : ");
		System.out.println(listePersonnes);
		
		//Recup d'une personne par son id
		Personne personneRecup = personneDAO.getById(2L);
		System.out.println("personnes n°2 après recup : ");
		System.out.println(personneRecup);
		
		//Test si la personne existe
		System.out.println("La personne ayant l'email = 'email' et mdp='123' existe : "+personneDAO.isPersonneExists("email", "123"));
		
		//Recup d'une personne par son mail et mdp
		Personne personneRecupMailMdp = personneDAO.getPersonneByMailMDP("email", "123");
		System.out.println("personnes n°2 après recup par le mail et mdp : ");
		System.out.println(personneRecupMailMdp);
		
		//Suppression d'un personne 
		personneDAO.supprimer(personneRecup);
		
		//Recup de la liste des personnes et affichage
		listePersonnes = personneDAO.getAll();
		System.out.println("Liste des personnes après 1 suppression : ");
		System.out.println(listePersonnes);
		
	}//end main

}//end class
