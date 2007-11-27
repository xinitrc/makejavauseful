package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public abstract class AbstractBinaryPredicate<T0, T1> extends
        AbstractBinaryFunction<Boolean, T0, T1> implements
        BinaryPredicate<T0, T1> {

    public BinaryPredicate<T0,T1> complement() {
        final BinaryPredicate<T0, T1> thisOne = this;
        
        final BinaryPredicate<T0, T1> abp = new AbstractBinaryPredicate<T0, T1> () {
            public final Boolean apply (final T0 t0, final T1 t1) {
                return ! thisOne.apply(t0, t1);
            }
        };
        return abp;
    }

    public BinaryPredicate<T0, T1> conjoin (final BinaryPredicate<T0, T1>... binaryPredicates) {
        final BinaryPredicate<T0, T1> thisOne = this;
        
        final BinaryPredicate<T0, T1> abp = new AbstractBinaryPredicate<T0, T1> () {
            public final Boolean apply (final T0 t0, final T1 t1) {
                if (!thisOne.apply (t0, t1)) {
                    return Boolean.FALSE;
                } else {
                    for (final BinaryPredicate<T0, T1> binaryPredicate : binaryPredicates) {
                        if (! binaryPredicate.apply(t0, t1)) {
                            return Boolean.FALSE;
                        }
                    }
                }
                return Boolean.TRUE;
            }
        };
        
        return abp;
    }

    public BinaryPredicate<T0, T1> disjoin(final BinaryPredicate<T0, T1>... binaryPredicates) {
        final BinaryPredicate<T0, T1> thisOne = this;
        
        final BinaryPredicate<T0, T1> abp = new AbstractBinaryPredicate<T0, T1> () {
            public final Boolean apply (final T0 t0, final T1 t1) {
                if (thisOne.apply (t0, t1)) {
                    return Boolean.TRUE;
                } else {
                    for (final BinaryPredicate<T0, T1> binaryPredicate : binaryPredicates) {
                        if (binaryPredicate.apply(t0, t1)) {
                            return Boolean.TRUE;
                        }
                    }
                }
                return Boolean.FALSE;
            }
        };
        
        return abp;
    }
    
    public UnaryPredicate<T1> curry (final T0 t0) {
        final BinaryPredicate<T0, T1> thisOne = this;
        
        final UnaryPredicate<T1> aup = new AbstractUnaryPredicate<T1> () {
            public final Boolean apply (final T1 t1) {
                return thisOne.apply (t0, t1);
            }
        };
        
        return aup;
    }
    
    public ConstantPredicate curry (final T0 t0, final T1 t1) {
        final AbstractBinaryPredicate<T0, T1> thisOne = this;
        
        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                return thisOne.apply (t0, t1);
            }
        };
        
        return acp;
    }
    
    public UnaryPredicate<T0> rcurry (final T1 t1) {
        final BinaryPredicate<T0, T1> thisOne = this;
        
        final UnaryPredicate<T0> aup = new AbstractUnaryPredicate<T0> () {
            public final Boolean apply (T0 t0) {
                return thisOne.apply (t0, t1);
            }
        };
        
        return aup;
        
    }
    
    public ConstantPredicate rcurry (final T0 t0, final T1 t1) {
        final AbstractBinaryPredicate<T0, T1> thisOne = this;
        
        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                return thisOne.apply (t0, t1);
            }
        };
        
        return acp;
    }
}
