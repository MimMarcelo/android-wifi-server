package com.mimmarcelo.wifiserver;

/**
 * Created by Marcelo Júnior on 07/11/2017.
 */

public interface IHandler {

    /**
     * RECEBE A MENSAGEM RECEBIDA ATRAVÉS DA THREAD DE CONEXÃO
     * @param mensagem MESAGEM RECEBIDA
     */
    void recebeMensagem(String mensagem);
}
