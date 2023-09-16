package com.google.android.ump;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.annotation.KeepForSdk;

public class ConsentRequestParameters {
  private final boolean zza;
  @Nullable
  private final String zzb;
  @Nullable
  private final ConsentDebugSettings zzc;

  @Nullable
  public ConsentDebugSettings getConsentDebugSettings() {
    return this.zzc;
  }

  public boolean isTagForUnderAgeOfConsent() {
    return this.zza;
  }

  @Nullable
  public final String zza() {
    return this.zzb;
  }

  // Add this constructor
  public ConsentRequestParameters(@NonNull Builder builder) {
    this.zza = builder.zza;
    this.zzb = builder.zzb;
    this.zzc = builder.zzc;
  }

  public static final class Builder {
    private boolean zza;
    @Nullable
    private String zzb;
    @Nullable
    private ConsentDebugSettings zzc;

    @KeepForSdk
    @NonNull
    public Builder setAdMobAppId(@Nullable String adMobAppId) {
      this.zzb = adMobAppId;
      return this;
    }

    @NonNull
    public Builder setConsentDebugSettings(@Nullable ConsentDebugSettings consentDebugSettings) {
      this.zzc = consentDebugSettings;
      return this;
    }

    @NonNull
    public Builder setTagForUnderAgeOfConsent(boolean tagForUnderAgeOfConsent) {
      this.zza = tagForUnderAgeOfConsent;
      return this;
    }

    @NonNull
    public ConsentRequestParameters build() {
      return new ConsentRequestParameters(this);
    }

    public Builder() {
    }
  }
}
