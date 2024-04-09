package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// test for MemberService using unit test
public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    /**
     * 관심사 분리 -> AppConfig 등장 -> 생성자 주입(의존관계 주입: DI, Dependency Injection)
     */
    MemberService memberService;
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        /* given */
        Member member = new Member(1L, "memberA", Grade.VIP);

        /* when */
        memberService.join(member);
        Member foundMember = memberService.findMember(1L); // Tests passed
        // Member foundMember = memberService.findMember(2L); // Tests failed

        /* then */
        Assertions.assertThat(member).isEqualTo(foundMember);
    }
}
