package com.intiformation.gestion_ecole.webServices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intiformation.gestion_ecole.dao.IEtudiantDAO;
import com.intiformation.gestion_ecole.domain.Etudiant;

@WebService(serviceName="etudiant-ws-soap")
@Component
public class EtudiantWsSOAP {
	// ========== DAO =========================//
	
	@Autowired
	private IEtudiantDAO etudiantDao;
	
	// ============ SETTER ====================//
	
	/**
	 * Setter pour injection spring
	 * @param etudiantDao
	 */
	public void setEtudiantDao(IEtudiantDAO etudiantDao) {
		this.etudiantDao = etudiantDao;
	}
	
	/*================================================================================================*/
	/*================================Méthodes (Services) à exposer dans le WS========================*/
	/*================================================================================================*/
	
	/**
	 * Méthode ou Service à exposer dans notre WS.
	 * Elle récupère la liste des etudiants dans la Bdd.
	 * @return
	 */
	@WebMethod
	public List<Etudiant> getAllEtudiantsBdd() {
		return  etudiantDao.getAllEtudiant();
	}//end getAllFonctionnairesBdd
	
	
}//end class
