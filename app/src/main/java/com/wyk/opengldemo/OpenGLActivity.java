package com.wyk.opengldemo;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLActivity extends AppCompatActivity {

    private static double glVersion = 3.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        GLSurfaceView view = new GLSurfaceView(this);
        view.setRenderer(new OpenGLRenderer());
        setContentView(view);
    }


    private class OpenGLRenderer implements GLSurfaceView.Renderer {

        @Override
            public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

            gl10.glClearColor(1.0f,0.0f,0.0f,0.0f);  // 设置背景颜色（rgba），取值范围是0-1
            gl10.glShadeModel(GL10.GL_SMOOTH);  // 选择恒定(GL10.GL_FLAT)或者光滑着色模式(GL10.GL_SMOOTH)
            gl10.glClearDepthf(1.0f); // 指明深度缓冲区的清理值，并通过glClear()方法清理深度缓冲区，取值范围为0-1，初始值为1
            gl10.glEnable(GL10.GL_DEPTH_TEST); // 启用服务端的GL功能。  GL10.GL_DEPTH_TEST：如果启用，做深度比较和更新深度缓存
            gl10.glDepthFunc(GL10.GL_LEQUAL); //
            gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST); // 控制GL某些行为。
                                                                             // GL_NICEST：选择正确或者质量最好的选项。
                                                                             // GL_PERSPECTIVE_CORRECTION_HINT：表明颜色和纹理坐标插值的效果
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int width, int height) {

            gl10.glViewport(0,0,width,height); // 设置一个视口
                                                /*x——指明视口矩形的左下角ｘ坐标，初始值为0。
                                                  y——指明视口矩形的左下角ｙ坐标，初始值为0。
                                                  width——指明视口的宽，如果ＧＬ上下文首次附于一个surface则宽、高为这个surface大小。
                                                  height——指明视口的高，如果ＧＬ上下文首次附于一个surface则宽、高为这个surface大小。*/
            gl10.glMatrixMode(GL10.GL_PROJECTION); // 设置当前矩阵模式
                                                    // GL_PROJECTION——应用投射矩阵堆的后续矩阵操作。
            gl10.glLoadIdentity();  // 用特征矩阵代替当前矩阵。
            GLU.gluPerspective(gl10,45.0f,(float)width / (float)height,0.1f,100.0f); // Set up a perspective projection matrix

                    /*Parameters
                    gl  a GL10 interface
                    fovy  specifies the field of view angle, in degrees, in the Y direction.
                    aspect  specifies the aspect ration that determins the field of view in the x direction. The aspect ratio is the ratio of x (width) to y (height).
                    zNear  specifies the distance from the viewer to the near clipping plane (always positive).
                    zFar  specifies the distance from the viewer to the far clipping plane (always positive).*/

            gl10.glMatrixMode(GL10.GL_MODELVIEW); // 应用视图矩阵堆的后续矩阵操作。
            gl10.glLoadIdentity();

        }

        @Override
        public void onDrawFrame(GL10 gl10) {

            gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // 清理缓冲区，并设置为预设值。
                                                                               // GL_COLOR_BUFFER_BIT:表明颜色缓冲区
                                                                               // GL_DEPTH_BUFFER_BIT：表明深度缓冲区
        }
    }
}
