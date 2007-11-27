package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public abstract class AbstractConstantPredicate extends AbstractConstantFunction<Boolean> implements ConstantPredicate {
    public ConstantPredicate complement() {
        final ConstantPredicate thisOne = this;
        
        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                return ! thisOne.apply ();
            }
        };
        return acp;
    }

    public ConstantPredicate conjoin (final ConstantPredicate... constantPredicates) {
        final ConstantPredicate thisOne = this;
        
        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                if (!thisOne.apply ()) {
                    return Boolean.FALSE;
                } else {
                    for (final ConstantPredicate constantPredicate : constantPredicates) {
                        if (! constantPredicate.apply()) {
                            return Boolean.FALSE;
                        }
                    }
                }
                return Boolean.TRUE;
            }
        };
        
        return acp;
    }

    public ConstantPredicate disjoin(final ConstantPredicate... constantPredicates) {
        final ConstantPredicate thisOne = this;
        
        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                if (thisOne.apply ()) {
                    return Boolean.TRUE;
                } else {
                    for (final ConstantPredicate constantPredicate : constantPredicates) {
                        if (constantPredicate.apply()) {
                            return Boolean.TRUE;
                        }
                    }
                }
                return Boolean.FALSE;
            }
        };
        
        return acp;
    }
}
