package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// test for OrderService using unit test
public class OrderServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    //OrderService orderService = new OrderServiceImpl();
    /**
     * 관심사 분리 -> AppConfig 등장 -> 생성자 주입(의존관계 주입: DI, Dependency Injection)
     */
    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    DiscountPolicy discountPolicy = new FixedDiscountPolicy();

    @Test
    void createOrder() {
        /* given */
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        /* when */
        int itemPrice = 10000;
        Order order = orderService.createOder(memberId, "itemA", itemPrice);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        /* then */
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // Tests passed
        Assertions.assertThat(order.calculatePrice()).isEqualTo(itemPrice - discountPrice); // Tests passed
        // Assertions.assertThat(order.getDiscountPrice()).isEqualTo(100); // Tests failed

    }
}
