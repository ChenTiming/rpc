package server;

import param.RequestParam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by chentm on 2017/9/26.
 */
public class HandlerTask implements Runnable{

    private Socket connection;

    public HandlerTask(Socket socket){
        this.connection = socket;
    }

    public void run() {
        ObjectInputStream inputStream = null;
        try {
            Thread.sleep(10);
            inputStream = new ObjectInputStream(connection.getInputStream());
            Object objectIn = inputStream.readObject();
            RequestParam requestParam = (RequestParam)objectIn;

            Class serviceClass = ServerCenter.registryServer.get(requestParam.getService());
            Method method = serviceClass.getMethod(requestParam.getMethod(),requestParam.getArgumentsTypes());

            Object result = method.invoke(serviceClass.newInstance(),requestParam.getArguments());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
            objectOutputStream.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
