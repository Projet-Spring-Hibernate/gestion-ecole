package com.intiformation.gestion_ecole.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Enseignant;
import com.intiformation.gestion_ecole.domain.Matiere;
import com.intiformation.gestion_ecole.domain.Promotion;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestEnseignantDAO {

	@Autowired
	@Qualifier("enseignantDAOImpl")
	private IEnseignantDAO enseignatDao;
	
	@Autowired
	private IMatiereDAO matiereDao;
	
	@Autowired
	private IPromotionDAO promotionDao;
	
	@Test
	public void testJointure() {
		
		//Creation des entités
		Promotion promotion1 = new Promotion("Promo1");
		Promotion promotion2 = new Promotion("Promo2");
		
		Matiere matiere1 = new Matiere("Math");
		Matiere matiere2 = new Matiere("Chimie");
		Matiere matiere3 = new Matiere("Meca");

		Enseignant enseignant1 = new Enseignant("123", "paul", "pile", "paul@gmail.com");
		Enseignant enseignant2 = new Enseignant("456", "Poule", "PALE", "pale@gmail.com");
		
		//Ajout des liste aux promotions et ajout des promotions à la bdd
		

		
		
		promotionDao.ajouter(promotion1);
		promotionDao.ajouter(promotion2);
		

		System.out.println(promotionDao.listePromotion());
		Promotion promo1Recup = promotionDao.getById(1L);
		Promotion promo2Recup = promotionDao.getById(2L);
		
		Matiere matiere1Recup = matiereDao.getById(1L);
		Matiere matiere2Recup = matiereDao.getById(2L);
		Matiere matiere3Recup = matiereDao.getById(3L);
		
		Enseignant enseignant1Recup = (Enseignant) enseignatDao.getById(1L);
		Enseignant enseignant2Recup = (Enseignant) enseignatDao.getById(2L);
		

		

		System.out.println("\n Liste des enseignants :" +enseignatDao.getAll());
		System.out.println("\n Liste des matières :" +matiereDao.listMatiere());
		System.out.println("\n Liste des promo :"+ promotionDao.listePromotion());
}
}
