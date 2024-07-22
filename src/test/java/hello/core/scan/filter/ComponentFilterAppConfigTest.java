package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        /**
         * 에러발생 :
         * org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'beanB' available
         */
        // BeanB beanB = ac.getBean("beanB", BeanB.class);
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class)
        );

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    @Configuration
//    @ComponentScan(
//            includeFilters = @ComponentScan.Filter(
//                    type = FilterType.ANNOTATION,
//                    classes = MyIncludeComponent.class
//            ),
//            excludeFilters = @ComponentScan.Filter(
//                    type = FilterType.ANNOTATION,
//                    classes = MyExcludeComponent.class
//            )
//    )
    @ComponentScan(
            includeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            },
            excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
                    // @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
            }
    )
    static class ComponentFilterAppConfig {
    }

    /**
     * FilterType 옵션
     * - ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
     *   ex) org.example.SomeAnnotation
     * - ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
     *   ex) org.example.SomeClass
     * - ASPECTJ: AspectJ 패턴 사용
     *   ex) org.example..*Service+
     * - REGEX: 정규 표현식
     *   ex) org\.example\.Default.*
     * - CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리
     *   ex) org.example.MyTypeFilter
     */

    /**
     * 컴포넌트 스캔과 의존 관계 자동 주입에서
     * - 스프링 기본 설정에 따른 '@Component' 사용만으로 충분하기때문에
     * - includeFilters 옵션을 사용하는 경우는 거의 없다.
     * - 다만, excludeFilters 옵션은 간혹 사용하는 경우가 있다.
     */
}
