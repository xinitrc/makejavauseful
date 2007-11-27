package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public abstract class AbstractTernaryPredicate<T0, T1, T2> extends AbstractTernaryFunction<Boolean, T0, T1, T2> implements TernaryPredicate<T0, T1, T2> {

    public TernaryPredicate<T0, T1, T2> complement() {
        final TernaryPredicate<T0, T1, T2> thisOne = this;
        
        final TernaryPredicate<T0, T1, T2> atp = new AbstractTernaryPredicate<T0, T1, T2> () {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2) {
                return ! thisOne.apply (t0, t1, t2);
            }
        };
        return atp;
    }

    public TernaryPredicate<T0, T1, T2> conjoin (final TernaryPredicate<T0, T1, T2>... ternaryPredicates) {
        final TernaryPredicate<T0, T1, T2> thisOne = this;
        
        final TernaryPredicate<T0, T1, T2> atp = new AbstractTernaryPredicate<T0, T1, T2> () {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2) {
                if (!thisOne.apply (t0, t1, t2)) {
                    return Boolean.FALSE;
                } else {
                    for (final TernaryPredicate<T0, T1, T2> ternaryPredicate : ternaryPredicates) {
                        if (! ternaryPredicate.apply(t0, t1, t2)) {
                            return Boolean.FALSE;
                        }
                    }
                }
                return Boolean.TRUE;
            }
        };
        
        return atp;
    }

    public TernaryPredicate<T0, T1, T2> disjoin(final TernaryPredicate<T0, T1, T2>... ternaryPredicates) {
        final TernaryPredicate<T0, T1, T2>  thisOne = this;
        
        final TernaryPredicate<T0, T1, T2> atp = new AbstractTernaryPredicate<T0, T1, T2> () {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2) {
                if (thisOne.apply (t0, t1, t2)) {
                    return Boolean.TRUE;
                } else {
                    for (final TernaryPredicate<T0, T1, T2> ternaryPredicate : ternaryPredicates) {
                        if (ternaryPredicate.apply(t0, t1, t2)) {
                            return Boolean.TRUE;
                        }
                    }
                }
                return Boolean.FALSE;
            }
        };
        
        return atp;
    }

    public BinaryPredicate<T1, T2> curry (final T0 t0) {
        final TernaryPredicate<T0, T1, T2> thisOne = this;

        final BinaryPredicate<T1, T2> abp = new AbstractBinaryPredicate<T1, T2> () {
            public final Boolean apply (final T1 t1, final T2 t2) {
                return thisOne.apply (t0, t1, t2);
            }
        };

        return abp;
    }
    
    public UnaryPredicate<T2> curry (final T0 t0, final T1 t1) {
        final TernaryPredicate<T0, T1, T2> thisOne = this;

        final UnaryPredicate<T2> aup = new AbstractUnaryPredicate<T2>() {
            public final Boolean apply (final T2 t2) {
                return thisOne.apply (t0, t1, t2);
            }
        };

        return aup;
    }
    
    public ConstantPredicate curry (final T0 t0, final T1 t1, final T2 t2) {
        final TernaryPredicate<T0, T1, T2> thisOne = this;

        final ConstantPredicate acp = new AbstractConstantPredicate() {
            public final Boolean apply () {
                return thisOne.apply (t0, t1, t2);
            }
        };

        return acp;
    }
    
    public BinaryPredicate<T0, T1> rcurry (final T2 t2) {
        final TernaryPredicate<T0, T1, T2> thisOne = this;

        final BinaryPredicate<T0, T1> abp = new AbstractBinaryPredicate<T0, T1> () {
            public final Boolean apply (final T0 t0, final T1 t1) {
                return thisOne.apply (t0, t1, t2);
            }
        };

        return abp;
    }
    
    public UnaryPredicate<T0> rcurry (final T1 t1, final T2 t2) {
        final TernaryPredicate<T0, T1, T2> thisOne = this;

        final UnaryPredicate<T0> aup = new AbstractUnaryPredicate<T0> () {
            public final Boolean apply (final T0 t0) {
                return thisOne.apply (t0, t1, t2);
            }
        };

        return aup;
    }
    
    public ConstantPredicate rcurry (final T0 t0, final T1 t1, final T2 t2) {
        final TernaryPredicate<T0, T1, T2> thisOne = this;

        final ConstantPredicate acp = new AbstractConstantPredicate() {
            public final Boolean apply () {
                return thisOne.apply (t0, t1, t2);
            }
        };

        return acp;
    }
}
