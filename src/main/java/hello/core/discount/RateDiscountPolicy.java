package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

/**
 * 정률 할인 정책 구현체
 */
/**
 * 컴포넌트 스캔과 의존 관계 자동 주입 : @component, @autowired
 */
@Component
public class RateDiscountPolicy implements  DiscountPolicy {

    private int discountPercent = 10; // 10% 정률 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
