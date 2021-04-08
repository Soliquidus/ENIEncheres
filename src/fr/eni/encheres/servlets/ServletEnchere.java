package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Enchere;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ServletEnchere
 */
@WebServlet("/ServletEnchere")
public class ServletEnchere extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEnchere() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvel_article.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    protected void insertEnchere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> listeCodesErreur = new ArrayList<>();
        int noUtilisateur = 0;
        int noArticle = 0;
        LocalDate dateEnchere = null;
        int montantEnchere = 0;
        noUtilisateur = lireParametreIdUtilisateur(request, listeCodesErreur);
        noArticle = lireParametreIdArticle(request, listeCodesErreur);
        montantEnchere = lireParametrePrixInitial(request, listeCodesErreur);
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateEnchere = LocalDate.parse(request.getParameter("date_fin"), dtf);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_ERREUR);
        }

        if (listeCodesErreur.size() > 0) {
            request.setAttribute("listeCodesErreur", listeCodesErreur);
        } else {
            EnchereManager enchereManager = new EnchereManager();
            try {
                Enchere enchere = enchereManager.insertEnchere(noUtilisateur, noArticle, dateEnchere, montantEnchere);
                enchere = enchereManager.selectByEnchere(enchere.getNoEnchere());
                String versPage = "newArticle";
                if (enchere != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("enchere", enchere);
                    session.setAttribute("no_enchere", enchere.getNoEnchere());
                    versPage = "accueil";
                }
                RequestDispatcher rd = request.getRequestDispatcher(versPage);
                rd.forward(request, response);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private int lireParametreIdArticle(HttpServletRequest request, List<Integer> listeCodesErreur) {
        int noArticle = 0;

        try {
            if (request.getParameter("no_article") != null) {
                noArticle = Integer.parseInt(request.getParameter("no_article"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            listeCodesErreur.add(CodesResultatServlets.FORMAT_ID_ARTICLE_ERREUR);
        }

        return noArticle;
    }

    private int lireParametreIdUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
        int noUtilisateur = 0;

        try {
            if (request.getParameter("no_utilisateur") != null) {
                noUtilisateur = Integer.parseInt(request.getParameter("no_utilisateur"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            listeCodesErreur.add(CodesResultatServlets.FORMAT_ID_UTILISATEUR_ERREUR);
        }

        return noUtilisateur;
    }

    private int lireParametrePrixInitial(HttpServletRequest request, List<Integer> listeCodesErreur) {
        int prixInitial = 0;
        try {
            if (request.getParameter("prix_enchere") != null) {
                prixInitial = Integer.parseInt(request.getParameter("prix_enchere"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            listeCodesErreur.add(CodesResultatServlets.PRIX_NULL);
        }
        return prixInitial;
    }
}
