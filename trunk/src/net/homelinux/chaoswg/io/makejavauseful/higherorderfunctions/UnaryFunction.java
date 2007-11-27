package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;

public interface UnaryFunction<R, I0> extends NAryFunction<R> {
    public R apply (I0 arg0) throws IllegalArgumentException;
    
    public ConstantFunction<R> curry (I0 arg0);
    
    public ConstantFunction<R> rcurry (I0 arg0);
    
    public <OI0> UnaryFunction<R, OI0> compose (final UnaryFunction<I0, OI0> unaryFunction);

    public <OI0,OI1> BinaryFunction<R, OI0, OI1> compose (final BinaryFunction<I0, OI0, OI1> binaryFunction);

    public <OI0,OI1,OI2> TernaryFunction<R, OI0, OI1, OI2> compose (final TernaryFunction<I0, OI0, OI1, OI2> ternaryFunction);
    
    public <OI0,OI1,OI2,OI3> QuadtaryFunction<R, OI0, OI1, OI2, OI3> compose (final QuadtaryFunction<I0, OI0, OI1, OI2, OI3> quadtaryFunction);

    public <OI0,OI1,OI2,OI3,OI4> QuintaryFunction<R, OI0, OI1, OI2, OI3, OI4> compose (final QuintaryFunction<I0, OI0, OI1, OI2, OI3, OI4> quintaryFunction);
    
    public List<R> mapcar (final List<? extends I0> list0);

//    public List<R> maplist (final List<I0> list0);

    public List<R> mapcar (final I0[] array0);

//    public List<R> maplist (final I0[] array0);

}
