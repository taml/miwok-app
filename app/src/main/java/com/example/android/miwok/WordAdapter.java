package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> appWords, int colorResourceId) {
        super(context,0, appWords);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView defaultWordTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultWordTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokWordTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokWordTextView.setText(currentWord.getmMiwokTranslation());

        View itemLayoutView = listItemView.findViewById(R.id.item);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        itemLayoutView.setBackgroundColor(color);

        ImageView miwokImageView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            miwokImageView.setImageResource(currentWord.getImageResourceId());
            miwokImageView.setVisibility(View.VISIBLE);
        } else {
            miwokImageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
