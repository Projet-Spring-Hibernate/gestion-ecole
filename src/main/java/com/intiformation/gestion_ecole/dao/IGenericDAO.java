package com.intiformation.gestion_ecole.dao;

import java.util.List;

/**
 * Interface générique de la DAO. Cette interface sera utilisée par toutes les classe
 * @author Laure
 *
 * @param <T>
 */
public interface IGenericDAO<T> {
	public void ajouter(T entity);

	public void modifier(T entity);

	public void supprimer(T entity);

	public T getById(Long id);

}//end interface
