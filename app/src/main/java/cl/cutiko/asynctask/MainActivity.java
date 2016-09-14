package cl.cutiko.asynctask;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mainPb, backgroundPb;
    private int mainCounter = 0;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        mainPb = (ProgressBar) findViewById(R.id.mainPb);
        backgroundPb = (ProgressBar) findViewById(R.id.backgroundPb);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        setFab();
    }

    private void setFab(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainThreadCount();
                backgroundCount();
            }
        });
    }

    private void mainThreadCount(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainCounter++;
                mainPb.setProgress(mainCounter);
                if (mainCounter != 10) {
                    mainThreadCount();
                } else {
                    mainCounter = 0;
                }
            }
        }, 1000);
    }

    private void backgroundCount(){
        new BackgroundCounter().execute(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    private class BackgroundCounter extends BackgroundCount {

        @Override
        protected void onPreExecute() {
            fab.animate().translationY(2000).setDuration(400).start();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            backgroundPb.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            fab.animate().translationY(0).setDuration(600).start();
        }

    }
}
