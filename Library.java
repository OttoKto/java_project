import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(String title) {
        books.add(new Book(title));
    }

    public void addUser(String name) {
        users.add(new User(name));
    }

    public void removeUser(String name) {
        users.removeIf(user -> user.getName().equals(name));
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isBorrowed()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    public List<User> getUsersWithBorrowedBooks() {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (!user.getBorrowedBooks().isEmpty()) {
                result.add(user);
            }
        }
        return result;
    }
}
