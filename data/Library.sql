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

INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleOne', 'AuthorOne', 'EditorOne', 'DescriptionOne', 20 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleTwo', 'AuthorTwo', 'EditorTwo', 'DescriptionTwo', 19 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleThree', 'AuthorThree', 'EditorThree', 'DescriptionThree', 20.5 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleFour', 'AuthorFour', 'EditorFour', 'DescriptionFour', 22.9 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleFive', 'AuthorFive', 'EditorFive', 'DescriptionFive', 15.5 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleSix', 'AuthorSix', 'EditorSix', 'DescriptionSix', 18 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleSeven', 'AuthorSeven', 'EditorSeven', 'DescriptionSeven', 17.9 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleEight', 'AuthorEight', 'EditorEight', 'DescriptionEight', 21.9 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleNine', 'AuthorNine', 'EditorNine', 'DescriptionNine', 16.9 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleTen', 'AuthorTen', 'EditorTen', 'DescriptionTen', 13.5);
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleEleven', 'AuthorEleven', 'EditorEleven', 'DescriptionEleven', 9.9 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice ) VALUES ( 'TitleTwelve', 'AuthorTwelve', 'EditorTwelve', 'DescriptionTwelve', 8.5 );


-- -----------------------------------------------------------------------------
-- - T_Books_T_Themes_Association                        ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Books_Themes_Association (
	IdBook			int(4) NOT NULL		REFERENCES T_Books(IdBook),
	IdTheme			int(4) NOT NULL		REFERENCES T_Themes(IdTheme)
	
) ENGINE = InnoDB;

INSERT INTO T_Books_Themes_Association VALUES (1, 1 ), (1, 3), (2, 1), (2, 8), (3, 5), (4, 6), (4, 8), (5, 1), (6, 2), (6, 1), (7, 7), (8, 3), (9, 4), (9,2), (10, 1), (11, 5), (12, 3);


-- -----------------------------------------------------------------------------
-- - T_Users                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
	IdUser					int(4)		PRIMARY KEY AUTO_INCREMENT,
	Name					varchar(30)	NOT NULL,
	Email					varchar(30)	NOT NULL,
	Phone					varchar(30)	NOT NULL,
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
	Amount 				  float(8,2) NOT NULL,
	IdUser 				  int(4) NOT NULL,
	CONSTRAINT `fk_user`
    FOREIGN KEY (IdUser) REFERENCES T_Users (IdUser)
) ENGINE = InnoDB;

-- -----------------------------------------------------------------------------
-- - T_OrderItems                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_OrderItems (
	IdOrderItem			int(4)		PRIMARY KEY AUTO_INCREMENT,
	Quantity			int(4) NOT NULL,
	ItemPrice 			float(8,2) NOT NULL,
	IdOrder				int(4) NOT NULL,
	IdBook				int(4) NOT NULL,
	CONSTRAINT `fk_book`
    FOREIGN KEY (IdBook) REFERENCES T_Books (IdBook),
    CONSTRAINT `fk_order`
    FOREIGN KEY (IdOrder) REFERENCES T_Orders (IdOrder)
) ENGINE = InnoDB;
