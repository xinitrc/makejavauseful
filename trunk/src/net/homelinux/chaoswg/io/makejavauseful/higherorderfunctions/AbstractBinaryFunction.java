package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public abstract class AbstractBinaryFunction<R, I0, I1> implements BinaryFunction<R, I0, I1> {
    public UnaryFunction<R, I1> curry(final I0 arg0) {
        final BinaryFunction<R, I0, I1> thisOne = this;
        
        final AbstractUnaryFunction<R,I1> toReturn = new AbstractUnaryFunction<R,I1> () {
            public final R apply (final I1 arg1) {
                return thisOne.apply(arg0, arg1);
            }
        };
        return toReturn;
    }

    public ConstantFunction<R> curry(final I0 arg0, final I1 arg1) {
        final BinaryFunction<R, I0, I1> thisOne = this;
        
        final ConstantFunction<R> toReturn = new AbstractConstantFunction<R> () {
            public final R apply () {
                return thisOne.apply(arg0, arg1);
            }
        };
        return toReturn;
    }

    public List<R> mapcar(final List<? extends I0> list0, final List<? extends I1> list1) {
        final Iterator<? extends I0> iter0 = list0.iterator();
        final Iterator<? extends I1> iter1 = list1.iterator();
        
        final List<R> toReturn = new Vector<R> ();
        
        while (iter0.hasNext() && iter1.hasNext()) {
            toReturn.add (this.apply(iter0.next(), iter1.next()));
        }
        
        return toReturn;
    }

    public List<R> mapcar(final I0[] array0, final I1[] array1) {
        return this.mapcar(Arrays.asList(array0), Arrays.asList(array1));
    }

//    public List<R> maplist(List<I0> list0, List<I1> list1) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public List<R> maplist(I0[] array0, I1[] array1) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
    
    public UnaryFunction<R, I0> rcurry(final I1 arg1) {
        final BinaryFunction<R, I0, I1> thisOne = this;
        
        final AbstractUnaryFunction<R, I0> toReturn = new AbstractUnaryFunction<R, I0> () {
            public final R apply (final I0 arg0) {
                return thisOne.apply(arg0, arg1);
            }
        };
        return toReturn;
    }

    public ConstantFunction<R> rcurry(final I0 arg0, final I1 arg1) {
        final BinaryFunction<R, I0, I1> thisOne = this;
        
        final ConstantFunction<R> toReturn = new AbstractConstantFunction<R> () {
            public final R apply () {
                return thisOne.apply(arg0, arg1);
            }
        };
        return toReturn;
    }

    public R reduce(final List<? extends R> list) {
        final BinaryFunction<R, R, R> toApply = ((BinaryFunction<R, R, R>) this);
        
        final Iterator<? extends R> iterator = list.iterator();
        R toReturn = null;
        
        if (iterator.hasNext()) {
            R first = iterator.next();
            if (iterator.hasNext()) {
                R second = iterator.next();
                
                toReturn = ((R)toApply.apply(first, second));
                
                while (iterator.hasNext()) {
                    toReturn = ((R)toApply.apply (toReturn, iterator.next())); 
                }
                
                return toReturn; 
            }
        }
        
        return null;
    }

    public R reduce(final R... list) {
        return this.reduce (Arrays.asList(list));
    }

    public Class [] domain () {
        return null;
    }
    
    public Class range() {
        return null;
    }
}
