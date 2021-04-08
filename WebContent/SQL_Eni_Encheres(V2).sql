CREATE TABLE CATEGORIES (
    no_categorie	INTEGER IDENTITY(1,1) NOT NULL,
    libelle			VARCHAR(30)			NOT NULL
	-- CONSTRAINT ck_categories_libelle CHECK (libelle IN ('Informatique','Ameublement', 'Vétements', 'Sport', 'Loisir'))
)

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie)

CREATE TABLE ENCHERES (
	no_enchere		 INTEGER IDENTITY(1,1) NOT NULL, --Ajout d'un id pour une enchère pour faciliter la logistique de traîtement des données
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     datetime NOT NULL,
	montant_enchere  INTEGER NOT NULL

)

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY (no_utilisateur, no_article)

CREATE TABLE RETRAITS (
	no_article       INTEGER	NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      CHAR(5)	NOT NULL		CONSTRAINT ck_retraits_cpo CHECK(convert(numeric(5),code_postal) BETWEEN 1000 AND 95999), --> Code postal compris entre 01000 et 95999 conforme aux normes françaises
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_article)

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) UNIQUE NOT NULL, --> Pas de pseudo identiques pour éviter des complications logistiques côté gestion des utilisateurs
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(40) UNIQUE NOT NULL, --> Mail unique par compte
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(5) NOT NULL	CONSTRAINT ck_utilisateurs_cpo CHECK(convert(numeric(5),code_postal) BETWEEN 1000 AND 95999), --> Code postal compris entre 01000 et 95999 conforme aux normes françaises
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER	NOT NULL	CONSTRAINT df_utilisateurs_credit DEFAULT 150, --> A la création du compte l'utilisateur commence avec 150 points
    administrateur   bit		NOT NULL	CONSTRAINT df_utilisateurs_administrateur DEFAULT 0 --> Par défaut à false, il y aura plus de comptes utilisateurs que admin de crées durant l'existence du site.
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)


CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30)	NOT NULL,
    description                   VARCHAR(300)	NOT NULL,
	photo						  VARBINARY (MAX),											--> Pour l'upload d'une photo de l'article	
	date_debut_encheres			   DATE			NOT NULL,	
    date_fin_encheres             DATE			NOT NULL,	
    prix_initial                  INTEGER		NOT NULL,
    prix_vente                    INTEGER,
	etat_vente					  bit			NULL CONSTRAINT df_articles_vendus_etat_vente DEFAULT 0,	--> Pour faciliter la logistique côté code pour l'état de la vente (si true, la vente est en cours) 
	etat_archive				  bit			NULL CONSTRAINT df_articles_vendus_etat_archive DEFAULT 0,	--> Pour faciliter la logistique côté code pour l'état de la vente (si true, l'enchère s'est terminée et l'article archivé) 
    no_utilisateur                INTEGER		NOT NULL,
    no_categorie                  INTEGER		NOT NULL
    
)

ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (no_article)

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 

INSERT INTO CATEGORIES (libelle) VALUES ('Informatique')
INSERT INTO CATEGORIES (libelle) VALUES ('Ameublement')
INSERT INTO CATEGORIES (libelle) VALUES ('Vêtements')
INSERT INTO CATEGORIES (libelle) VALUES ('Sport')
INSERT INTO CATEGORIES (libelle) VALUES ('Loisirs')
