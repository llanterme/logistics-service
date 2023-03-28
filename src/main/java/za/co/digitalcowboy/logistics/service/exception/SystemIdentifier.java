package za.co.digitalcowboy.logistics.service.exception;

public enum SystemIdentifier {
  INTEGRATION_PLATFORM("01");

  private final String code;

  SystemIdentifier(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
