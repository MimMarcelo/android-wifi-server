package com.mimmarcelo.wifiserver;

import android.content.Context;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Marcelo Júnior on 07/11/2017.
 */

public class Conexao extends Thread {
    int porta;
    ServerSocket ss;
    Socket socket;
    IHandler myUpdateHandler;
    Context context;
    OutputStream output;

    public Conexao(int porta, Context context, IHandler myUpdateHandler){
        this.porta = porta;
        this.context = context;
        this.myUpdateHandler = myUpdateHandler;
        this.output = null;
    }

    @Override
    public void run() {
        super.run();
        try{
            ss = new ServerSocket(this.porta);
            socket = null;
            if(ss != null){
                socket = ss.accept();
            }
            if(socket != null){
                output = socket.getOutputStream();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void enviarMensagem(String mensagem){
        byte[] data = mensagem.getBytes();

        if(output != null) {
            try {
                //TRANSMITE A MENSAGEM
                output.write(data);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
