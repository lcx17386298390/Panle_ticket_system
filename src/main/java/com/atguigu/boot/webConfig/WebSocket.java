package com.atguigu.boot.webConfig;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.boot.bean.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/webSocket")
@Slf4j
@Component
public class WebSocket {

    private static int onlineCount = 0;
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    private Session session;
    private Object obj;

    @OnOpen
    public void onOpen(Session session) {
//        this.obj = ticket;
        this.session = session;
        WebSocket.onlineCount++;
        clients.put(obj.toString(), this);

    }

    @OnClose
    public void onClose() {
        clients.remove(obj);
        WebSocket.onlineCount--;
    }

    @OnMessage
    public void onMessage(String message) {}

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebSocket发生错误：" + throwable.getMessage());
    }

    public static void sendMessage(String message) {
        // 向所有连接websocket的客户端发送消息
        // 可以修改为对某个客户端发消息
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

}

