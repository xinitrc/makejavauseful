package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public abstract class AbstractQuintaryPredicate<T0, T1, T2, T3, T4> extends
        AbstractQuintaryFunction<Boolean, T0, T1, T2, T3, T4> implements
        QuintaryPredicate<T0, T1, T2, T3, T4> {

    public QuintaryPredicate<T0, T1, T2, T3, T4> complement() {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;
        
        final QuintaryPredicate<T0, T1, T2, T3, T4> aqp = new AbstractQuintaryPredicate<T0, T1, T2, T3, T4>() {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
                return ! thisOne.apply (t0, t1, t2, t3, t4);
            }
        };
        return aqp;
    }

    public QuintaryPredicate<T0, T1, T2, T3, T4> conjoin (final QuintaryPredicate<T0, T1, T2, T3, T4>... quintaryPredicates) {
        final QuintaryPredicate<T0,T1,T2,T3,T4> thisOne = this;
        
        final QuintaryPredicate<T0, T1, T2, T3, T4> atp = new AbstractQuintaryPredicate<T0, T1, T2, T3, T4> () {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
                if (!thisOne.apply (t0, t1, t2, t3, t4)) {
                    return Boolean.FALSE;
                } else {
                    for (final QuintaryPredicate<T0, T1, T2, T3, T4> quintaryPredicate : quintaryPredicates) {
                        if (! quintaryPredicate.apply(t0, t1, t2, t3, t4)) {
                            return Boolean.FALSE;
                        }
                    }
                }
                return Boolean.TRUE;
            }
        };
        
        return atp;
    }

    public QuintaryPredicate<T0, T1, T2, T3, T4> disjoin(final QuintaryPredicate<T0, T1, T2, T3, T4>... quintaryPredicates) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;
        
        final QuintaryPredicate<T0, T1, T2, T3, T4> aqp = new AbstractQuintaryPredicate<T0, T1, T2, T3, T4> () {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
                if (thisOne.apply (t0, t1, t2, t3, t4)) {
                    return Boolean.TRUE;
                } else {
                    for (final QuintaryPredicate<T0, T1, T2, T3, T4> quintaryPredicate : quintaryPredicates) {
                        if (quintaryPredicate.apply(t0, t1, t2, t3, t4)) {
                            return Boolean.TRUE;
                        }
                    }
                }
                return Boolean.FALSE;
            }
        };
        
        return aqp;
    }

    public QuadtaryPredicate<T1, T2, T3, T4> curry (final T0 t0) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final QuadtaryPredicate<T1, T2, T3, T4> aqp = new AbstractQuadtaryPredicate<T1, T2, T3, T4> () {
            public final Boolean apply (final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return aqp;
    }
    
    public TernaryPredicate<T2, T3, T4> curry (final T0 t0, final T1 t1) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final TernaryPredicate<T2, T3, T4> atp = new AbstractTernaryPredicate<T2, T3, T4> (){
            public final Boolean apply (final T2 t2, final T3 t3, final T4 t4) {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return atp;
    }
    
    public BinaryPredicate<T3, T4> curry (final T0 t0, final T1 t1, final T2 t2) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final BinaryPredicate<T3, T4> abp = new AbstractBinaryPredicate<T3,T4> () {
            public final Boolean apply (final T3 t3, final T4 t4) {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return abp;
    }
    
    public UnaryPredicate<T4> curry (final T0 t0, final T1 t1, final T2 t2, final T3 t3) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final UnaryPredicate<T4> aup = new AbstractUnaryPredicate<T4> () {
            public final Boolean apply (final T4 t4) {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return aup;
    }
    
    public ConstantPredicate curry (final T0 t0, final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return acp;
    }
    
    public QuadtaryPredicate<T0, T1, T2, T3> rcurry (final T4 t4) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final QuadtaryPredicate<T0, T1, T2, T3> aqp = new AbstractQuadtaryPredicate<T0, T1, T2, T3> () {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2, final T3 t3) {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return aqp;
    }

    public TernaryPredicate<T0, T1, T2> rcurry (final T3 t3, final T4 t4){
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final TernaryPredicate<T0, T1, T2> atp = new AbstractTernaryPredicate<T0, T1, T2> (){
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2) {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return atp;
    }

    public BinaryPredicate<T0, T1> rcurry (final T2 t2, final T3 t3, final T4 t4) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final BinaryPredicate<T0, T1> abp = new AbstractBinaryPredicate<T0, T1> () {
            public final Boolean apply (final T0 t0, final T1 t1) {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return abp;
    }
    
    public UnaryPredicate<T0> rcurry (final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final UnaryPredicate<T0> aup = new AbstractUnaryPredicate<T0> () {
            public final Boolean apply (final T0 t0) {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return aup;
    }

    public ConstantPredicate rcurry (final T0 t0, final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        final QuintaryPredicate<T0, T1, T2, T3, T4> thisOne = this;

        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                return thisOne.apply (t0, t1, t2, t3, t4);
            }
        };

        return acp;
    }
}
