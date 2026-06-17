package com.bookstore.member;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.model.Book;
import com.bookstore.common.model.CartItem;
import com.bookstore.common.model.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BookDatabase bookDatabase = BookDatabase.getInstance();

    public boolean signup(MemberDto memberDto) {
        if (memberRepository.existsByUserId(memberDto.getUserId())) {
            return false;
        }

        Member member = new Member(
                memberDto.getUserId(),
                memberDto.getPassword(),
                memberDto.getName(),
                memberDto.getEmail(),
                "USER"
        );
        memberRepository.save(member);
        return true;
    }

    public boolean isPasswordConfirmed(MemberDto memberDto) {
        return memberDto.getPassword() != null
                && memberDto.getPassword().equals(memberDto.getPasswordConfirm());
    }

    public boolean hasPasswordInput(MemberDto memberDto) {
        return memberDto.getPassword() != null && !memberDto.getPassword().isBlank();
    }

    public Optional<Member> login(String userId, String password) {
        return memberRepository.findByUserId(userId)
                .filter(member -> member.getPassword().equals(password));
    }

    public Optional<Member> findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    public List<Purchase> findPurchasesByUserId(String userId) {
        return bookDatabase.getPurchaseList().stream()
                .filter(purchase -> purchase.getUserId().equals(userId))
                .sorted(Comparator.comparing(Purchase::getPurchaseDate, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<CartItem> findCartItemsByUserId(String userId) {
        return bookDatabase.getCartList().stream()
                .filter(cartItem -> cartItem.getUserId().equals(userId))
                .toList();
    }

    public int calculateCartTotal(String userId) {
        return findCartItemsByUserId(userId).stream()
                .mapToInt(cartItem -> cartItem.getUnitPrice() * cartItem.getQuantity())
                .sum();
    }

    public void clearCart(String userId) {
        bookDatabase.clearCart(userId);
    }

    public List<PurchaseHistoryItem> findRecentPurchaseItemsByUserId(String userId) {
        return findPurchasesByUserId(userId).stream()
                .sorted(Comparator.comparing(Purchase::getPurchaseDate, Comparator.nullsLast(Comparator.reverseOrder())))
                .limit(3)
                .map(this::toPurchaseHistoryItem)
                .toList();
    }

    private PurchaseHistoryItem toPurchaseHistoryItem(Purchase purchase) {
        String imageUrl = bookDatabase.getBooks().stream()
                .filter(book -> book.getBookId().equals(purchase.getBookId()))
                .findFirst()
                .map(Book::getImageUrl)
                .orElse(null);

        return new PurchaseHistoryItem(
                purchase.getBookName(),
                purchase.getUnitPrice(),
                purchase.getPurchaseDate(),
                imageUrl
        );
    }

    public record PurchaseHistoryItem(String bookName, int unitPrice, LocalDateTime purchaseDate, String imageUrl) {
    }

    public boolean withdraw(String userId) {
        Optional<Member> member = memberRepository.findByUserId(userId);
        if (member.isEmpty() || "ADMIN".equals(member.get().getRole())) {
            return false;
        }

        memberRepository.deleteByUserId(userId);
        return true;
    }

    public void updateProfile(MemberDto memberDto) {
        memberRepository.updateProfile(
                memberDto.getUserId(),
                memberDto.getName(),
                memberDto.getEmail()
        );

        if (hasPasswordInput(memberDto)) {
            memberRepository.updatePassword(memberDto.getUserId(), memberDto.getPassword());
        }
    }
}
