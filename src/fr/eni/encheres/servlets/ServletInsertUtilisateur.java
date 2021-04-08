package fr.eni.encheres.servlets;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ServletGestionUtilisateur
 */
@WebServlet("/newUser")
public class ServletInsertUtilisateur extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvel_utilisateur.jsp");
		rd.forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//Ajout d'un utilisateur
		ajouterNouvelUtilisateur(request, response);
	}
	
	private void ajouterNouvelUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Lecture des paramètres
		String pseudo = null;
		String nom = null;
		String prenom = null;
		String email = null;
		String telephone = null;
		String rue = null;
		String codePostal = null;
		String ville = null;
		String motDePasse = null;
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		pseudo = lireParametrePseudoUtilisateur(request, listeCodesErreur);
		nom = lireParametreNomUtilisateur(request, listeCodesErreur);
		prenom = lireParametrePrenomUtilisateur(request, listeCodesErreur);
		email = lireParametreMailUtilisateur(request, listeCodesErreur);
		telephone = lireParametreTelephoneUtilisateur(request, listeCodesErreur);
		rue = lireParametreRueUtilisateur(request, listeCodesErreur);
		codePostal = lireParametreCodePostalUtilisateur(request, listeCodesErreur);
		ville = lireParametreVilleUtilisateur(request, listeCodesErreur);
		motDePasse = lireParametreMotDePasseUtilisateur(request, listeCodesErreur);
		
		if(listeCodesErreur.size()>0)
		{
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		else
		{
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			try 
			{
				Utilisateur utilisateur = utilisateurManager.ajouterUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
				utilisateur = utilisateurManager.SelectByUtilisateur(utilisateur.getNoUtilisateur());
				String versPage="newUser";
				if (utilisateur != null)
				{
					HttpSession session = request.getSession();
					session.setAttribute("utilisateur", utilisateur);
					session.setAttribute("no_utilisateur", utilisateur.getNoUtilisateur());
					versPage = "accueil";
				}
				RequestDispatcher rd = request.getRequestDispatcher(versPage);
				rd.forward(request, response);
			}catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
		}
	}
	
	//Validations
	
	private String lireParametrePseudoUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String pseudo;
		String regex = "^.*[a-zA-Z0-9]+.*$";
		pseudo = request.getParameter("pseudo");
		boolean alphanumerique = pseudo.matches(regex);
		if(pseudo == null || pseudo.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_UTILISATEUR_OBLIGATOIRE);
		}
		if(!alphanumerique)
		{
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_UTILISATEUR_REGLE_ALPHANUMERIQUE);
		}
		if(pseudo.trim().length()>30)
		{
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_UTILISATEUR_LONGUEUR_MAX);
		}
		return pseudo;
	}
	
	private String lireParametreNomUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String nom;
		String regex = "^.*[a-zA-Z]+.*$";
		nom = request.getParameter("nom");
		boolean alphanumerique = nom.matches(regex);
		if(nom == null || nom.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.NOM_UTILISATEUR_OBLIGATOIRE);
		}
		if(!alphanumerique)
		{
			listeCodesErreur.add(CodesResultatServlets.NOM_UTILISATEUR_REGLE_ALPHANUMERIQUE);
		}
		if(nom.trim().length()>30)
		{
			listeCodesErreur.add(CodesResultatServlets.NOM_UTILISATEUR_LONGUEUR_MAX);
		}
		return nom;
	}
	
	private String lireParametrePrenomUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String prenom;
		String regex = "^.*[a-zA-Z]+.*$";
		prenom = request.getParameter("prenom");
		boolean alphanumerique = prenom.matches(regex);
		if(prenom == null || prenom.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.PRENOM_UTILISATEUR_OBLIGATOIRE);
		}
		if(!alphanumerique)
		{
			listeCodesErreur.add(CodesResultatServlets.PRENOM_UTILISATEUR_REGLE_ALPHANUMERIQUE);
		}
		if(prenom.trim().length()>30)
		{
			listeCodesErreur.add(CodesResultatServlets.PRENOM_UTILISATEUR_LONGUEUR_MAX);
		}
		return prenom;
	}
	
	private String lireParametreMailUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String email;
		String regex = "^(.+)@(.+)$";
		email = request.getParameter("email");
		boolean formatMail = email.matches(regex);
		if(email == null || email.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.EMAIL_UTILISATEUR_OBLIGATOIRE);
		}
		if(!formatMail)
		{
			listeCodesErreur.add(CodesResultatServlets.EMAIL_UTILISATEUR_REGLE_FORMAT_MAIL);
		}
		if(email.trim().length()>40)
		{
			listeCodesErreur.add(CodesResultatServlets.EMAIL_UTILISATEUR_LONGUEUR_MAX);
		}
		return email;
	}
	
	private String lireParametreTelephoneUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String telephone;
		String regex = "^.*[0-9]+.*$";
		telephone = request.getParameter("telephone");
		boolean alphanumerique = telephone.matches(regex);
		if(telephone == null || telephone.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.TELEPHONE_UTILISATEUR_OBLIGATOIRE);
		}
		if(!alphanumerique)
		{
			listeCodesErreur.add(CodesResultatServlets.TELEPHONE_UTILISATEUR_REGLE_ALPHANUMERIQUE);
		}
		if(telephone.trim().length()>15)
		{
			listeCodesErreur.add(CodesResultatServlets.TELEPHONE_UTILISATEUR_LONGUEUR_MAX);
		}
		return telephone;
	}
	
	private String lireParametreRueUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String rue;
		String regex = "^.*[a-zA-Z0-9]+.*$";
		rue = request.getParameter("rue");
		boolean alphanumerique = rue.matches(regex);
		if(rue == null || rue.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.RUE_UTILISATEUR_OBLIGATOIRE);
		}
		if(!alphanumerique)
		{
			listeCodesErreur.add(CodesResultatServlets.RUE_UTILISATEUR_REGLE_ALPHANUMERIQUE);
		}
		if(rue.trim().length()>30)
		{
			listeCodesErreur.add(CodesResultatServlets.RUE_UTILISATEUR_LONGUEUR_MAX);
		}
		return rue;
	}
	
	private String lireParametreCodePostalUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String codePostal;
		String regex = "^.*[0-9]+.*$";
		codePostal = request.getParameter("code_postal");
		boolean alphanumerique = codePostal.matches(regex);
		if(codePostal == null || codePostal.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.CODE_POSTAL_UTILISATEUR_OBLIGATOIRE);
		}
		if(!alphanumerique)
		{
			listeCodesErreur.add(CodesResultatServlets.CODE_POSTAL_UTILISATEUR_REGLE_ALPHANUMERIQUE);
		}
		if(codePostal.trim().length()>5)
		{
			listeCodesErreur.add(CodesResultatServlets.CODE_POSTAL_UTILISATEUR_LONGUEUR_MAX);
		}
		return codePostal;
	}
	
	private String lireParametreVilleUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String ville;
		String regex = "^.*[a-zA-Z]+.*$";
		ville = request.getParameter("ville");
		boolean alphanumerique = ville.matches(regex);
		if(ville == null || ville.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.RUE_UTILISATEUR_OBLIGATOIRE);
		}
		if(!alphanumerique)
		{
			listeCodesErreur.add(CodesResultatServlets.RUE_UTILISATEUR_REGLE_ALPHANUMERIQUE);
		}
		if(ville.trim().length()>30)
		{
			listeCodesErreur.add(CodesResultatServlets.RUE_UTILISATEUR_LONGUEUR_MAX);
		}
		return ville;
	}
	
	private String lireParametreMotDePasseUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String motDePasse;
		String regex = "^.*[a-zA-Z]+.*$";
		motDePasse = request.getParameter("mot_de_passe");
		boolean alphanumerique = motDePasse.matches(regex);
		if(motDePasse == null || motDePasse.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.MDP_UTILISATEUR_OBLIGATOIRE);
		}
		if(!alphanumerique)
		{
			listeCodesErreur.add(CodesResultatServlets.MDP_UTILISATEUR_REGLE_ALPHANUMERIQUE);
		}
		if(motDePasse.trim().length()>30)
		{
			listeCodesErreur.add(CodesResultatServlets.MDP_UTILISATEUR_LONGUEUR_MAX);
		}
		return motDePasse;
	}
}
