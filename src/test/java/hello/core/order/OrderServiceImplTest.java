package hello.core.order;


import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 왜 생성자 주입을 선택해야 하는지 알 수 있는 테스트
 */
public class OrderServiceImplTest {

    /**
     * 'OrderServiceImpl' 에서 수정자 주입 방식일 경우
     * - NullPointerException 에러 발생
     * - 왜 에러가 발생했는지, 무엇을 필요로 하는지 알기 어려움
     */
    /**
     * java.lang.NullPointerException:
     *  Cannot invoke "hello.core.member.MemberRepository.findById(java.lang.Long)" because "this.memberRepository" is null
     */
//    @Test
//    void createOrder() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.createOrder(1L, "itemA", 10000);
//    }

    /**
     * 'OrderServiceImpl' 에서 생성자 주입 방식의 경우
     * - 컴파일 에러 발생
     * - 왜 에러가 발생했는지, 무엇을 필요로 하는지 쉽게 알 수 있음
     */
    /**
     * java: constructor OrderServiceImpl in class hello.core.order.OrderServiceImpl cannot be applied to given types;
     *   required: hello.core.member.MemberRepository,hello.core.discount.DiscountPolicy
     *   found:    no arguments
     *   reason: actual and formal argument lists differ in length
     */
//    @Test
//    void createOrder() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.createOrder(1L, "itemA", 10000);
//    }

    /**
     * 에러 메시지에 따라 수정
     */
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "memberA", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        Assertions.assertThat(order.getMemberId()).isEqualTo(1L);
        Assertions.assertThat(order.getItemName()).isEqualTo("itemA");
        Assertions.assertThat(order.getItemPrice()).isEqualTo(10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
