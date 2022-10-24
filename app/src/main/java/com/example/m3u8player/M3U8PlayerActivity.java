package com.example.m3u8player;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class M3U8PlayerActivity extends AppCompatActivity {
    private static final String urlStream = "http://113.163.216.23:1935/live/radio1.sdp_audio/playlist.m3u8";
    private PlayerControlView playerControlView;
    private ExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m3_u8_player);
        playerControlView = findViewById(R.id.player_view);
        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
    }

    private void initializePlayer() {
        player = new ExoPlayer.Builder(this)
                .build();
        playerControlView.setPlayer(player);

        // create media item
        Uri uri = Uri.parse(urlStream);
        MediaSource mediaSource = buildMediaSource(uri);
        player.setMediaSource(mediaSource);
        player.setPlayWhenReady(true);
        // prepare the player
        player.prepare();
        player.play();
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, "exoplayer-codelab");
        return new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(uri));
    }
}