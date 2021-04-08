package fr.eni.encheres.servlets;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
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
@WebServlet(urlPatterns= {"/accueil", "/deleteUser", "/login", "/logout", "/profile"})
public class ServletGestionUtilisateur extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionUtilisateur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		try {
			switch(action) {
			case "/accueil" :
				List<ArticleVendu> articlesUtilisateur = insertArticlesUtilisateur(request);
//				List<ArticleVendu> articles = insertArticles(request);
//				request.setAttribute("articles", articles);
				request.setAttribute("articles_utilisateur", articlesUtilisateur);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
				rd.forward(request, response);
				break;
			case "/updateUser" :
				RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/modification_utilisateur.jsp");
				rd2.forward(request, response);
			case "/deleteUser" :
				deleteUtilisateur(request, response);
				break;
			case "/login" :
				loginUtilisateur(request, response);
				break;
			case "/logout" :
				logoutUtilisateur(request, response);
				break;
			case "/profile" :
				readUtilisateur(request, response);
				break;
			default :
				RequestDispatcher rd3 = request.getRequestDispatcher("accueil");
				rd3.forward(request, response);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if (action == "/updateUser")
		{
			updateUtilisateur(request, response);
		} else
		doGet(request,response);
	}
	
	
	private List <ArticleVendu> insertArticlesUtilisateur(HttpServletRequest request) throws ServletException, IOException {
		List <ArticleVendu> articlesUtilisateur = null;
		List<Integer> listeCodesErreur = new ArrayList<>();
		ArticleManager articleManager = new ArticleManager();
		int noUtilisateur = 0;
		noUtilisateur = lireParametreIdUtilisateur(request, listeCodesErreur);
		try {
			articlesUtilisateur = articleManager.selectAllByUtilisateur(noUtilisateur);
		//	request.setAttribute("categories", categories);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
	
		return articlesUtilisateur;
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvel_article.jsp");
//		rd.forward(request, response);
	}
	private void updateUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Lecture des paramètres
				request.setCharacterEncoding("UTF-8");
				int noUtilisateur;
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
				
				noUtilisateur = lireParametreIdUtilisateur(request, listeCodesErreur);
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
						Utilisateur utilisateur = utilisateurManager.updateUtilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
						request.setAttribute("utilisateur", utilisateur);
						
						if (utilisateur != null)
						{
							HttpSession session = request.getSession();
							session.setAttribute("utilisateur", utilisateur);
						}
					}catch (BusinessException e) {
						// TODO: handle exception
					}
				}
				
//				PrintWriter out =response.getWriter();
//				out.println("noUtilisateur" + noUtilisateur);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil_utilisateur.jsp");
				rd.forward(request, response);
	}
	
	private void deleteUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> listeCodesErreur=new ArrayList<>();
		int noUtilisateur = lireParametreIdUtilisateur(request, listeCodesErreur);
		
		if(listeCodesErreur.size()>0)
		{
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		} else
		{
			UtilisateurManager utilisateurManager = new UtilisateurManager();
		try {
			
			utilisateurManager.deleteUtilisateur(noUtilisateur);
			
			HttpSession session = request.getSession(false);
			if (session != null)
			{
				session.removeAttribute("utilisateur");
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/supprimer_utilisateur.jsp");
				rd.forward(request, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/supprimer_utilisateur.jsp");
		rd.forward(request, response);
	}
	
	private void loginUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("mot_de_passe");
		List <Integer> listeCodesErreur = new ArrayList <>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try
		{
			Utilisateur utilisateur = utilisateurManager.connectUtilisateur(pseudo, motDePasse);
			String versPage = "connexion";
			if (utilisateur != null)
			{
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				session.setAttribute("no_utilisateur", utilisateur.getNoUtilisateur());
				versPage = "accueil";
			}
			if(listeCodesErreur.size()>0)
			{
				request.setAttribute("listeCodesErreur", listeCodesErreur);
			}
			RequestDispatcher rd = request.getRequestDispatcher(versPage);
			rd.forward(request, response);
		}
		catch (BusinessException e) 
		{
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}

	}
	
	private void logoutUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			session.removeAttribute("utilisateur");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		}
	}
	
	private void readUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().getAttribute("utilisateur");
		RequestDispatcher rd=request.getRequestDispatcher("profil");
		rd.forward(request, response);
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
	
	private List <ArticleVendu> insertArticles(HttpServletRequest request) throws ServletException, IOException {
		List <ArticleVendu> articles = null;
		ArticleManager articleManager = new ArticleManager();
		try {
			articles = articleManager.selectAllArticles();
		//	request.setAttribute("categories", categories);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		return articles;
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvel_article.jsp");
//		rd.forward(request, response);
	}
	private int lireParametreIdUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		int noUtilisateur=0;
		try
		{
			if(request.getParameter("no_utilisateur")!=null)
			{
				noUtilisateur = Integer.parseInt(request.getParameter("no_utilisateur"));
			}
		}catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ID_UTILISATEUR_ERREUR);
		}
		return noUtilisateur;
	}
}
