package com.mimmarcelo.wifiserver;

import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;

public class MainActivity extends AppCompatActivity implements IHandler, View.OnClickListener {
    ServerSocket ss = null;
    Conexao conexao;
    public static final int SERVERPORT = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        conexao = new Conexao(SERVERPORT, getBaseContext(), this);
        conexao.start();

        Button btn = findViewById(R.id.btnSend);
        btn.setOnClickListener(this);

        TextView txt = findViewById(R.id.txtMensagem);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        txt.setText("Server IP Address: " + ipAddress);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
// make sure you close the socket upon exiting
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recebeMensagem(String mensagem) {
        TextView txt = (TextView)findViewById(R.id.txtMensagem);
        txt.setText(mensagem);
    }

    @Override
    public void onClick(View view) {
        conexao.enviarMensagem("1");
    }
}
