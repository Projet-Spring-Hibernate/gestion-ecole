package com.intiformation.gestion_ecole.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * classe consacrée à la constitution de la page d'aide

 * @author Valentin
>>>>>>> f141fb8cf0d593d1a32b889cf419aafc29bb93cb
 *
 */
@Entity(name="aide")
@Table(name="aides")
public class Aide {
	
	/*-----propriétés-----*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_aide;
	
	private String page;
	
	private String contenu;
	
	
	/*-----constructeurs-----*/
	public Aide() {
		super();
	}
	
	
	public Aide(String page, String contenu) {
		super();
		this.page = page;
		this.contenu = contenu;
	}


	public Aide(String page, String contenu, Long id_page) {
		super();
		this.page = page;
		this.contenu = contenu;
		this.id_aide = id_page;
	}


	/*-----getters/setters + méthodes-----*/
	
	
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	



	public Long getId_aide() {
		return id_aide;
	}


	public void setId_aide(Long id_aide) {
		this.id_aide = id_aide;
	}


	@Override
	public String toString() {
		return "Aide [page=" + page + ", contenu=" + contenu + ", id_aide=" + id_aide + "]";
	}
	

}//fin classe
