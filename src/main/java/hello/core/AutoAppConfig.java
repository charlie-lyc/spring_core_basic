package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트 스캔과 의존 관계 자동 주입 : @component, @autowired
 */
/**
 * '@Component' 가 사용된 모든 대상이 스캔의 대상이 된다.
 * - 그런데 '@Configuration' 을 따라가 보면 이 또한 컴포넌트임을 알 수 있다.
 * - 따라서 '@Configuration' 이 붙은 설정 정보도 자동으로 등록되기 때문에,
 * - AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보가 함께 등록되고 실행되지 않도록 하기 위해
 * - excludeFilters 를 이용해서 컴포넌트 스캔 대상에서 제외했다.
 */
@Configuration
//@ComponentScan
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class
        )
)
public class AutoAppConfig {

        /**
         * 다양한 의존 관계 주입 방법
         * 3. 필드 주입
         * - 실제 개발에서 사용을 추천하지 않지만,
         * - 이와 같은 스프링 설정에서 빈의 수동 등록시 자동 등록된 빈의 의존 관계가 필요할 경우 사용할 수 있음
         */
        @Autowired MemberRepository memberRepository;
        @Autowired DiscountPolicy discountPolicy;
        @Bean
        OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
                return new OrderServiceImpl(memberRepository, discountPolicy);
        }

        /**
         * 컴포넌트 스캔에서 같은 이름의 빈이 중복으로 등록되면 어떻게 될까?
         */
        /**
         * 1. 자동 빈 등록 vs 자동 빈 등록 -> 에러 발생
         * - ConflictingBeanDefinitionException
         * 2. 수동 빈 등록 vs 자동 빈 등록 -> 수동 등록이 우선권 즉, 자동으로 등록된 빈을 override
         * - Overriding bean definition for bean 'memoryMemberRepository' with a different definition ...
         */
        @Bean(name = "memoryMemberRepository")
        public MemberRepository memberRepository() {
                return new MemoryMemberRepository();
        }
}
/**
 * 컴포넌트 스캔은 @Component 뿐만 아니라 다음과 내용도 추가로 대상에 포함한다.
 * '@Component' : 컴포넌트 스캔에서 사용
 * '@Configuration' : 스프링 설정 정보에서 사용
 * '@Controller' : 스프링 MVC 컨트롤러에서 사용
 * '@Service' : 스프링 비즈니스 로직에서 사용
 * '@Repository' : 스프링 데이터 접근 계층에서 사용
 */
/**
 * 각각의 정보를 따라가 보면 '@Component' 가 포함되어 있는 것을 볼 수 있다.
 */
// @Component
// public @interface Configuration { ... }
//
// @Component
// public @interface Controller { ... }
//
// @Component
// public @interface Service { ... }
//
// @Component
// public @interface Repository { ... }

/**
 * 다양한 의존 관계 주입 방법
 * 1. 생성자 주입
 * 2. 수정자 주입(setter 주입)
 * 3. 필드 주입
 * 4. 일반 메서드 주입
 */