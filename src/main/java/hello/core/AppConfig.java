package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixedDiscountPolicy());
//    }
    /**
     * 리팩토링
     */
//    public MemberService memberService() {
//        return new MemberServiceImpl(memberRepository());
//    }
//    public OrderService orderService() {
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//    public DiscountPolicy discountPolicy() {
//        //return new FixedDiscountPolicy();
//        /**
//         * 할인 정책 변경
//         */
//        return new RateDiscountPolicy();
//    }

    /**
     * 이전까지는 자바로만 코딩했었지만 이제는
     * 스프링 컨테이너 적용
     */
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
