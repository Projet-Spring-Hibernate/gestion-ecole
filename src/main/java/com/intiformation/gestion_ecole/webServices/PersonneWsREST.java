package com.intiformation.gestion_ecole.webServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.intiformation.gestion_ecole.dao.IPersonneDao;
import com.intiformation.gestion_ecole.domain.Personne;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class PersonneWsREST {
	// ========== DAO =========================//

	@Autowired
	@Qualifier("personneDaoImpl")
	private IPersonneDao personneDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param personneDao
	 */
	public void setPersonneDao(IPersonneDao personneDao) {
		this.personneDao = personneDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Personnes ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * personnes dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/personne/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/personne/get-all", method = RequestMethod.GET)
	public List<Personne> getAllPersonnesBdd() {
		return personneDao.getAll();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id personne ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un personne dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/personne/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/personne/get-by-id/{id}", method = RequestMethod.GET)
	public Personne personneyId(@PathVariable("id") Long pIdPersonne) {

		return (Personne) personneDao.getById(pIdPersonne);
	}// end personneyId

	// ===========================================================//
	// =========== save personne =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un personne la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/personne/save (POST)
	 * 
	 */
	@RequestMapping(value = "/personne/save", method = RequestMethod.POST)
	public void savePersonne(@RequestBody Personne pPersonne) {
		personneDao.ajouter(pPersonne);
	}// end savePersonne

	// ===========================================================//
	// =========== update personne ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un personne dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/personne/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/personne/update/{id}", method = RequestMethod.PUT)
	public void updatePersonne(@PathVariable("id") Long pIdPersonne, @RequestBody Personne pPersonne) {

		personneDao.modifier(pPersonne);
	}// end updatePersonne

	// ===========================================================//
	// =========== delete personne ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un personne dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/personne/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/personne/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletePersonne(@PathVariable("id") Long pIdPersonne) {

		personneDao.supprimer(personneDao.getById(pIdPersonne));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deletePersonne
}// end class
