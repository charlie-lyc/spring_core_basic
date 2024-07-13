package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 주문과 할인정책 테스트
 * - 바람직한 테스트가 아님
 */
public class OrderApp {

    public static void main(String[] args) {

//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        /**
         * OCP, DIP를 지키기 위해 의존성 주입 과정 실행
         */
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        /**
         * 스프링 기반으로 변경
         * - 'AnnotationConfigApplicatoinContext' 의 객체로써
         * - 'ApplicationContext' 를 '스피링 컨테이너'라 함
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * Long: object type, this can be null
         * long: primitive type, this must be value
         */
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("Order is: " + order);

    }
}
