package com.intiformation.gestion_ecole.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * classe consacrée à la constitution de la page d'aide
 * @author Valentin
 *
 */
@Entity(name="aide")
@Table(name="aides")
public class Aide {
	
	/*-----propriétés-----*/
	private int page;
	private String contenu;
	
	/*-----constructeurs-----*/
	public Aide() {
		super();
	}
	public Aide(int page, String contenu) {
		super();
		this.page = page;
		this.contenu = contenu;
	}
	
	/*-----getters/setters + méthodes-----*/
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	@Override
	public String toString() {
		return "Aide [page=" + page + ", contenu=" + contenu + "]";
	}

}//fin classe
