package com.google.android.ump;

import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FormError {
  private final int errorCode;
  private final String errorMessage;

  public int getErrorCode() {
    return this.errorCode;
  }

  @NonNull
  public String getMessage() {
    return this.errorMessage;
  }

  public FormError(int errorCode, @NonNull String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  @Retention(RetentionPolicy.SOURCE)
  public @interface ErrorCode {
    int INTERNAL_ERROR = 1;
    int INTERNET_ERROR = 2;
    int INVALID_OPERATION = 3;
    int TIME_OUT = 4;
  }
}
