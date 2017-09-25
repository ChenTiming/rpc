package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Constant.Constants;

/**
 * Created by chentm on 2017/9/25.
 */
public class ServerCenter {

    private ExecutorService executor = Executors.newFixedThreadPool(Constants.THREAD_POOL_SIZE);
    static Map<String,Class> registryServer = new HashMap<String, Class>();

    public static int port;

    public ServerCenter(int port){
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        executor.submit(new ServerTask(serverSocket));
    }

    //服务注册
    public void registerServer(Class interfaceClass,Class imp){
        registryServer.put(interfaceClass.getName(),imp);
    }

    private static class ServerTask implements Runnable{

        public static ServerSocket serverSocket = null;

        public ServerTask(ServerSocket serverSocket){
            this.serverSocket = serverSocket;
        }
        public void run() {
            //循环接收客户端的请求
            while (true){
                Socket connection =null;

                try {
                    connection = serverSocket.accept();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
