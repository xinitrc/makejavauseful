package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.concurrent.Callable;

public abstract class AbstractConstantFunction<R> implements ConstantFunction<R> {
    public Callable<R> toCallable () {
        final ConstantFunction<R> thisOne = this; 
        
        final Callable<R> toReturn = new Callable<R> () {
            public final R call () {
                return thisOne.apply ();
            }
        };
        
        return toReturn;
    }
    
    public Class [] domain () {
        return null;
    }
    
    public Class range() {
        return null;
    }
}
