package com.csy.tcp.chat.demo3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


/**
 * 项目名称：
 * 类名称:聊天室进入显示
 * 创建时间：2016年04月03日 下午17:13
 *
 * @author csypc
 * @version 1.0
 */
public class Server {
    //用来管理所有的客户端
    private List<MyChannel> all = new ArrayList<MyChannel>();

    public static void main(String[] args) throws IOException {
       new Server().start();
    }

    public void start() throws IOException {
        //创建服务器端，制定端口
        ServerSocket serverSocket = new ServerSocket(8889);
        //循环读取
        while(true) {
            //接收客户端连接、阻塞式的（获取到通信通道）
            Socket socket = serverSocket.accept();
            MyChannel myChannel = new MyChannel(socket);
            new Thread(myChannel).start();
            all.add(myChannel);//管理客户端
        }
    }

    /**
     *
     * 一个客户端，一条道路
     * 1.输入流
     * 2.输出流
     * 3.接收数据
     * 4.发送数据
     */
    private class MyChannel implements Runnable{

        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning = true;

        public MyChannel(Socket socket){

            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

            } catch (IOException e) {
                //e.printStackTrace();
                isRunning = false;
                CloseIOUtil.closeAll(dis,dos);
            }

        }

        //接收数据
        public String receive(){
            String msg = "";
            try {
                 msg = dis.readUTF();
            } catch (IOException e) {
                //e.printStackTrace();
                isRunning = false;
                CloseIOUtil.closeAll(dis);
                all.remove(this);//出现异常，移除自身
            }
            return msg;
        }

        //发送数据
        public void send(String msg){
            //若内容为空，则不发送
            if(msg == null || msg.equals("")){
                return ;
            }

            try {
                dos.writeUTF(msg);
            } catch (IOException e) {
                //e.printStackTrace();
                isRunning = false;
                CloseIOUtil.closeAll(dos);
                all.remove(this);//出现异常，移除自身
            }
        }

        //发送给其它客户端
        public void sendOther(){
            String msg = receive();
            for(MyChannel other : all){
                if(other == this){
                    continue;
                }
                other.send(msg);
            }
        }

        @Override
        public void run() {
            while(isRunning){
                sendOther();//转发给其它客户端
            }
        }
    }
 }
