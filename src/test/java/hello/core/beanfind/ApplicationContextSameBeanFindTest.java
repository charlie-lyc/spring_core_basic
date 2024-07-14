package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 스프링 컨테이너에 등록된 스프링 빈 조회 테스트 - 동일한 타입이 둘 이상일 경우
 */
class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        //MemberRepository bean = ac.getBean(MemberRepository.class);

        /**
         * ac.getBean(MemberRepository.class) 실행시 중복 오류 발생
         * - org.springframework.beans.factory.NoUniqueBeanDefinitionException
         *   : No qualifying bean of type 'hello.core.member.MemberRepository' available
         *   : expected single matching bean but found 2
         *   : memberRepository1,memberRepository2
         */

        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanNameByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기.")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("Bean name(key): " + key + ", Bean object(value): " + beansOfType.get(key));
        }
        System.out.println("Beans of type: " + beansOfType);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2); // memberRepository1, memberRepository2
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

}
