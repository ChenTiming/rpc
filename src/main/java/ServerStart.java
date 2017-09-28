import server.ServerCenter;
import service.HelloImp;
import service.HelloService;

import java.io.IOException;

/**
 * Created by chentm on 2017/9/26.
 */
public class ServerStart {

    public static void main(String args[]){
        ServerCenter serverCenter = new ServerCenter(8899);
        try {
            serverCenter.registerServer(service.HelloService.class, service.HelloImp.class);
            serverCenter.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
