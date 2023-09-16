package com.google.android.ump;

import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FormError {
    private final int zza;
    private final String zzb;

    public int getErrorCode() {
        return this.zza;
    }

    @NonNull
    public String getMessage() {
        return this.zzb;
    }

    public FormError(int var1, @NonNull String var2) {
        this.zza = var1;
        this.zzb = var2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
        int INTERNAL_ERROR = 1;
        int INTERNET_ERROR = 2;
        int INVALID_OPERATION = 3;
        int TIME_OUT = 4;
    }
}
