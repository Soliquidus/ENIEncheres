package fr.eni.encheres.servlets;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ServletGestionArticles
 */
@WebServlet("/articlesUser")
public class ServletArticle extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		selectAllArticlesUser(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void selectAllArticlesUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleManager articleManager = new ArticleManager();
		List<Integer> listeCodesErreur=new ArrayList<>();
		int noUtilisateur = 0;
		noUtilisateur = lireParametreIdUtilisateur(request, listeCodesErreur);
		
		try
		{
			request.setAttribute("articles_utilisateur", articleManager.selectAllByUtilisateur(noUtilisateur));
		}catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("accueil");
		rd.forward(request, response);
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
