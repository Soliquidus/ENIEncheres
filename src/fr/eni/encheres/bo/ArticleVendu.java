package fr.eni.encheres.bo;


import java.io.InputStream;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe qui modélise un article à enchérir
 *
 * @author Tibo Pfeifer
 * @version 2.0
 * @date 10/08/2020
 * @dateModif 17/08/2020 sécurisation des classes
 */
public class ArticleVendu implements Serializable {
    @Serial
	private static final long serialVersionUID = 1L;

    //Attributs

    private int noArticle;
    private String nomArticle;
    private String description;
    private InputStream photo;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private int prixInitial;
    private int prixVente;
    private int noUtilisateur;
    private int noCategorie;
    private List<Enchere> listeEncheres;

    //Constructeurs

    public ArticleVendu() {
    }

    public ArticleVendu(String nomArticle, String description, InputStream photo, LocalDate dateDebutEncheres,
                        LocalDate dateFinEncheres, int prixInitial, int prixVente, int noUtilistateur, int noCategorie) {
        super();
        setNomArticle(nomArticle);
        setDescription(description);
        setPhoto(photo);
        setDateDebutEncheres(dateDebutEncheres);
        setDateFinEncheres(dateFinEncheres);
        setPrixInitial(prixInitial);
        setPrixVente(prixVente);
        setNoUtilisateur(noUtilistateur);
        getNoCategorie();
    }

    public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
                        LocalDate dateFinEncheres, int prixInitial, int prixVente, int noUtilistateur, int noCategorie) {
        super();
        setNomArticle(nomArticle);
        setDescription(description);
        setDateDebutEncheres(dateDebutEncheres);
        setDateFinEncheres(dateFinEncheres);
        setPrixInitial(prixInitial);
        setPrixVente(prixVente);
        setNoUtilisateur(noUtilistateur);
        getNoCategorie();
    }


    //Getters et Setters

//	public ArticleVendu(int noArticle, String nomArticle, String description, InputStream photo, LocalDate dateDebutEncheres,
//			LocalDate dateFinEncheres, int prixInitial, int prixVente, int noCategorie) {
//		// TODO Auto-generated constructor stub
//	}

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public int getNoCategorie() {
        return noCategorie;
    }


    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }


    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public List<Enchere> getListeEnchere() {
        return listeEncheres;
    }

    public void setListeEnchere(List<Enchere> listeEncheres) {
        this.listeEncheres = listeEncheres;
    }


    @Override
    public String toString() {
        return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
                + ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
                + prixInitial + ", prixVente=" + prixVente + ", noUtilistateur=" + noUtilisateur + ", noCategorie="
                + noCategorie + ", listeEnchere=" + listeEncheres + "]";
    }


}
