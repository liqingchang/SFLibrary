package com.android.liqingchang.sf.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.liqingchang.sf.Cargo;
import com.android.liqingchang.sf.DefaultSFMethod;
import com.android.liqingchang.sf.Order;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cargo cargo = new Cargo("Iphone5");
        final Order order = new Order("TE2015072313", "广田智能", "李先生", "13760783748", "广东省", "深圳市", "南山区", "桂庙路向南花园A1",
                "顺丰速运", "王先生", "11111111111", "广东省", "中山市", "火炬开发区", "将为头11111",
                1, cargo
        );
        new Thread() {
            @Override
            public void run() {
                DefaultSFMethod sfClient = new DefaultSFMethod();
//                sfClient.order(order);
                sfClient.query(DefaultSFMethod.TYPE_ORDERNO, "TE20150104");
            }
        }.start();
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
}
