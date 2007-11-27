package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public abstract class AbstractUnaryFunction<R, I0> implements UnaryFunction<R, I0> {
    public <OI0> UnaryFunction<R, OI0> compose(final UnaryFunction<I0, OI0> unaryFunction) {
        final UnaryFunction<R,I0> thisOne = this;
        
        final UnaryFunction<R,OI0> toReturn = new AbstractUnaryFunction<R,OI0> () {
            public final R apply (final OI0 arg0) {
                return thisOne.apply (unaryFunction.apply(arg0));
            }
        };
        
        return toReturn;
    }

    public <OI0, OI1> BinaryFunction<R, OI0, OI1> compose(final BinaryFunction<I0, OI0, OI1> binaryFunction) {
        final UnaryFunction<R,I0> thisOne = this;
        
        final BinaryFunction<R,OI0,OI1> toReturn = new AbstractBinaryFunction<R,OI0,OI1> () {
            public final R apply (final OI0 arg0, final OI1 arg1) {
                return thisOne.apply (binaryFunction.apply(arg0, arg1));
            }
        };
        
        return toReturn;
    }

    public <OI0, OI1, OI2> TernaryFunction<R, OI0, OI1, OI2> compose(final TernaryFunction<I0, OI0, OI1, OI2> ternaryFunction) {
        final UnaryFunction<R,I0> thisOne = this;
        
        final TernaryFunction<R,OI0,OI1,OI2> toReturn = new AbstractTernaryFunction<R,OI0,OI1,OI2> () {
            public final R apply (final OI0 arg0, final OI1 arg1, final OI2 arg2) {
                return thisOne.apply (ternaryFunction.apply(arg0, arg1, arg2));
            }
        };
        
        return toReturn;
    }

    public <OI0, OI1, OI2, OI3> QuadtaryFunction<R, OI0, OI1, OI2, OI3> compose(final QuadtaryFunction<I0, OI0, OI1, OI2, OI3> quardtaryFunction) {
        final UnaryFunction<R,I0> thisOne = this;
        
        final QuadtaryFunction<R,OI0,OI1,OI2,OI3> toReturn = new AbstractQuadtaryFunction<R,OI0,OI1,OI2,OI3> () {
            public final R apply (final OI0 arg0, final OI1 arg1, final OI2 arg2, final OI3 arg3) {
                return thisOne.apply (quardtaryFunction.apply(arg0, arg1, arg2, arg3));
            }
        };
        
        return toReturn;
    }

    public <OI0, OI1, OI2, OI3, OI4> QuintaryFunction<R, OI0, OI1, OI2, OI3, OI4> compose(final QuintaryFunction<I0, OI0, OI1, OI2, OI3, OI4> quintaryFunction) {
        final UnaryFunction<R,I0> thisOne = this;
        
        final QuintaryFunction<R,OI0,OI1,OI2,OI3,OI4> toReturn = new AbstractQuintaryFunction<R,OI0,OI1,OI2,OI3,OI4> () {
            public final R apply (final OI0 arg0, final OI1 arg1, final OI2 arg2, final OI3 arg3, final OI4 arg4) {
                return thisOne.apply (quintaryFunction.apply(arg0, arg1, arg2, arg3, arg4));
            }
        };
        
        return toReturn;
    }

    public ConstantFunction<R> curry(final I0 arg0) {
        final UnaryFunction<R, I0> thisOne = this;
        
        final ConstantFunction<R> toReturn = new AbstractConstantFunction<R> () {
            public final R apply () {
                return thisOne.apply(arg0);
            }
        };
        return toReturn;
    }

    public List<R> mapcar(final List<? extends I0> list0) {
        final List<R> toReturn = new Vector<R> ();
        for (final I0 element : list0) {
            toReturn.add(this.apply(element));
        }
        
        return toReturn;
    }

    public List<R> mapcar(final I0[] array0) {
        return this.mapcar(Arrays.asList(array0));
    }

    public ConstantFunction<R> rcurry(final I0 arg0) {
        final UnaryFunction<R, I0> thisOne = this;
        
        final ConstantFunction<R> toReturn = new AbstractConstantFunction<R> () {
            public final R apply () {
                return thisOne.apply(arg0);
            }
        };
        return toReturn;
    }
    
    public Class[] domain() {
        return null;
    }
    
    public Class range () {
        return null;
    }
}
