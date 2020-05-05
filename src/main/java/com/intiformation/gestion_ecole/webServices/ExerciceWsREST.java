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

import com.intiformation.gestion_ecole.dao.IExerciceDAO;
import com.intiformation.gestion_ecole.domain.Exercice;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
public class ExerciceWsREST {
	// ========== DAO =========================//

	@Autowired
	private IExerciceDAO exerciceDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param exerciceDao
	 */
	public void setExerciceDao(IExerciceDAO exerciceDao) {
		this.exerciceDao = exerciceDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Exercices ===========================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des
	 * exercices dans la Bdd. renvoie la liste des employés en JSON. url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/exercice/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exercice/get-all", method = RequestMethod.GET)
	public List<Exercice> getAllExercicesBdd() {
		return exerciceDao.listeExercice();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get by id exercice ============================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un exercice dans la bdd url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/exercice/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exercice/get-by-id/{id}", method = RequestMethod.GET)
	public Exercice exerciceyId(@PathVariable("id") Long pIdExercice) {

		return (Exercice) exerciceDao.getById(pIdExercice);
	}// end exerciceyId

	// ===========================================================//
	// =========== save exercice =================================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un exercice la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/exercice/save (POST)
	 * 
	 */
	@RequestMapping(value = "/exercice/save", method = RequestMethod.POST)
	public void saveExercice(@RequestBody Exercice pExercice) {
		exerciceDao.ajouter(pExercice);
	}// end saveExercice

	// ===========================================================//
	// =========== update exercice ===============================//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour la modif d'un exercice dans la bdd la
	 * méthode reçoit les données JSON et les transforme en JAva via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody. Url d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/exercice/update/{id}
	 * (POST) (POST)
	 */
	@RequestMapping(value = "/exercice/update/{id}", method = RequestMethod.PUT)
	public void updateExercice(@PathVariable("id") Long pIdExercice, @RequestBody Exercice pExercice) {

		exerciceDao.modifier(pExercice);
	}// end updateExercice

	// ===========================================================//
	// =========== delete exercice ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un exercice dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/01_gestion_ecoles/spring-rest/exercice/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exercice/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteExercice(@PathVariable("id") Long pIdExercice) {

		exerciceDao.supprimer(exerciceDao.getById(pIdExercice));

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteExercice
}// end class
