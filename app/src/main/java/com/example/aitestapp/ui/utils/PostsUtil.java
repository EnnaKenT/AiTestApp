package com.example.aitestapp.ui.utils;

import android.content.Context;

import com.example.aitestapp.R;
import com.example.aitestapp.retrofit.api.models.post.Hit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PostsUtil {

    public static String getFormattedData(Hit hit, Context context) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyy HH:mm", Locale.getDefault());

        try {
            return context.getString(R.string.created_at_format, formatter.format(parser.parse(hit.getCreatedAt())));
        } catch (ParseException e) {
            return "";
        }
    }

}
