package fr.fms;

import java.util.List;
import java.util.Scanner;

import fr.fms.business.BookBusiness;
import fr.fms.business.BookBusinessImpl;
import fr.fms.entities.Book;
import fr.fms.entities.Theme;
import fr.fms.entities.User;

/**
 * L'aaplicaion, dans un premier temps doit permettre aux
 * utilisateurs de visualiser l’ensemble des livres classés ou pas par
 * thématiques puis la possibilité à tout instant d’ajouter des livres à un
 * panier, supprimer ou afficher son contenu puis passer commande, il faudra
 * vérifier que le client existe bien en base ou le créer pour passer commande,
 * elle est caractérisée par son id, montant total, date du jour, id du client
 * associé.
 * 
 * @author Stagiaires11P
 *
 */
public class LibraryApp1 {

	/**
	 * Flux entrée/sortie
	 */
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * instantiation de l'objet de la couche metier
	 */
	private static BookBusiness business = new BookBusinessImpl(null);

	/**
	 *Garder un identifiant pour l'utilsateur 
	 */
	private static int userId = 0;

	/**
	 * Un role pour définir les droit des utilisateur
	 */
	private static String role = null;

	public static void main(String[] args) {

		while (true) {
			System.out.println("Bienvenue dans votre LIBRAIRIE. Voici la liste de nos livres ");
			showAllBooks(business.getAllBooks());
			libraryMenu();
		}

	}

	/**
	 * Affiche tous les livre à l'ouverture de l'appli
	 * @param books
	 */
	private static void showAllBooks(List<Book> books) {
		// Build the table header
		System.out.println(
				"--------------------------------------------------------------------------------------------------");
		System.out.println(
				"|ID  | TITLE        | Author           | Editor          | DESCRIPTION                |PRICE (€) |");
		System.out.println(
				"-----+--------------+------------------+-----------------+----------------------------+----------+");//

		/* * Display the table body: Browse the training HashMap */
		for (Book b : books) {
			System.out.println(String.format("|%-4s|%-14s|%-18s|%-17s|%-28s|%-10s|", b.getId(), b.getTitle(),
					b.getAuthor(), b.getEditor(), b.getDescription(), b.getUnitaryPrice()));
			System.out.println(
					"-----+--------------+------------------+-----------------+----------------------------+----------+");
		}

	}

