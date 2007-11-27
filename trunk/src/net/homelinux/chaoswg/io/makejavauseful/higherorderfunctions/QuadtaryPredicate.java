package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public interface QuadtaryPredicate<T0, T1, T2, T3> extends QuadtaryFunction<Boolean, T0, T1, T2, T3>,NAryPredicate {
    public QuadtaryPredicate<T0,T1,T2,T3> disjoin (final QuadtaryPredicate<T0,T1,T2,T3>... unaryPredicates);
    public QuadtaryPredicate<T0,T1,T2,T3> conjoin (final QuadtaryPredicate<T0,T1,T2,T3>... unaryPredicates);
    public QuadtaryPredicate<T0,T1,T2,T3> complement ();
    
    public TernaryPredicate<T1, T2, T3> curry (final T0 t0);
    public BinaryPredicate<T2, T3> curry (final T0 t0, final T1 t1);
    public UnaryPredicate<T3> curry (final T0 t0, final T1 t1, final T2 t2);
    public ConstantPredicate curry (final T0 t0, final T1 t1, final T2 t2, final T3 t3);
    
    public TernaryPredicate<T0,T1, T2> rcurry (final T3 t3);
    public BinaryPredicate<T0,T1> rcurry (final T2 t2, final T3 t3);
    public UnaryPredicate<T0> rcurry (final T1 t1, final T2 t2, final T3 t3);
    public ConstantPredicate rcurry (final T0 t0, final T1 t1, final T2 t2, final T3 t3);
}
