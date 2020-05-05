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

import com.intiformation.gestion_ecole.dao.IAideDAO;
import com.intiformation.gestion_ecole.domain.Aide;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class AideWsREST {
	// ========== DAO =========================//

	@Autowired
	private IAideDAO aideDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param aideDao
	 */
	public void setAideDao(IAideDAO aideDao) {
		this.aideDao = aideDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Aides ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * aides dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/aide/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/aide/get-all", method = RequestMethod.GET)
	public List<Aide> getAllAidesBdd() {
		return aideDao.getAllAide();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id aide ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un aide dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/aide/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/aide/get-by-id/{id}", method = RequestMethod.GET)
	public Aide aideyId(@PathVariable("id") Long pIdAide) {

		return (Aide) aideDao.getById(pIdAide);
	}// end aideyId

	// ===========================================================//
	// =========== save aide =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un aide la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/aide/save (POST)
	 * 
	 */
	@RequestMapping(value = "/aide/save", method = RequestMethod.POST)
	public void saveAide(@RequestBody Aide pAide) {
		aideDao.ajouter(pAide);
	}// end saveAide

	// ===========================================================//
	// =========== update aide ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un aide dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/aide/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/aide/update/{id}", method = RequestMethod.PUT)
	public void updateAide(@PathVariable("id") Long pIdAide, @RequestBody Aide pAide) {

		aideDao.modifier(pAide);
	}// end updateAide

	// ===========================================================//
	// =========== delete aide ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un aide dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/aide/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/aide/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAide(@PathVariable("id") Long pIdAide) {

		aideDao.supprimer(aideDao.getById(pIdAide));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteAide
}// end class
