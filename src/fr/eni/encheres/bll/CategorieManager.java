package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

import java.util.List;

public class CategorieManager {

	private final CategorieDAO categorieDAO;
	
	public CategorieManager() {
		this.categorieDAO=DAOFactory.getCategorieDAO();
	}
	
	public Categorie selectByCategorie (int noCategorie) throws BusinessException{
		return this.categorieDAO.selectByCategorie(noCategorie);
	}
	
	public List<Categorie> selectAllCategorie() throws BusinessException{
		return this.categorieDAO.selectAllCategorie();
	}
}
