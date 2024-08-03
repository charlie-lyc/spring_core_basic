package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

/**
 * 자동 주입할 스프링 빈이 없어도 동작이 되는지 테스트
 * - @Autowired(required = true) 가 기본 옵션이어서 자동 주입 대상이 없으면 오류 발생
 */
public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        /**
         * 자동 주입이 필수일 경우( 예: @Autowired(required = true) ) 그 대상이 없다면 에러 발생!
         * org.springframework.beans.factory.UnsatisfiedDependencyException:
         *  Error creating bean with name 'autowiredTest.TestBean':
         *   Unsatisfied dependency expressed through method 'setNoBean1' parameter 0:
         *    No qualifying bean of type 'hello.core.member.Member' available:
         *      expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
         */
        /**
         * 자동 주입을 필수가 아니라 옵션으로 처리하는 방법
         * 1. @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
         * 2. org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null 이 호출됨
         * 3. Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 호출됨
         */

        // 1. 호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("setNoBean1 = " + member);
        }

        // 2. null 호출
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member);
        }

        // 3. Optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member);
        }
    }
}
