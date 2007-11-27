package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public interface TernaryPredicate<T0, T1, T2> extends TernaryFunction<Boolean, T0, T1, T2>,NAryPredicate {
    public TernaryPredicate<T0,T1,T2> disjoin (final TernaryPredicate<T0,T1,T2>... unaryPredicates);
    public TernaryPredicate<T0,T1,T2> conjoin (final TernaryPredicate<T0,T1,T2>... unaryPredicates);
    public TernaryPredicate<T0,T1,T2> complement ();
    
    public BinaryPredicate<T1, T2> curry (final T0 t0);
    public UnaryPredicate<T2> curry (final T0 t0, final T1 t1);
    public ConstantPredicate curry (final T0 t0, final T1 t1, final T2 t2);
    
    public BinaryPredicate<T0,T1> rcurry (final T2 t2);
    public UnaryPredicate<T0> rcurry (final T1 t1, final T2 t2);
    public ConstantPredicate rcurry (final T0 t0, final T1 t1, final T2 t2);
}
