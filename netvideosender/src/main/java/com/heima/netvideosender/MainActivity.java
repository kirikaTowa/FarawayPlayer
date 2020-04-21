package com.heima.netvideosender;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onclick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);//和配置清单的action——view一致
        intent.setDataAndType(Uri.parse("http://47.98.147.90:8080/faraway.mp4"), "video/mp4");
        startActivity(intent);
    }

}
