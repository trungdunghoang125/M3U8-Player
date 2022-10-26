package com.example.m3u8player;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;

public class M3U8PlayerActivity extends AppCompatActivity {
    private static final String urlStream = "https://strm.voh.com.vn/radio/channel2/playlist.m3u8";
    private static final String urlStream2 = "http://antvlive.ab5c6921.cdnviet.com/antv/playlist.m3u8";
    private PlayerControlView playerView;
    private ExoPlayer player;
    private ProgressBar loading;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m3_u8_player);
        playerView = findViewById(R.id.player_view);
        loading = findViewById(R.id.loading);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
    }

    private void initializePlayer() {
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory();
        MediaItem mediaItem = new MediaItem.Builder()
                .setUri(urlStream)
                .setMimeType(MimeTypes.APPLICATION_M3U8)
                .build();
        HlsMediaSource hlsMediaSource = new HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mediaItem);

        MediaItem mediaItem2 = new MediaItem.Builder()
                .setUri(urlStream2)
                .setMimeType(MimeTypes.APPLICATION_M3U8)
                .build();
        HlsMediaSource hlsMediaSource2 = new HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mediaItem2);

        if (player == null) {
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
            trackSelector.setParameters(
                    trackSelector.buildUponParameters().setMaxVideoSizeSd());
            player = new ExoPlayer.Builder(this)
                    .setTrackSelector(trackSelector)
                    .build();
        }

        playerView.setPlayer(player);
        player.setMediaSource(hlsMediaSource);
        player.addMediaSource(hlsMediaSource2);
        player.prepare();
        player.setPlayWhenReady(true);

//        player = new ExoPlayer.Builder(this)
//                .build();
//        playerView.setPlayer(player);
//        Uri uri = Uri.parse(urlStream);
//        MediaItem mediaItem = MediaItem.fromUri(uri);
//        player.setMediaItem(mediaItem);
//        player.prepare();
//        player.setPlayWhenReady(true);
    }
}