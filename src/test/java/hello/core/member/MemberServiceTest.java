package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Assertions; // XXX
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 회원 가입 테스트
 */
class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();
    /**
     * OCP, DIP를 지키기 위해 의존성 주입 과정 실행
     */
    MemberService memberService;
    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);
        // when
        memberService.join(member);
        Member foundMember = memberService.findMember(1L);
        // then
        Assertions.assertThat(member).isEqualTo(foundMember);
    }

}
