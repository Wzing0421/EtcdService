package com.wang.server;

import java.io.IOException;
import java.net.*;
import java.util.List;

import com.wang.service.EtcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class udpServer {

    @Resource
    private EtcdService etcdService;

    public void run() throws Exception {
        DatagramSocket server = new DatagramSocket(22555);
        while (true){
            byte[] container = new byte[1024];
            DatagramPacket packet = new DatagramPacket(container, container.length);
            server.receive(packet);

            String ip = packet.getAddress().getHostAddress();
            int port = packet.getPort();
            String data = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Receive data from " + ip + " , port : " + port + " , Data is : " + data);

            List<String> strList = etcdService.getAllMultipleCastInfoFromEtcd();
            String res = "";
            if(strList.size() != 0){
                res += strList.get(0);
            }
            for(int i = 1; i < strList.size(); i++){
                res = res + "_" + strList.get(i);
            }
            System.out.println(res);
            DatagramPacket sendpacket = new DatagramPacket(res.getBytes(), res.getBytes().length, new InetSocketAddress(ip, port));
            server.send(sendpacket);
        }
    }
}
