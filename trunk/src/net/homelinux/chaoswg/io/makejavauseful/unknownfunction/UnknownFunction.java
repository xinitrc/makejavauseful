package net.homelinux.chaoswg.io.makejavauseful.unknownfunction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
 * Inspired by the Smalltalk-feature which let's an object react to
 * a message for which there is no method implemented. This is really nifty 
 * 
 * TODO: Implement UnknownFunction
 */
public class UnknownFunction implements InvocationHandler  {
    
    /*
     * This object needs to implement some specific method in oder to be eligable 
     * to be of type. 
     * Let's say public Object iDonTKnowThisFuckingMethod (Object... args);
     */
    
    
    public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

}
