package com.bookstore.member;

import com.bookstore.common.model.CartItem;
import com.bookstore.common.util.SessionUtils;
import com.bookstore.domain.admin.service.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AdminService adminService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberDto memberDto, Model model) {
        if (!memberService.isPasswordConfirmed(memberDto)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "member/signup";
        }

        boolean signupSuccess = memberService.signup(memberDto);
        if (!signupSuccess) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            return "member/signup";
        }

        return "redirect:/member/id-input";
    }

    @GetMapping("/login")
    public String loginRedirect() {
        return "redirect:/member/id-input";
    }

    @GetMapping("/id-input")
    public String idInputForm(@RequestParam(required = false) String redirectUrl, Model model) {
        model.addAttribute("memberDto", new MemberDto());
        model.addAttribute("redirectUrl", redirectUrl);
        return "member/id-input";
    }

    @PostMapping("/id-input")
    public String idInput(
            @ModelAttribute MemberDto memberDto,
            @RequestParam(required = false) String redirectUrl,
            Model model,
            HttpSession session
    ) {
        Member member = memberService.login(memberDto.getUserId(), memberDto.getPassword()).orElse(null);
        if (member == null) {
            model.addAttribute("redirectUrl", redirectUrl);
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "member/id-input";
        }

        session.setAttribute("loginUserId", member.getUserId());
        session.setAttribute("loginRole", member.getRole());

        if ("ADMIN".equals(member.getRole())) {
            return "redirect:/admin";
        }

        if (isSafeRedirectUrl(redirectUrl)) {
            return "redirect:" + redirectUrl;
        }

        return "redirect:/books";
    }

    @GetMapping("/mypage")
    public String myPage(@RequestParam(required = false) String userId, Model model, HttpSession session) {
        userId = resolveLoginUserId(userId, session);

        if (userId == null) {
            return "redirect:/member/id-input";
        }

        model.addAttribute("member", memberService.findByUserId(userId).orElse(null));
        List<?> purchases = memberService.findPurchasesByUserId(userId);
        model.addAttribute("purchases", purchases);
        model.addAttribute("recentPurchases", memberService.findRecentPurchaseItemsByUserId(userId));
        model.addAttribute("hasMorePurchases", purchases.size() >= 3);
        return "member/mypage";
    }

    @GetMapping("/purchases")
    public String purchases(Model model, HttpSession session) {
        String userId = SessionUtils.getLoginUserId(session);
        if (userId == null) {
            return "redirect:/member/id-input";
        }

        model.addAttribute("purchases", memberService.findPurchasesByUserId(userId));
        return "member/purchases";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        String userId = SessionUtils.getLoginUserId(session);
        if (userId == null) {
            return "redirect:/member/id-input";
        }

        model.addAttribute("cartItems", memberService.findCartItemsByUserId(userId));
        model.addAttribute("cartTotal", memberService.calculateCartTotal(userId));
        return "member/cart";
    }

    @GetMapping("/cart/payment")
    public String cartPayment(Model model, HttpSession session) {
        String userId = SessionUtils.getLoginUserId(session);
        if (userId == null) {
            return "redirect:/member/id-input";
        }

        List<CartItem> cartItems = memberService.findCartItemsByUserId(userId);
        if (cartItems.isEmpty()) {
            return "redirect:/member/cart?cartError=empty";
        }

        boolean allAvailable = cartItems.stream()
                .allMatch(item -> adminService.isStockAvailable(item.getBookId(), item.getQuantity()));
        if (!allAvailable) {
            return "redirect:/member/cart?cartError=outOfStock";
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", memberService.calculateCartTotal(userId));
        return "member/cart-payment";
    }

    @PostMapping("/cart/complete")
    public String completeCartPurchase(
            @RequestParam(required = false) String paymentMethod,
            HttpSession session,
            Model model
    ) {
        String userId = SessionUtils.getLoginUserId(session);
        if (userId == null) {
            return "redirect:/member/id-input";
        }

        List<CartItem> cartItems = memberService.findCartItemsByUserId(userId);
        if (cartItems.isEmpty()) {
            return "redirect:/member/cart?cartError=empty";
        }

        if (!isValidPaymentMethod(paymentMethod)) {
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("cartTotal", memberService.calculateCartTotal(userId));
            model.addAttribute("error", "결제 수단을 다시 선택해 주세요.");
            return "member/cart-payment";
        }

        boolean allAvailable = cartItems.stream()
                .allMatch(item -> adminService.isStockAvailable(item.getBookId(), item.getQuantity()));
        if (!allAvailable) {
            return "redirect:/member/cart?cartError=outOfStock";
        }

        try {
            for (CartItem item : cartItems) {
                adminService.processPurchase(userId, item.getBookId(), item.getQuantity());
            }
            memberService.clearCart(userId);
        } catch (RuntimeException e) {
            return "redirect:/member/cart?cartError=outOfStock";
        }

        return "redirect:/member/cart?cartSuccess=true";
    }

    @PostMapping("/cart/purchase")
    public String purchaseCart(HttpSession session) {
        if (SessionUtils.getLoginUserId(session) == null) {
            return "redirect:/member/id-input";
        }
        return "redirect:/member/cart/payment";
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Model model, HttpSession session) {
        String userId = SessionUtils.getLoginUserId(session);
        if (userId == null) {
            return "redirect:/member/id-input";
        }

        Member member = memberService.findByUserId(userId).orElse(null);
        model.addAttribute("member", member);
        if (member != null && "ADMIN".equals(member.getRole())) {
            model.addAttribute("error", "관리자 계정은 탈퇴할 수 없습니다.");
        }
        return "member/withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(HttpSession session, Model model) {
        String userId = SessionUtils.getLoginUserId(session);
        if (userId == null) {
            return "redirect:/member/id-input";
        }

        if (!memberService.withdraw(userId)) {
            model.addAttribute("member", memberService.findByUserId(userId).orElse(null));
            model.addAttribute("error", "관리자 계정은 탈퇴할 수 없습니다.");
            return "member/withdraw";
        }

        session.invalidate();
        return "redirect:/books";
    }

    @GetMapping("/mypage/edit")
    public String editMyPageForm(@RequestParam(required = false) String userId, Model model, HttpSession session) {
        userId = resolveLoginUserId(userId, session);

        if (userId == null) {
            return "redirect:/member/id-input";
        }

        Member member = memberService.findByUserId(userId).orElse(null);
        if (member == null) {
            model.addAttribute("member", null);
            return "member/mypage";
        }

        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(member.getUserId());
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        model.addAttribute("memberDto", memberDto);
        return "member/mypage-edit";
    }

    @PostMapping("/mypage/edit")
    public String editMyPage(@ModelAttribute MemberDto memberDto, Model model) {
        if (memberService.hasPasswordInput(memberDto) && !memberService.isPasswordConfirmed(memberDto)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "member/mypage-edit";
        }

        memberService.updateProfile(memberDto);
        return "redirect:/member/mypage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/id-input";
    }

    private String resolveLoginUserId(String userId, HttpSession session) {
        if (userId != null && !userId.isBlank()) {
            return userId;
        }

        Object loginUserId = session.getAttribute("loginUserId");
        return loginUserId == null ? null : loginUserId.toString();
    }

    private boolean isSafeRedirectUrl(String redirectUrl) {
        return redirectUrl != null
                && redirectUrl.startsWith("/member/purchase/")
                && !redirectUrl.contains("\r")
                && !redirectUrl.contains("\n");
    }

    private boolean isValidPaymentMethod(String paymentMethod) {
        return "CARD".equals(paymentMethod) || "BANK".equals(paymentMethod);
    }
}
