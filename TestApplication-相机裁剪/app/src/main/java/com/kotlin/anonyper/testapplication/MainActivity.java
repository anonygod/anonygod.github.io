package com.kotlin.anonyper.testapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    View parent_view;
    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = this.findViewById(R.id.parent_view);
        text = this.findViewById(R.id.text);
        button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSelectPictureManager();
            }
        });
//

    }

    SelectPictureManager selectPictureManager;


    void initSelectPictureManager() {
        selectPictureManager = new SelectPictureManager(this);
        selectPictureManager.setPictureSelectListner(new SelectPictureManager.PictureSelectListner() {
            @Override
            public void onPictureSelect(String imagePath) {
                Log.i("TAG", "选择图片的路径是：" + imagePath);
                text.setText("选择图片的路径是：" + imagePath);
            }

            @Override
            public void throwError(Exception e) {
                e.printStackTrace();
            }
        });
        selectPictureManager.setNeedCrop(true);//需要裁剪
        selectPictureManager.setOutPutSize(400, 400);//输入尺寸
        selectPictureManager.setContinuous(true);//设置连拍
        selectPictureManager.showSelectPicturePopupWindow(parent_view);
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
