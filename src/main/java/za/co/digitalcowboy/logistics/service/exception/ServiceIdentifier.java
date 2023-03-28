package za.co.digitalcowboy.logistics.service.exception;

public enum ServiceIdentifier {
  PAYMENT_PROCCESSING("01");

  private final String code;

  ServiceIdentifier(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
