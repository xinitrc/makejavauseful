package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;

public interface BinaryFunction<R, I0, I1> extends NAryFunction<R> {
    public R apply (final I0 arg0, final I1 arg1);

    public UnaryFunction<R, I1> curry (final I0 arg0);

    public UnaryFunction<R, I0> rcurry (final I1 arg1);

    public ConstantFunction<R> curry (final I0 arg0, final I1 arg1);

    public ConstantFunction<R> rcurry (final I0 arg0, final I1 arg1);

    public List<R> mapcar (final List<? extends I0> list0, final List<? extends I1> list1);

//    public List<R> maplist (final List<I0> list0, final List<I1> list1);

    public R reduce (final List<? extends R> list);

    public List<R> mapcar (final I0[] array0, final I1[] array1);

//    public List<R> maplist (final I0[] array0, final I1[] array1);

    public R reduce (final R... list);
}
