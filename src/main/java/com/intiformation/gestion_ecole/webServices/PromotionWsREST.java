package com.intiformation.gestion_ecole.webServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intiformation.gestion_ecole.dao.IPromotionDAO;
import com.intiformation.gestion_ecole.domain.Promotion;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class PromotionWsREST {
	// ========== DAO =========================//

	@Autowired
	private IPromotionDAO promotionDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param promotionDao
	 */
	public void setPromotionDao(IPromotionDAO promotionDao) {
		this.promotionDao = promotionDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Promotions ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * promotions dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/promotion/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/promotion/get-all", method = RequestMethod.GET)
	public List<Promotion> getAllPromotionsBdd() {
		return promotionDao.listePromotion();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id promotion ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un promotion dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/promotion/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/promotion/get-by-id/{id}", method = RequestMethod.GET)
	public Promotion promotionyId(@PathVariable("id") Long pIdPromotion) {

		return (Promotion) promotionDao.getById(pIdPromotion);
	}// end promotionyId

	// ===========================================================//
	// =========== save promotion =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un promotion la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/promotion/save (POST)
	 * 
	 */
	@RequestMapping(value = "/promotion/save", method = RequestMethod.POST)
	public void savePromotion(@RequestBody Promotion pPromotion) {
		promotionDao.ajouter(pPromotion);
	}// end savePromotion

	// ===========================================================//
	// =========== update promotion ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un promotion dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/promotion/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/promotion/update/{id}", method = RequestMethod.PUT)
	public void updatePromotion(@PathVariable("id") Long pIdPromotion, @RequestBody Promotion pPromotion) {

		promotionDao.modifier(pPromotion);
	}// end updatePromotion

	// ===========================================================//
	// =========== delete promotion ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un promotion dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/promotion/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/promotion/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletePromotion(@PathVariable("id") Long pIdPromotion) {

		promotionDao.supprimer(promotionDao.getById(pIdPromotion));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deletePromotion
}// end class

