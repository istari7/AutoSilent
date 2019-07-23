package com.example.quickksilent;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

public class Normal{

    private AudioManager mAudioManager;
    private boolean mPhoneIsNormal;

    Normal(Context context) {
        mAudioManager=(AudioManager)context.getSystemService(Activity.AUDIO_SERVICE);
        checkIfPhoneIsNormal();
        switchToNormalMode();
    }

    public void checkIfPhoneIsNormal() {
        int ringerMode = mAudioManager.getRingerMode();
        if (ringerMode == AudioManager.RINGER_MODE_NORMAL)
            mPhoneIsNormal = true;
        else
            mPhoneIsNormal = false;
    }

    public void switchToNormalMode() {
        if(!mPhoneIsNormal)
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

}