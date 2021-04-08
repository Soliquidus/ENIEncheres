package fr.eni.encheres.servlets;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.CodesResultatBLL;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ServletInsertArticle
 */
@WebServlet("/newArticle")
@MultipartConfig(maxFileSize = 16177215)    // taille de la photo max 16MB
public class ServletInsertArticle extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List <Categorie> categories = insertCategories(request);
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvel_article.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		insertArticle(request, response);
	}

	protected void insertArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> listeCodesErreur = new ArrayList<>();
		String nom = null;
		String description = null;
		LocalDate dateDebutEncheres = null;
		LocalDate dateFinEncheres = null;
		InputStream photo = null;
		int noUtilisateur = lireParametreIdUtilisateur(request, listeCodesErreur);
		int prixInitial = 0;
		int noCategorie = 0;
		nom = lireParametreNomArticle(request, listeCodesErreur);
		description = lireParametreDescriptionArticle(request, listeCodesErreur);
		prixInitial = lireParametrePrixInitial(request, listeCodesErreur);
		noCategorie = lireParametreCategorie(request, listeCodesErreur);
		photo = lireParametrePhoto(request, listeCodesErreur);
		
		//Lecture date de début d'enchère
		try 
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(dateDebutEncheres==null || 
					dateDebutEncheres.isBefore(LocalDate.now()))
				{
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_ERREUR);
				}
			dateDebutEncheres = LocalDate.parse(request.getParameter("date_debut"),dtf);
			
		}
		catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_ERREUR);
		}
		//Lecture date de fin d'enchère
		try 
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateFinEncheres = LocalDate.parse(request.getParameter("date_fin"),dtf);
		}
		catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_ERREUR);
		}
		
		if(listeCodesErreur.size()>0)
		{
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		else
			
		{
			ArticleManager articleManager = new ArticleManager();
			try
			{
//			ArticleVendu article = articleManager.insertAchatNoPhoto(nom, description, dateDebutEncheres, dateFinEncheres, noUtilisateur, prixInitial, noCategorie);
			ArticleVendu article = articleManager.insertAchat(nom, description, photo, dateDebutEncheres, dateFinEncheres, noUtilisateur, prixInitial, noCategorie);
			article = articleManager.selectByArticle(article.getNoArticle());
			String versPage="newArticle";
				if (article != null)
				{
					HttpSession session = request.getSession();
					session.setAttribute("article", article);
					session.setAttribute("no_article", article.getNoArticle());
					session.setAttribute("no_utilisateur_article", article.getNoUtilisateur());
					versPage="accueil";
				}
				RequestDispatcher rd = request.getRequestDispatcher(versPage);
				rd.forward(request, response);
			}
			catch (BusinessException e) 
			{
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
		}
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
	
	private List <Categorie> insertCategories(HttpServletRequest request) throws ServletException, IOException {
		List <Categorie> categories = null;
		CategorieManager categorieManager = new CategorieManager();
		try {
			categories = categorieManager.selectAllCategorie();
		//	request.setAttribute("categories", categories);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		return categories;
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvel_article.jsp");
//		rd.forward(request, response);
	}
	
	//Validations
	
		private String lireParametreNomArticle(HttpServletRequest request, List<Integer> listeCodesErreur) {
			String nomArticle;
			String regex = "^.*[a-zA-Z0-9]+.*$";
			nomArticle = request.getParameter("nom_article");
			boolean alphanumerique = nomArticle.matches(regex);
			if(nomArticle == null || nomArticle.trim().equals(""))
			{
				listeCodesErreur.add(CodesResultatServlets.NOM_ARTICLE_OBLIGATOIRE);
			}
			if(!alphanumerique)
			{
				listeCodesErreur.add(CodesResultatServlets.NOM_ARTICLE_REGLE_ALPHANUMERIQUE);
			}
			if(nomArticle.trim().length()>30)
			{
				listeCodesErreur.add(CodesResultatServlets.NOM_ARTICLE_LONGUEUR_MAX);
			}
			return nomArticle;
		}
		
		private String lireParametreDescriptionArticle(HttpServletRequest request, List<Integer> listeCodesErreur) {
			String description;
			String regex = "^.*[a-zA-Z0-9]+.*$";
			description = request.getParameter("description_article");
			boolean alphanumerique = description.matches(regex);
			if(description == null || description.trim().equals(""))
			{
				listeCodesErreur.add(CodesResultatServlets.DESCRIPTION_OBLIGATOIRE);
			}
			if(!alphanumerique)
			{
				listeCodesErreur.add(CodesResultatServlets.DESCRIPTION_REGLE_ALPHANUMERIQUE);
			}
			if(description.trim().length()>300)
			{
				listeCodesErreur.add(CodesResultatServlets.DESCRIPTION_LONGUEUR_MAX);
			}
			return description;
		}
		
		private int lireParametrePrixInitial (HttpServletRequest request, List<Integer> listeCodesErreur) {
			int prixInitial=0;
			try
			{
				if(request.getParameter("prix_initial")!=null)
				{
					prixInitial = Integer.parseInt(request.getParameter("prix_initial"));
				}
			}catch (NumberFormatException e) {
				e.printStackTrace();
				listeCodesErreur.add(CodesResultatServlets.PRIX_NULL);
			}
			return prixInitial;
		}
		
		private int lireParametreCategorie (HttpServletRequest request, List<Integer> listeCodesErreur) {
			int noCategorie=0;
			try
			{
				if(request.getParameter("no_categorie")!=null)
				{
					noCategorie = Integer.parseInt(request.getParameter("no_categorie"));
				}
			}catch (NumberFormatException e) {
				e.printStackTrace();
				listeCodesErreur.add(CodesResultatServlets.CATEGORIE_NULL);
			}
			return noCategorie;
		}
		
		private InputStream lireParametrePhoto  (HttpServletRequest request, List<Integer> listeCodesErreur) throws IOException, ServletException {
			InputStream photo = null;
//			try 
//			{
//				if(request.getParameter("photo")!=null)
//				{
//					photo = request.getInputStream();
//				}
//			}catch (Exception e) {
//				// TODO: handle exception
//			}
			Part fichier;
//			try {
				fichier = request.getPart("photo");
				 if (fichier != null) {
			            // infos pour le debug
			            System.out.println(fichier.getName());
			            System.out.println(fichier.getSize());
			            System.out.println(fichier.getContentType());
			             
			            // obtains input stream of the upload file
			            photo = fichier.getInputStream();
				}
//			} catch (IOException | ServletException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				 System.out.println(photo);
			return photo;
		}
}
