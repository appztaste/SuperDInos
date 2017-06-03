package com.drm.superdinos;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity {
    public static final int[] DINOS = {R.drawable.ankylo, R.drawable.argentino, R.drawable.bronto,
            R.drawable.pentacera, R.drawable.ptero, R.drawable.raptors, R.drawable.spino, R.drawable.stego
            , R.drawable.trex, R.drawable.tricera};
    public static final int[] SUPER_DINOS = {R.drawable.ankylo_m, R.drawable.argentino_m, R.drawable.bronto_m,
            R.drawable.pentacera_m, R.drawable.ptero_m, R.drawable.raptors_m, R.drawable.spino_m, R.drawable.stego_m,
            R.drawable.trex_m, R.drawable.tricera_m};
    public static final String DINO_IDX_KEY = "DINO_IDX_KEY";
    private DinoCollectionPagerAdapter dinoCollectionPagerAdapter;
    private ViewPager viewPager;

    public static Map<Integer, String> dinoNames = new HashMap<>();
    public static Map<Integer, Integer> dinoRoars = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dinoCollectionPagerAdapter = new DinoCollectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(dinoCollectionPagerAdapter);

        populateDinoNames();
        populateDinoRoars();
    }

    private void populateDinoRoars() {
        dinoRoars.put(R.drawable.ankylo, R.raw.ankylo);
        dinoRoars.put(R.drawable.argentino, R.raw.argentino);
        dinoRoars.put(R.drawable.bronto, R.raw.bronto);
        dinoRoars.put(R.drawable.pentacera, R.raw.pentacera);
        dinoRoars.put(R.drawable.ptero, R.raw.ptero);
        dinoRoars.put(R.drawable.raptors, R.raw.raptor);
        dinoRoars.put(R.drawable.spino, R.raw.spino);
        dinoRoars.put(R.drawable.stego, R.raw.stego);
        dinoRoars.put(R.drawable.trex, R.raw.trex);
        dinoRoars.put(R.drawable.tricera, R.raw.tricera);
    }

    /**
     * Populate dinosaurs' names
     */
    private void populateDinoNames() {
        dinoNames.put(R.drawable.ankylo, "ANKYLOSAURUS");
        dinoNames.put(R.drawable.argentino, "ARGENTINOSAURUS");
        dinoNames.put(R.drawable.bronto, "BRONTOSAURUS");
        dinoNames.put(R.drawable.pentacera, "PENTACERATOPS");
        dinoNames.put(R.drawable.ptero, "PTERODACTYL");
        dinoNames.put(R.drawable.raptors, "VELOCIRAPTOR");
        dinoNames.put(R.drawable.stego, "STEGOSAURUS");
        dinoNames.put(R.drawable.tricera, "TRICERATOPS");
        dinoNames.put(R.drawable.trex, "TYRANNOSAURUS");
        dinoNames.put(R.drawable.spino, "SPINOSAURUS");
    }
}
