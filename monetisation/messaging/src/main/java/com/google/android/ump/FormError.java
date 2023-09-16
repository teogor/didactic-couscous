package com.google.android.ump;

import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents an error that can occur in the User Messaging Platform (UMP).
 */
public class FormError {
  private final int errorCode;
  private final String errorMessage;

  /**
   * Gets the error code associated with the error.
   *
   * @return The error code.
   */
  public int getErrorCode() {
    return this.errorCode;
  }

  /**
   * Gets the error message associated with the error.
   *
   * @return The error message.
   */
  @NonNull
  public String getMessage() {
    return this.errorMessage;
  }

  /**
   * Constructs a new FormError instance with the given error code and error message.
   *
   * @param errorCode    The error code.
   * @param errorMessage The error message.
   */
  public FormError(int errorCode, @NonNull String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  /**
   * Enumerates possible error codes for FormError instances.
   */
  @Retention(RetentionPolicy.SOURCE)
  public @interface ErrorCode {
    int INTERNAL_ERROR = 1;
    int INTERNET_ERROR = 2;
    int INVALID_OPERATION = 3;
    int TIME_OUT = 4;
  }
}
