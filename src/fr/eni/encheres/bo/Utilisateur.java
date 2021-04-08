package fr.eni.encheres.bo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Classe qui modélise un utilisateur, qu'il soit acheteur, enchérisseur ou admin
 *
 * @author Tibo Pfeifer
 * @version 1.0
 * @date 10/08/2020
 */
public class Utilisateur implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //Attributs

    private int noUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String codePostal;
    private String ville;
    private String motDePasse;
    private int credit;
    private boolean administrateur;
    private List<ArticleVendu> listeVentes;
    private List<ArticleVendu> listeAchats;
    private List<ArticleVendu> listeEncheres;

    //Constructeurs

    public Utilisateur() {

    }

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone,
                       String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur,
                       List<ArticleVendu> listeVentes, List<ArticleVendu> listeAchats, List<ArticleVendu> listeEncheres) {
        super();
        setPseudo(pseudo);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
        setTelephone(telephone);
        setRue(rue);
        setCodePostal(codePostal);
        setVille(ville);
        setMotDePasse(motDePasse);
        setCredit(credit);
        setAdministrateur(administrateur);
        setListeVentes(listeVentes);
        setListeAchats(listeAchats);
        setListeEncheres(listeEncheres);
    }

    public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
                       String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur,
                       List<ArticleVendu> listeVentes, List<ArticleVendu> listeAchats, List<ArticleVendu> listeEncheres) {
        super();
        setNoUtilisateur(noUtilisateur);
        setPseudo(pseudo);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
        setTelephone(telephone);
        setRue(rue);
        setCodePostal(codePostal);
        setVille(ville);
        setMotDePasse(motDePasse);
        setCredit(credit);
        setAdministrateur(administrateur);
        setListeVentes(listeVentes);
        setListeAchats(listeAchats);
        setListeEncheres(listeEncheres);
    }

    //Getters et Setters

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public List<ArticleVendu> getListeVentes() {
        return listeVentes;
    }

    public void setListeVentes(List<ArticleVendu> listeVentes) {
        this.listeVentes = listeVentes;
    }

    public List<ArticleVendu> getListeAchats() {
        return listeAchats;
    }

    public void setListeAchats(List<ArticleVendu> listeAchats) {
        this.listeAchats = listeAchats;
    }

    public List<ArticleVendu> getListeEncheres() {
        return listeEncheres;
    }

    public void setListeEncheres(List<ArticleVendu> listeEncheres) {
        this.listeEncheres = listeEncheres;
    }

    @Override
    public String toString() {
        return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
                + prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
                + codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
                + ", administrateur=" + administrateur + ", listeVentes=" + listeVentes + ", listeAchats=" + listeAchats
                + ", listeEncheres=" + listeEncheres + "]";
    }


}
