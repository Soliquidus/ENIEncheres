package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

import java.util.List;

public interface  ArticleVenduDAO {
	
	void insertAchat(ArticleVendu article) throws BusinessException;
	ArticleVendu selectByArticle(int noArticle) throws BusinessException;
	List<ArticleVendu> selectAll() throws BusinessException;
	List<ArticleVendu> selectAllArticlesUtilisateur(int noUtilisateur) throws BusinessException;
}
