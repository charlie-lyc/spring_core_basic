package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트 스캔과 의존 관계 자동 주입
 */
/**
 * '@Component' 가 사용된 모든 대상이 스캔의 대상이 된다.
 * - 그런데 '@Configuration' 을 따라가 보면 이 또한 컴포넌트임을 알 수 있다.
 * - 따라서 '@Configuration' 이 붙은 설정 정보도 자동으로 등록되기 때문에,
 *   AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되지 않도록 하기 위해
 *   excludeFilters 를 이용해서 컴포넌트 스캔 대상에서 제외했다.
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class
        )
)
public class AutoAppConfig {

}
