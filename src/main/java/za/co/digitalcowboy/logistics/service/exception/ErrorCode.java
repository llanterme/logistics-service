package za.co.digitalcowboy.logistics.service.exception;

public enum ErrorCode {

  UNKNOWN_ERROR("000", "Unknown error"),
  INVALID_INPUT_FIELD("001", "Invalid input field %s"),
  MISSING_HEADER("002", "Missing header"),

  EVENT_INPUT_FIELDS_INVALID("041", "Event input fields are invalid %s"),
  EVENT_ALREADY_MARKED_AS_READ("042", "Event is already marked as read"),
  EVENT_NOT_FOUND("043", "Event not found in the inbox"),

  // 011 - 020 Idempotency
  MISSING_IDEMPOTENCY("011", "Idempotency key is required"),
  MISSING_CALLER_ID("012", "Caller id is required"),
  INVALID_IDEMPOTENCY("013", "Invalid idempotency key"),
  PARAMETERS_MISMATCH("014", "Parameters mismatch"),

  // 021 - 030 Transaction
  CHANNEL_KEY_NOT_EXIST("021", "Transaction Channel key does not exist"),
  MAMBU_CLIENT_ERROR("022", "Client error on url %s - status %d - error %s"),
  MAMBU_SERVER_ERROR("023", "Server error on url %s - status %d - reason %s"),
  FAILED_TO_EVALUATE_DYNAMIC_EXPRESSION("024", "Failed to evaluate dynamic expression %s"),
  MISSING_BANKING_LEDGER_TXN_ID("026", "Missing banking ledger transaction id"),

  CANNOT_EVALUATE_CHANNEL_ID("027", "Cannot evaluate channel id from expression %s"),

  // 031 - 040 PUC
  DUPLICATE_PAYMENT_USE_CASE("031", "Payment use case already exists"),
  PAYMENT_USE_CASE_NOT_EXIST("032", "Payment use case does not exist"),
  INVALID_PAYMENT_USE_CASE_FORMAT("033", "Invalid payment use case format"),
  UNKNOWN_PAYMENT_USE_CASE("034", "Unknown payment use case"),
  PAYMENT_USE_CASE_ALREADY_ENABLED("035", "Payment use case is already enabled"),
  PAYMENT_USE_CASE_ALREADY_DISABLED("036", "Payment use case is already disabled"),
  TRANSACTION_DEFINITION_IS_NOT_MATCH("038", "Transaction definition is not match"),
  CUSTOM_FIELDS_IS_NOT_EXITS("039", "The custom fields is not exits.");

  private final String value;

  private final String message;

  ErrorCode(String value, String message) {
    this.value = value;
    this.message = message;
  }

  public String toUniversalCode() {
    return String.format("%s%s%s", SystemIdentifier.INTEGRATION_PLATFORM.getCode(),
        ServiceIdentifier.PAYMENT_PROCCESSING.getCode(), value);
  }

  public String getMessage() {
    return message;
  }
}