package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public interface BinaryPredicate<T0, T1> extends BinaryFunction<Boolean, T0, T1>, NAryPredicate {
    public BinaryPredicate<T0, T1> disjoin (final BinaryPredicate<T0, T1>... unaryPredicates);
    public BinaryPredicate<T0, T1> conjoin (final BinaryPredicate<T0, T1>... unaryPredicates);
    public BinaryPredicate<T0, T1> complement ();
    
    public UnaryPredicate<T1> curry (final T0 t0);
    public ConstantPredicate curry (final T0 t0, final T1 t1);
    
    public UnaryPredicate<T0> rcurry (final T1 t1);
    public ConstantPredicate rcurry (final T0 t0, final T1 t1);
}
