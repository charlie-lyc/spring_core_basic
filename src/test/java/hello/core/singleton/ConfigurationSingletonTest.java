package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * '@Configuration' 과 싱글톤 - 관련 테스트
 */
class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 모두 같은 인스턴스를 참고하고 있나?
        System.out.println("memberService -> memberRepository : " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository : " + orderService.getMemberRepository());
        System.out.println("memberRepository : " + memberRepository);

        // 모두 같은 인스턴스를 참고하고 있다!
        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());
    }

    /**
     * '@Configuration' 과 바이트코드 조작의 마법
     * - 스피링 컨테이너는 싱글톤 컨테이너이자 싱글톤 레지스트리 즉, 스프링 빈이 싱글톤이 되도록 보장해주어야 한다!
     * - 하지만 아무리 그렇더라도 호출되는 자바 코드를 어떻게 조작한다는 것인가?
     * - ...
     * - 그렇다! 스프링은 클래스의 바이트 코드를 조작하는 라이브로리를 사용한다!
     */
    @Test
    void configurationDeep() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // AppConfig 도 스프링 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);

        /**
         * 빈으로 등록이 된 후에 클래스를 조회하면 아래와 같은 결과를 얻는다.
         * Bean : class hello.core.AppConfig$$SpringCGLIB$$0
         * 만약 순수한 클래스라면 아래와 같이 출력되어야 하는데
         * Bean : class hello.core.AppConfig
         * 왜 이럴까?
         */
        System.out.println("Bean : " + bean.getClass());
        /**
         * 이것은 바로 스프링이 'CGLIB' 라는 바이트코드 조작 라이브러리를 사용해서
         * AppConfig 클래스를 상속받은 임의의 다른 클래스를 스프링 빈으로 등록했기 때문이다.
         */
        /**
         * 결론적으로
         * '@Bean' 만 사용해도 등록은 되지만 싱글톤을 보장하지는 않으며,
         * '@Configuration' 을 사용하는 것은 CGLIB 기술은 사용해서 싱글톤을 보장하는 것이다.
         */
    }

}
