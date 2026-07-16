import java.util.*;
import java.io.*;
public class BookApp
{   
    private static String bookID, title, author, category, borrowerName, dueDate, status;
    private static int menuNum;
    private static char system;
    private static Scanner input = new Scanner(System.in);
    private static Queue<Book> waitingQueue = new Queue<>();
    private static ArrayList <Book> bookList = new ArrayList<>();
    private static ArrayList <Book> removedList = new ArrayList<>();

    
    //Read books.txt (Reading)
    public static void bookStarter () {
        try {
            FileReader frBook = new FileReader ("books.txt");
            BufferedReader brBook = new BufferedReader (frBook);
            
            String bookData = brBook.readLine();
            while (bookData != null) {

                    StringTokenizer stz = new StringTokenizer(bookData, ",");
                    bookID = stz.nextToken();
                    title = stz.nextToken();
                    author = stz.nextToken();
                    category = stz.nextToken();
                    borrowerName = stz.nextToken();
                    dueDate = stz.nextToken();
                    status = stz.nextToken();
                    
                    Book b = new Book(bookID, title, author, category, borrowerName, dueDate, status);
                    bookData = brBook.readLine();

                    if (status.equalsIgnoreCase("waiting")) {
                        waitingQueue.enqueue(b);
                    } else {
                        bookList.add(b);
                    }
                    
                }
            brBook.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Make sure studentData.txt is in the correct location.");
        } catch (Exception e) {
            System.out.println("Error while reading file (studentData.txt): " + e.getMessage());
        }
    }
    
    public static int menu() {
            System.out.println("MENU");
            System.out.println("1. Add New Book");
            System.out.println("2. Search Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Book");
            System.out.println("5. Remove Book");
    
            System.out.print("Enter Menu Number: ");
            menuNum = input.nextInt();
            input.nextLine();
    
            return menuNum;
    }
    
    //Add new book (Insert  ion)
    public static void addBooking() {
        int categoryNum, statusNum;
        boolean duplicateID;
    
        do {
            duplicateID = false;
            System.out.print("\nEnter Book ID [Exp: B001]: ");
            bookID = input.nextLine();

            //Check if book ID already exists
            for (int x = 0; x < bookList.size(); x++) {
                Book b = bookList.get(x);
                if (bookID.equalsIgnoreCase(b.getBookID())) {
                    duplicateID = true;
                    System.out.println("Alert: Book ID Exists! Please enter a different one.\n");
                    break;
                }
            }
         } while (duplicateID == true);
        
        System.out.print("Enter Title: ");
        title = input.nextLine();
        
        System.out.print("Enter Author: ");
        author = input.nextLine();
        
        System.out.println("\nCategory");
        System.out.println("1. Programming");
        System.out.println("2. Computer Science");
        System.out.println("3. Technology");
        System.out.println("4. Data & AI");
        System.out.println("5. Design & Creative");
        
        do {
            System.out.print("\nEnter Category: ");
            categoryNum = input.nextInt();
            input.nextLine();
            
            switch (categoryNum) {
                case 1: category = "Programming";
                        break;
                case 2: category = "Computer";
                        break;
                case 3: category = "Technology";
                        break;
                case 4: category = "Data & AI";
                        break;
                case 5: category = "Design & Creative";
                        break;
                default: System.out.println("Alert: Unknown Category. Please enter only [1-5]!");
            }
        } while (categoryNum < 1 || categoryNum > 5);
        
    
        System.out.print("Enter Borrower Name: ");
        borrowerName = input.nextLine();
    
        System.out.print("Enter Due Date [Exp: 10-01-2025]: ");
        dueDate = input.nextLine();

        //Check book availability
        if (checkBook(title) == false) {
            System.out.println("Book is fully used. You are added to waiting queue.");
            status = "Waiting";
        } else {
            status = "Using";
        }
            
        // Create new Book object
        Book newBook = new Book(bookID, title, author, category, borrowerName, dueDate, status);
    
        // Add to ArrayList
        bookList.add(newBook);

        // enqueue ONLY if waiting
        if (status.equalsIgnoreCase("Waiting")) {
            waitingQueue.enqueue(newBook);
        }
    
        System.out.println("\nAlert: New Booking successfully added!\n");
    }
    
    //Search for book with specific information (Searching)
    public static void searchBook() {
        int searchMethod;
        boolean found = false;

        System.out.println("\nSearch Method");
        System.out.println("1. Book ID");
        System.out.println("2. Title");
        
        do {
            System.out.print("\nEnter Search Method: ");
            searchMethod = input.nextInt();
            input.nextLine();
            
            if (searchMethod == 1) {

                System.out.print("Enter Book ID [Exp: B001]: ");
                bookID = input.nextLine();

                for (int x = 0; x < bookList.size(); x ++) {
                    Book searchBook = bookList.get(x);

                    //Search for book
                    if (bookID.equalsIgnoreCase(searchBook.getBookID())) {
                        System.out.print("\n" + searchBook.toString());
                        found = true;
                    }
                }

            } else if (searchMethod == 2) {
                System.out.print("Enter Book Title [Exp: Java Programming]: ");
                bookID = input.nextLine();

                for (int x = 0; x < bookList.size(); x ++) {
                    Book searchBook = bookList.get(x);

                    //Search for book
                    if (bookID.equalsIgnoreCase(searchBook.getTitle())) {
                        System.out.print("\n" + searchBook.toString());
                        found = true;
                    }
                }

            } else {
                System.out.println("Alert: Unknown Search Methood. Please enter only [1-2]!");
            }

            //Availabality Check
                if (found == false) {
                    System.out.println("Alert: Book not found.");
                }
            
            System.out.print("\n\nEnter 'Y' to search again: ");
            system = input.next().charAt(0);
            input.nextLine();
 
        } while (system == 'Y' || system == 'y');
    }

