package com.bookstore.common.database;

import com.bookstore.common.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase {
    private static BookDatabase instance;
    private List<Book> books;

    private BookDatabase() {
        books = new ArrayList<>();
        initializeData();
    }

    public static synchronized BookDatabase getInstance() {
        if (instance == null) {
            instance = new BookDatabase();
        }
        return instance;
    }

    private void initializeData() {
        books.add(new Book("P1234", "The Great Gatsby", 15000, "F. Scott Fitzgerald", "A classic novel about the American Dream.", "Scribner", "Classic", 100, "1925-04-10", "New"));
        books.add(new Book("P1235", "To Kill a Mockingbird", 12000, "Harper Lee", "A story of racial injustice in the Deep South.", "J.B. Lippincott & Co.", "Fiction", 150, "1960-07-11", "New"));
        books.add(new Book("P1236", "1984", 13000, "George Orwell", "A dystopian social science fiction novel.", "Secker & Warburg", "Dystopian", 200, "1949-06-08", "New"));
        books.add(new Book("P1237", "Moby Dick", 18000, "Herman Melville", "The narrative of Ishmael's voyage on the whaling ship Pequod.", "Richard Bentley", "Classic", 50, "1851-10-18", "New"));
        books.add(new Book("P1238", "Pride and Prejudice", 11000, "Jane Austen", "A romantic novel of manners.", "T. Egerton", "Romance", 80, "1813-01-28", "New"));
        books.add(new Book("P1239", "The Catcher in the Rye", 14000, "J.D. Salinger", "A story about teenage angst and alienation.", "Little, Brown and Company", "Fiction", 120, "1951-07-16", "New"));
        books.add(new Book("P1240", "The Hobbit", 16000, "J.R.R. Tolkien", "A children's fantasy novel.", "George Allen & Unwin", "Fantasy", 90, "1937-09-21", "New"));
        books.add(new Book("P1241", "The Alchemist", 10000, "Paulo Coelho", "A journey of discovering one's destiny.", "HarperTorch", "Adventure", 300, "1988-01-01", "New"));
        books.add(new Book("P1242", "Harry Potter and the Sorcerer's Stone", 20000, "J.K. Rowling", "A boy discovers he is a wizard.", "Bloomsbury", "Fantasy", 500, "1997-06-26", "New"));
        books.add(new Book("P1243", "The Da Vinci Code", 15000, "Dan Brown", "A mystery thriller novel.", "Doubleday", "Thriller", 250, "2003-03-18", "New"));
        books.add(new Book("P1244", "The Chronicles of Narnia", 25000, "C.S. Lewis", "A series of seven fantasy novels.", "Geoffrey Bles", "Fantasy", 110, "1950-10-16", "New"));
        books.add(new Book("P1245", "Animal Farm", 9000, "George Orwell", "An allegorical novella about the Russian Revolution.", "Secker & Warburg", "Satire", 180, "1945-08-17", "New"));
        books.add(new Book("P1246", "The Little Prince", 8000, "Antoine de Saint-Exupéry", "A poetic tale about a young prince.", "Reynal & Hitchcock", "Children", 400, "1943-04-06", "New"));
        books.add(new Book("P1247", "One Hundred Years of Solitude", 17000, "Gabriel García Márquez", "A multi-generational story of the Buendía family.", "Harper & Row", "Magic Realism", 70, "1967-05-30", "New"));
        books.add(new Book("P1248", "Brave New World", 13500, "Aldous Huxley", "A dystopian novel set in a futuristic World State.", "Chatto & Windus", "Science Fiction", 130, "1932-01-01", "New"));
        books.add(new Book("P1249", "The Lord of the Rings", 45000, "J.R.R. Tolkien", "An epic high-fantasy novel.", "George Allen & Unwin", "Fantasy", 60, "1954-07-29", "New"));
        books.add(new Book("P1250", "Gone with the Wind", 21000, "Margaret Mitchell", "A romance set in the American South during the Civil War.", "Macmillan Publishers", "Historical Fiction", 45, "1936-06-30", "New"));
        books.add(new Book("P1251", "The Kite Runner", 12500, "Khaled Hosseini", "A story of friendship and redemption.", "Riverhead Books", "Fiction", 160, "2003-05-29", "New"));
        books.add(new Book("P1252", "Life of Pi", 11500, "Yann Martel", "A fantasy adventure novel.", "Knopf Canada", "Adventure", 220, "2001-09-11", "New"));
        books.add(new Book("P1253", "Thinking, Fast and Slow", 22000, "Daniel Kahneman", "A book on psychology and decision-making.", "Farrar, Straus and Giroux", "Non-fiction", 105, "2011-10-25", "New"));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
