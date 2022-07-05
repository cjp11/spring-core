package hello.core.annotaion;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * String이기 때문에 컴파일 타임에 에러 확인하기 어렵다.(ex. "mainnDiscountPolicy")
 * 그래서 annotation으로 만듦.
 *
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {

}
