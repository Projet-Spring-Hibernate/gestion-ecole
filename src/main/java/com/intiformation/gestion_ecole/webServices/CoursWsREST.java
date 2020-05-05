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

import com.intiformation.gestion_ecole.dao.ICoursDAO;
import com.intiformation.gestion_ecole.domain.Cours;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class CoursWsREST {
	// ========== DAO =========================//

	@Autowired
	private ICoursDAO coursDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param coursDao
	 */
	public void setCoursDao(ICoursDAO coursDao) {
		this.coursDao = coursDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Courss ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * courss dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/cours/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cours/get-all", method = RequestMethod.GET)
	public List<Cours> getAllCourssBdd() {
		return coursDao.getAllCours();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id cours ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un cours dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/cours/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cours/get-by-id/{id}", method = RequestMethod.GET)
	public Cours coursyId(@PathVariable("id") Long pIdCours) {

		return (Cours) coursDao.getById(pIdCours);
	}// end coursyId

	// ===========================================================//
	// =========== save cours =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un cours la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/cours/save (POST)
	 * 
	 */
	@RequestMapping(value = "/cours/save", method = RequestMethod.POST)
	public void saveCours(@RequestBody Cours pCours) {
		coursDao.ajouter(pCours);
	}// end saveCours

	// ===========================================================//
	// =========== update cours ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un cours dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/cours/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/cours/update/{id}", method = RequestMethod.PUT)
	public void updateCours(@PathVariable("id") Long pIdCours, @RequestBody Cours pCours) {

		coursDao.modifier(pCours);
	}// end updateCours

	// ===========================================================//
	// =========== delete cours ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un cours dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/cours/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cours/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteCours(@PathVariable("id") Long pIdCours) {

		coursDao.supprimer(coursDao.getById(pIdCours));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteCours
}// end class
