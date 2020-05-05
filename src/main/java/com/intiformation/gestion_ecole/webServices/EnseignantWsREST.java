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

import com.intiformation.gestion_ecole.dao.IEnseignantDAO;
import com.intiformation.gestion_ecole.domain.Enseignant;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class EnseignantWsREST {
	// ========== DAO =========================//

	@Autowired
	private IEnseignantDAO enseignantDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param enseignantDao
	 */
	public void setEnseignantDao(IEnseignantDAO enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Enseignants ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * enseignants dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignant/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enseignant/get-all", method = RequestMethod.GET)
	public List<Enseignant> getAllEnseignantsBdd() {
		return enseignantDao.listEnseignant();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id enseignant ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un enseignant dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignant/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enseignant/get-by-id/{id}", method = RequestMethod.GET)
	public Enseignant enseignantyId(@PathVariable("id") Long pIdEnseignant) {

		return (Enseignant) enseignantDao.getById(pIdEnseignant);
	}// end enseignantyId

	// ===========================================================//
	// =========== save enseignant =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un enseignant la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignant/save (POST)
	 * 
	 */
	@RequestMapping(value = "/enseignant/save", method = RequestMethod.POST)
	public void saveEnseignant(@RequestBody Enseignant pEnseignant) {
		enseignantDao.ajouter(pEnseignant);
	}// end saveEnseignant

	// ===========================================================//
	// =========== update enseignant ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un enseignant dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignant/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/enseignant/update/{id}", method = RequestMethod.PUT)
	public void updateEnseignant(@PathVariable("id") Long pIdEnseignant, @RequestBody Enseignant pEnseignant) {

		enseignantDao.modifier(pEnseignant);
	}// end updateEnseignant

	// ===========================================================//
	// =========== delete enseignant ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un enseignant dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/enseignant/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enseignant/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteEnseignant(@PathVariable("id") Long pIdEnseignant) {

		enseignantDao.supprimer(enseignantDao.getById(pIdEnseignant));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteEnseignant
}// end class
