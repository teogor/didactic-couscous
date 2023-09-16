package com.google.android.ump;

import android.app.Activity;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

public interface ConsentForm {
    void show(@NonNull Activity var1, @NonNull OnConsentFormDismissedListener var2);

    public interface OnConsentFormDismissedListener {
        void onConsentFormDismissed(@Nullable FormError var1);
    }
}
