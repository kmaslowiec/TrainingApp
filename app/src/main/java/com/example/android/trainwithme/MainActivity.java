package com.example.android.trainwithme;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
    TextView txt;
    boolean flag = true;

    int i = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // below hides the status bar but the class has to be changed to Activity !!!
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);


    }

    public void timerLeftTop(View view) {


        if (flag) {
            txt = findViewById(R.id.timer_left_top);
            final CounterClass time = new CounterClass(30000, 1000, R.id.set_top_left, R.id.button_left_top);
            time.start();


            flag = false;
        }


    }

    public void timerRightTop(View view) {
        if (flag ) {
            txt = findViewById(R.id.timer_right_top);
            final CounterClass time = new CounterClass(30000, 1000, R.id.set_top_right, R.id.button_right_top);
            time.start();
            flag = false;
        }

    }

    public void timerLeftBottom(View view) {
        if (flag ) {
            txt = findViewById(R.id.timer_left_bottom);
            final CounterClass time = new CounterClass(30000, 1000, R.id.set_bottom_left, R.id.button_left_bottom);
            time.start();
            flag = false;
        }

    }

    public void timerRightBottom(View view) {
        if (flag ) {
            txt = findViewById(R.id.timer_right_bottom);
            final CounterClass time = new CounterClass(30000, 1000, R.id.set_bottom_right, R.id.button_right_bottom);
            time.start();
            flag = false;
        }

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        private int view;
        private int button;


        CounterClass(long millisInFuture, long countDownInterval, int view, int button) {
            super(millisInFuture, countDownInterval);
            this.view = view;
            this.button = button;

        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NewApi")
        @Override
        public void onTick(long millisUntilFinished) {


            long millis = millisUntilFinished;


            String hms = String.format(Locale.CANADA, "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));


            System.out.println(hms);
            txt.setText(hms);
        }

        @Override
        public void onFinish() {


            TextView set = findViewById(view);
            Button butt = findViewById(button);

            if (i < 3) {
                i++;
                set.setText(String.format(Locale.CANADA, "%d/3", i));
                Log.i("MainActivity", "i is " + i);
            }
            if (i == 3) {
                set.setAllCaps(true);
                set.setTextColor(getResources().getColor(R.color.done_color));
                set.setText(getResources().getString(R.string.completed));
                butt.setClickable(false);
                i = 1;

            }

            txt.setText(getResources().getString(R.string.timer));

            flag = true;


        }


    }
}