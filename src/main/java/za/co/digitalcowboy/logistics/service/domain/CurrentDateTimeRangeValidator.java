package za.co.digitalcowboy.logistics.service.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class CurrentDateTimeRangeValidator implements
    ConstraintValidator<CurrentDateTimeRange, LocalDateTime> {

  private CurrentDateTimeRange currentDateTimeRange;

  @Override
  public void initialize(CurrentDateTimeRange constraintAnnotation) {
    this.currentDateTimeRange = constraintAnnotation;
  }

  @Override
  public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
    if(value == null){
      return true;
    }
    int numberOfDays = this.currentDateTimeRange.numberOfDays();
    LocalDateTime endRange = LocalDateTime.now().plusDays(numberOfDays);
    LocalDateTime startRange = LocalDateTime.now().minusDays(numberOfDays);
    return value.isBefore(endRange) && value.isAfter(startRange);
  }
}