    //Update book information (Updating)
    public static void updateBook() {
        int index = -1, statusNum;
        Book b = null;

        displayBook(); //Display Book

        do{
            System.out.print("\nEnter Book ID to return[Exp: B001]: ");
            bookID = input.nextLine();

            for (int x = 0; x < bookList.size(); x++) {
                b = bookList.get(x);
                if (bookID.equalsIgnoreCase(b.getBookID())) {
                    index = x;
                    status = "returned";
                    break;
                }
            }

            b.setStatus(status);
            
            //Give book to next person if eligible
            if (status.equalsIgnoreCase("Returned")) {
                processQueue(b.getTitle());
            }

            System.out.println("Alert: Book information updated successfully!");

            if (index == -1) {
                System.out.println("Alert: Book is not found! Please try again.");
            }

        } while (index == -1);
    }

    //Queue process happens here
    public static void processQueue(String title) {
        Queue<Book> tempQueue = new Queue<>();

        if (waitingQueue.isEmpty()) return;

        while (!waitingQueue.isEmpty()) {
                Book next = waitingQueue.dequeue();

                if (next.getTitle().equalsIgnoreCase(title) && checkBook(title)) {
                    next.setStatus("using");
                    bookList.add(next);

                    System.out.println("\nAlert: " + next.getBorrowerName() + " is now USING the book \"" + title + "\".");
                    return; 

                } else {
                    tempQueue.enqueue(next);
                }
        }

         while (!tempQueue.isEmpty()) {
            waitingQueue.enqueue(tempQueue.dequeue());
         }
    }


    //Display all book (Traversing)
    public static void displayBook() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-25s | %-20s | %-18s | %-15s | %-12s | %-12s |\n", "ID", "Title", "Author", "Category", "Borrower", "Due Date", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

        // Display bookList
        for (int x = 0; x < bookList.size(); x++) {
            Book b = bookList.get(x);
            System.out.printf("| %-6s | %-25s | %-20s | %-18s | %-15s | %-12s | %-12s |\n", b.getBookID(), b.getTitle(), b.getAuthor(), b.getCategory(), b.getBorrowerName(), b.getDueDate(), b.getStatus());
        }

        // Display waitingQueue
        if (!waitingQueue.isEmpty()) {

            Queue<Book> tempQueue = new Queue<>(); //Create temporary queue

            while (!waitingQueue.isEmpty()) {
                Book b = waitingQueue.dequeue();

                System.out.printf("| %-6s | %-25s | %-20s | %-18s | %-15s | %-12s | %-12s |\n", b.getBookID(), b.getTitle(), b.getAuthor(), b.getCategory(), b.getBorrowerName(), b.getDueDate(), b.getStatus());
                tempQueue.enqueue(b);
            }

            // Restore queue
            while (!tempQueue.isEmpty()) {
                waitingQueue.enqueue(tempQueue.dequeue());
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
    }

    //Remove book that are no longer available (Removal)
    public static void removeBook() {
        boolean found = false;
        displayBook();

        System.out.print("\nEnter Book ID to remove[Exp: B001]: ");
        bookID = input.nextLine();

        for (int x = 0; x < bookList.size(); x++) {
            Book b = bookList.get(x);
            if (bookID.equalsIgnoreCase(b.getBookID())) {
                found = true;
                removedList.add(bookList.remove(x));
                System.out.println("Alert: Book has been removed!\n");
                break;
            }
        }

        // Remove from waitingQueue
        if (!waitingQueue.isEmpty()) {
            Queue<Book> tempQueue = new Queue<>(); //Create temporary queue

            while (!waitingQueue.isEmpty()) {
                Book b = waitingQueue.dequeue();

                if (bookID.equalsIgnoreCase(b.getBookID())) {
                    removedList.add(b);   // remove from queue
                    found = true;
                    System.out.println("Alert: Book has been removed!\n");
                    break;
                } else {
                    tempQueue.enqueue(b);
                }

            }

            // Restore queue
            while (!tempQueue.isEmpty()) {
                waitingQueue.enqueue(tempQueue.dequeue());
            }
        }

            if (found == false) {
                System.out.println("Alert: Book is not found!");
            }
    }

        //Count book availabality (All Book Quantity = 3)
        public static boolean checkBook (String title) {
            int counter = 0;
            Book b = null;

            for (int x = 0; x < bookList.size(); x++) {
                b = bookList.get(x);
                if (title.equalsIgnoreCase(b.getTitle()) && b.getStatus().equalsIgnoreCase("using")) {
                    counter++;
                }
            }
            
            if (counter < 3) {
                return true;
            } else {
                return false;
            }

        }

    public static void main (String[] args) {
        bookStarter();

        menuNum = menu();
        
        do {
            switch (menuNum) {
                case 1: addBooking();
                        break;
                case 2: searchBook();
                        break;
                case 3: updateBook();
                        break;
                case 4: displayBook();
                        break;
                case 5: removeBook();
                        break;
            }
            
            System.out.println("");
            menuNum = menu();

        } while (menuNum >= 1 || menuNum <= 5);
    }
}