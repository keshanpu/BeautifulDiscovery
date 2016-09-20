package com.android.beautifulthing.MagazineFragment.tools.html;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Html;
import android.util.DisplayMetrics;

import com.android.beautifulthing.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by keshanpu on 16/9/12.
 */
public class GetImgFromHtml implements Html.ImageGetter {

        private Context context;
        private int height;
        private int width;

        public GetImgFromHtml(Context context) {
            this.context = context;
        }

        @Override
        public Drawable getDrawable(String source) {
//            Log.d("dd", "getDrawable: "+source);
//kesp
            //
            //解析图片的大小;
            //http://dstatic.zuimeia.com/media/article/image/2016/9/1/0ac3-73fc1bcaaf68_800x526.jpeg
            //取800X356px;
            String substring = source.substring(source.indexOf('_')+1, source.lastIndexOf('.'));
//            Log.d("eedd", "getDrawable: "+substring);//得到800X526
            String[] split = substring.split("x");

//            for(int i = 0; i < split.length; i++){
//                Log.d("ffxx", "getDrawable: "+split[i]);
//            }
//            Log.d("ffxx", "getDrawable: ------");
            width = Integer.valueOf(split[0]);
            height = Integer.valueOf(split[1]);
//            Log.d("hhgg", "getDrawable: "+width+" X "+height);
            //获取当前手机屏幕宽高;
            Resources resources = context.getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
//            float density1 = dm.density;
            int width3 = dm.widthPixels;
//            int height3 = dm.heightPixels;
            if(width < width3){
                int temp = width;
                width = width3;//宽度始终设为屏幕宽度
                //高度;
                height += (width3-temp);
            }
            //over kesp

            // 将source进行MD5加密并保存至本地
            String imageName = getMD5Str(source);
            String sdcardPath = Environment.getExternalStorageDirectory()
                    .toString(); // 获取SDCARD的路径

            // 获取图片后缀名
            String[] ss = source.split("\\.");
            String ext = ss[ss.length - 1];


            // 最终图片保持的地址
            String savePath = sdcardPath + "/" + context.getPackageName() + "/"
                    + imageName + "." + ext;

            File file = new File(savePath);
            if (file.exists()) {
                // 如果文件已经存在，直接返回
                Drawable drawable = Drawable.createFromPath(savePath);
                //有可能创建失败,所以需要加非空判断;
                if (drawable != null){
                    drawable.setBounds(0, 0, width,
                            height);
                    return drawable;
                }

            }

            // 不存在文件时返回默认图片，并异步加载网络图片
            Resources res = context.getResources();
            //默认图片;
            URLDrawable drawable = new URLDrawable(res.getDrawable(R.mipmap.ic_launcher));

            new ImageAsync(drawable).execute(savePath, source);

            return drawable;

        }

        private class ImageAsync extends AsyncTask<String, Integer, Drawable> {

            private URLDrawable drawable;

            public ImageAsync(URLDrawable drawable) {
                this.drawable = drawable;
            }

            @Override
            protected Drawable doInBackground(String... params) {
                // TODO Auto-generated method stub
                String savePath = params[0];
                String url = params[1];

                InputStream in = null;
                try {
                    // 获取网络图片
                    URL myurl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();
                    connection.connect();
                    if(connection.getResponseCode() ==HttpURLConnection.HTTP_OK){
                        in = connection.getInputStream();


                        File file = new File(savePath);
                        //savepath
                        String basePath = file.getParent();
                        File basePathFile = new File(basePath);
                        if (!basePathFile.exists()) {
                            //这里如果多次执行mkdirs说明,你上次就创建不成功,最后找到原因是手机系统没有给应用程序设置存储权限;
                            basePathFile.mkdirs();
                        }
                        file.createNewFile();
                        FileOutputStream out = new FileOutputStream(file);
//                        FileOutputStream out = new FileOutputStream(getFilePath(savePath));
                        byte[] buffer = new byte[1024];
                        int len = 0;
                        while ((len = in.read(buffer)) != -1) {
                            out.write(buffer,0,len);
                        }
                        out.flush();

                        Drawable mDrawable = Drawable.createFromPath(savePath);
                        return mDrawable;
                    }

                } catch (Exception e) {
                    try {
                        if (in != null)
                            in.close();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
                }
                return drawable;
            }

            @Override
            protected void onPostExecute(Drawable result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                if (result != null) {
                    drawable.setDrawable(result);
                    //这块不需要;
//                    tv.setText(tv.getText()); // 通过这里的重新设置 TextView 的文字来更新UI
                }
            }
        }

        public class URLDrawable extends BitmapDrawable {

            private Drawable drawable;

            public URLDrawable(Drawable defaultDraw) {
                setDrawable(defaultDraw);
            }

            private void setDrawable(Drawable nDrawable) {
                drawable = nDrawable;

                //设置图片大小
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
//                        drawable.getIntrinsicHeight());
                drawable.setBounds(0, 0, width,
                        height);
                setBounds(0, 0, width,
                        height);

            }

            @Override
            public void draw(Canvas canvas) {
                // TODO Auto-generated method stub
                drawable.draw(canvas);
            }

        }


    /*
    * MD5加密
    */
    private String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        //16位加密，从第9位到25位
        return md5StrBuff.substring(8, 24).toString().toUpperCase();
    }
}
