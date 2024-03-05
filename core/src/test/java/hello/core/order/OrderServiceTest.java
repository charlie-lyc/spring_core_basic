package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// test for OrderService using unit test
public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();
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
