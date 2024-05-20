import java.util.Scanner;

public class LibrarySystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        library.addBook("Book 1");
        library.addBook("Book 2");
        library.addBook("Book 3");

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Menu wypożyczalni");
            System.out.println("2. Menu użytkownika");
            System.out.println("3. Zakończ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    libraryMenu();
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
            }
        }
    }

    private static void libraryMenu() {
        System.out.println("1. Dodaj użytkownika");
        System.out.println("2. Usuń użytkownika");
        System.out.println("3. Wyświetl listę użytkowników");
        System.out.println("4. Wyświetl wszystkie książki");
        System.out.println("5. Wyświetl wypożyczone książki");
        System.out.println("6. Wyświetl użytkowników z wypożyczonymi książkami");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.println("Podaj nazwę użytkownika:");
                String name = scanner.nextLine();
                library.addUser(name);
                break;
            case 2:
                System.out.println("Podaj nazwę użytkownika do usunięcia:");
                String nameToRemove = scanner.nextLine();
                library.removeUser(nameToRemove);
                break;
            case 3:
                System.out.println("Lista użytkowników:");
                for (User user : library.getUsers()) {
                    System.out.println(user);
                }
                break;
            case 4:
                System.out.println("Lista książek:");
                for (Book book : library.getBooks()) {
                    System.out.println(book);
                }
                break;
            case 5:
                System.out.println("Lista wypożyczonych książek:");
                for (Book book : library.getBorrowedBooks()) {
                    System.out.println(book);
                }
                break;
            case 6:
                System.out.println("Użytkownicy z wypożyczonymi książkami:");
                for (User user : library.getUsersWithBorrowedBooks()) {
                    System.out.println(user + " - " + user.getBorrowedBooks());
                }
                break;
            default:
                System.out.println("Nieprawidłowy wybór.");
        }
    }

    private static void userMenu() {
        System.out.println("Podaj nazwę użytkownika:");
        String name = scanner.nextLine();
        User user = library.getUsers().stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);

        if (user == null) {
            System.out.println("Nie znaleziono użytkownika.");
            return;
        }

        System.out.println("1. Wyświetl listę dostępnych książek");
        System.out.println("2. Wypożycz książkę");
        System.out.println("3. Oddaj książkę");
        System.out.println("4. Wyświetl wypożyczone książki");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.println("Lista dostępnych książek:");
                for (Book book : library.getBooks()) {
                    if (!book.isBorrowed()) {
                        System.out.println(book);
                    }
                }
                break;
            case 2:
                System.out.println("Podaj tytuł książki do wypożyczenia:");
                String titleToBorrow = scanner.nextLine();
                Book bookToBorrow = library.getBooks().stream().filter(b -> b.getTitle().equals(titleToBorrow) && !b.isBorrowed()).findFirst().orElse(null);
                if (bookToBorrow != null) {
                    user.borrowBook(bookToBorrow);
                    System.out.println("Wypożyczono książkę.");
                } else {
                    System.out.println("Książka niedostępna.");
                }
                break;
            case 3:
                System.out.println("Podaj tytuł książki do oddania:");
                String titleToReturn = scanner.nextLine();
                Book bookToReturn = user.getBorrowedBooks().stream().filter(b -> b.getTitle().equals(titleToReturn)).findFirst().orElse(null);
                if (bookToReturn != null) {
                    user.returnBook(bookToReturn);
                    System.out.println("Oddano książkę.");
                } else {
                    System.out.println("Nie masz takiej książki.");
                }
                break;
            case 4:
                System.out.println("Twoje wypożyczone książki:");
                for (Book book : user.getBorrowedBooks()) {
                    System.out.println(book);
                }
                break;
            default:
                System.out.println("Nieprawidłowy wybór.");
        }
    }
}
