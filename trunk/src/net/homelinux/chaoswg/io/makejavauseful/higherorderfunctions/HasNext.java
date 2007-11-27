package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.Iterator;

public final class HasNext extends AbstractUnaryPredicate<Iterator> {
    /*public final Boolean apply(Iterator arg) throws IllegalArgumentException {
        /*
        if (arguments.length == 1 && arguments[0] instanceof Iterator) {
            return apply ((Iterator)arguments[0]);
        }
        
        throw new IllegalArgumentException();*/
//        return arg.hasNext ();
    //}
    
    public final Boolean apply (Iterator iter) {
        return iter.hasNext();
    }
}
