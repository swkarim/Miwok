package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fazal on 3/27/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int backgroundColor;

    public WordAdapter(Activity context, ArrayList<Word> words, int inputBackgroundColor){
        super(context, 0, words);
        backgroundColor = inputBackgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);
        TextView miwokWord = (TextView) listItemView.findViewById(R.id.miwokTextView);
        miwokWord.setText(currentWord.getmMiwokTranslation());

        TextView defaultWord = (TextView) listItemView.findViewById(R.id.defaultTextView);
        defaultWord.setText(currentWord.getmDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentWord.getmImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        //Find the LinearLayout to which we need to modify the background color
        View wordContainer = listItemView.findViewById(R.id.word_container_layout);
        int color = ContextCompat.getColor(getContext(), backgroundColor);
        wordContainer.setBackgroundColor(color);

        //Find the other LinearLayout (The one that contains the button) and
        //modify the background color for that as well.

        View buttonContainer = listItemView.findViewById(R.id.play_button_container);
        buttonContainer.setBackgroundColor(color);

        return listItemView;
    }
}
