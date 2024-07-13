package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OCP, DIP 를 지키기 위해 애플리케이션이 실행될 때 적용할
 * 구현 객체 생성 및 연결 역할을 하는 별도의 (설정을 위한) 클래스
 * - 배우는 등장 인물의 역할에, 공연 기획자는 공연 구성과 배우 섭외에 집중
 * - 관심사 분리, 책임 또는 역할 분리
 * - AppConfig 는 어떤 역할? : Dependency Injection(의존성 주입)
 * - 이렇게 제어의 주도권을 프로그램 객체 내에서 가지지 않고 외부에서 관리하는 것을
 * - Inversion of Control(제어의 역전)이라고 함.
 */

/**
 * 스프링 기반으로 변경
 * - 스프링 컨테이너에 스프링 빈으로 등록
 * - '@Configuration'
 * - '@Bean'
 */
@Configuration
public class AppConfig {

//    public MemberService memberService() {
//        return new MemberServiceImpl(
//                new MemoryMemberRepository() // ...
//        );
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(
//                new MemoryMemberRepository(), // ...
//                new RateDiscountPolicy() // new FixDiscountPolicy(), ...
//        );
//    }

    /**
     * 중복을 제거하고, 각 역할에 따른 구현이 명시적으로 보이도록 리팩터링
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
        // return ...
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
