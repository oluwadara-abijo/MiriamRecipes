package com.example.dara.miriamrecipes.ui.detail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepVideoFragment extends Fragment {

    private SimpleExoPlayer mExoPlayer;

    //UI element
    @BindView(R.id.recipe_player_view)
    PlayerView mPlayerView;

    //Empty constructor
    public StepVideoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the fragment_recipe_step_video layout
        View rootView = inflater.inflate(R.layout.fragment_recipe_step_video, container, false);

        //Bind the text view
        ButterKnife.bind(this, rootView);

        //Current step
        Step mStep = ((ViewRecipeStepActivity) this.getActivity()).getStep();

        //Get the video url of the current step
        String videoUrl = mStep.getVideoUrl();
        //Initialize player if current step has a video
        if (!TextUtils.isEmpty(videoUrl)) {
            initializePlayer(Uri.parse(videoUrl));
        }

        // Return the rootView
        return rootView;
    }

    private void initializePlayer(Uri uri) {
        if (mExoPlayer == null) {

            RenderersFactory renderersFactory = new DefaultRenderersFactory(getContext());
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();

            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), renderersFactory, trackSelector, loadControl);

            mPlayerView.setPlayer(mExoPlayer);

            mExoPlayer.setPlayWhenReady(true);

            MediaSource mediaSource = new ExtractorMediaSource.Factory(
                    new DefaultHttpDataSourceFactory("baking-app")).createMediaSource(uri);

            mExoPlayer.prepare(mediaSource, true, false);
        }

    }

    //Release ExoPlayer.
    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}
