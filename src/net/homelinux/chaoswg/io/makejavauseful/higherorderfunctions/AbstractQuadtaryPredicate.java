package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public abstract class AbstractQuadtaryPredicate<T0, T1, T2, T3> extends
        AbstractQuadtaryFunction<Boolean, T0, T1, T2, T3> implements
        QuadtaryPredicate<T0, T1, T2, T3> {

    public QuadtaryPredicate<T0, T1, T2, T3> complement() {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;
        
        final QuadtaryPredicate<T0, T1, T2, T3> aqp = new AbstractQuadtaryPredicate<T0, T1, T2, T3>() {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2, final T3 t3) {
                return ! thisOne.apply (t0, t1, t2, t3);
            }
        };
        return aqp;
    }

    public QuadtaryPredicate<T0, T1, T2, T3> conjoin (final QuadtaryPredicate<T0, T1, T2, T3>... quadtaryPredicates) {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;
        
        final QuadtaryPredicate<T0, T1, T2, T3> atp = new AbstractQuadtaryPredicate<T0, T1, T2, T3> () {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2, final T3 t3) {
                if (!thisOne.apply (t0, t1, t2, t3)) {
                    return Boolean.FALSE;
                } else {
                    for (final QuadtaryPredicate<T0, T1, T2, T3> quadtaryPredicate : quadtaryPredicates) {
                        if (! quadtaryPredicate.apply(t0, t1, t2, t3)) {
                            return Boolean.FALSE;
                        }
                    }
                }
                return Boolean.TRUE;
            }
        };
        
        return atp;
    }

    public QuadtaryPredicate<T0, T1, T2, T3> disjoin(final QuadtaryPredicate<T0, T1, T2, T3>... quadteryPredicates) {
        final QuadtaryPredicate<T0, T1, T2, T3>thisOne = this;
        
        final QuadtaryPredicate<T0, T1, T2, T3> aqp = new AbstractQuadtaryPredicate<T0, T1, T2, T3> () {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2, final T3 t3) {
                if (thisOne.apply (t0, t1, t2, t3)) {
                    return Boolean.TRUE;
                } else {
                    for (final QuadtaryPredicate<T0, T1, T2, T3> quadteryPredicate : quadteryPredicates) {
                        if (quadteryPredicate.apply(t0, t1,t2,t3)) {
                            return Boolean.TRUE;
                        }
                    }
                }
                return Boolean.FALSE;
            }
        };
        
        return aqp;
    }


    public TernaryPredicate<T1, T2, T3> curry (final T0 t0) {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;

        final TernaryPredicate<T1, T2, T3> atp = new AbstractTernaryPredicate<T1, T2, T3> () {
            public final Boolean apply (final T1 t1, final T2 t2, final T3 t3) {
                return thisOne.apply (t0, t1, t2, t3);
            }
        };

        return atp;
    }
    
    public BinaryPredicate<T2, T3> curry (final T0 t0, final T1 t1) {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;

        final BinaryPredicate<T2, T3> abp = new AbstractBinaryPredicate<T2, T3> () {
            public final Boolean apply (final T2 t2, final T3 t3) {
                return thisOne.apply (t0, t1, t2, t3); 
            }
        };

        return abp;
    }
    
    public UnaryPredicate<T3> curry (final T0 t0, final T1 t1, final T2 t2) {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;

        final UnaryPredicate<T3> aup = new AbstractUnaryPredicate<T3> () {
            public final Boolean apply (final T3 t3) {
                return thisOne.apply (t0, t1, t2, t3);
            }
        };

        return aup;
    }
    
    public ConstantPredicate curry (final T0 t0, final T1 t1, final T2 t2, final T3 t3) {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;

        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                return thisOne.apply (t0, t1, t2, t3);
            }
        };

        return acp;
    }
    
    public TernaryPredicate<T0, T1, T2> rcurry (final T3 t3) {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;

        final TernaryPredicate<T0, T1, T2> atp = new AbstractTernaryPredicate<T0, T1, T2> () {
            public final Boolean apply (final T0 t0, final T1 t1, final T2 t2) {
                return thisOne.apply (t0, t1, t2, t3);
            }
        };

        return atp;
    }
    
    public BinaryPredicate<T0, T1> rcurry (final T2 t2, final T3 t3) {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;

        final BinaryPredicate<T0, T1> abp = new AbstractBinaryPredicate<T0, T1> () {
            public final Boolean apply (final T0 t0, final T1 t1) {
                return thisOne.apply (t0, t1, t2, t3);
            }
        };

        return abp;
    }
    
    public UnaryPredicate<T0> rcurry (final T1 t1, final T2 t2, final T3 t3) {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;

        final UnaryPredicate<T0> aup = new AbstractUnaryPredicate<T0> () {
            public final Boolean apply (final T0 t0) {
                return thisOne.apply (t0, t1, t2, t3);
            }
        };

        return aup;
    }
    
    public ConstantPredicate rcurry (final T0 t0, final T1 t1, final T2 t2, final T3 t3) {
        final QuadtaryPredicate<T0, T1, T2, T3> thisOne = this;

        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                return thisOne.apply (t0, t1, t2, t3);
            }
        };

        return acp;
    }

}
