package com.example.websocket.server;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;

/**
 * 采用注解方式
 */
@ServerEndpoint("/echo")
@Component
public class EchoServer {

    @OnMessage
    public String echo(String incomingMessage){
        String result = String
                .format("I got this ( %s ) so I am sending it back !", incomingMessage);
        System.out.println("echo reslut : " + result);
        return result;
    }

    @OnOpen
    public void openMessage(Session session) throws InterruptedException, IOException {

        for (int i = 0 ; i < 3 ; i ++){
            Thread.sleep(2000);
            session.getBasicRemote().sendText("后台发送的消息 : " + i);
        }
    }

    @OnClose
    public void closeMessage(Session session) throws IOException {
        session.getBasicRemote().sendText("服务器关闭");
    }

}
