package fr.eni.encheres.servlets;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {
	
	/**
	 * Erreurs Utilisateur
	 */
	//Pseudo
	public static final Integer PSEUDO_UTILISATEUR_OBLIGATOIRE=30000;
	public static final Integer PSEUDO_UTILISATEUR_REGLE_ALPHANUMERIQUE=30001;
	public static final Integer PSEUDO_UTILISATEUR_LONGUEUR_MAX=30002;
	//Nom
	public static final Integer NOM_UTILISATEUR_OBLIGATOIRE=30003;
	public static final Integer NOM_UTILISATEUR_REGLE_ALPHANUMERIQUE=30004;
	public static final Integer NOM_UTILISATEUR_LONGUEUR_MAX=30005;
	//Prenom
	public static final Integer PRENOM_UTILISATEUR_OBLIGATOIRE=30006;
	public static final Integer PRENOM_UTILISATEUR_REGLE_ALPHANUMERIQUE=30007;
	public static final Integer PRENOM_UTILISATEUR_LONGUEUR_MAX=30008;
	//Email
	public static final Integer EMAIL_UTILISATEUR_OBLIGATOIRE=30009;
	public static final Integer EMAIL_UTILISATEUR_REGLE_FORMAT_MAIL=30010;
	public static final Integer EMAIL_UTILISATEUR_LONGUEUR_MAX=30011;
	//Téléphone
	public static final Integer TELEPHONE_UTILISATEUR_OBLIGATOIRE=30012;
	public static final Integer TELEPHONE_UTILISATEUR_REGLE_ALPHANUMERIQUE=30013;
	public static final Integer TELEPHONE_UTILISATEUR_LONGUEUR_MAX=30014;
	//Rue
	public static final Integer RUE_UTILISATEUR_OBLIGATOIRE=30015;
	public static final Integer RUE_UTILISATEUR_REGLE_ALPHANUMERIQUE=30016;
	public static final Integer RUE_UTILISATEUR_LONGUEUR_MAX=30017;
	//Code postal
	public static final Integer CODE_POSTAL_UTILISATEUR_OBLIGATOIRE=30018;
	public static final Integer CODE_POSTAL_UTILISATEUR_REGLE_ALPHANUMERIQUE=30019;
	public static final Integer CODE_POSTAL_UTILISATEUR_LONGUEUR_MAX=30020;
	//Ville
	public static final Integer VILLE_UTILISATEUR_OBLIGATOIRE=30021;
	public static final Integer VILLE_UTILISATEUR_REGLE_ALPHANUMERIQUE=30022;
	public static final Integer VILLE_UTILISATEUR_LONGUEUR_MAX=30023;
	//Mot de passe
	public static final Integer MDP_UTILISATEUR_OBLIGATOIRE=30024;
	public static final Integer MDP_UTILISATEUR_REGLE_ALPHANUMERIQUE=30025;
	public static final Integer MDP_UTILISATEUR_LONGUEUR_MAX=30026;
	
	//ID en BDD
	public static final Integer FORMAT_ID_UTILISATEUR_ERREUR = 30027;
	public static final Integer FORMAT_ID_ARTICLE_ERREUR = 30028;
	public static final Integer FORMAT_ID_ENCHERE_ERREUR = 30029;
	public static final Integer VERIF_ID_UTILISATEUR_ERREUR = 30030;
	
	/*
	 * Erreurs articles et enchères
	 */
	public static final Integer FORMAT_DATE_ERREUR = 30040;
	public static final Integer NOM_ARTICLE_OBLIGATOIRE = 30041;
	public static final Integer NOM_ARTICLE_REGLE_ALPHANUMERIQUE = 30042;
	public static final Integer NOM_ARTICLE_LONGUEUR_MAX=30043;
	public static final Integer DESCRIPTION_OBLIGATOIRE = 30044;
	public static final Integer DESCRIPTION_REGLE_ALPHANUMERIQUE = 30045;
	public static final Integer DESCRIPTION_LONGUEUR_MAX=30046;
	public static final Integer PRIX_NULL=30047;
	public static final Integer CATEGORIE_NULL=30048;
	public static final Integer DATE_DEBUT_ARTICLE_REGLE = 30049;
	public static final Integer DATE_FIN_ARTICLE_REGLE = 30050;
	
	
	
	
	
	
}