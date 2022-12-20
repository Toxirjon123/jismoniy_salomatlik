package com.example.jismoniysalomatlik.intro;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.widget.ViewPager2;

public class SliderTransforme implements ViewPager2.PageTransformer {
    int offScreenPageLimit;
    float DEFAULT_TRANSLATION_X = .0f;
    float DEFAULT_TRANSLATION_FACTOR = 1.2f;
    float SCALE_FACTOR = .14f;
    float DEFAULT_SCALE = 1f;
    float ALPHA_FACTOR = .3f;
    float DEFAULT_ALPHA = 1f;

    public SliderTransforme(int offScreenPageLimit) {
        this.offScreenPageLimit = offScreenPageLimit;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        ViewCompat.setElevation(page, -Math.abs(position));
        float scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE;
        float alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA;
//        if (position <= 0f) {
//            page.setTranslationX(DEFAULT_TRANSLATION_X);
//            page.setScaleX(DEFAULT_SCALE);
//            page.setScaleY(DEFAULT_SCALE);
//            page.setAlpha(DEFAULT_ALPHA + position);
//        } else if (position <= offScreenPageLimit - 1) {
//            page.setTranslationX(-(page.getWidth() / DEFAULT_TRANSLATION_FACTOR) * position);
//            page.setScaleX(scaleFactor);
//            page.setScaleY(scaleFactor);
//            page.setAlpha(alphaFactor);
//        } else {
//            page.setTranslationX(DEFAULT_TRANSLATION_X);
//            page.setScaleX(DEFAULT_SCALE);
//            page.setScaleY(DEFAULT_SCALE);
//            page.setAlpha(DEFAULT_ALPHA);
//        }
        if(position >= 0){
            page.setScaleX(0.7f - 0.05f * position);
            page.setScaleY(0.75f);
            page.setTranslationX(-page.getWidth() * position);
            page.setTranslationY( - 30  * position);
        }
    }
}
