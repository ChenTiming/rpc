package service;

/**
 * Created by chentm on 2017/9/26.
 */
public class HelloImp implements HelloService{
    public String sayHi(String name) {
        return "Hello: " + name;
    }
}
