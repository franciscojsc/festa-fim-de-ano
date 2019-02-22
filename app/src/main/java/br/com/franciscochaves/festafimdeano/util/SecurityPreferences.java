package br.com.franciscochaves.festafimdeano.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private final SharedPreferences mSharePreferences;

    public SecurityPreferences(Context context) {
        this.mSharePreferences = context.getSharedPreferences("FimDeAno", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value) {
        this.mSharePreferences.edit().putString(key, value).apply();
    }

    public String getStoreString(String key) {
        return this.mSharePreferences.getString(key, "");
    }

}
