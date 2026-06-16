package com.bookstore.common.database;

import com.bookstore.common.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase {
    private static BookDatabase instance;
    private List<Book> books;
    private String baseUrl = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/";

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
        books.add(new Book("P1234", "위대한 개츠비", 15000, "F. 스콧 피츠제럴드", "아메리칸 드림을 다룬 고전 소설.", "민음사", "고전", 100, "2009-01-20", "신규", baseUrl+"9788937460753.jpg"));
        books.add(new Book("P1235", "앵무새 죽이기", 12000, "하퍼 리", "미국 남부의 인종 불평등을 다룬 이야기.", "열린책들", "소설", 150, "2015-06-30", "신규", baseUrl+"9788932917207.jpg"));
        books.add(new Book("P1236", "1984", 13000, "조지 오웰", "디스토피아를 다룬 사회과학 소설.", "민음사", "디스토피아", 200, "2007-03-30", "신규", baseUrl+"9788937460777.jpg"));
        books.add(new Book("P1237", "모비딕", 18000, "허먼 멜빌", "포경선 피쿼드호를 탄 이슈마엘의 항해기.", "푸른숲주니어", "고전", 50, "2007-11-07", "신규", baseUrl+"9788971847534.jpg"));
        books.add(new Book("P1238", "오만과 편견", 11000, "제인 오스틴", "풍속을 다룬 로맨스 소설.", "민음사", "로맨스", 80, "2009-01-20", "신규", baseUrl+"9788937460883.jpg"));
        books.add(new Book("P1239", "호밀밭의 파수꾼", 14000, "J.D. 샐린저", "청소년기의 고뇌와 소외에 관한 이야기.", "민음사", "소설", 120, "2023-01-17", "신규", baseUrl+"9788937460470.jpg"));
        books.add(new Book("P1240", "호빗", 16000, "J.R.R. 톨킨", "어린이를 위한 판타지 소설.", "아르테(arte)", "판타지", 90, "2021-03-01", "신규", baseUrl+"9788950992521.jpg"));
        books.add(new Book("P1241", "연금술사", 10000, "파울로 코엘료", "자신의 운명을 찾아가는 여정.", "문학동네", "모험", 300, "2018-12-05", "신규", baseUrl+"9788982814471.jpg"));
        books.add(new Book("P1242", "해리 포터와 마법사의 돌", 20000, "J.K. 롤링", "자신이 마법사임을 깨닫는 소년의 이야기.", "문학수첩", "판타지", 500, "2024-11-20", "신규", baseUrl+"9791193790403.jpg"));
        books.add(new Book("P1243", "다빈치 코드", 15000, "댄 브라운", "미스터리 스릴러 소설.", "문학수첩", "스릴러", 250, "2013-12-11", "신규", baseUrl+"9788983925008.jpg"));
        books.add(new Book("P1244", "나니아 연대기", 25000, "C.S. 루이스", "일곱 권으로 구성된 판타지 소설 시리즈.", "시공주니어", "판타지", 110, "2019-03-20", "신규", baseUrl+"9788952789020.jpg"));
        books.add(new Book("P1245", "동물농장", 9000, "조지 오웰", "러시아 혁명을 풍자한 우화 소설.", "민음사", "풍자", 180, "2009-01-07", "신규", baseUrl+"9788937460050.jpg"));
        books.add(new Book("P1246", "어린 왕자", 8000, "앙투안 드 생텍쥐페리", "어린 왕자에 관한 시적인 이야기.", "열린책들", "어린이", 400, "2015-10-20", "신규", baseUrl+"9788932917245.jpg"));
        books.add(new Book("P1247", "백년 동안의 고독", 17000, "G. 마르케스", "부엔디아 가문의 여러 세대에 걸친 이야기.", "문학사상", "마술적 리얼리즘", 70, "2005-07-28", "신규", baseUrl+"9788970126937.jpg"));
        books.add(new Book("P1248", "멋진 신세계", 13500, "올더스 헉슬리", "미래의 세계국가를 배경으로 한 디스토피아 소설.", "소담출판사", "SF", 130, "2015-06-12", "신규", baseUrl+"9788973814725.jpg"));
        books.add(new Book("P1249", "반지의 제왕: 반지 원정대", 45000, "J.R.R. 톨킨", "서사적인 하이 판타지 소설.", "아르테(arte)", "판타지", 60, "2021-02-23", "신규", baseUrl+"9788950992460.jpg"));
        books.add(new Book("P1250", "바람과 함께 사라지다", 21000, "마거릿 미첼", "남북 전쟁 당시 미국 남부를 배경으로 한 로맨스.", "동서문화사", "역사 소설", 45, "2010-08-01", "신규", baseUrl+"9788949706696.jpg"));
        books.add(new Book("P1251", "연을 쫓는 아이", 12500, "할레드 호세이니", "우정과 구원에 관한 이야기.", "현대문학", "소설", 160, "2022-08-20", "신규", baseUrl+"9791167901187.jpg"));
        books.add(new Book("P1252", "파이 이야기", 11500, "얀 마텔", "판타지 모험 소설.", "작가정신", "모험", 220, "2022-03-29", "신규", baseUrl+"9791160262780.jpg"));
        books.add(new Book("P1253", "생각에 관한 생각", 22000, "대니얼 카너먼", "심리학과 의사결정에 관한 책.", "김영사", "비소설", 105, "2018-03-30", "신규", baseUrl+"9788934981213.jpg"));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId().equals(updatedBook.getBookId())) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    public void deleteBook(String bookId) {
        books.removeIf(book -> book.getBookId().equals(bookId));
    }
}
