package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    // test for MemberService using Java not using unit test
    public static void main(String[] args) {

        //MemberService memberService = new MemberServiceImpl();
        /**
         * 관심사 분리 -> AppConfig 등장 -> 생성자 주입(의존관계 주입: DI, Dependency Injection)
         */
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        /**
         * 스프링 컨테이너 적용
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        // 1. first feature
        memberService.join(member);
        // 2. second feature
        Member foundMember = memberService.findMember(1L);
        // Results of test
        System.out.println("new member : " + member);
        System.out.println("found Member : " + foundMember);
        System.out.println("new member ID : " + member.getId());
        System.out.println("found Member ID : " + foundMember.getId());
        System.out.println("new member name : " + member.getName());
        System.out.println("found Member name : " + foundMember.getName());
        System.out.println("new member grade : " + member.getGrade());
        System.out.println("found Member grade : " + foundMember.getGrade());
        System.out.println(member == foundMember);
    }
}
