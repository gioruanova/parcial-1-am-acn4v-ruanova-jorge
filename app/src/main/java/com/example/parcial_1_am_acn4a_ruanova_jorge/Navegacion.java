package com.example.parcial_1_am_acn4a_ruanova_jorge;

import android.app.Activity;
import android.content.Intent;

import androidx.core.app.ActivityOptionsCompat;

// una clase random para volver al home
public class Navegacion {

    public static void desloguearUsuario(Activity currentActivity, Class<? extends Activity> targetActivity) {
        Intent intent = new Intent(currentActivity, targetActivity);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                currentActivity,
                R.anim.slide_in_left,
                R.anim.slide_out_right
        );
        currentActivity.startActivity(intent, options.toBundle());
    }


}
