package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;

public interface TernaryFunction<R, I0, I1, I2> extends NAryFunction<R> {
    public R apply (final I0 arg0, final I1 arg1, final I2 arg2);

    public BinaryFunction<R, I1, I2> curry (final I0 arg0);

    public BinaryFunction<R, I0, I1> rcurry (final I2 arg2);

    public UnaryFunction<R, I2> curry (final I0 arg0, final I1 arg1);

    public UnaryFunction<R, I0> rcurry (final I1 arg1, final I2 arg2);

    public ConstantFunction<R> curry (final I0 arg0, final I1 arg1, final I2 arg2);

    public ConstantFunction<R> rcurry (final I0 arg0, final I1 arg1, final I2 arg2);

    public List<R> mapcar (final List<? extends I0> list0, final List<? extends I1> list1, final List<? extends I2> list2);

//    public List<R> maplist (final List<I0> list0, final List<I1> list1, final List<I2> list2);

    public List<R> mapcar (final I0[] array0, final I1[] array1, final I2[] array2);

//    public List<R> maplist (final I0[] array0, final I1[] array1, final I2[] array2);
}
