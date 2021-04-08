package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

public class ArticleManager {
	
	private final ArticleVenduDAO articleVenduDAO;
	
	public ArticleManager() {
		this.articleVenduDAO=DAOFactory.getArticleVenduDAO();
	}
	
	public ArticleVendu insertAchat (String nom, String description, InputStream photo, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres,int noUtilisateur, int prixInitial, int noCategorie) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		this.validerPrixInitial(prixInitial, businessException);
		this.validerDateEnchere(dateDebutEncheres,dateFinEncheres,businessException);
		
		ArticleVendu articleVendu = null;
		
		articleVendu = new ArticleVendu();
		articleVendu.setNomArticle(nom);
		articleVendu.setDescription(description);
		articleVendu.setPhoto(photo);
		articleVendu.setDateDebutEncheres(dateDebutEncheres);
		articleVendu.setDateFinEncheres(dateFinEncheres);
		articleVendu.setNoUtilisateur(noUtilisateur);
		articleVendu.setPrixInitial(prixInitial);
		
		articleVendu.setNoCategorie(noCategorie);
		articleVenduDAO.insertAchat(articleVendu);
		
				return articleVendu;
	}
	
	public ArticleVendu selectByArticle (int noArticle) throws BusinessException {
		return this.articleVenduDAO.selectByArticle(noArticle);
	}
	
	public List<ArticleVendu> selectAllByUtilisateur (int noUtilisateur) throws BusinessException{
		return this.articleVenduDAO.selectAllArticlesUtilisateur(noUtilisateur);
	}
	
	public List<ArticleVendu> selectAllArticles() throws BusinessException{
		return this.articleVenduDAO.selectAll();
	}

	//Validations
	
	private void validerDateEnchere(LocalDate dateDebutEncheres,LocalDate dateFinEncheres, BusinessException businessException) {
		if(dateDebutEncheres==null || 
			dateDebutEncheres.isBefore(LocalDate.now()))
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_ERREUR);
		}
		if(dateFinEncheres == null || dateFinEncheres.isBefore(dateDebutEncheres))
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_ERREUR);
		}
	}
	private void validerPrixInitial(int prixInitial, BusinessException businessException){
		if (prixInitial >=0)
		{
			businessException.ajouterErreur(CodesResultatBLL.PRIX_MIN);
		}
	}
}
