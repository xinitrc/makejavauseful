package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public interface QuintaryPredicate<T0, T1, T2, T3, T4> extends QuintaryFunction<Boolean, T0, T1, T2, T3, T4>,NAryPredicate {
    public QuintaryPredicate<T0,T1,T2,T3,T4> disjoin (final QuintaryPredicate<T0,T1,T2,T3,T4>... unaryPredicates);
    public QuintaryPredicate<T0,T1,T2,T3,T4> conjoin (final QuintaryPredicate<T0,T1,T2,T3,T4>... unaryPredicates);
    public QuintaryPredicate<T0,T1,T2,T3,T4> complement ();

    public QuadtaryPredicate<T1, T2, T3, T4> curry (final T0 t0);
    public TernaryPredicate<T2, T3, T4> curry (final T0 t0, final T1 t1);
    public BinaryPredicate<T3, T4> curry (final T0 t0, final T1 t1, final T2 t2);
    public UnaryPredicate<T4> curry (final T0 t0, final T1 t1, final T2 t2, final T3 t3);
    public ConstantPredicate curry (final T0 t0, final T1 t1, final T2 t2, final T3 t3, final T4 t4);
    
    public QuadtaryPredicate<T0, T1, T2, T3> rcurry (final T4 t4);
    public TernaryPredicate<T0, T1, T2> rcurry (final T3 t3, final T4 t4);
    public BinaryPredicate<T0, T1> rcurry (final T2 t2, final T3 t3, final T4 t4);
    public UnaryPredicate<T0> rcurry (final T1 t1, final T2 t2, final T3 t3, final T4 t4);
    public ConstantPredicate rcurry (final T0 t0, final T1 t1, final T2 t2, final T3 t3, final T4 t4);

}
