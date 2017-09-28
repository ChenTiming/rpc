package ClientTest;

import service.HelloService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {
 
    public static void main(String[] args) throws IOException {

//        HelloService service = RPCClient.getRemoteProxyObj(service.HelloService.class, new InetSocketAddress("localhost", 8899));
//        //service.sayHi("test");
//        System.out.println(service.sayHi("test"));
        for(int i = 0; i < 3; i++){
            Thread t = new Thread(new Runnable() {
                public void run() {
                    HelloService service = RPCClient.getRemoteProxyObj(service.HelloService.class, new InetSocketAddress("localhost", 8899));
                    System.out.println(service.sayHi("test"));
                }
            });
            t.start();
        }
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}