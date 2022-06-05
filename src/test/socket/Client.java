package test.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luotao
 * @date 2022-6-5  20:04
 */
public class Client {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(5,5,60, TimeUnit.SECONDS,new LinkedBlockingQueue(10));

    public static void main(String[] args) {
        AtomicInteger sendCount = new AtomicInteger();
        while (true){
            try {
                Thread.sleep(200);
                pool.execute(new ClientMessagePublish("127.0.0.1",10086,sendCount.getAndIncrement()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
class ClientMessagePublish implements Runnable{

    String host;
    int port;
    int message;

    public ClientMessagePublish(String host,int port,int message){
        this.host = host;
        this.port = port;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(host,port);
            //向服务器发送消息
            OutputStream os = s.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端  "+message+"\n");
            bw.flush();

            //读取服务器返回的消息
            InputStream is = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String mess = br.readLine();
            System.out.println(mess);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
