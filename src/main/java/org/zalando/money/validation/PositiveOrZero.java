package org.zalando.money.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @deprecated use @DecimalMin("0")
 */
@Deprecated
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
@DecimalMin("0")
public @interface PositiveOrZero {

    String message() default "{org.zalando.money.validation.PositiveOrZero.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
