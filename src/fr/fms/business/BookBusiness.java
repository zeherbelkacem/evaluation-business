package fr.fms.business;

import java.util.List;

import fr.fms.entities.Book;
import fr.fms.entities.Order;
import fr.fms.entities.Theme;
import fr.fms.entities.User;

public interface BookBusiness {

	/**
	 * renvoie la liste de tous livres diponibles
	 * 
	 * @return List de livres
	 */
	public List<Book> getAllBooks();

	/**
	 * Renvoie une liste de livre selon la thematique choisie
	 * 
	 * @param themeId
	 * @return une List de Book
	 */
	public List<Book> getAllBooksByTheme(int themeId);

	/**
	 * Renvoie la liste des thématiques de livre
	 * 
	 * @return List de Theme
	 */
	public List<Theme> getAllThemes();

	/**
	 * Ajout d'un livre dans le panier
	 * 
	 * @param bookId l'ID du livre choisi
	 * @return true ou false
	 */
	public boolean addBookToCart(int bookId);

	/**
	 * Supprime un livre depuis le panier
	 * 
	 * @param bookId l'ID du livre choisi
	 * @return
	 */
	public boolean removeBookFromcart(int bookId);

	/**
	 * Calcule le montant total des produits (livres) dans le panier
	 * 
	 * @return double
	 */
	public double getTotalAmount();

	/**
	 * ENregistre la commande dans la base de donnée
	 * 
	 * @param idUser l'ID de l'utilisateur passant la commande
	 * @return l'ID de la commande une fois enregistrée (int)
	 */
	public int saveOrder(int idUser);

	/**
	 * Genere la facture client
	 * 
	 * @param idOrder l'ID de la commande concernée par la facture
	 */
	public void loadInvoice(int idOrder);

	/**
	 * Recupere la liste des livre disponobles dans la base de données
	 * 
	 * @return List<Book>
	 */

	/**
	 * Renvoie le contenu du panier
	 * 
	 * @return
	 */
	public List<Book> getMyCart();

	/**
	 * Crée un nouveau utilisateur
	 * @param user 
	 * @return l'ID de l'utilisateur une fois crée
	 */
	public int saveANewUser(User user);

	/**
	 * Chercher l'existence d'un l'utilisateur dans la base de donneés
	 * @param user
	 * @return l'ID de l'utilisateur s'il existe
	 */
	public int userAuthentication(User user);

	/**
	 * Authentifie si l'utilisateur est admin ou user (role)
	 * @param user
	 * @return
	 */
	public String adminAuthentication(User user);

	/**
	 * Renvoie le détails du livre avec ses thematiques
	 * @param idTheme
	 * @return
	 */
	public List<Book> getBookThemesDetails(int idTheme);

}
