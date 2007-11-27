package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public abstract class AbstractQuintaryFunction<R, I0, I1, I2, I3, I4> implements QuintaryFunction<R, I0, I1, I2, I3, I4> {
    public QuadtaryFunction<R, I1, I2, I3, I4> curry(final I0 arg0) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;
        
        final QuadtaryFunction<R, I1, I2, I3, I4> toReturn = new AbstractQuadtaryFunction<R, I1, I2, I3, I4> () {
            public final R apply (final I1 arg1, final I2 arg2, final I3 arg3, final I4 arg4) {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
            }
        };
        return toReturn;
    }

    public TernaryFunction<R, I2, I3, I4> curry(final I0 arg0, final I1 arg1) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;

        final TernaryFunction<R, I2, I3, I4> toReturn = new AbstractTernaryFunction<R, I2, I3, I4> () {
            public final R apply (final I2 arg2, final I3 arg3, final I4 arg4) {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
            }
        };
        return toReturn;
    }

    public BinaryFunction<R, I3, I4> curry(final I0 arg0, final I1 arg1, final I2 arg2) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;

        final BinaryFunction<R, I3, I4> toReturn = new AbstractBinaryFunction<R, I3, I4> () {
            public final R apply (final I3 arg3, final I4 arg4) {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
            }
        };
        return toReturn;
    }

    public UnaryFunction<R, I4> curry(final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;

        final UnaryFunction<R, I4> toReturn = new AbstractUnaryFunction<R, I4> () {
            public final R apply (final I4 arg4) {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
            }
        };
        return toReturn;
    }

    public ConstantFunction<R> curry(final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3, final I4 arg4) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;

        final ConstantFunction<R> toReturn = new AbstractConstantFunction<R> () {
            public final R apply () {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
            }
        };
        return toReturn;
    }

    public List<R> mapcar(final List<? extends I0> list0, final List<? extends I1> list1, final List<? extends I2> list2, final List<? extends I3> list3, final List<? extends I4> list4) {
        final Iterator<? extends I0> iter0 = list0.iterator();
        final Iterator<? extends I1> iter1 = list1.iterator();
        final Iterator<? extends I2> iter2 = list2.iterator();
        final Iterator<? extends I3> iter3 = list3.iterator();
        final Iterator<? extends I4> iter4 = list4.iterator();
        
        final List<R> toReturn = new Vector<R> ();
        
        while (iter0.hasNext() && iter1.hasNext() && iter2.hasNext() && iter3.hasNext() && iter4.hasNext()) {
            toReturn.add (this.apply(iter0.next(), iter1.next(), iter2.next(), iter3.next(), iter4.next()));
        }

        return toReturn;
    }

    public List<R> mapcar(final I0[] array0, final I1[] array1, final I2[] array2, final I3[] array3, final I4[] array4) {
        return this.mapcar(Arrays.asList(array0), Arrays.asList(array1), Arrays.asList(array2), Arrays.asList(array3), Arrays.asList(array4));
    }

    public QuadtaryFunction<R, I0, I1, I2, I3> rcurry(final I4 arg4) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;
        
        final QuadtaryFunction<R, I0, I1, I2, I3> toReturn = new AbstractQuadtaryFunction<R, I0, I1, I2, I3> () {
            public final R apply (final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3) {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
            }
        };
        return toReturn;
    }

    public TernaryFunction<R, I0, I1, I2> rcurry(final I3 arg3, final I4 arg4) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;
        
        final TernaryFunction<R, I0, I1, I2> toReturn = new AbstractTernaryFunction<R, I0, I1, I2> () {
            public final R apply (final I0 arg0, final I1 arg1, final I2 arg2) {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
            }
        };
        return toReturn;
    }

    public BinaryFunction<R, I0, I1> rcurry(final I2 arg2, final I3 arg3, final I4 arg4) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;
        
        final BinaryFunction<R, I0, I1> toReturn = new AbstractBinaryFunction<R, I0, I1> () {
            public final R apply (final I0 arg0, final I1 arg1) {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
            }
        };
        return toReturn;
    }

    public UnaryFunction<R, I0> rcurry(final I1 arg1, final I2 arg2, final I3 arg3, final I4 arg4) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;
        
        final UnaryFunction<R, I0> toReturn = new AbstractUnaryFunction<R, I0> () {
            public final R apply (final I0 arg0) {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
            }
        };
        return toReturn;
    }

    public ConstantFunction<R> rcurry(final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3, final I4 arg4) {
        final QuintaryFunction<R, I0, I1, I2, I3, I4> thisOne = this;

        final ConstantFunction<R> toReturn = new AbstractConstantFunction<R> () {
            public final R apply () {
                return thisOne.apply(arg0, arg1, arg2, arg3, arg4);
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
