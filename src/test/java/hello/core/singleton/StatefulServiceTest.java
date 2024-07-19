package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 상태를 유지할 경우 발생하는 문제점
 */
class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA 로 가정: 사용자 A 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB 로 가정: 사용자 B 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A 주문금액 조회
        int price = statefulService1.getPrice();
        // 10000원 일 줄 기대했지만, 20000원 출력
        System.out.println("Price: " + price);
        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(10000); // Test failed
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
