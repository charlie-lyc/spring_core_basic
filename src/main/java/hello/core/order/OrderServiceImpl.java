package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * DIP 위반! interface에만 의존해야하는데 구체적인 class에도 의존하고 있음
     * ex) FixedDiscountPolicy 또는 RateDiscountPolicy 에 의존
     *
     * OCP 위반! 기존 소스코드를 변경하지 않고 확장할 수 있어야 하는데 변경(삭제)하게 됨
     * ex) FixedDiscountPolicy 또는 RateDiscountPolicy 둘 중 하나를 삭제
     */
//    private final DiscountPolicy discountPolicy = new FixedDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /**
     * 그렇다면 DIP, OCP 를 위반하지 않을 방법은?
     */
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
