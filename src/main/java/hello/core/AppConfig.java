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

/**
 * '@Configuration' 과 싱글톤
 * - '@Configuration' 은 결국 싱글톤을 보장하기 위한 설정이다!
 * - 그런데 아래의 코드를 살펴보면,
 *   '@Bean memberService -> new MemoryMemberRepository()'
 *   '@Bean orderService -> new MemoryMemberRepository()'
 *   이렇게 같은 메서드를 두번 호출하여 2개의 객체가 생성되면서 싱글톤을 위반하는 것처럼 보이는데...
 * - 스프링(싱글톤) 컨테이너는 이 문제를 어떻게 해결하는 걸까?
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
    /**
     * 빈 이름은 기본적으로 메서드 이름을 사용 하지만 직접 부여할 수도 있음
     * 단, 반드시 서로 다른 이름을 부여해야 함
     * - @Bean(name="memberService")
     */
    /**
     *  '@Bean memberService -> new MemoryMemberRepository()'
     *  '@Bean orderService -> new MemoryMemberRepository()'
     * 코드로 보기에는 분명히 2번의 new MemoryMemberRepository() 호출로 다른 인스턴스가 되는 것처럼 보이는데
     * 정말 그런지 로그를 남겨보자!
     * 결과적으로 아래와 같이 1번씩만 호출되었다!
     * -> call AppConfig.memberService
     * -> call AppConfig.memberRepository
     * -> call AppConfig.orderService
     */
    /**
     * 결론적으로
     * '@Bean' 만 사용해도 등록은 되지만 싱글톤을 보장하지는 않으며,
     * '@Configuration' 을 사용하는 것은 CGLIB 기술은 사용해서 싱글톤을 보장하는 것이다.
     */
    /**
     * 테스트로 '@Configuration' 을 삭제하고 실행해보면 아래와 같은 결과를 볼수 있다.
     * -> call AppConfig.memberService
     * -> call AppConfig.memberRepository
     * -> call AppConfig.orderService
     * -> call AppConfig.memberRepository
     * -> call AppConfig.memberRepository
     */
    @Bean
    public MemberService memberService() {
        // 1번 호출
        System.out.println("-> call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        // 1번 호출
        System.out.println("-> call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        // 2번 또는 3번 호출 ?
        System.out.println("-> call AppConfig.memberRepository");
        return new MemoryMemberRepository();
        // return ...
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
