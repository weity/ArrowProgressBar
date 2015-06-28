package com.jiozuo.arrowprogressbar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    protected ArrowPb mArrowPb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mArrowPb = (ArrowPb) findViewById(R.id.arrow);

        findViewById(R.id.sub).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int curProgress = mArrowPb.getProgress();
        int offset = 5;
        switch (id) {
            case R.id.sub:
                offset *= -1;
                break;
            case R.id.add:
                break;
        }
        Log.i("","--progress->"+curProgress);
        mArrowPb.setProgress(curProgress + offset);
    }

}
