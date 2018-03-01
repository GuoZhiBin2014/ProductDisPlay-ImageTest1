package com.cambricon.productdisplay.activity;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.ImageReader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cambricon.productdisplay.R;
import com.cambricon.productdisplay.caffenative.CaffeDetection;
import com.cambricon.productdisplay.task.CNNListener;

import java.io.File;

/**
 * Created by dell on 18-1-30.
 */

public class DetectionActivity extends AppCompatActivity implements CNNListener{

    private static final SparseIntArray ORIENTATIONS =  new SparseIntArray();
    static {
        ORIENTATIONS.append( Surface.ROTATION_0,90 );
        ORIENTATIONS.append( Surface.ROTATION_90,0 );
        ORIENTATIONS.append( Surface.ROTATION_180,270 );
        ORIENTATIONS.append( Surface.ROTATION_270,180 );
    }

    private android.support.v7.widget.Toolbar toolbar;
    private CaffeDetection mCaffeDetection;
    private LinearLayout mLinearLayout;
    private Button mButton;
    private static String LOG_TAG="DetectionActivity";
    private CaffeDetection caffeDetection;

    //camera
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private ImageView mImageView;
    private CameraManager mCameraManager;
    private Handler childHandler,mainHandler;
    private String cameraID;
    private ImageReader mImageReader;
    private CameraCaptureSession mCaptureSession;
    private CameraDevice mCameraDevice;

    final File modelFile = new File(Environment.getExternalStorageDirectory(), "resnet101_train_agnostic.prototxt");
    final File weightFile = new File(Environment.getExternalStorageDirectory(), "resnet101_rfcn_final.caffemodel");
    File sdcard = Environment.getExternalStorageDirectory();
    String modelProto = sdcard + "/resnet101_train_agnostic.prototxt";
    String modelBinary = sdcard + "/resnet101_rfcn_final.caffemodel";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detection_layout);
        initView();
        setListener();
        setToolbar();
        openCamera();

//        initData();

    }
    //绑定监听事件
    private void setListener() {
        caffeDetection=new CaffeDetection();
        //caffeDetection.loadModel(modelProto,modelBinary);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    //打开相机
    private void openCamera() {
    }

    public void initView() {
        toolbar = findViewById(R.id.detection_toolbar);
        mLinearLayout = findViewById( R.id.detection_layout );
        mButton = findViewById( R.id.detection_but );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
    }

    public void initData() {

    }

    /**
     * toolBar返回按钮
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置toolbar属性
     */
    public void setToolbar() {
        toolbar.setTitle(R.string.gv_text_item2);
        setSupportActionBar(toolbar);
        /*显示Home图标*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onTaskCompleted(int result) {

    }

    private static class ORIENTATIONS {
    }

    private class CNNTask extends AsyncTask<String, Void, Integer> {
        private CNNListener listener;
        private long startTime;

        public CNNTask(CNNListener listener) {
            this.listener = listener;
        }

        @Override
        protected Integer doInBackground(String... strings) {
            startTime = SystemClock.uptimeMillis();
            //return caffeDetection.detectImage(strings[0])[0];
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Log.i(LOG_TAG, String.format("elapsed wall time: %d ms", SystemClock.uptimeMillis() - startTime));
            listener.onTaskCompleted(integer);
            super.onPostExecute(integer);
        }
    }
}
