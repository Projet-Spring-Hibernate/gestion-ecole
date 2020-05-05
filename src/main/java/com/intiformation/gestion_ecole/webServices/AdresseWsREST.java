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

import com.intiformation.gestion_ecole.dao.IAdresseDao;
import com.intiformation.gestion_ecole.domain.Adresse;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class AdresseWsREST {
	// ========== DAO =========================//

	@Autowired
	private IAdresseDao adresseDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param adresseDao
	 */
	public void setAdresseDao(IAdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Adresses ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * adresses dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/adresse/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adresse/get-all", method = RequestMethod.GET)
	public List<Adresse> getAllAdressesBdd() {
		return adresseDao.getAll();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id adresse ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un adresse dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/adresse/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adresse/get-by-id/{id}", method = RequestMethod.GET)
	public Adresse adresseyId(@PathVariable("id") Long pIdAdresse) {

		return (Adresse) adresseDao.getById(pIdAdresse);
	}// end adresseyId

	// ===========================================================//
	// =========== save adresse =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un adresse la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/adresse/save (POST)
	 * 
	 */
	@RequestMapping(value = "/adresse/save", method = RequestMethod.POST)
	public void saveAdresse(@RequestBody Adresse pAdresse) {
		adresseDao.ajouter(pAdresse);
	}// end saveAdresse

	// ===========================================================//
	// =========== update adresse ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un adresse dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/adresse/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/adresse/update/{id}", method = RequestMethod.PUT)
	public void updateAdresse(@PathVariable("id") Long pIdAdresse, @RequestBody Adresse pAdresse) {

		adresseDao.modifier(pAdresse);
	}// end updateAdresse

	// ===========================================================//
	// =========== delete adresse ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un adresse dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/adresse/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adresse/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAdresse(@PathVariable("id") Long pIdAdresse) {

		adresseDao.supprimer(adresseDao.getById(pIdAdresse));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteAdresse
}// end class
