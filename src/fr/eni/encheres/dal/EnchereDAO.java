package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	public void insertEnchere (Enchere enchere) throws BusinessException;
	public Enchere selectByEnchere (int noEnchere) throws BusinessException;
}
