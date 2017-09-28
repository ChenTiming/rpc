package server;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Constant.Constants;
import param.RequestParam;

/**
 * Created by chentm on 2017/9/25.
 */
public class ServerCenter {

    private static ExecutorService executor = Executors.newFixedThreadPool(Constants.THREAD_POOL_SIZE);
    static Map<String,Class> registryServer = new HashMap<String, Class>();

    public static int port;
    public static InetAddress hostAddress;

    private Selector selector = null;

    public ServerCenter(int port){

        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        selector = initSelector();
        executor.submit(new ServerTask(selector));
    }

    public void stop(){
        executor.shutdown();
    }

    //服务注册
    public void registerServer(Class interfaceClass,Class imp){
        registryServer.put(interfaceClass.getName(),imp);
    }

    private static class ServerTask implements Runnable{

        public static Selector selector = null;

        public ServerTask(Selector selector){
            this.selector = selector;
        }
        public void run() {
            //循环接收客户端的请求
            while (true){
                try {
                    selector.select();
                    Iterator<SelectionKey> items = selector.selectedKeys().iterator();
                    while (items.hasNext()){
                        SelectionKey selectionKey = items.next();
                        items.remove();
                        if(!selectionKey.isValid()){
                            continue;
                        }

                        executor.submit(new HandlerTask(selectionKey));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Selector initSelector() throws IOException {
        //create a selector
        Selector selector = SelectorProvider.provider().openSelector();
        //open a serverSocket channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(hostAddress,port));
        serverSocketChannel.configureBlocking(false);

        //register serverSocket channel to selector
        SelectionKey selectionKey = serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        return selector;
    }


}
