package za.co.digitalcowboy.logistics.service.domain;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, PARAMETER, ANNOTATION_TYPE, LOCAL_VARIABLE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UuidValidator.class)
public @interface VerifyUuid {

  String message() default "Invalid UUID format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}