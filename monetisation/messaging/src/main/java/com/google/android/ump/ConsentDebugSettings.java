package com.google.android.ump;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.consent_sdk.zzcn;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Settings for debugging consent behavior in the User Messaging Platform (UMP).
 */
public class ConsentDebugSettings {
  private final boolean isTestDevice;
  private final int debugGeography;

  /**
   * Gets the debug geography setting.
   *
   * @return The debug geography setting.
   */
  public int getDebugGeography() {
    return this.debugGeography;
  }

  /**
   * Checks if the device is set as a test device.
   *
   * @return True if the device is set as a test device, false otherwise.
   */
  public boolean isTestDevice() {
    return this.isTestDevice;
  }

  /**
   * Constructs a new instance of ConsentDebugSettings.
   *
   * @param isTestDevice   True if the device is set as a test device, false otherwise.
   * @param debugGeography The debug geography setting.
   */
  public ConsentDebugSettings(boolean isTestDevice, int debugGeography) {
    this.isTestDevice = isTestDevice;
    this.debugGeography = debugGeography;
  }

  /**
   * Builder class for creating ConsentDebugSettings instances.
   */
  public static class Builder {
    private final List<String> testDeviceHashedIds = new ArrayList<>();
    private final Context context;
    private int debugGeography = 0;
    private boolean forceTesting;

    /**
     * Adds a hashed ID for a test device.
     *
     * @param hashedId The hashed ID of the test device.
     * @return The builder instance.
     */
    @NonNull
    public Builder addTestDeviceHashedId(@NonNull String hashedId) {
      this.testDeviceHashedIds.add(hashedId);
      return this;
    }

    /**
     * Sets the debug geography for testing purposes.
     *
     * @param debugGeography The debug geography setting.
     * @return The builder instance.
     */
    @NonNull
    public Builder setDebugGeography(int debugGeography) {
      this.debugGeography = debugGeography;
      return this;
    }

    /**
     * Sets whether to force testing mode.
     *
     * @param forceTesting True to force testing mode, false otherwise.
     * @return The builder instance.
     */
    @KeepForSdk
    @NonNull
    public Builder setForceTesting(boolean forceTesting) {
      this.forceTesting = forceTesting;
      return this;
    }

    /**
     * Builds a ConsentDebugSettings instance based on the builder's settings.
     *
     * @return The constructed ConsentDebugSettings instance.
     */
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

    /**
     * Constructs a new builder instance.
     *
     * @param context The application context.
     */
    public Builder(@NonNull Context context) {
      this.context = context.getApplicationContext();
    }
  }

  /**
   * Enumerates possible debug geography settings.
   */
  @Retention(RetentionPolicy.SOURCE)
  public @interface DebugGeography {
    int DEBUG_GEOGRAPHY_DISABLED = 0;
    int DEBUG_GEOGRAPHY_EEA = 1;
    int DEBUG_GEOGRAPHY_NOT_EEA = 2;
  }
}
