package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 회원 가입 테스트
 * - 애플리케이션 내에서 로직을 이용한 이러한 테스트는 바람직한 방법은 아님
 * - '/src/test/~' 에서 실행하는 것이 정상적인 방법
 */
public class MemberApp {

    public static void main(String[] args) {

//        MemberService memberService = new MemberServiceImpl();

        /**
         * OCP, DIP를 지키기 위해 의존성 주입 과정 실행
         */
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /**
         * 스프링 기반으로 변경
         * - 'AnnotationConfigApplicatoinContext' 의 객체로써
         * - 'ApplicationContext' 를 '스피링 컨테이너'라 함
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);
        System.out.println("New member: " + member.getName());
        System.out.println("Found member: " + foundMember.getName());
    }
}
