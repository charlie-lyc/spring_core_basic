package hello.core.member;

public interface MemberRepository {

    // 1. first feature
    void save(Member member);

    // 2. second feature
    Member findById(Long memberId);
}
