package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

import java.time.LocalDate;

public class EnchereManager {

	private final EnchereDAO enchereDAO;
	
	public EnchereManager() {
		this.enchereDAO=DAOFactory.getEnchereDAO();
	}
	
	public Enchere insertEnchere (int noUtilisateur, int noArticle, LocalDate dateEnchere, int montantEnchere) throws BusinessException {
		
		Enchere enchere = null;
		
		enchere = new Enchere();
		enchere.setNoUtilisateur(noUtilisateur);
		enchere.setNoArticle(noArticle);
		enchere.setDateEnchere(dateEnchere);
		enchere.setMontantEnchere(montantEnchere);
		enchereDAO.insertEnchere(enchere);
		
		return enchere;
	}
	
	public Enchere selectByEnchere (int noEnchere) throws BusinessException {
		return this.enchereDAO.selectByEnchere(noEnchere);
	}
}
