package za.co.digitalcowboy.logistics.service.domain;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CurrentDateTimeRangeValidator.class})
public @interface CurrentDateTimeRange {

  Class<?>[] groups() default {};

  String message() default "";

  Class<? extends Payload>[] payload() default {};

  int numberOfDays() default 1;
}
