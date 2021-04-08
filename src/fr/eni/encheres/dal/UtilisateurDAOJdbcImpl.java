package fr.eni.encheres.dal;


import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Jdbc permettant l'exécution des principales fonctionnalités de gestion d'utilisateurs du site
 * @date 11/08/2020
 * @datemodif 16/08/2020 (corrections diverses)
 * @version 2.0
 * @author Tibo Pfeifer
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	
	private static final String INSERT_UTILISATEUR="INSERT into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, "
			+ "code_postal, ville, mot_de_passe) values(?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_ADMIN="INSERT into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, "
			+ "code_postal, ville, mot_de_passe, administrateur) values(?,?,?,?,?,?,?,?,?,1)";
	private static final String UPDATE_UTILISATEUR="UPDATE UTILISATEURS set pseudo=?, nom=?, prenom=?, email=?, "
			+ "telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	private static final String DELETE_UTILISATEUR="DELETE from UTILISATEURS where no_utilisateur=?";
	private static final String CONNECT_UTILISATEUR="SELECT * FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?";
	private static final String SELECT_BY_ID_UTILISATEUR="SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe FROM "
			+ "UTILISATEURS WHERE no_utilisateur=?";
	
	//Ajouter un utilisateur
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
			cnx.setAutoCommit(false);
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			cnx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			cnx.rollback();
			throw e;
		}
	}catch (Exception e)
		{
		e.printStackTrace();
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
		throw businessException;
		}
	}
	
	//Supprimer un utilisateur
	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt=cnx.prepareStatement(DELETE_UTILISATEUR);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}
	
	//Mise à jour d'un profil utilisateur
	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			e.printStackTrace();
		}
	}
	
	//Selection de l'utilisateur suivant le pseudo et mot de passe entré, une "connexion" en somme
	@Override
	public Utilisateur connectUtilisateur(String pseudo, String motDePasse) throws BusinessException {
		Utilisateur utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(CONNECT_UTILISATEUR);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, motDePasse);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNEXION_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}
	
	//Séléctionner un utilisateur suivant son numéro d'identifiant (méthode créée avant tout pour 
	//aider la création de compte, cf ServletGestionUtilisateur, méthode "ajouterNouvelUtilisateur")
	public Utilisateur selectByUtilisateur (int id) throws BusinessException{
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_UTILISATEUR);
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNEXION_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}
	//Créer un compte administrateur (penser à imposer les conditions nécéssaires pour créer ce type de compte à part)
	@Override
	public void insertAdmin(Utilisateur utilisateur) throws BusinessException {
			if(utilisateur==null)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_NULL);
				throw businessException;
			}
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				try
				{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ADMIN, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(5, utilisateur.getRue());
				pstmt.setString(6, utilisateur.getCodePostal());
				pstmt.setString(7, utilisateur.getVille());
				pstmt.setString(8, utilisateur.getMotDePasse());
				pstmt.setBoolean(9, true);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}catch (Exception e)
			{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw businessException;
			}
		}
	@Override
	public void insertAchat(ArticleVendu article) throws BusinessException {
		// TODO Auto-generated method stub
	}
	@Override
	public void deleteEnchere(Enchere article) throws BusinessException {
		// TODO Auto-generated method stub
	}

	
		
//	private Enchere enchereBuilder(ResultSet rs) throws SQLException {
//		Enchere enchereCourante;
//		enchereCourante= new Enchere();
//		enchereCourante.setNoArticle(rs.getInt("id_article"));
//		enchereCourante.setDateFinEncheres(rs.getDate("date_enchere").toLocalDate());
//		enchereCourante.setPrixInitial(rs.getInt("montant_enchere"));
//		return enchereCourante;
//	}
}
