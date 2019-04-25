package com.lucky.androidlearn.socket;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.lucky.customviewlearn.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*客户端代码*/
public class SocketIPClientActivity extends AppCompatActivity {

    private Socket mClientSocket;
    private PrintWriter mPrintWriter;
    @BindView(R.id.et_input)
    EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);
        ButterKnife.bind(this);

        new Thread(){
            @Override
            public void run() {
                super.run();
                connectTCPServer();
            }
        }.start();
    }

    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("127.0.0.1", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                System.out.println("connect server success!");
            } catch (IOException e) {
                SystemClock.sleep(1000*3);
                //e.printStackTrace();
                System.out.println("Connect tcp server failed, retry...");
            }
        }
        try {
            // 接受服务端的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!SocketIPClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                if (msg != null) {
                    System.out.println("msg from server " + msg);
                }
            }
            System.out.println("quit...");
            mPrintWriter.close();
            br.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btn_send)
    public void onSendMsgClick() {
        String msg = mEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(msg)) {
            if (mPrintWriter != null) {
                mPrintWriter.println(msg);
            }
        } else {
            Toast.makeText(this, "发送内容不能为空", Toast.LENGTH_SHORT).show();
        }
    }

}
