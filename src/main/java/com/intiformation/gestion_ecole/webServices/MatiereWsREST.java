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

import com.intiformation.gestion_ecole.dao.IMatiereDAO;
import com.intiformation.gestion_ecole.domain.Matiere;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class MatiereWsREST {
	// ========== DAO =========================//

	@Autowired
	private IMatiereDAO matiereDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param matiereDao
	 */
	public void setMatiereDao(IMatiereDAO matiereDao) {
		this.matiereDao = matiereDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Matieres ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * matieres dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/matiere/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/matiere/get-all", method = RequestMethod.GET)
	public List<Matiere> getAllMatieresBdd() {
		return matiereDao.listMatiere();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id matiere ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un matiere dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/matiere/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/matiere/get-by-id/{id}", method = RequestMethod.GET)
	public Matiere matiereyId(@PathVariable("id") Long pIdMatiere) {

		return (Matiere) matiereDao.getById(pIdMatiere);
	}// end matiereyId

	// ===========================================================//
	// =========== save matiere =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un matiere la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/matiere/save (POST)
	 * 
	 */
	@RequestMapping(value = "/matiere/save", method = RequestMethod.POST)
	public void saveMatiere(@RequestBody Matiere pMatiere) {
		matiereDao.ajouter(pMatiere);
	}// end saveMatiere

	// ===========================================================//
	// =========== update matiere ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un matiere dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/matiere/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/matiere/update/{id}", method = RequestMethod.PUT)
	public void updateMatiere(@PathVariable("id") Long pIdMatiere, @RequestBody Matiere pMatiere) {

		matiereDao.modifier(pMatiere);
	}// end updateMatiere

	// ===========================================================//
	// =========== delete matiere ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un matiere dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/matiere/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/matiere/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteMatiere(@PathVariable("id") Long pIdMatiere) {

		matiereDao.supprimer(matiereDao.getById(pIdMatiere));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteMatiere
}// end class
