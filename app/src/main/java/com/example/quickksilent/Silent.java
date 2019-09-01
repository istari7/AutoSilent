package com.example.quickksilent;


import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

public class Silent{

    private AudioManager mAudioManager;
    private boolean mPhoneIsSilent;

    Silent(Context context) {
        mAudioManager=(AudioManager)context.getSystemService(Activity.AUDIO_SERVICE);
        checkIfPhoneIsSilent();
        switchToSilentMode();
    }

    public void checkIfPhoneIsSilent() {

        int ringerMode = mAudioManager.getRingerMode();
        if (ringerMode == AudioManager.RINGER_MODE_SILENT)
            mPhoneIsSilent = true;
        else
            mPhoneIsSilent = false;
    }

    public void switchToSilentMode() {
        if(!mPhoneIsSilent)
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

}
