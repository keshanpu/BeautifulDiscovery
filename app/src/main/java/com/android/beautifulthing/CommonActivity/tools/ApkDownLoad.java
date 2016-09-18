package com.android.beautifulthing.CommonActivity.tools;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.android.beautifulthing.R;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ydy on 2016/9/18
 */
public class ApkDownLoad {

    public static Context mContext;

    public ApkDownLoad(Context mContext) {
        this.mContext = mContext;
    }

    Handler mDownHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            install();
        }
    };

    //安装apk
    public void install(){
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(externalStoragePublicDirectory + "/zuimei.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

    //下载apk
    public void apkDownload(final String path){
        final File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(externalStoragePublicDirectory + "/zuimei.apk");
        if (file.exists()){
            Toast.makeText(mContext, "已经下载,马上开始安装", Toast.LENGTH_SHORT).show();
            install();//如果已经下载了，就安装即可
            return;
        }
        new Thread(new Runnable() {
            private double totalLength;
            private double downloadLength;
            @Override
            public void run() {
                InputStream inputStream = null;
                FileOutputStream fileOutputStream = null;
                try {
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    totalLength = urlConnection.getContentLength();
                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        fileOutputStream = new FileOutputStream(externalStoragePublicDirectory + "/zuimei.apk");
                        inputStream = urlConnection.getInputStream();
                        int len = 0;
                        byte[] buffer = new byte[2048];
                        while ((len = inputStream.read(buffer)) != -1){
                            fileOutputStream.write(buffer, 0, len);
                        }
                        fileOutputStream.flush();
                        Message message = mDownHandler.obtainMessage();
                        message.what = 1;
                        message.sendToTarget();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(inputStream);
                    close(fileOutputStream);
                }
            }
        }).start();
    }

    private void close(Closeable closeable){
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
