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
-- - T_Categories                        ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Categories (
	IdCategory			int(4)		PRIMARY KEY AUTO_INCREMENT,
	CatName			varchar(30)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Categories ( CatName ) VALUES ( 'voyage' );
INSERT INTO T_Categories ( CatName ) VALUES ( 'jeunesse' );
INSERT INTO T_Categories ( CatName ) VALUES ( 'cuisine' );
INSERT INTO T_Categories ( CatName ) VALUES ( 'poesie' );
INSERT INTO T_Categories ( CatName ) VALUES ( 'polar' );
INSERT INTO T_Categories ( CatName ) VALUES ( 'roman' );
INSERT INTO T_Categories ( CatName ) VALUES ( 'petit-prix' );
INSERT INTO T_Categories ( CatName ) VALUES ( 'meilleures ventes' );


-- -----------------------------------------------------------------------------
-- - T_Books                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Books (
	IdBook				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Title				varchar(30)	NOT NULL,
	Author				varchar(30)	NOT NULL,
	Editor				varchar(30)	NOT NULL,
	Description			varchar(30)	NOT NULL,
	UnitaryPrice		float(8)	NOT NULL DEFAULT 0,
	IdCategory 			int(4) NOT NULL,
	CONSTRAINT `fk_category`
    FOREIGN KEY (IdCategory) REFERENCES T_Categories (IdCategory)
) ENGINE = InnoDB;

INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleOne', 'AuthorOne', 'EditorOne', 'DescriptionOne', 20, 1 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleTwo', 'AuthorTwo', 'EditorTwo', 'DescriptionTwo', 19, 5 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleThree', 'AuthorThree', 'EditorThree', 'DescriptionThree', 20.5 ,8 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleFour', 'AuthorFour', 'EditorFour', 'DescriptionFour', 22.9, 4 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleFive', 'AuthorFive', 'EditorFive', 'DescriptionFive', 15.5, 6 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleSix', 'AuthorSix', 'EditorSix', 'DescriptionSix', 18, 3 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleSeven', 'AuthorSeven', 'EditorSeven', 'DescriptionSeven', 17.9, 2 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleEight', 'AuthorEight', 'EditorEight', 'DescriptionEight', 21.9, 8 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleNine', 'AuthorNine', 'EditorNine', 'DescriptionNine', 16.9, 3 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleTen', 'AuthorTen', 'EditorTen', 'DescriptionTen', 13.5, 8 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleEleven', 'AuthorEleven', 'EditorEleven', 'DescriptionEleven', 9.9, 7 );
INSERT INTO T_Books ( Title, Author, Editor, Description, UnitaryPrice, idCategory ) VALUES ( 'TitleTwelve', 'AuthorTwelve', 'EditorTwelve', 'DescriptionTwelve', 8.5, 7 );


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
