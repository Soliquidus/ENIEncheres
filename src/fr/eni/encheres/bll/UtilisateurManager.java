package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private final UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		this.utilisateurDAO=DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) throws BusinessException {
			
			BusinessException businessException = new BusinessException();
			this.validerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, businessException);
			
			Utilisateur utilisateur = null;
			 if(businessException.hasErreurs())
			 {
				 utilisateur = new Utilisateur();
				 utilisateur.setPseudo(pseudo);
				 utilisateur.setNom(nom);
				 utilisateur.setPrenom(prenom);
				 utilisateur.setEmail(email);
				 utilisateur.setTelephone(telephone);
				 utilisateur.setRue(rue);
				 utilisateur.setCodePostal(codePostal);
				 utilisateur.setVille(ville);
				 utilisateur.setMotDePasse(motDePasse);
				 utilisateurDAO.insertUtilisateur(utilisateur);
			 }
			 else
			 {
				 throw businessException;
			 }
			 	return utilisateur;
	}
	
	public Utilisateur connectUtilisateur(String pseudo, String motDePasse) throws BusinessException{
		return this.utilisateurDAO.connectUtilisateur(pseudo, motDePasse);
	}
	
	public Utilisateur updateUtilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		this.validerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, businessException);
		Utilisateur utilisateur = null;
		if(businessException.hasErreurs())
		{
			utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(noUtilisateur);
			 utilisateur.setPseudo(pseudo);
			 utilisateur.setNom(nom);
			 utilisateur.setPrenom(prenom);
			 utilisateur.setEmail(email);
			 utilisateur.setTelephone(telephone);
			 utilisateur.setRue(rue);
			 utilisateur.setCodePostal(codePostal);
			 utilisateur.setVille(ville);
			 utilisateur.setMotDePasse(motDePasse);
			 utilisateurDAO.updateUtilisateur(utilisateur);
		}else
		{
			throw businessException;
		}
				return utilisateur;
		
	}
	
	public Utilisateur SelectByUtilisateur (int id) throws BusinessException {
		return this.utilisateurDAO.selectByUtilisateur(id);
	}
	
	public void deleteUtilisateur(int noUtilisateur) throws BusinessException {
		Utilisateur utilisateur = null;
		utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(noUtilisateur);
		utilisateurDAO.deleteUtilisateur(utilisateur);
	}
	//Validations à affiner (cf.ServletGestionUtilisateur)
	
	private void validerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, BusinessException businessException) {
		if(pseudo == null || pseudo.trim().length()>30 || nom == null || nom.trim().length()>30 || prenom == null ||
				prenom.trim().length()>30 || email == null || email.trim().length()>40 || telephone==null || telephone.trim().length()>15
				|| rue == null || rue.trim().length()>30 || codePostal == null || codePostal.trim().length()>5 || ville == null ||
				ville.trim().length()>30 || motDePasse == null || motDePasse.trim().length()>30)
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_INFOS_UTILISATEUR_ERREUR);
		}
		
	}
	
}
	