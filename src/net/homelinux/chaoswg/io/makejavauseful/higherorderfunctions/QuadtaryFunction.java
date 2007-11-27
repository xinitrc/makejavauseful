package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;

public interface QuadtaryFunction<R, I0,I1,I2,I3> extends NAryFunction<R> {
    public R apply (final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3) throws IllegalArgumentException;
    
    public TernaryFunction<R, I1, I2, I3> curry (final I0 arg0);
    
    public TernaryFunction<R, I0, I1, I2> rcurry (final I3 arg3);
    
    public BinaryFunction<R, I2, I3> curry (final I0 arg0, final I1 arg1);
    
    public BinaryFunction<R, I0, I1> rcurry (final I2 arg2, final I3 arg3);
    
    public UnaryFunction<R, I3> curry (final I0 arg0, final I1 arg1, final I2 arg2);
    
    public UnaryFunction<R, I0> rcurry (final I1 arg1, final I2 arg2, final I3 arg3);
    
    public ConstantFunction<R> curry (final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3);
    
    public ConstantFunction<R> rcurry (final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3);

    public List<R> mapcar (final List<? extends I0> list0, final List<? extends I1> list1, final List<? extends I2> list2, final List<? extends I3> list3);
    
//    public List<R> maplist (final List<I0> list0, final List<I1> list1, final List<I2> list2, final List<I3> list3);
    
    public List<R> mapcar (final I0[] array0, final I1[] array1, final I2[] array2, final I3[] array3);
    
//    public List<R> maplist (final I0[] array0, final I1[] array1, final I2[] array2, final I3[] array3);
}
