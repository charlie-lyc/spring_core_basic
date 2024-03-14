package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

import java.util.Arrays;

public class OrderApp {

    // test for OrderService using Java not using unit test
    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        int itemPrice = 10000;
        Order order = orderService.createOder(memberId, "itemA", itemPrice);

        System.out.println("order : " + order);
        System.out.println("price after discount : " + order.calculatePrice());

        /* ------------------------------------------------------------------------- */

        DiscountPolicy discountPolicy = new FixedDiscountPolicy();
        int discountPrice = discountPolicy.discount(member, itemPrice);
        System.out.println("discount price : " + discountPrice);

    }

}
