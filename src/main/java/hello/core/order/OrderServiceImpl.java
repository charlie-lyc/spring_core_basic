package hello.core.order;

//import hello.core.discount.RateDiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 주문 서비스 구현체
 */
/**
 * 컴포넌트 스캔과 의존 관계 자동 주입 : @component, @autowired
 */
@Component
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
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;
    /**
     * 다양한 의존 관계 주입 방법
     * 1. 생성자 주입
     * - 생성자를 통해서 의존 관계를 주입받는 방법
     * - 생성자가 딱 1번만 호출되는 것이 보장
     * - [불변], [필수] 의존 관계에 사용
     * - 생성자가 딱 1개만 있다면 '@Autowired' 를 생략해도 자동 주입 된다. 단, 스프링 빈일 경우에만 해당된다.
     */
//    //@Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    ////////////////////////////////////////////////////////////////////////////////////////

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;
    /**
     * 다양한 의존 관계 주입 방법
     * 2. 수정자 주입(setter 주입)
     * - 'setter' 라고 불리는 '수정자 메서드'를 통해서 의존 관계를 주입받는 방법
     * - [선택], [변경] 가능성이 있는 의존 관계에 사용
     * - Java Bean property 규약의 '수정자 메서드 방식'을 사용
     */
    /**
     * '@Autowired' 기본 동작은 주입할 대상이 없으면 오류가 발생
     * 따라서 주입할 대상이 없어도 동작하게 하려면 '@Autowired(required = false)'로 설정
     * 즉 '@Autowired(required = true)' 이 기본 설정
     */
    @Autowired(required = false)
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 다양한 의존 관계 주입 방법
     * 3. 필드 주입
     * - 코드가 간결해서 많은 개발자들을 유혹하지마나 외부에서 변경이 불가능해서 테스트하기 어려움
     * - 스프링 설정 목적의 '@Configuration' 등에서 특별한 용도로만 사용
     * - 추천하지 않는 '안티 패턴' 이므로 실제 개발에서는 사용하지 말자!
     */
//    @Autowired private final MemberRepository memberRepository;
//    @Autowired private final DiscountPolicy discountPolicy;

    ////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 다양한 의존 관계 주입 방법
     * 4. 일반 메서드 주입
     */

    ////////////////////////////////////////////////////////////////////////////////////////

    /**
     * AppConfig 예시 때문에 어쩔 수 없이 이 생성자를 남겨 둔다.
     * 생성자 주입 방식이 아니라면 이 생성자는 필요없다.
     */
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

    /**
     * '@Configuration' 과 싱글톤 - 검증 용도 코드
     */
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
