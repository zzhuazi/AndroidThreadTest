package com.mycompany.androidthreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 异步消息处理机制
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int UPDATA_TEXT = 1;
    private TextView text;
    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case UPDATA_TEXT:
                    //进行ui操作
                    text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text);
        Button changeText = (Button)findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       /* text.setText("Nice to meet you");*/
                        Message message = new Message();
                        message.what = UPDATA_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }

}
