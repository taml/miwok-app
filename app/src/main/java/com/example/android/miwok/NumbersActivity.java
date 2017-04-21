package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };


    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> numbersWords = new ArrayList<Word>();
        numbersWords.add(new Word ("one", "lutti", R.drawable.number_one, R.raw.number_one));
        numbersWords.add(new Word ("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numbersWords.add(new Word ("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numbersWords.add(new Word ("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbersWords.add(new Word ("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbersWords.add(new Word ("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbersWords.add(new Word ("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbersWords.add(new Word ("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbersWords.add(new Word ("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbersWords.add(new Word ("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

//        for(int i = 0; i < numbersWords.size(); i++) {
//            TextView wordView = new TextView(this);
//            wordView.setText(numbersWords.get(i));
//            rootView.addView(wordView);
//        }

        WordAdapter adapter = new WordAdapter(this, numbersWords, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long id)
            {
                releaseMediaPlayer();
                Word word = numbersWords.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getSoundResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private final void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
