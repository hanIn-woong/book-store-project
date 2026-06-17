package com.bookstore.domain.content.service;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.dto.BookDto;
import com.bookstore.common.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final BookDatabase database;

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

    public BookDto getTodayBook() {
        List<Book> topBooks = database.getBooks().stream()
                .sorted(Comparator.comparingLong(Book::getSalesCount).reversed())
                .limit(5)
                .toList();
        if (topBooks.isEmpty()) return null;
        int index = (int)(LocalDate.now().toEpochDay() % topBooks.size());
        return BookDto.from(topBooks.get(index));
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
