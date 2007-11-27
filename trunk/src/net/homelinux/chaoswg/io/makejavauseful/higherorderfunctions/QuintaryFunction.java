package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;

public interface QuintaryFunction<R, I0, I1, I2, I3, I4> extends NAryFunction<R> {
    public R apply (final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3, final I4 arg4) throws IllegalArgumentException;
    
    public QuadtaryFunction<R, I1, I2, I3, I4> curry (final I0 arg0);
    
    public QuadtaryFunction<R, I0, I1, I2, I3> rcurry (final I4 arg2);
    
    public TernaryFunction<R, I2, I3, I4> curry (final I0 arg0, final I1 arg1);
    
    public TernaryFunction<R, I0, I1, I2> rcurry (final I3 arg3, final I4 arg4);
    
    public BinaryFunction<R, I3, I4> curry (final I0 arg0, final I1 arg1, final I2 arg2);
    
    public BinaryFunction<R, I0, I1> rcurry (final I2 arg2, final I3 arg3, final I4 arg4);
    
    public UnaryFunction<R, I4> curry (final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3);
    
    public UnaryFunction<R, I0> rcurry (final I1 arg1, final I2 arg2, final I3 arg3, final I4 arg4);
    
    public ConstantFunction<R> curry (final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3, final I4 arg4);
    
    public ConstantFunction<R> rcurry (final I0 arg0, final I1 arg1, final I2 arg2, final I3 arg3, final I4 arg4);

    public List<R> mapcar (final List<? extends  I0> list0, final List<? extends  I1> list1, final List<? extends I2> list2, final List<? extends I3> list3, final List<? extends  I4> list4);
    
//    public List<R> maplist (final List<I0> list0, final List<I1> list1, final List<I2> list2, final List<I3> list3, final List<I4> list4);
    
    public List<R> mapcar (final I0[] array0, final I1[] array1, final I2[] array2, final I3[] array3, final I4[] array4);
    
//    public List<R> maplist (final I0[] array0, final I1[] array1, final I2[] array2, final I3[] array3, final I4[] array4);

}
