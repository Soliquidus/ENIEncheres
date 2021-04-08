package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public void insertUtilisateur (Utilisateur utilisateur) throws BusinessException;
	public void deleteUtilisateur (Utilisateur utilisateur) throws BusinessException;
	public void updateUtilisateur (Utilisateur utilisateur) throws BusinessException;
	public Utilisateur connectUtilisateur (String pseudo, String motDePasse) throws BusinessException;
	public Utilisateur selectByUtilisateur (int id) throws BusinessException;
	public void insertAdmin(Utilisateur utilisateur) throws BusinessException;
	public void insertAchat (ArticleVendu article) throws BusinessException;
	public void deleteEnchere(Enchere article) throws BusinessException;
}
