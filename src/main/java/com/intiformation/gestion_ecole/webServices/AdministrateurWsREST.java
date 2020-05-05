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

import com.intiformation.gestion_ecole.dao.IAdministrateurDao;
import com.intiformation.gestion_ecole.domain.Administrateur;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class AdministrateurWsREST {
	// ========== DAO =========================//

	@Autowired
	private IAdministrateurDao administrateurDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param administrateurDao
	 */
	public void setAdministrateurDao(IAdministrateurDao administrateurDao) {
		this.administrateurDao = administrateurDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Administrateurs ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * administrateurs dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/administrateur/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/administrateur/get-all", method = RequestMethod.GET)
	public List<Administrateur> getAllAdministrateursBdd() {
		return administrateurDao.getAllAdministrateur();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id administrateur ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un administrateur dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/administrateur/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/administrateur/get-by-id/{id}", method = RequestMethod.GET)
	public Administrateur administrateuryId(@PathVariable("id") Long pIdAdministrateur) {

		return (Administrateur) administrateurDao.getById(pIdAdministrateur);
	}// end administrateuryId

	// ===========================================================//
	// =========== save administrateur =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un administrateur la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/administrateur/save (POST)
	 * 
	 */
	@RequestMapping(value = "/administrateur/save", method = RequestMethod.POST)
	public void saveAdministrateur(@RequestBody Administrateur pAdministrateur) {
		administrateurDao.ajouter(pAdministrateur);
	}// end saveAdministrateur

	// ===========================================================//
	// =========== update administrateur ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un administrateur dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/administrateur/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/administrateur/update/{id}", method = RequestMethod.PUT)
	public void updateAdministrateur(@PathVariable("id") Long pIdAdministrateur, @RequestBody Administrateur pAdministrateur) {

		administrateurDao.modifier(pAdministrateur);
	}// end updateAdministrateur

	// ===========================================================//
	// =========== delete administrateur ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un administrateur dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/administrateur/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/administrateur/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAdministrateur(@PathVariable("id") Long pIdAdministrateur) {

		administrateurDao.supprimer(administrateurDao.getById(pIdAdministrateur));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteAdministrateur
}// end class
