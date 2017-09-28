package server;

import param.RequestParam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by chentm on 2017/9/26.
 */
public class HandlerTask implements Runnable{

    private SelectionKey selectionKey;
    private ServerSocketChannel serverSocketChannel;

    public HandlerTask(SelectionKey selectionKey,ServerSocketChannel serverSocketChannel) throws IOException {
        this.selectionKey = selectionKey;
        Selector selector = selectionKey.selector();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.register(selector,SelectionKey.OP_READ);

    }

    public void run() {
        ObjectInputStream inputStream = null;
        SocketChannel socketChannel;
        try {



        } finally {

        }

    }
}
