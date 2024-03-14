package hello.core.member;

public interface MemberService {

    // 1. first feature
    void join(Member member);

    // 2. second feature
    Member findMember(Long memberId);
}
