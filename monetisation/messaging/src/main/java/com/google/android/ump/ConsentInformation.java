package com.google.android.ump;

import android.app.Activity;
import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ConsentInformation {
    int getConsentStatus();

    @NonNull
    PrivacyOptionsRequirementStatus getPrivacyOptionsRequirementStatus();

    void requestConsentInfoUpdate(@NonNull Activity var1, @NonNull ConsentRequestParameters var2, @NonNull OnConsentInfoUpdateSuccessListener var3, @NonNull OnConsentInfoUpdateFailureListener var4);

    void reset();

    boolean canRequestAds();

    boolean isConsentFormAvailable();

    public interface OnConsentInfoUpdateFailureListener {
        void onConsentInfoUpdateFailure(@NonNull FormError var1);
    }

    public interface OnConsentInfoUpdateSuccessListener {
        void onConsentInfoUpdateSuccess();
    }

    public static enum PrivacyOptionsRequirementStatus {
        @NonNull
        UNKNOWN,
        @NonNull
        NOT_REQUIRED,
        @NonNull
        REQUIRED;

        private PrivacyOptionsRequirementStatus() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConsentStatus {
        int UNKNOWN = 0;
        int NOT_REQUIRED = 1;
        int REQUIRED = 2;
        int OBTAINED = 3;
    }
}
