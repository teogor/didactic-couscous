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
  private final boolean zza;
  private final int zzb;

  public int getDebugGeography() {
    return this.zzb;
  }

  public boolean isTestDevice() {
    return this.zza;
  }

  public ConsentDebugSettings(boolean zza, int zzb) {
    this.zza = zza;
    this.zzb = zzb;
  }

  public static class Builder {
    private final List<String> zza = new ArrayList<>();
    private final Context zzb;
    private int zzc = 0;
    private boolean zzd;

    @NonNull
    public Builder addTestDeviceHashedId(@NonNull String hashedId) {
      this.zza.add(hashedId);
      return this;
    }

    @NonNull
    public Builder setDebugGeography(int debugGeography) {
      this.zzc = debugGeography;
      return this;
    }

    @KeepForSdk
    @NonNull
    public Builder setForceTesting(boolean forceTesting) {
      this.zzd = forceTesting;
      return this;
    }

    @NonNull
    public ConsentDebugSettings build() {
      Context var1 = this.zzb;
      List<String> var2 = this.zza;
      boolean var3;
      if (!zzcn.zzb()) {
        if (var2.contains(zzcn.zza(var1))) {
          var3 = true;
        } else if (this.zzd) {
          var3 = true;
        } else {
          var3 = false;
        }
      } else {
        var3 = true;
      }

      return new ConsentDebugSettings(var3, this.zzc);
    }

    public Builder(@NonNull Context context) {
      this.zzb = context.getApplicationContext();
    }
  }

  @Retention(RetentionPolicy.SOURCE)
  public @interface DebugGeography {
    int DEBUG_GEOGRAPHY_DISABLED = 0;
    int DEBUG_GEOGRAPHY_EEA = 1;
    int DEBUG_GEOGRAPHY_NOT_EEA = 2;
  }
}
