package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    // test for MemberService using Java not using unit test
    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
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
