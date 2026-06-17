package com.bookstore.domain.detail.service;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.dto.BookDto;
import com.bookstore.common.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DetailService {

    private final BookDatabase database = BookDatabase.getInstance();
    private static final int PRICE_RANGE = 3000;

    public List<BookDto> getRelatedBooks(String bookId, int limit) {
        List<Book> all = database.getBooks();
        Book current = all.stream()
                .filter(b -> b.getBookId().equals(bookId))
                .findFirst()
                .orElse(null);

        if (current == null) return List.of();

        List<BookDto> result = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        seen.add(bookId);

        // 우선순위 1: 같은 카테고리
        all.stream()
                .filter(b -> !seen.contains(b.getBookId()) && b.getCategory().equals(current.getCategory()))
                .forEach(b -> { result.add(BookDto.from(b)); seen.add(b.getBookId()); });

        // 우선순위 2: 같은 저자
        all.stream()
                .filter(b -> !seen.contains(b.getBookId()) && b.getAuthor().equals(current.getAuthor()))
                .forEach(b -> { result.add(BookDto.from(b)); seen.add(b.getBookId()); });

        // 우선순위 3: 비슷한 가격대 (±3000원)
        all.stream()
                .filter(b -> !seen.contains(b.getBookId())
                        && Math.abs(b.getUnitPrice() - current.getUnitPrice()) <= PRICE_RANGE)
                .forEach(b -> { result.add(BookDto.from(b)); seen.add(b.getBookId()); });

        return result.stream().limit(limit).toList();
    }

    public BookDto getPrevBook(String bookId) {
        List<Book> all = database.getBooks();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getBookId().equals(bookId)) {
                return i > 0 ? BookDto.from(all.get(i - 1)) : null;
            }
        }
        return null;
    }

    public BookDto getNextBook(String bookId) {
        List<Book> all = database.getBooks();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getBookId().equals(bookId)) {
                return i < all.size() - 1 ? BookDto.from(all.get(i + 1)) : null;
            }
        }
        return null;
    }
}
