package com.google.android.ump;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.annotation.KeepForSdk;

public class ConsentRequestParameters {
  private final boolean tagForUnderAgeOfConsent;
  @Nullable
  private final String adMobAppId;
  @Nullable
  private final ConsentDebugSettings consentDebugSettings;

  @Nullable
  public ConsentDebugSettings getConsentDebugSettings() {
    return this.consentDebugSettings;
  }

  public boolean isTagForUnderAgeOfConsent() {
    return this.tagForUnderAgeOfConsent;
  }

  // getAdMobAppId
  @Nullable
  public final String zza() {
    return this.adMobAppId;
  }

  // Added constructor
  public ConsentRequestParameters(@NonNull Builder builder) {
    this.tagForUnderAgeOfConsent = builder.tagForUnderAgeOfConsent;
    this.adMobAppId = builder.adMobAppId;
    this.consentDebugSettings = builder.consentDebugSettings;
  }

  public static final class Builder {
    private boolean tagForUnderAgeOfConsent;
    @Nullable
    private String adMobAppId;
    @Nullable
    private ConsentDebugSettings consentDebugSettings;

    @KeepForSdk
    @NonNull
    public Builder setAdMobAppId(@Nullable String adMobAppId) {
      this.adMobAppId = adMobAppId;
      return this;
    }

    @NonNull
    public Builder setConsentDebugSettings(@Nullable ConsentDebugSettings consentDebugSettings) {
      this.consentDebugSettings = consentDebugSettings;
      return this;
    }

    @NonNull
    public Builder setTagForUnderAgeOfConsent(boolean tagForUnderAgeOfConsent) {
      this.tagForUnderAgeOfConsent = tagForUnderAgeOfConsent;
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
