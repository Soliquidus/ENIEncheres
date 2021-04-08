package fr.eni.encheres.bo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe qui modélise une enchére
 *
 * @author Tibo Pfeifer
 * @version 2.0
 * @date 10/08/2020
 * @dateModif 17/08/2020 (sécurisation de la classe)
 */

public class Enchere implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //Attributs
    private int noEnchere;
    private int noUtilisateur;
    private LocalDate dateEnchere;
    private int montantEnchere;
    private int noArticle;
    private List<ArticleVendu> articlesVendus;

    //Constructeurs

    public Enchere() {

    }

    public Enchere(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial,
                   int prixVente, int noUtilistateur, int noCategorie, List<Enchere> listeEnchere, LocalDate dateEnchere,
                   int montantEnchere, List<ArticleVendu> articlesVendus) {
        super();
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.articlesVendus = articlesVendus;
    }


    public Enchere(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial,
                   int prixVente, int noCategorie, List<Enchere> listeEnchere, LocalDate dateEnchere,
                   int montantEnchere, List<ArticleVendu> articlesVendus) {
        super();
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.articlesVendus = articlesVendus;
    }

    public int getNoEnchere() {
        return noEnchere;
    }

    public void setNoEnchere(int noEnchere) {
        this.noEnchere = noEnchere;
    }

    public LocalDate getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDate dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public List<ArticleVendu> getArticlesVendus() {
        return articlesVendus;
    }

    public void setArticlesVendus(List<ArticleVendu> articlesVendus) {
        this.articlesVendus = articlesVendus;
    }

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }


    //Getters et Setters


}
