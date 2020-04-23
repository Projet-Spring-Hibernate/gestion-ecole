package com.intiformation.gestion_ecole.test;

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
		
		Enseignant enseignant1 = new Enseignant("123", "paul", "pile", "paul@gmail.com");
		Enseignant enseignant2 = new Enseignant("456", "Poule", "PALE", "pale@gmail.com");
		
		Promotion promotion1 = new Promotion("Promo1");
		Promotion promotion2 = new Promotion("Promo2");
		
		Matiere matiere1 = new Matiere("Math");
		Matiere matiere2 = new Matiere("Chimie");
		Matiere matiere3 = new Matiere("Meca");
		
		promotionDao.ajouter(promotion1);
		promotionDao.ajouter(promotion2);
		
		List<Promotion> listepromotion = promotionDao.listePromotion();
		matiere1.setListePromotion(listepromotion);
		matiere2.setListePromotion(listepromotion);
		
		matiereDao.ajouter(matiere1);
		matiereDao.ajouter(matiere2);
		matiereDao.ajouter(matiere3);
		
		List<Matiere> listematiere = matiereDao.listMatiere();
		System.out.println("////////////////////////////////////////////"+listematiere.subList(0, 1));

		enseignant1.setListeMatiere(listematiere.subList(0, 1));
		enseignant1.setListePromotion(listepromotion);
		enseignant2.setListeMatiere(listematiere);
		enseignant2.setListePromotion(listepromotion);
		
		enseignatDao.ajouter(enseignant1);
		enseignatDao.ajouter(enseignant2);
		
		List<Enseignant> listeenseignant = enseignatDao.listEnseignant();
		promotion1.setListeMatiere(listematiere);
		promotion1.setListeEnseignant(listeenseignant);
		promotion2.setListeMatiere(listematiere);
		promotion2.setListeEnseignant(listeenseignant);
		
		promotionDao.modifier(promotion1);
		promotionDao.modifier(promotion2);
		
		matiere1.setListeEnseignant(listeenseignant);
		matiere2.setListeEnseignant(listeenseignant);
		
		matiereDao.modifier(matiere1);
		matiereDao.modifier(matiere2);
		
			
		System.out.println(enseignatDao.getAll());
		Enseignant enseignant1recup =(Enseignant) enseignatDao.getAll().get(0);
		System.out.println(enseignant1recup.getListeMatiere());
		

}
}
