package com.example.admin.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Admin on 8/18/2017.
 */

public class DownloadTask extends AsyncTask<String , Void, Pair<Bitmap, String>> {

    private Activity context;
    ProgressDialog dialog;
    private TextView txtText;
    private ImageView imgAva;

    public DownloadTask(Activity context, TextView txtText, ImageView imgAva){
        this.context = context;
        this.txtText = txtText;
        this.imgAva = imgAva;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog =  ProgressDialog.show( context, "Thông báo","Đang tải...");
    }

    @Override
    protected Pair<Bitmap, String> doInBackground(String... strings) {

        String img = strings[0];
        String text = strings[1];
        String title = "";
        try {
            title  = addText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = null;
        try {
            bitmap = addImg(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pair<Bitmap, String> pair = new Pair<>(bitmap, title);
        return pair ;
    }

    private Bitmap addImg(String img) throws IOException {
        Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(img).getContent());
        return bitmap;
    }

    private String addText(String text) throws IOException {

        Document document = (Document) Jsoup.connect(text).get();

        String title = document.title();

        return title;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(Pair<Bitmap, String> bitmapTextPair) {
        super.onPostExecute(bitmapTextPair);
        dialog.dismiss();
        txtText.setText(bitmapTextPair.second.toString());
        imgAva.setImageBitmap(bitmapTextPair.first);
    }
}
