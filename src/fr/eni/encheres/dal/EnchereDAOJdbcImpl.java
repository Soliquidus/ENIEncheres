package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO{
	
	private static final String INSERT_ENCHERE="INSERT into ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (?,?,?,?)";
	private static final String SELECT_BY_ID_ENCHERE = "SELECT * FROM ENCHERES WHERE no_enchere = ?";
	
	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		if(enchere == null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1,  enchere.getNoUtilisateur());
				pstmt.setInt(2, enchere.getNoArticle());
				pstmt.setDate(4, java.sql.Date.valueOf(enchere.getDateEnchere()));
				pstmt.setInt(5, enchere.getMontantEnchere());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					enchere.setNoEnchere(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				cnx.commit();
			}catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_ECHEC);
		}
	}

	@Override
	public Enchere selectByEnchere(int noEnchere) throws BusinessException {
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_ENCHERE);
			pstmt.setInt(1, noEnchere);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
				enchere.setNoArticle(rs.getInt("no_article"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_ECHEC);
			throw businessException;
		}
		return enchere;
}
		
}
