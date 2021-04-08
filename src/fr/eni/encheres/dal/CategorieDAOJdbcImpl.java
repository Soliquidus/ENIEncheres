package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO{

	private static final String SELECT_BY_ID_CATEGORIE = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
	private static final String SELECT_ALL_CATEGORIE = "SELECT * FROM CATEGORIES";

	@Override
	public Categorie selectByCategorie(int noCategorie) throws BusinessException {
		Categorie categorie = new Categorie();
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_CATEGORIE);
			pstmt.setInt(1, noCategorie);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				categorie.setLibelle(rs.getString("nom_categorie"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_CATEGORIE_ECHEC);
			throw businessException;	
		}
		return categorie;
	}

	@Override
	public List <Categorie> selectAllCategorie() throws BusinessException {
		List <Categorie> categories = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIE);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				categories.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
			}
	} catch (Exception e) {
		e.printStackTrace();
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_CATEGORIE_ECHEC);
		throw businessException;
	}
		return categories;
	}

	
}
