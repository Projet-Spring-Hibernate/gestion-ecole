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

import com.intiformation.gestion_ecole.dao.IEtudiantCoursDAO;
import com.intiformation.gestion_ecole.domain.EtudiantCours;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class EtudiantCoursWsREST {
	// ========== DAO =========================//

	@Autowired
	private IEtudiantCoursDAO etudiantCoursDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param etudiantCoursDao
	 */
	public void setEtudiantCoursDao(IEtudiantCoursDAO etudiantCoursDao) {
		this.etudiantCoursDao = etudiantCoursDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL EtudiantCourss ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * etudiantCourss dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiantCours/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/etudiantCours/get-all", method = RequestMethod.GET)
	public List<EtudiantCours> getAllEtudiantCourssBdd() {
		return etudiantCoursDao.getAllEtudiantCours();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id etudiantCours ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un etudiantCours dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiantCours/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/etudiantCours/get-by-id/{id}", method = RequestMethod.GET)
	public EtudiantCours etudiantCoursyId(@PathVariable("id") Long pIdEtudiantCours) {

		return (EtudiantCours) etudiantCoursDao.getById(pIdEtudiantCours);
	}// end etudiantCoursyId

	// ===========================================================//
	// =========== save etudiantCours =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un etudiantCours la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiantCours/save (POST)
	 * 
	 */
	@RequestMapping(value = "/etudiantCours/save", method = RequestMethod.POST)
	public void saveEtudiantCours(@RequestBody EtudiantCours pEtudiantCours) {
		etudiantCoursDao.ajouter(pEtudiantCours);
	}// end saveEtudiantCours

	// ===========================================================//
	// =========== update etudiantCours ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un etudiantCours dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiantCours/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/etudiantCours/update/{id}", method = RequestMethod.PUT)
	public void updateEtudiantCours(@PathVariable("id") Long pIdEtudiantCours, @RequestBody EtudiantCours pEtudiantCours) {

		etudiantCoursDao.modifier(pEtudiantCours);
	}// end updateEtudiantCours

	// ===========================================================//
	// =========== delete etudiantCours ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un etudiantCours dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiantCours/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/etudiantCours/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteEtudiantCours(@PathVariable("id") Long pIdEtudiantCours) {

		etudiantCoursDao.supprimer(etudiantCoursDao.getById(pIdEtudiantCours));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteEtudiantCours
}// end class
