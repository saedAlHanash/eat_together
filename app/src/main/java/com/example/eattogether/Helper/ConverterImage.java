package com.example.eattogether.Helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ConverterImage {
    public static Bitmap convertBase64ToBitmap(String str) {
        byte[] decodedString = Base64.decode(str, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static String convertUriToBase64(Context context, Uri selectedFile) {
        Bitmap bitmap = null;
        String encodedString = null;
        if (selectedFile != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();

            encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        return encodedString;
    }
}
