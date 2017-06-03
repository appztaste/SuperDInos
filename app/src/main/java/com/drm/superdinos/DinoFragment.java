package com.drm.superdinos;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static com.drm.superdinos.MainActivity.*;

/**
 * Created by mmt6081 on 3/6/17.
 */

public class DinoFragment extends Fragment {

    private int curImgIdx = 0;
    private boolean isSuper = false;
    public AlphaAnimation fadeOut = new AlphaAnimation(1f, 0.01f);
    public AlphaAnimation fadeIn = new AlphaAnimation(0.01f, 1f);
    public ScaleAnimation scaleOut = new ScaleAnimation(1f, 5f, 1f, 5f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
    public ScaleAnimation scaleIn = new ScaleAnimation(5f, 1f, 5f, 1f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
    public AnimationSet outAnimationSet = new AnimationSet(false);
    public AnimationSet inAnimationSet = new AnimationSet(false);
    private int syncedAnimDuration = 600;
    private MediaPlayer mediaPlayer;
    private Context context;
    public ImageView dinoImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dino, container, false);
        Bundle args = getArguments();
        curImgIdx = args.getInt(MainActivity.DINO_IDX_KEY);

        final ImageView dinoImage = (ImageView)rootView.findViewById(R.id.imgDino);
        dinoImage.setImageResource(MainActivity.DINOS[curImgIdx]);

        final TextView dinoName = (TextView) rootView.findViewById(R.id.dinoName);
        dinoName.setText(dinoNames.get(MainActivity.DINOS[curImgIdx]));

        context = this.getContext();
        this.dinoImage = dinoImage;

        initializeMediaPlayer();
        prepareAnimation();

        dinoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dinoImage.startAnimation(outAnimationSet);
            }
        });

        return rootView;
    }

    /**
     * Initialize media player with listeners
     */
    private void initializeMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e("Dino", "Error: " + mp.getDuration() + ", " + what + ", " + extra);
                return false;
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d("Dino", "MP prepared");
                mediaPlayer.start();
            }
        });
    }

    /**
     * Animate imageView and associate image resource and names
     */
    private void prepareAnimation() {
        scaleOut.reset();
        scaleIn.reset();

        fadeIn.setDuration(syncedAnimDuration);
        fadeOut.setDuration(syncedAnimDuration);
        scaleOut.setDuration(syncedAnimDuration);
        scaleIn.setDuration(syncedAnimDuration);

        scaleOut.setFillBefore(false);
        scaleIn.setFillBefore(false);

        outAnimationSet.addAnimation(fadeOut);
        outAnimationSet.addAnimation(scaleOut);
        inAnimationSet.addAnimation(fadeIn);
        inAnimationSet.addAnimation(scaleIn);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                roar();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switchImage();
                dinoImage.startAnimation(inAnimationSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void roar() {
        mediaPlayer.reset();
        try {
            Uri roar = Uri.parse("android.resource://" + context.getPackageName() + "/" + dinoRoars.get(DINOS[curImgIdx]));
            mediaPlayer.setDataSource(context, roar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.prepareAsync();
    }

    private void switchImage() {
        if(!isSuper) {
            dinoImage.setImageResource(SUPER_DINOS[curImgIdx]);
            isSuper = true;
        } else {
            dinoImage.setImageResource(DINOS[curImgIdx]);
            isSuper = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
