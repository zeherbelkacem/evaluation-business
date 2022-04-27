package fr.fms;

import java.util.List;
import java.util.Scanner;

import fr.fms.business.BookBusiness;
import fr.fms.business.BookBusinessImpl;
import fr.fms.entities.Book;
import fr.fms.entities.Theme;
import fr.fms.entities.User;

/**
 * 
 * @author Stagiaires11P
 *
 */
public class LibraryApp1 {

	/**
	 * 
	 */
	private static Scanner scanner = new Scanner(System.in);

	private static BookBusiness business = new BookBusinessImpl(null);

	/**
	 *
	 */
	private static int userId = 0;

	/**
	 *
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
	 * 
	 * @param articles
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
			System.out.println(String.format("|%-4s|%-14s|%-18s|%-17s|%-28s|%-10s|", b.getId(), b.getAuthor(),
					b.getAuthor(), b.getEditor(), b.getDescription(), b.getUnitaryPrice()));
			System.out.println(
					"-----+--------------+------------------+-----------------+----------------------------+----------+");
		}

	}

	/**
	 * 
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
					System.out.println("Entrez votre NOM:");
					String userName = scanner.next();
					System.out.println("Entrez votre téléphone:");
					String phone = scanner.next();
					role = business.adminAuthentication(new User(0, userName, null, phone, null));
					if (role.equalsIgnoreCase("admin"))
						adminMenu();
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
	 * 
	 */
	private static void showBooksByTeme() {
		showAllThemes(business.getAllThemes());
		int idTheme = (int) getPositiveIntegerInput(scanner, "\nEntrez l'ID de la THEMATIQUE concernée!");
		showAllBooks(business.getAllBooksByTheme(idTheme));

	}

	/**
	 * 
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
	 * 
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
	 * 
	 */
	private static void showFinalCart() {
		showTheAddedBook(business.getMyCart());
		double amount = business.getTotalAmount();
		System.out.println(String.format("|%-19s|%86s|", "TOTAL AMOUNT", amount));
		System.out.println(
				"-----+--------------+------------------+-----------------+----------------------------+----------+----------+");

	}

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
	 * 
	 */
	private static void addBookToCart() {
		showAllBooks(business.getAllBooks());
		int idArticle = (int) getPositiveIntegerInput(scanner, "\nEntrez l'ID du LIVRE que vous souhaitez acheter!");
		if (business.addBookToCart(idArticle))
			showTheAddedBook(business.getMyCart());

	}

	/**
	 * 
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
	 * 
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
	 * 
	 * @param scanner2
	 * @param string
	 * @return
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
	 * 
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
	 * 
	 * @param scanner
	 * @param string
	 * @return
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
