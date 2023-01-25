package dao;

import java.util.List;

import beans.Employe;

public interface IDao<T> {

	// crud methodes

	boolean create(T x);

	boolean delete(T x);

	boolean update(T x);

	List<T> findAll();

	T findById(int id);

	List<T> findElementsByKeyWord(String colomnName, String kw);

}
