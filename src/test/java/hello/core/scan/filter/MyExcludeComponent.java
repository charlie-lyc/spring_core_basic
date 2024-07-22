package hello.core.scan.filter;

import java.lang.annotation.*;

/**
 * 필터
 * - useDefaultFilters: 기본 옵션
 * - includeFilters: 컴포넌트 스캔 대상으로 추가
 * - excludeFilters: 컴포넌트 스탠 대상에서 제외
 */
/**
 * 컴포넌트 스캔 대상에서 제외할 애노테이션
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
