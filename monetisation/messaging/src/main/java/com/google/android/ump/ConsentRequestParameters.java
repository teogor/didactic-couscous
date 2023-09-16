package com.google.android.ump;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.annotation.KeepForSdk;

/**
 * Represents the parameters used to request consent in the User Messaging Platform (UMP).
 */
public class ConsentRequestParameters {
  private final boolean tagForUnderAgeOfConsent;
  @Nullable
  private final String adMobAppId;
  @Nullable
  private final ConsentDebugSettings consentDebugSettings;

  /**
   * Gets the consent debug settings associated with these parameters.
   *
   * @return The consent debug settings.
   */
  @Nullable
  public ConsentDebugSettings getConsentDebugSettings() {
    return this.consentDebugSettings;
  }

  /**
   * Checks if the request is tagged for users under the age of consent.
   *
   * @return True if tagged for users under the age of consent, otherwise false.
   */
  public boolean isTagForUnderAgeOfConsent() {
    return this.tagForUnderAgeOfConsent;
  }

  /**
   * Gets the AdMob app ID associated with these parameters.
   * name - getAdMobAppId
   *
   * @return The AdMob app ID.
   */
  @Nullable
  public final String zza() {
    return this.adMobAppId;
  }

  /**
   * Constructs a new ConsentRequestParameters instance with the given builder.
   *
   * @param builder The builder containing the parameters.
   */
  public ConsentRequestParameters(@NonNull Builder builder) {
    this.tagForUnderAgeOfConsent = builder.tagForUnderAgeOfConsent;
    this.adMobAppId = builder.adMobAppId;
    this.consentDebugSettings = builder.consentDebugSettings;
  }

  /**
   * Builder class for creating ConsentRequestParameters instances.
   */
  public static final class Builder {
    private boolean tagForUnderAgeOfConsent;
    @Nullable
    private String adMobAppId;
    @Nullable
    private ConsentDebugSettings consentDebugSettings;

    /**
     * Sets the AdMob app ID for these parameters.
     *
     * @param adMobAppId The AdMob app ID.
     * @return The builder instance.
     */
    @KeepForSdk
    @NonNull
    public Builder setAdMobAppId(@Nullable String adMobAppId) {
      this.adMobAppId = adMobAppId;
      return this;
    }

    /**
     * Sets the consent debug settings for these parameters.
     *
     * @param consentDebugSettings The consent debug settings.
     * @return The builder instance.
     */
    @NonNull
    public Builder setConsentDebugSettings(@Nullable ConsentDebugSettings consentDebugSettings) {
      this.consentDebugSettings = consentDebugSettings;
      return this;
    }

    /**
     * Sets whether the request is tagged for users under the age of consent.
     *
     * @param tagForUnderAgeOfConsent True if tagged for users under the age of consent, otherwise false.
     * @return The builder instance.
     */
    @NonNull
    public Builder setTagForUnderAgeOfConsent(boolean tagForUnderAgeOfConsent) {
      this.tagForUnderAgeOfConsent = tagForUnderAgeOfConsent;
      return this;
    }

    /**
     * Builds a new ConsentRequestParameters instance based on the builder's settings.
     *
     * @return The constructed ConsentRequestParameters instance.
     */
    @NonNull
    public ConsentRequestParameters build() {
      return new ConsentRequestParameters(this);
    }

    /**
     * Constructs a new builder instance.
     */
    public Builder() {
    }
  }
}
