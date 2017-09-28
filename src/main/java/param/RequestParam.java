package param;

import java.io.Serializable;

/**
 * Created by chentm on 2017/9/25.
 */
public class RequestParam implements Serializable{
    String service;
    String method;
    Object[] arguments;
    Class<?>[] argumentsTypes;

    public void setService(String s){
        this.service = s;
    }

    public String getService(){
        return this.service;
    }

    public void setMethod(String method){
        this.method = method;
    }

    public String getMethod(){
        return this.method;
    }

    public void setArguments(Object[] arguments){
        this.arguments = arguments;
    }

    public Object[] getArguments(){
        return this.arguments;
    }

    public void setArgumentsTypes(Class<?>[] argumentsTypes) {
        this.argumentsTypes = argumentsTypes;
    }

    public Class<?>[] getArgumentsTypes() {
        return argumentsTypes;
    }
}