	/**
	 * Affcihe le menu des choix utilisateur
	 * @param userId
	 */
	private static void libraryMenu() {
		int menuChoice = -1;
		while (menuChoice != 0) { // (0) to exit menu
			try {
				System.out.println("-------------------------- STORE MENU --------------------------" + "\n"
						+ "Pour afficher les livres PAR THEMEMATIQUE,             enter (1)\n"
						+ "Pour AJOUTER un livre au PANIER,                       enter (2)\n"
						+ "Pour RETIRER un livre dans le PANIER                   enter (3)\n"
						+ "Pour AFFICHER et VALIDER le PANIER,                    enter (4)\n"
						+ "ADMINISTRATION                                         enter (5)\n"
						+ "SORTIR de l'application,                               enter (0)\n"
						+ "----------------------------------------------------------------");

				/** Only integer entries accepted */
				menuChoice = (int) getPositiveIntegerInput(scanner, "\nFaite votre choix!");
				switch (menuChoice) {
				case 1:
					showBooksByTeme();
					break;

				case 2:
					addBookToCart();

					break;

				case 3:
					removeBookFromCart();

					break;

				case 4:
					validateMyCart();
					break;

				case 5:
					autehticateAdmin();
					break;

				case 0:
					menuChoice = 0;
					break;

				default:
					System.out.println("Wrong entry: ONLY INTEGERS ENTRIES ( 0 to 5)");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Envoie l'admin à l'authentification
	 */
	private static void autehticateAdmin() {
		System.out.println("Entrez votre NOM:");
		String userName = scanner.next();
		System.out.println("Entrez votre téléphone:");
		String phone = scanner.next();
		role = business.adminAuthentication(new User(0, userName, null, phone, null));
		if (role.equalsIgnoreCase("admin"))
			adminMenu();

	}

	/**
	 * Affiche le menu d'actions à réaliser par l'admin
	 */
	private static void adminMenu() {
		int menuChoice = -1;
		while (menuChoice != 0) { // (0) to exit menu
			try {
				System.out.println("-------------------------- STORE MENU --------------------------" + "\n"
						+ "Pour afficher les COMMANDES,                           enter (1)\n"
						+ "Pour afficher UNE SEULE commande,                      enter (2)\n"
						+ "Pour Afficher les utilisateurs                         enter (3)\n"
						+ "Pour SUUPRIMER un utilsateur      ,                    enter (4)\n"
						+ "Pour Supprimer un LIVRE,                               enter (5)\n"
						+ "Pour METTER A JOUR un LIVRE,                           enter (6)\n"
						+ "SORTIR de l'application,                               enter (0)\n"
						+ "----------------------------------------------------------------");

				/** Only integer entries accepted */
				menuChoice = (int) getPositiveIntegerInput(scanner, "\nFaite votre choix!");
				switch (menuChoice) {
				case 1:

					break;

				case 2:

					break;

				case 3:

					break;

				case 4:

					break;

				case 5:

					break;
				case 6:

					break;

				case 0:
					menuChoice = 0;
					break;

				default:
					System.out.println("Wrong entry: ONLY INTEGERS ENTRIES ( 0 to 5)");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Affiche la liste des livre choisis par thématique
	 */
	private static void showBooksByTeme() {
		showAllThemes(business.getAllThemes());
		int idTheme = (int) getPositiveIntegerInput(scanner, "\nEntrez l'ID de la THEMATIQUE concernée!");
		showAllBooks(business.getAllBooksByTheme(idTheme));

	}

	/**
	 * Valide le panier avant de finaliser la commande
	 */
	private static void validateMyCart() {
		/* Check if the user is already a customer */
		int saving = getPositiveOneOrTwo(scanner, "Etes vous client chez nous:  ?(1:oui/ 2:non)");
		if (saving == 1) {
			System.out.println("Connectez-vous afin de passer la commande ");

			System.out.println("Entrez votre NOM:");
			String userName = scanner.next();
			System.out.println("Entrez votre téléphone:");
			String phone = scanner.next();
			userId = business.userAuthentication(new User(0, userName, null, phone, null));
			if (userId != 0)
				finalizeOrder();

		} else {
			System.out.println("Vous de vez cous enregister avant de passer la commande");
			userId = saveANewCustomer();
			if (userId != 0)
				finalizeOrder();
		}
	}

	/**
	 * Après l'authentification de l'utilsateur, on passe la commande et on l'enregistre dans la DB 
	 */
	private static void finalizeOrder() {
		showFinalCart();
		/* Suggest to validate and confirm the buy */
		int validate = getPositiveOneOrTwo(scanner, "Voulez-vous VALIDER votre panier:  ?(1:oui/ 2:non)");

		if (validate == 1) {
			int confirm = getPositiveOneOrTwo(scanner, "Voulez-vous CONFIRMER LA VALIDATION:  ?(1:oui/ 2:non)");

			/* Save the order information in DB and finally delete cart */
			if (confirm == 1) {

				/*
				 * As we can't add or update a child (orderitem) row (foreign key constraint,
				 * start by save a default parent row (order)
				 */
				int idOrder = business.saveOrder(userId);

				/**/
				int getInvoice = getPositiveOneOrTwo(scanner, "Voulez-vous récuperer votre facture:  ?(1:oui/ 2:non)");
				if (getInvoice == 1)
					business.loadInvoice(idOrder);
				System.out.println("Merci et à bientôt.");
			}
		}
	}

	/**
	 * Récupere le panier et l'affiche
	 */
	private static void showFinalCart() {
		showTheAddedBook(business.getMyCart());
		double amount = business.getTotalAmount();
		System.out.println(String.format("|%-19s|%86s|", "TOTAL AMOUNT", amount));
		System.out.println(
				"-----+--------------+------------------+-----------------+----------------------------+----------+----------+");

	}

	/**
	 * Formulaire d'ajout d'un client
	 * @return
	 */
	private static int saveANewCustomer() {
		System.out.println("Votre Nom");
		String userName = scanner.next();
		System.out.println("Votre Email");
		String email = scanner.next();
		System.out.println("Votre Numero de téléphone");
		String phone = scanner.next();
		System.out.println("Votre Adresse");
		String address = scanner.next();
		int userId = business.saveANewUser(new User(0, userName, email, phone, address));
		return userId;
	}

	/**
	 * Ajout d'un livre au panier
	 */
	private static void addBookToCart() {
		showAllBooks(business.getAllBooks());
		int idArticle = (int) getPositiveIntegerInput(scanner, "\nEntrez l'ID du LIVRE que vous souhaitez acheter!");
		if (business.addBookToCart(idArticle))
			showTheAddedBook(business.getMyCart());

	}

	/**
	 * Supprime un livre(s) du panier
	 */
	private static void removeBookFromCart() {
		if (!business.getMyCart().isEmpty()) {
			showTheAddedBook(business.getMyCart());
			int idBook = (int) getPositiveIntegerInput(scanner, "\nEntrez l'ID de l'article que vous souhaitez!");
			if (business.removeBookFromcart(idBook))
				showTheAddedBook(business.getMyCart());
		} else
			System.out.println("Votre panier est vide");

	}

	/**
	 * Affiche le panier à l'utilisateur à chaque ajout de livre
	 * @param book
	 */
	private static void showTheAddedBook(List<Book> book) {
		// Build the table header
		if (book.isEmpty())
			System.out.println("Votre pnier est VIDE");
		else {
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"|ID  | TITLE        | Author           | Editor          | DESCRIPTION                |QUANTITY |PRICE (€) |");
			System.out.println(
					"-----+--------------+------------------+-----------------+----------------------------+----------+----------+");//

			/* Start to fill the table body with the selected training */
			for (Book b : book) {
				System.out.println(String.format("|%-4s|%-14s|%-18s|%-17s|%-28s|%-10s|%-10s|", b.getId(), b.getAuthor(),
						b.getAuthor(), b.getEditor(), b.getDescription(), b.getQuantity(), b.getUnitaryPrice()));
				System.out.println(
						"-----+--------------+------------------+-----------------+----------------------------+----------+----------+");
			}
		}

	}

	/**
	 * Gestion d'erruer de flux entrée sortie 
	 * @param scanner2
	 * @param string
	 * @return un 1 ou 2
	 */
	private static int getPositiveOneOrTwo(Scanner scanner2, String string) {
		String menuresponse = "";
		while (true) {
			System.out.println(string);
			try {
				menuresponse = scanner.next();
				if (Integer.parseInt(menuresponse) == 1 || Integer.parseInt(menuresponse) == 2)
					break;
				System.out.println("La réponse doit être numerique et positive entre 1 & 2");
			} catch (NumberFormatException e) {
				System.out.println("La réponse doit être numerique et positive entre 1 ou 2");
			}
		}
		return Integer.parseInt(menuresponse);
	}

	/**
	 * Affiche tous le thématiques concernant les livres
	 * @param allThemes
	 */
	private static void showAllThemes(List<Theme> allThemes) {
		System.out.println("------+----------------------------------+");
		System.out.println("|ID   | THEMATIQUES                      |");
		System.out.println("------+----------------------------------+");//

		/* * Display the table body: Browse the training HashMap */
		for (Theme t : allThemes) {
			System.out.println(String.format("|%-5s|%-34s|", t.getId(), t.getThemeName()));
			System.out.println("------+----------------------------------+");
		}

	}

	/**
	 * Gestion d'erreurs de flux entrée sortie
	 * @param scanner
	 * @param string
	 * @return un nombre positif (>0)
	 */
	private static long getPositiveIntegerInput(Scanner scanner, String string) {
		String menuresponse = "";
		while (true) {
			System.out.println(string);
			try {
				menuresponse = scanner.next();
				if (Integer.parseInt(menuresponse) >= 0)
					break;
				System.out.println("La réponse doit être numerique et positive >= 0");
			} catch (NumberFormatException e) {
				System.out.println("La réponse doit être numerique et positive >= 0");
			}
		}
		return Long.parseLong(menuresponse);
	}
}
