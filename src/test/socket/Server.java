package test.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author luotao
 * @date 2022-6-5  19:50
 */
public class Server {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(5,5,60, TimeUnit.SECONDS,new LinkedBlockingQueue(10));


    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(10086);
            System.out.println("启动服务器....");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                // 监听客户端的连接
                Socket s = ss.accept();
                // 读取到客服端连接 交给线程进行处理
                pool.execute(new ScoketClientMsgHandler(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 服务端处理客户端消息
 */
class ScoketClientMsgHandler implements Runnable{

    Socket s;

    public ScoketClientMsgHandler(Socket s){
        this.s = s;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("收到客户端："+s.getLocalAddress()+"所发的消息为:"+mess);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write("服务端回复消息:"+mess+"\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
