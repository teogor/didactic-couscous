package com.google.android.ump;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.consent_sdk.zzcn;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class ConsentDebugSettings {
  private final boolean isTestDevice;
  private final int debugGeography;

  public int getDebugGeography() {
    return this.debugGeography;
  }

  public boolean isTestDevice() {
    return this.isTestDevice;
  }

  public ConsentDebugSettings(boolean isTestDevice, int debugGeography) {
    this.isTestDevice = isTestDevice;
    this.debugGeography = debugGeography;
  }

  public static class Builder {
    private final List<String> testDeviceHashedIds = new ArrayList<>();
    private final Context context;
    private int debugGeography = 0;
    private boolean forceTesting;

    @NonNull
    public Builder addTestDeviceHashedId(@NonNull String hashedId) {
      this.testDeviceHashedIds.add(hashedId);
      return this;
    }

    @NonNull
    public Builder setDebugGeography(int debugGeography) {
      this.debugGeography = debugGeography;
      return this;
    }

    @KeepForSdk
    @NonNull
    public Builder setForceTesting(boolean forceTesting) {
      this.forceTesting = forceTesting;
      return this;
    }

    @NonNull
    public ConsentDebugSettings build() {
      Context context = this.context;
      List<String> testDeviceHashedIds = this.testDeviceHashedIds;
      boolean isTestDevice;
      if (!zzcn.zzb()) {
        if (testDeviceHashedIds.contains(zzcn.zza(context))) {
          isTestDevice = true;
        } else if (this.forceTesting) {
          isTestDevice = true;
        } else {
          isTestDevice = false;
        }
      } else {
        isTestDevice = true;
      }

      return new ConsentDebugSettings(isTestDevice, this.debugGeography);
    }

    public Builder(@NonNull Context context) {
      this.context = context.getApplicationContext();
    }
  }

  @Retention(RetentionPolicy.SOURCE)
  public @interface DebugGeography {
    int DEBUG_GEOGRAPHY_DISABLED = 0;
    int DEBUG_GEOGRAPHY_EEA = 1;
    int DEBUG_GEOGRAPHY_NOT_EEA = 2;
  }
}
