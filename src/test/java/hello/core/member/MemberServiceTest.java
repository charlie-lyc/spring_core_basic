package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// test for MemberService using unit test
public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

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
