package com.bookstore.common.dto;

import com.bookstore.common.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private String bookId;
    private String name;
    private int unitPrice;
    private String author;
    private String description;
    private String publisher;
    private String category;
    private long unitsInStock;
    private String releaseDate;
    private String condition;
    private String imageUrl;
    private long salesCount;
    private long viewCount;

    /**
     * Entity -> DTO 변환
     */
    public static BookDto from(Book book) {
        return BookDto.builder()
                .bookId(book.getBookId())
                .name(book.getName())
                .unitPrice(book.getUnitPrice())
                .author(book.getAuthor())
                .description(book.getDescription())
                .publisher(book.getPublisher())
                .category(book.getCategory())
                .unitsInStock(book.getUnitsInStock())
                .releaseDate(book.getReleaseDate())
                .condition(book.getCondition())
                .imageUrl(book.getImageUrl())
                .salesCount(book.getSalesCount())
                .viewCount(book.getViewCount())
                .build();
    }

    /**
     * DTO -> Entity 변환
     */
    public Book toEntity() {
        return Book.builder()
                .bookId(this.bookId)
                .name(this.name)
                .unitPrice(this.unitPrice)
                .author(this.author)
                .description(this.description)
                .publisher(this.publisher)
                .category(this.category)
                .unitsInStock(this.unitsInStock)
                .releaseDate(this.releaseDate)
                .condition(this.condition)
                .imageUrl(this.imageUrl)
                .salesCount(this.salesCount)
                .viewCount(this.viewCount)
                .build();
    }
}
