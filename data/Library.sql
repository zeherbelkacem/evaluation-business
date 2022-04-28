-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données  Library                                   ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS Library;
CREATE DATABASE Library;
USE Library;

-- -----------------------------------------------------------------------------
-- - Construction de la tables des books, categories, users, orders, orderItems                         ---
-- -----------------------------------------------------------------------------


-- -----------------------------------------------------------------------------
-- - T_Themes                       ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Themes (
	IdTheme			int(4)		PRIMARY KEY AUTO_INCREMENT,
	ThemeName			varchar(30)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Themes ( ThemeName ) VALUES ( 'voyage' );
INSERT INTO T_Themes ( ThemeName ) VALUES ( 'jeunesse' );
INSERT INTO T_Themes ( ThemeName ) VALUES ( 'cuisine' );
INSERT INTO T_Themes ( ThemeName ) VALUES ( 'poesie' );
INSERT INTO T_Themes ( ThemeName ) VALUES ( 'science fiction' );
INSERT INTO T_Themes ( ThemeName ) VALUES ( 'polar' );
INSERT INTO T_Themes ( ThemeName ) VALUES ( 'roman' );
INSERT INTO T_Themes ( ThemeName ) VALUES ( 'petit-prix' );
INSERT INTO T_Themes ( ThemeName ) VALUES ( 'meilleures ventes' );

-- -----------------------------------------------------------------------------
-- - T_Books                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Books (
	IdBook				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Title				varchar(30)	NOT NULL,
	Author				varchar(30)	NOT NULL,
	Editor				varchar(30)	NOT NULL,
	Description			varchar(30)	NOT NULL,
	UnitaryPrice		float(8, 2)	NOT NULL DEFAULT 0
) ENGINE = InnoDB;

INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'Voyager en Famille', 'Voyager en Famille', 'Hachette ', 'Voici un livre qu...', 20 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'Le monde - 2ed', 'Lonely planet fr', 'Lonely Planet', '221 destinations, de A... ', 19 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'Le Club des Cinq', 'Enid Blyton', 'Nouvelle Bibliotheque', 'est une serie de...', 20.5 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'Elisabeth T22', 'Any gays', 'Albin Michel', 'Angelique et Mme de', 22.9 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'Mieux manger', 'Mariotte Laurent', 'Solar', ' manger sans se rui...', 15.5 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'Les fleurs du mal', 'Baudelaire', 'Auguste', 'Description', 18 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'Paroles', 'Jacques Prevert', 'wLe Point', ' est un recueil de 91...', 17.9 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'la nuit du faune', 'Romain Lucazeau', 'Albin Michel', 'Au sommet d’une...', 21.9 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'Tout quitter', 'Anais Vanel', 'FLAMMARION', 'Un jour j''ai achete', 16.9 );

-- -----------------------------------------------------------------------------
-- - T_Books_T_Themes_Association                        ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Books_Themes_Association (
	IdBook			int(4) NOT NULL		REFERENCES T_Books(IdBook),
	IdTheme			int(4) NOT NULL		REFERENCES T_Themes(IdTheme)
) ENGINE = InnoDB;

INSERT INTO T_Books_Themes_Association VALUES (1, 1 ), (1, 8), (2, 1), (3, 2), (3, 9), (4, 2), (5, 3), (5, 1), (5, 8), (6, 4), (6, 9), (6, 7), (7, 4), (7, 9), (8,5), (9, 1), (9, 7), (9, 9);


-- -----------------------------------------------------------------------------
-- - T_Users                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
	IdUser					int(4)		PRIMARY KEY AUTO_INCREMENT,
	Name					varchar(30)	NOT NULL,
	Email					varchar(30)	NOT NULL UNIQUE,
	Phone					varchar(30)	NOT NULL UNIQUE,
	Address					varchar(30)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Users ( Name, Email, Phone, Address) VALUES ( 'Belkacem', 'belkacem@fms.fr', '0600110022', 'Toulouse 1' );
INSERT INTO T_Users ( Name, Email, Phone, Address) VALUES ( 'Sarah', 'sarah@fms.fr', '0611002200', 'Toulouse 2' );
INSERT INTO T_Users ( Name, Email, Phone, Address) VALUES ( 'Christophe', 'christophe@fms.fr', '0600330044', 'Toulouse 3' );
INSERT INTO T_Users ( Name, Email, Phone, Address) VALUES ( 'Jean Charles', 'jcharles@fms.fr', '0600220033', 'Toulouse 4' );
INSERT INTO T_Users ( Name, Email, Phone, Address) VALUES ( 'Delmerie', 'delmerie@fms.fr', '0622003300', 'Toulouse 5' );
INSERT INTO T_Users ( Name, Email, Phone, Address) VALUES ( 'Huho', 'hugo@fms.fr', '0633004400', 'Toulouse 6' );
INSERT INTO T_Users ( Name, Email, Phone, Address) VALUES ( 'Caroline', 'caroline@fms.fr', '0600440055', 'Toulouse 7' );

-- -----------------------------------------------------------------------------
-- - T_Orders                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Orders (
	IdOrder				  int(4)		PRIMARY KEY AUTO_INCREMENT,
	Date				  date,
	Amount 				  float(8,2)    NOT NULL,
	IdUser 				  int(4)		NOT NULL,
	CONSTRAINT `fk_user`
    FOREIGN KEY (IdUser) REFERENCES T_Users (IdUser)
) ENGINE = InnoDB;

-- -----------------------------------------------------------------------------
-- - T_OrderItems                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_OrderItems (
	IdOrderItem			int(4)		PRIMARY KEY AUTO_INCREMENT,
	Quantity			int(4) 		NOT NULL,
	ItemPrice 			float(8,2)	NOT NULL,
	IdOrder				int(4) 		NOT NULL,
	IdBook				int(4) 		NOT NULL,
	CONSTRAINT `fk_book`
    FOREIGN KEY (IdBook) REFERENCES T_Books (IdBook),
    CONSTRAINT `fk_order`
    FOREIGN KEY (IdOrder) REFERENCES T_Orders (IdOrder)
) ENGINE = InnoDB;
