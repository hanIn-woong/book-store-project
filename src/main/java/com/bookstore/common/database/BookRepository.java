package com.bookstore.common.database;

import com.bookstore.common.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepository {

    private final List<Book> books = new ArrayList<>(List.of(
            new Book(
                    1L,
                    "Clean Code",
                    "Robert C. Martin",
                    "IT",
                    28000,
                    "좋은 코드를 작성하는 방법과 유지보수하기 쉬운 코드 작성 원칙을 설명하는 책입니다.",
                    "/images/clean-code.jpg"
            ),
            new Book(
                    2L,
                    "토지",
                    "박경리",
                    "소설",
                    15000,
                    "한국 근현대사를 배경으로 한 대하소설입니다.",
                    "/images/toji.jpg"
            ),
            new Book(
                    3L,
                    "스프링 부트 입문",
                    "김개발",
                    "IT",
                    30000,
                    "Spring Boot의 기본 구조와 MVC 패턴을 쉽게 배울 수 있는 입문서입니다.",
                    "/images/spring-boot.jpg"
            )
    ));

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }
}