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

import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.domain.Etudiant;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class EtudiantWsREST {
	// ========== DAO =========================//

	@Autowired
	private IEtudiantDAO etudiantDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param etudiantDao
	 */
	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Etudiants ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * etudiants dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiant/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/etudiant/get-all", method = RequestMethod.GET)
	public List<Etudiant> getAllEtudiantsBdd() {
		List<Etudiant> listeAllEtudiant = etudiantDao.getAllEtudiant();
		return etudiantDao.getAllEtudiant();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id etudiant ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un etudiant dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiant/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/etudiant/get-by-id/{id}", method = RequestMethod.GET)
	public Etudiant etudiantyId(@PathVariable("id") Long pIdEtudiant) {

		return (Etudiant) etudiantDao.getById(pIdEtudiant);
	}// end etudiantyId

	// ===========================================================//
	// =========== save etudiant =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un etudiant la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiant/save (POST)
	 * 
	 */
	@RequestMapping(value = "/etudiant/save", method = RequestMethod.POST)
	public void saveEtudiant(@RequestBody Etudiant pEtudiant) {
		etudiantDao.ajouter(pEtudiant);
	}// end saveEtudiant

	// ===========================================================//
	// =========== update etudiant ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un etudiant dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiant/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/etudiant/update/{id}", method = RequestMethod.PUT)
	public void updateEtudiant(@PathVariable("id") Long pIdEtudiant, @RequestBody Etudiant pEtudiant) {

		etudiantDao.modifier(pEtudiant);
	}// end updateEtudiant

	// ===========================================================//
	// =========== delete etudiant ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un etudiant dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/etudiant/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/etudiant/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteEtudiant(@PathVariable("id") Long pIdEtudiant) {

		etudiantDao.supprimer(etudiantDao.getById(pIdEtudiant));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteEtudiant
}// end class
