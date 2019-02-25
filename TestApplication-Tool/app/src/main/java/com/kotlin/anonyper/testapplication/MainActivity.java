package com.kotlin.anonyper.testapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kotlin.anonyper.testapplication.image.ImageConfig;
import com.kotlin.anonyper.testapplication.image.ImageLoadBaseTool;
import com.kotlin.anonyper.testapplication.image.ImageLoadProcessInterface;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    View parent_view;
    TextView text;
    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = this.findViewById(R.id.parent_view);
        text = this.findViewById(R.id.text);
        button = this.findViewById(R.id.button);
        imageView = this.findViewById(R.id.imageView);
        button.setOnClickListener(view -> initSelectPictureManager());//这是lambda的使用方式
//
    }

    SelectPictureManager selectPictureManager;

    void initSelectPictureManager() {

        selectPictureManager = new SelectPictureManager(this);
        selectPictureManager.setPictureSelectListner(new SelectPictureManager.PictureSelectListner() {
            @Override
            public void onPictureSelect(String imagePath) {
                LogUtil.i("TAG", "选择图片的路径是：" + imagePath);
                text.setText("选择图片的路径是：" + imagePath);
                showImage(imageView, imagePath);
            }

            @Override
            public void throwError(Exception e) {
                e.printStackTrace();
            }
        });
//        selectPictureManager.setNeedCrop(true);//需要裁剪
        selectPictureManager.setOutPutSize(400, 400);//输入尺寸
        selectPictureManager.setContinuous(true);//设置连拍
        selectPictureManager.showSelectPicturePopupWindow(parent_view);
    }

    /**
     * 展示图片
     *
     * @param path
     */
    void showImage(ImageView imageView, String path) {
//        path = "https://upload-images.jianshu.io/upload_images/5207488-9b7d8d755f83092b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp";
//        path = "file://"+new File(path).getPath();
        ImageLoadBaseTool.display(this, imageView, path, new ImageConfig(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, 25), new ImageLoadProcessInterface() {
            @Override
            public void onLoadStarted() {

            }

            @Override
            public void onResourceReady() {

            }

            @Override
            public void onLoadCleared() {

            }

            @Override
            public void onLoadFailed() {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectPictureManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        selectPictureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
