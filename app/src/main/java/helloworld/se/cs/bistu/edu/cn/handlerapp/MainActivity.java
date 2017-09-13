package helloworld.se.cs.bistu.edu.cn.handlerapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.textView);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                SeekBar seekBar= (SeekBar) findViewById(R.id.seekBar);
                seekBar.setProgress(msg.arg1);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        textView.setText("当前进度值:" + progress + "  / 100 ");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "触碰SeekBar", Toast.LENGTH_SHORT).show();
                        seekBar.setProgress(seekBar.getProgress());
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "放开SeekBar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress <= 100) {
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress += 10;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
                Button button = (Button) findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread thread = new Thread(null, runnable, "thread");
                        thread.start();
                    }
                });
            }

}


