package fr.eni.encheres.bo;

import java.io.Serial;
import java.io.Serializable;

/**
 * Classe qui modélise une catégorie
 *
 * @author Tibo Pfeifer
 * @version 2.0
 * @date 10/08/2020
 * @dateModif 17/08/2020 sécurisation des classes
 */
public class Categorie implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //Attributs

    private int noCategorie;
    private String libelle;


    //Constructeurs

    public Categorie() {
    }

    public Categorie(int noCategorie, String libelle) {
        super();
        setNoCategorie(noCategorie);
        setLibelle(libelle);
    }

    public int getNoCategorie() {
        return noCategorie;
    }

    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }


    //Getters et Setters


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


}
