package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * XML 기반 스피링 빈 설정 테스트
 */
class XmlAppContext {

    @Test
    void xmlAppContext() {

        /**
         * 커뮤니티 버전에서는 New > XML Configuration File > Spring Config 메뉴가 활성화되지 않는다.
         * 따라서 appConfig.xml 파일을 생성해도 적용되지 않고 이 테스트 또한 실행되지 않는다.
         * 반대로 얼티밋 버전에서 위의 메뉴에 따라 appCofig.xml 파일을 생성하면 정상적으로 실행이 된다.
         */
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
