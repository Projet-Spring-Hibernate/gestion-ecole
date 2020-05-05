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

import com.intiformation.gestion_ecole.dao.IEnseignantMatierePromotionDao;
import com.intiformation.gestion_ecole.domain.EnseignantMatierePromotion;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class EnseignantMatierePromotionWsREST {
	// ========== DAO =========================//

	@Autowired
	private IEnseignantMatierePromotionDao enseignantMatierePromotionDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param enseignantMatierePromotionDao
	 */
	public void setEnseignantMatierePromotionDao(IEnseignantMatierePromotionDao enseignantMatierePromotionDao) {
		this.enseignantMatierePromotionDao = enseignantMatierePromotionDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL EnseignantMatierePromotions ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * enseignantMatierePromotions dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignantMatierePromotion/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enseignantMatierePromotion/get-all", method = RequestMethod.GET)
	public List<EnseignantMatierePromotion> getAllEnseignantMatierePromotionsBdd() {
		return enseignantMatierePromotionDao.getAllEnseignantMatierePromotion();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id enseignantMatierePromotion ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un enseignantMatierePromotion dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignantMatierePromotion/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enseignantMatierePromotion/get-by-id/{id}", method = RequestMethod.GET)
	public EnseignantMatierePromotion enseignantMatierePromotionyId(@PathVariable("id") Long pIdEnseignantMatierePromotion) {

		return (EnseignantMatierePromotion) enseignantMatierePromotionDao.getById(pIdEnseignantMatierePromotion);
	}// end enseignantMatierePromotionyId

	// ===========================================================//
	// =========== save enseignantMatierePromotion =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un enseignantMatierePromotion la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignantMatierePromotion/save (POST)
	 * 
	 */
	@RequestMapping(value = "/enseignantMatierePromotion/save", method = RequestMethod.POST)
	public void saveEnseignantMatierePromotion(@RequestBody EnseignantMatierePromotion pEnseignantMatierePromotion) {
		enseignantMatierePromotionDao.ajouter(pEnseignantMatierePromotion);
	}// end saveEnseignantMatierePromotion

	// ===========================================================//
	// =========== update enseignantMatierePromotion ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un enseignantMatierePromotion dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignantMatierePromotion/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/enseignantMatierePromotion/update/{id}", method = RequestMethod.PUT)
	public void updateEnseignantMatierePromotion(@PathVariable("id") Long pIdEnseignantMatierePromotion, @RequestBody EnseignantMatierePromotion pEnseignantMatierePromotion) {

		enseignantMatierePromotionDao.modifier(pEnseignantMatierePromotion);
	}// end updateEnseignantMatierePromotion

	// ===========================================================//
	// =========== delete enseignantMatierePromotion ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un enseignantMatierePromotion dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignantMatierePromotion/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enseignantMatierePromotion/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteEnseignantMatierePromotion(@PathVariable("id") Long pIdEnseignantMatierePromotion) {

		enseignantMatierePromotionDao.supprimer(enseignantMatierePromotionDao.getById(pIdEnseignantMatierePromotion));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteEnseignantMatierePromotion
}// end class
