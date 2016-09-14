package cl.cutiko.asynctask;

import android.os.AsyncTask;

/**
 * Created by cutiko on 14-09-16.
 */
public class BackgroundCount extends AsyncTask<Integer, Integer, Integer> {

    @Override
    protected Integer doInBackground(Integer... integers) {
        for(Integer integer : integers) {
            try {
                Thread.sleep(1000);
                publishProgress(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
