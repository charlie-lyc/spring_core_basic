package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class SingletonTest {
    /**
     * 스프링 없는 순수한 DI 컨테이너 테스트
     */
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {

        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("Member service 1: " + memberService1);
        System.out.println("Member service 2: " + memberService2);
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
    /**
     * 고객 트래픽이 초당 100이라면 초당 100개의 객체가 생성 및 소멸 -> 메모리 낭비...
     * 해결 방안은 ?
     * 객체가 1개만 새성되고 공유되도록 설계 -> Singleton Pattern!
     */

    /**
     * 싱글턴 패턴을 사용하는 테스트
     */
    @Test
    @DisplayName("싱글턴 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        /**
         * 에러 발생 : java: SingletonService() has private access in hello.core.singleton.SingletonService
         * 왜 ?
         * private 을 사용하여 생성자를 막아두었으므로 오류가 발생한다.
         */
        //SingletonService singletonService = new SingletonService();

        // 1. 조회: 호출할 때마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        // 2. 조회: 호출할 때마다 같은 객체를 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은 것을 확인
        System.out.println("Singleton service 1: " + singletonService1);
        System.out.println("Singleton service 2: " + singletonService2);
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
    }
    /**
     * 싱글턴 패턴 문제점
     * - DIP, OCP 위반 가능성 높음
     * - 테스트하기 어려우며, 유연성이 떨어짐
     * - '안티패턴' 이라고 불리기도 함
     */

    /**
     * 스프링 컨테이너를 사용하는 테스트
     * - 스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도 객체 인스턴스를 싱글톤으로 관리 즉, 스프링 컨테이너가 싱글턴 컨테이너 역할
     * - 따라서 스프링 컨테이너를 통해 싱글턴 패턴 방식의 문제점을 해결하면서 객체를 싱글톤으로 유지하는 것이 가능함
     * - 스프링에서 싱글톤 객체를 생성하고 관리하는 기능을 '싱글톤 레지스트리'라고 함
     */
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회: 호출할 때마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        // 2. 조회: 호출할 때마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은 것을 확인
        System.out.println("Member service 1: " + memberService1);
        System.out.println("Member service 2: " + memberService2);
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }


}
