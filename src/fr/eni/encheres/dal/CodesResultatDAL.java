package fr.eni.encheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	//Erreurs utilisateur

	public static final Integer INSERT_UTILISATEUR_NULL=10000; //L'utilisateur ne peut pas être null
	public static final Integer INSERT_UTILISATEUR_ECHEC=10001; //Erreur généralisée pendant l'insertion
	public static final Integer DELETE_UTILISATEUR_ECHEC=10002; //Erreur généralisée pendant la suppression
	public static final Integer UPDATE_UTILISATEUR_ECHEC=10003; //Erreur pendant mise à jour
	public static final Integer CONNEXION_UTILISATEUR_ECHEC=10004; //Erreur pendant la connexion
	public static final Integer SELECT_BY_UTILISATEUR_ECHEC=10005; //Erreur pendant la séléction du no_utilisateur
	
	//Erreurs article
	public static final Integer INSERT_ARTICLE_NULL=10006; //L'utilisateur ne peut pas être null
	public static final Integer INSERT_ARTICLE_ECHEC=10007; //Erreur généralisée pendant l'insertion
	public static final Integer DELETE_ARTICLE_ECHEC=10008; //Erreur généralisée pendant la suppression
	public static final Integer SELECT_ARTICLE_ECHEC=10009; //Article non trouvé ou non existant
	public static final Integer SELECT_ALL_ARTICLE_ECHEC=10016; //Articles non trouvés ou non existants
	
	//Erreurs catégorie
	public static final Integer SELECT_CATEGORIE_ECHEC=10010; //Erreur de séléction de catégorie
	public static final Integer SELECT_ALL_CATEGORIE_ECHEC=10011; //Erreur dans la séléction des catégories
	
	//Erreurs enchères
	public static final Integer INSERT_ENCHERE_NULL=10012; //L'enchère ne peut pas être null
	public static final Integer INSERT_ENCHERE_ECHEC=10013; //Erreur généralisée peendant l'insertion
	public static final Integer SELECT_ENCHERE_ECHEC=10014; //Enchère non trouvée ou non existante
	public static final Integer SELECT_ENCHERES_UTLISATEUR=10015; //Enchères non trouvées pour l'utilisateur
	
}