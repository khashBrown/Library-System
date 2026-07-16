public class Book {
    
    private String bookID;
    private String title;
    private String author;
    private String category;
    private String borrowerName;
    private String dueDate;
    private String status;

    public Book(String bookID, String title, String author,String category, String borrowerName, String dueDate, String status) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.borrowerName = borrowerName;
        this.dueDate = dueDate;
        this.status = status;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getDueDate() {
        return dueDate;
    }
    
    public String getStatus() {
        return status;
    }

    public String toString() {
        return "Book ID: " + bookID +
               "\nTitle: " + title +
               "\nAuthor: " + author +
               "\nCategory: " + category +
               "\nBorrower Name: " + borrowerName +
               "\nDue Date: " + dueDate +
               "\nStatus: " + status;
    }
}
