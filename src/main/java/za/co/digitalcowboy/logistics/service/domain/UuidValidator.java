package za.co.digitalcowboy.logistics.service.domain;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Log4j2
@Component
public class UuidValidator implements ConstraintValidator<VerifyUuid, String> {

  public static final String VALID_UUD_REGEXP = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";
  private static final Pattern pattern;

  static {
    pattern = Pattern.compile(VALID_UUD_REGEXP);
  }

  @Autowired
  public UuidValidator() {
    // Do nothing
  }

  @Override
  public boolean isValid(String idemKey, ConstraintValidatorContext context) {
    return StringUtils.isNotBlank(idemKey) && pattern.matcher(idemKey).matches();
  }
}
