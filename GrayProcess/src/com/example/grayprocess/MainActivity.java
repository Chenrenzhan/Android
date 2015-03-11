/*package com.example.grayprocess;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
*/


package com.example.grayprocess;  
  
import org.opencv.android.BaseLoaderCallback;  
import org.opencv.android.LoaderCallbackInterface;  
import org.opencv.android.OpenCVLoader;  
import org.opencv.android.Utils;  
import org.opencv.core.Mat;  
import org.opencv.imgproc.Imgproc;  
  



import android.view.Menu;
import android.os.Bundle;  
import android.app.Activity;  
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.util.Log;

public class MainActivity extends Activity implements OnClickListener{  
  
    private Button btnProc;  
    private ImageView imageView;  
    private Bitmap bmp;  
      
    //OpenCV�����ز���ʼ���ɹ���Ļص��������ڴ����ǲ������κβ���  
    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {  
        @Override  
        public void onManagerConnected(int status) {  
            switch (status) {  
                case LoaderCallbackInterface.SUCCESS:{  
                } break;  
                default:{  
                    super.onManagerConnected(status);  
                } break;  
            }  
        }  
    };  
      
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        btnProc = (Button) findViewById(R.id.btn_gray_process);
        imageView = (ImageView) findViewById(R.id.image_view);  
        //��lennaͼ����س����в�������ʾ  
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.lenna);  
        imageView.setImageBitmap(bmp);  
       // Utils.bitmapToMat(bmp, mat); 
        //mat = new Mat();
        btnProc.setOnClickListener(this);  
    }  
  
    private Mat mat;
    
    @Override  
    public void onClick(View v) {  
        Mat rgbMat = new Mat();  
        Mat grayMat = new Mat();  
        //mat = new Mat();
        //��ȡlena��ɫͼ������Ӧ����������  
        Utils.bitmapToMat(bmp, rgbMat); 
        Utils.bitmapToMat(bmp, grayMat); 
       if(mat.empty())
        {
        	 Utils.bitmapToMat(bmp, mat); 
        	 Log.v("debug", "channel0.toString()");
        }
        Integer channel0 = mat.channels();
        Log.v("debug", channel0.toString());
        //�ж���Ƭ�Ƿ�Ϊ�Ҷ�ͼ��
        if(mat.channels() == 1)
        {
        	imageView.setImageBitmap(bmp);  
        	Integer channel = mat.channels();
        	//JOptionPane.showMessageDialog("huise");
        	//Log.v("debug", channel.toString());
        }
        else
        {
	        //����ɫͼ������ת��Ϊ�Ҷ�ͼ�����ݲ��洢��grayMat��  
	        Imgproc.cvtColor(rgbMat, grayMat, Imgproc.COLOR_RGB2GRAY);  
	        //����һ���Ҷ�ͼ��  
	        Bitmap grayBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Config.RGB_565);  
	        //������grayMatת��Ϊ�Ҷ�ͼ��  
	        Utils.matToBitmap(grayMat, grayBmp);  
	        imageView.setImageBitmap(grayBmp);  
	        
	        mat = grayMat;
        }
    }  
      
    @Override  
    public void onResume(){  
        super.onResume();  
        //ͨ��OpenCV���������ز���ʼ��OpenCV��⣬��νOpenCV���������  
        //OpenCV_2.4.3.2_Manager_2.4_*.apk�������������OpenCV��װ����apkĿ¼��  
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);  
    }  
}
