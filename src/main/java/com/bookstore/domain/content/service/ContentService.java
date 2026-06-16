package com.bookstore.domain.content.service;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.dto.BookDto;
import com.bookstore.common.model.Book;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final BookDatabase database = BookDatabase.getInstance();

    public List<BookDto> search(String keyword, String category, String sort) {
        return database.getBooks().stream()
                .filter(book -> keyword.isBlank() ||
                        book.getName().contains(keyword) ||
                        book.getAuthor().contains(keyword))   // ✅ 복구
                .filter(book -> category.isBlank() || book.getCategory().equals(category))
                .sorted(getComparator(sort))
                .map(BookDto::from)
                .collect(Collectors.toList());                     // ✅ 괄호 수정
    }

    public List<String> getCategories() {
        return database.getBooks().stream()
                .map(Book::getCategory)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private Comparator<Book> getComparator(String sort) {
        return switch (sort) {
            case "price_asc"  -> Comparator.comparingInt(Book::getUnitPrice);
            case "price_desc" -> Comparator.comparingInt(Book::getUnitPrice).reversed();
            case "popular"    -> Comparator.comparingLong(Book::getSalesCount).reversed();
            case "newest"     -> Comparator.comparing(Book::getReleaseDate).reversed();
            default           -> Comparator.comparing(Book::getName);
        };
    }
}
