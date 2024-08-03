package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	/**
	 * CoreApplicatoinTests 에서 발생된 에러 해결 -> AutoAppConfig 에서 충돌 발생의 경우를 주석 처리
	 * ...
	 * Caused by:
	 * 		org.springframework.beans.factory.support.BeanDefinitionOverrideException:
	 * 		Invalid bean definition with name 'orderService' defined in class path resource
	 * ...
	 */

	@Test
	void contextLoads() {
	}

}
