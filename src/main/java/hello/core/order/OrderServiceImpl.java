package hello.core.order;

//import hello.core.discount.RateDiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

/**
 * 주문 서비스 구현체
 */
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 할인정책을 변경하여 적용하려고 보니,
     * - single responsibility, open-closed, Liskov substitution, interface segregation, dependency inversion
     * - 이 모든 '객체 지향 설계 원칙(SOLID)'들을 지켰다고 생각을 했는데...
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    /**
     * (1) 'Open-Closed Priciple(OCP)'을 생각해보면,
     * - 확장에는 열려있고, 변경에는 닫혀 있나?
     * - 하드 코딩을 통한 변경 행위를 하고 있으므로 명백히 OCP 위반!
     * (2) 'Dependency-Inversion Priciple(DIP)'을 생각해보면,
     * - 추상(인터페이스)에 의존하고, 구(현)체에는 의존하지 않고 있나?
     * - 'DiscountPoclicy' 인터페이스에 의존하고는 있지만 'FixDiscountPolicy' 또는 ' RateDiscountPolicy'라는 구(현)체에도 의존하고 있으므로 명백히 DIP 위반!
     */
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * OCP, DIP를 지키기 위해 생성자를 이용한 의존성 주입 방식으로 수정
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    /**
     * '@Configuration' 과 싱글톤 - 검증 용도 코드
     */
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
