package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	public Categorie selectByCategorie (int noCategorie) throws BusinessException;
	List<Categorie> selectAllCategorie() throws BusinessException;
}
