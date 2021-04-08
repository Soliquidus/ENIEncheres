package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO{

	private static final String INSERT_ARTICLE="INSERT into ARTICLES_VENDUS (nom_article, description, photo, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, no_utilisateur, no_categorie) values (?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_ID_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String SELECT_ALL_ARTICLE_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	private static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS";
	
	
	//Ajouter un article à vendre, ce qui implique la création d'une enchère automatiquement
		@Override
		public void insertAchat(ArticleVendu article) throws BusinessException {
			if(article == null)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
				throw businessException;
			}
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				try
				{
					cnx.setAutoCommit(false);
					PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, article.getNomArticle());
					pstmt.setString(2, article.getDescription());
					pstmt.setBinaryStream(3, article.getPhoto());
					pstmt.setDate(4, java.sql.Date.valueOf(article.getDateDebutEncheres()));
					pstmt.setDate(5, java.sql.Date.valueOf(article.getDateFinEncheres()));
					pstmt.setInt(6, article.getPrixInitial());
					pstmt.setInt(7,article.getNoUtilisateur());
					pstmt.setInt(8, article.getNoCategorie());
					
					pstmt.executeUpdate();
					ResultSet rs = pstmt.getGeneratedKeys();
					if(rs.next())
					{
						article.setNoArticle(rs.getInt(1));
					}
					rs.close();
					pstmt.close();
					cnx.commit();
				}catch (Exception e) {
					e.printStackTrace();
					cnx.rollback();
					throw e;
				}
			}catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
			}
		}
		
		@Override
		public ArticleVendu selectByArticle(int id) throws BusinessException {
			ArticleVendu articleVendu = new ArticleVendu();
			try (Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
				pstmt.setInt(1, id);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					articleVendu.setNomArticle(rs.getString("nom_article"));
					articleVendu.setDescription(rs.getString("description"));
					articleVendu.setPhoto(rs.getBinaryStream("photo"));
					articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					articleVendu.setPrixInitial(rs.getInt("prix_initial"));
					articleVendu.setPrixVente(rs.getInt("prix_vente"));
					articleVendu.setNoCategorie(rs.getInt("no_categorie"));
					articleVendu.setNoUtilisateur(rs.getInt("no_utilisateur"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_ECHEC);
				throw businessException;
			}
			return articleVendu;
	}


		@Override
		public List<ArticleVendu> selectAllArticlesUtilisateur(int noUtilisateur) throws BusinessException {
			List<ArticleVendu> articlesUtilisateur = new ArrayList<>();
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLE_UTILISATEUR);
				pstmt.setInt(1, noUtilisateur);
				ResultSet rs = pstmt.executeQuery();
			
				while(rs.next())
				{
					ArticleVendu articleConsulte = new ArticleVendu()	;
					articleConsulte = articleBuilder(articleConsulte, rs);
						articlesUtilisateur.add(articleConsulte);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ENCHERES_UTLISATEUR);
				throw businessException;
			}

			return articlesUtilisateur;
		}
		
		private ArticleVendu articleBuilder(ArticleVendu articleConsulte, ResultSet rs) throws SQLException{
//			ArticleVendu articleConsulte;
//			articleConsulte = new ArticleVendu();
			articleConsulte.setNoArticle(rs.getInt("no_article"));
			articleConsulte.setNomArticle(rs.getString("nom_article"));
			articleConsulte.setDescription(rs.getString("description"));
			articleConsulte.setPhoto(rs.getBinaryStream("photo"));
			articleConsulte.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
			articleConsulte.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
			articleConsulte.setPrixInitial(rs.getInt("prix_initial"));
			articleConsulte.setPrixVente(rs.getInt("prix_vente"));
			articleConsulte.setNoCategorie(rs.getInt("no_categorie"));
			articleConsulte.setNoUtilisateur(rs.getInt("no_utilisateur"));
			return articleConsulte;
		}

		@Override
		public List<ArticleVendu> selectAll() throws BusinessException {
			List<ArticleVendu> articles = new ArrayList<>();
			ArticleVendu article = new ArticleVendu()	;
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
				ResultSet rs = pstmt.executeQuery();
				article = articleBuilder(article, rs);
				while(rs.next())
				{
					articles.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_ARTICLE_ECHEC);
				throw businessException;
			}
			// TODO Auto-generated method stub
			return articles;
		}
}
