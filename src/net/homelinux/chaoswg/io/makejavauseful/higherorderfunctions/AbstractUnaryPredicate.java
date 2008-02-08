package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.homelinux.chaoswg.io.makejavauseful.datatypes.Pair;

public abstract class AbstractUnaryPredicate<T0> extends AbstractUnaryFunction<Boolean, T0> implements UnaryPredicate<T0> {
    public Boolean any(final List<? extends T0> l) {
        for (final T0 t0 : l) {
            if (this.apply(t0)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean any(final T0... l) {
        return this.any (Arrays.asList(l));
    }

    public UnaryPredicate<T0> complement() {
        final UnaryPredicate<T0> thisOne = this;
        
        final UnaryPredicate<T0> acp = new AbstractUnaryPredicate<T0> () {
            public final Boolean apply (final T0 t0) {
                return !thisOne.apply (t0);
            }
        };
        
        return acp;
    }

    public UnaryPredicate<T0> conjoin (final UnaryPredicate<T0>... unaryPredicates) {
        final UnaryPredicate<T0> thisOne = this;
        
        final UnaryPredicate<T0> aup = new AbstractUnaryPredicate<T0> () {
            public final Boolean apply (final T0 t0) {
                if (!thisOne.apply (t0)) {
                    return Boolean.FALSE;
                } else {
                    for (final UnaryPredicate<T0> unaryPredicate : unaryPredicates) {
                        if (! unaryPredicate.apply(t0)) {
                            return Boolean.FALSE;
                        }
                    }
                }
                return Boolean.TRUE;
            }
        };
        
        return aup;
    }

    public UnaryPredicate<T0> disjoin(final UnaryPredicate<T0>... unaryPredicates) {
        final UnaryPredicate<T0> thisOne = this;
        
        final UnaryPredicate<T0> aup = new AbstractUnaryPredicate<T0> () {
            public final Boolean apply (final T0 t0) {
                if (thisOne.apply (t0)) {
                    return Boolean.TRUE;
                } else {
                    for (final UnaryPredicate<T0> binaryPredicate : unaryPredicates) {
                        if (binaryPredicate.apply(t0)) {
                            return Boolean.TRUE;
                        }
                    }
                }
                return Boolean.FALSE;
            }
        };
        
        return aup;
    }
    

    public Boolean every(final List<? extends T0> l) {
        for (final T0 t0 : l) {
            if (! this.apply(t0)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public Boolean every(final T0... l) {
        return this.every(Arrays.asList(l));
    }

    public List<T0> filter(final List<? extends T0> l) {
        final List<T0> toReturn = new Vector<T0> ();
        for (final T0 t0 : l) {
            if (apply(t0)) {
                toReturn.add (t0);
            }
        }
        
        return toReturn;
    }

    public <ANY> List<ANY> filter(final List<ANY> l, final UnaryFunction<? extends T0, ANY> key) {
        final List<ANY> toReturn = new Vector<ANY> ();
        
        for (final ANY a : l) {
            if (apply (key.apply(a))) {
                toReturn.add (a);
            }
        }
        return toReturn;
    }

    public List<T0> filter(final T0... l) {
        return this.filter(Arrays.asList(l));
    }

    public <ANY> List<ANY> filter(final ANY[] l, final UnaryFunction<? extends T0, ANY> key) {
        return this.filter(Arrays.asList(l), key);
    }

    public <ANY> List<ANY> filterBy(final List<? extends T0> toTest, final List<ANY> values) {
        final List<ANY> toReturn = new Vector<ANY> ();
        final Iterator<? extends T0> ttIter = toTest.iterator();
        final Iterator<ANY> valIter = values.iterator();
        
        while (ttIter.hasNext() && valIter.hasNext()) {
            final T0 toTst = ttIter.next();
            final ANY val = valIter.next();
            
            if (apply (toTst)) {
                toReturn.add (val);
            }
        }
        return toReturn;
        //return filterBy (toTest, values, IDENTITY);
    }

    public <ANY0, ANY1> List<ANY1> filterBy(final List<ANY0> toTest, final List<ANY1> values, final UnaryFunction<? extends T0, ANY0> key) {
        final List<ANY1> toReturn = new Vector<ANY1> ();
        
        final Iterator<ANY0> ttIter = toTest.iterator();
        final Iterator<ANY1> valIter = values.iterator();
        
        while (ttIter.hasNext() && valIter.hasNext()) {
            final ANY0 toTst = ttIter.next();
            final ANY1 val = valIter.next();
            
            if (apply (key.apply (toTst))) {
                toReturn.add(val);
            }

        }
        
        return toReturn;
    }

    public <ANY> List<ANY> filterBy(final T0[] toTest, final ANY[] values) {
        return this.filterBy (Arrays.asList(toTest), Arrays.asList(values));
    }

    public <ANY0, ANY1> List<ANY1> filterBy(final ANY0[] toTest, final ANY1[] values, final UnaryFunction<? extends T0, ANY0> key) {
        return this.filterBy (Arrays.asList(toTest), Arrays.asList(values), key);
    }

    public T0 findIf(final List<? extends T0> l) {
        for (final T0 t0 : l) {
            if (apply (t0)) {
                return t0;
            }
        }
        return null;
    }

    public <ANY> ANY findIf(final List<ANY> l, final UnaryFunction<? extends T0, ANY> key) {
        for (final ANY a : l) {
            if (apply (key.apply(a))) {
                return a;
            }
        }
        return null;
    }

    public T0 findIf(final T0... l) {
        return findIf (Arrays.asList(l));
    }

    public <ANY> ANY findIf(final ANY[] l, final UnaryFunction<? extends T0, ANY> key) {
        return findIf (Arrays.asList(l), key);
    }

    public T0 findIfNot(final List<? extends T0> l) {
        for (final T0 t0 : l) {
            if (! apply (t0)) {                
                return t0;
            }
        }
        return null;
    }

    public <ANY> ANY findIfNot(final List<ANY> l, final UnaryFunction<? extends T0, ANY> key) {
        for (final ANY a : l) {
            if (! apply (key.apply(a))) {
                return a;
            }
        }
        return null;
    }

    public T0 findIfNot(final T0... l) {
        return findIfNot(Arrays.asList(l));
    }

    public <ANY> ANY findIfNot(final ANY[] l, final UnaryFunction<? extends T0, ANY> key) {
        return findIfNot(Arrays.asList(l), key);
    }
    
    public <T1> UnaryPredicate<T1> compose (final UnaryFunction<T0, T1> unaryFunction) {
        final UnaryPredicate<T0> thisOne = this;
        
        final UnaryPredicate<T1> abp = new AbstractUnaryPredicate<T1> () {
            public final Boolean apply (final T1 t1) {
                return thisOne.apply (unaryFunction.apply(t1));
            }
        };
        
        return abp;
    }
    
    public <T1, T2> BinaryPredicate<T1, T2> compose (final BinaryFunction<T0, T1, T2> binaryFunction) {
        final UnaryPredicate<T0> thisOne = this;
        
        final BinaryPredicate<T1,T2> aup = new AbstractBinaryPredicate<T1,T2> () {
            public final Boolean apply (final T1 t1, final T2 t2) {
                return thisOne.apply (binaryFunction.apply(t1, t2));
            }
        };
        
        return aup;
    }
    
    public <T1, T2, T3> TernaryPredicate<T1, T2, T3> compose (final TernaryFunction<T0, T1, T2, T3> ternaryFunction) {
        final UnaryPredicate<T0> thisOne = this;
        
        final TernaryPredicate<T1, T2, T3> atp = new AbstractTernaryPredicate <T1, T2, T3> () {
            public final Boolean apply (final T1 t1, final T2 t2, final T3 t3) {
                return thisOne.apply (ternaryFunction.apply(t1, t2, t3));
            }
        };
        
        return atp;
    }
    
    public <T1, T2, T3, T4> QuadtaryPredicate<T1, T2, T3, T4> compose (final QuadtaryFunction<T0, T1, T2, T3, T4> quartaryFunction) {
        final UnaryPredicate<T0> thisOne = this;
        
        final QuadtaryPredicate<T1, T2, T3, T4> aqp = new AbstractQuadtaryPredicate <T1, T2, T3, T4> () {
            public final Boolean apply (final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
                return thisOne.apply (quartaryFunction.apply(t1, t2, t3, t4));
            }
        };
        
        return aqp;
    }
    
    public <T1,T2,T3,T4,T5> QuintaryPredicate<T1,T2,T3,T4,T5> compose (final QuintaryFunction<T0, T1,T2,T3,T4,T5> quintaryFunction) {
        final UnaryPredicate<T0> thisOne = this;
        
        final QuintaryPredicate<T1,T2,T3,T4,T5> aqp = new AbstractQuintaryPredicate <T1,T2,T3,T4,T5> () {
            public final Boolean apply (final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
                return thisOne.apply (quintaryFunction.apply(t1, t2, t3, t4, t5));
            }
        };
        
        return aqp;
        
    }

    public ConstantPredicate curry (final T0 t0) {
        final UnaryPredicate<T0> thisOne = this;
        
        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                return thisOne.apply(t0);
            }
        };
        
        return acp;
    }
    
    public ConstantPredicate rcurry (final T0 t0) {
        final UnaryPredicate<T0> thisOne = this;
        
        final ConstantPredicate acp = new AbstractConstantPredicate () {
            public final Boolean apply () {
                return thisOne.apply(t0);
            }
        };
        
        return acp;
    }

	@Override
	public Pair<List<T0>, List<T0>> split(final List<? extends T0> l) {
		final List<T0> toReturn0 = new Vector<T0> ();
		final List<T0> toReturn1 = new Vector<T0> ();
        for (final T0 t0 : l) {
            if (apply(t0)) {
                toReturn0.add (t0);
            } else {
            	toReturn1.add (t0);
            }
        }
        
        return new Pair<List<T0>, List<T0>> (toReturn0, toReturn1);
	}

	@Override
	public <ANY> Pair<List<ANY>, List<ANY>> split(final List<ANY> l, final UnaryFunction<? extends T0, ANY> key) {
        final List<ANY> toReturn0 = new Vector<ANY> ();
        final List<ANY> toReturn1 = new Vector<ANY> ();
        
        for (final ANY a : l) {
            if (apply (key.apply(a))) {
                toReturn0.add (a);
            } else {
            	toReturn1.add (a);
            }
        }
        return new Pair<List<ANY>, List<ANY>> (toReturn0, toReturn1);
	}

	@Override
	public Pair<List<T0>, List<T0>> split(final T0... l) {
		return split (Arrays.asList(l));
	}

	@Override
	public <ANY> Pair<List<ANY>, List<ANY>> split(final ANY[] l, final UnaryFunction<? extends T0, ANY> key) {
        final List<ANY> toReturn0 = new Vector<ANY> ();
        final List<ANY> toReturn1 = new Vector<ANY> ();
        
        for (final ANY a : l) {
            if (apply (key.apply(a))) {
                toReturn0.add (a);
            } else {
            	toReturn1.add (a);
            }
        }
		return new Pair<List<ANY>, List<ANY>>(toReturn0, toReturn1);
	}

	@Override
	public <ANY> Pair<List<ANY>, List<ANY>> splitBy(final List<? extends T0> toTest, final List<ANY> values) {
        final List<ANY> toReturn0 = new Vector<ANY> ();
        final List<ANY> toReturn1 = new Vector<ANY> ();
        final Iterator<? extends T0> ttIter = toTest.iterator();
        final Iterator<ANY> valIter = values.iterator();
        
        while (ttIter.hasNext() && valIter.hasNext()) {
            final T0 toTst = ttIter.next();
            final ANY val = valIter.next();
            
            if (apply (toTst)) {
                toReturn0.add (val);
            } else {
            	toReturn1.add (val);
            }
        }
		return new Pair<List<ANY>, List<ANY>>(toReturn0, toReturn1);
	}

	@Override
	public <ANY0, ANY1> Pair<List<ANY1>, List<ANY1>> splitBy(final List<ANY0> toTest, final List<ANY1> values, final UnaryFunction<? extends T0, ANY0> key) {
        final List<ANY1> toReturn0 = new Vector<ANY1> ();
        final List<ANY1> toReturn1 = new Vector<ANY1> ();
        
        final Iterator<ANY0> ttIter = toTest.iterator();
        final Iterator<ANY1> valIter = values.iterator();
        
        while (ttIter.hasNext() && valIter.hasNext()) {
            final ANY0 toTst = ttIter.next();
            final ANY1 val = valIter.next();
            
            if (apply (key.apply (toTst))) {
                toReturn0.add(val);
            } else {
            	toReturn1.add(val);
            }

        }
        
		return new Pair<List<ANY1>, List<ANY1>> (toReturn0, toReturn1);
	}

	@Override
	public <ANY> Pair<List<ANY>, List<ANY>> splitBy(final T0[] toTest, final ANY[] values) {
        return this.splitBy (Arrays.asList(toTest), Arrays.asList(values));
	}

	@Override
	public <ANY0, ANY1> Pair<List<ANY1>, List<ANY1>> splitBy(final ANY0[] toTest, final ANY1[] values, final UnaryFunction<? extends T0, ANY0> key) {
        return this.splitBy (Arrays.asList(toTest), Arrays.asList(values), key);
	}
}
