package com.restaurant.restaurant.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;


/**
 * @version v1.0
 * @ClassName: GetHttpSessionConfig
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //http和websocket是两种不同的协议 一开始服务器和客户端是通过http通信的，之后由客户端发起websocket升级请求，第一次握手后转变成了websocket，转变前可以存下关于http协议的内容
        // 而session和servletcontext都是关于http的，这个时候就能够存入配置中，之后在websocket中再取出来。
        if (request.getHttpSession() == null){
            System.out.println("session为空");
        }
        System.out.println("nmsl");
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        sec.getUserProperties().put("httpSession", httpSession);
        ServletContext servletContext = ((HttpSession) request.getHttpSession()).getServletContext();
        sec.getUserProperties().put("servletContext", servletContext);
    }
}
