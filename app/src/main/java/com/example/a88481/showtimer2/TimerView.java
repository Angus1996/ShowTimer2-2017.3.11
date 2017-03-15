package com.example.a88481.showtimer2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.Timer;

/**
 * Created by 88481 on 2017/3/15 0015.
 */

public class TimerView extends View {

    private VisualTimer timer = new VisualTimer();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {

            switch (message.what){
                case 1:
                    invalidate();
                    break;
            }
        }
    };
    private  TimerThread thread = new TimerThread();

    class TimerThread extends Thread{
        @Override
        public void run() {
            super.run();

            while (true) {
                try {
                    sleep(1000);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public TimerView(Context context) {
        super(context);

        this.thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect frame = new Rect();
        frame.left = this.getLeft();
        frame.right = this.getRight();
        frame.top = this.getTop();
        frame.bottom = this.getBottom();
        this.timer.setFrame(frame);
        this.timer.draw(canvas);

        Rect rectSmall = new Rect(10,100,400,400);
        VisualTimer timerSmall = new VisualTimer();
        timerSmall.setFrame(rectSmall);
        timerSmall.draw(canvas);

        Rect rectSmall1 = new Rect(600,100,400,400);
        VisualTimer timerSmall1 = new VisualTimer();
        timerSmall1.setFrame(rectSmall1);
        timerSmall1.draw(canvas);


    }
}
