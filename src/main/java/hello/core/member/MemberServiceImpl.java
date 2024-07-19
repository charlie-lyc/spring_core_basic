package hello.core.member;

import org.springframework.stereotype.Component;

/**
 * 회원 서비스 구현체
 */
/**
 * 컴포넌트 스캔과 의존 관계 자동 주입
 */
@Component
public class MemberServiceImpl implements MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /**
     * OCP, DIP를 지키기 위해 생성자를 이용한 의존성 주입 방식으로 수정
     */
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
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
