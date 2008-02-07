package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;

import net.homelinux.chaoswg.io.makejavauseful.datatypes.Pair;

public interface UnaryPredicate<T0> extends UnaryFunction<Boolean, T0>,NAryPredicate {
    public UnaryPredicate<T0> disjoin (final UnaryPredicate<T0>... unaryPredicates);
    public UnaryPredicate<T0> conjoin (final UnaryPredicate<T0>... unaryPredicates);
    public UnaryPredicate<T0> complement ();
    
    public ConstantPredicate curry (final T0 t0);
    public ConstantPredicate rcurry (final T0 t0);
    
    public <T1> UnaryPredicate<T1> compose (final UnaryFunction<T0, T1> unaryFunction);
    public <T1,T2> BinaryPredicate<T1,T2> compose (final BinaryFunction<T0, T1, T2> binaryFunction);
    public <T1,T2,T3> TernaryPredicate<T1,T2,T3> compose (final TernaryFunction<T0, T1, T2, T3> ternaryFunction);
    public <T1,T2,T3,T4> QuadtaryPredicate<T1,T2,T3,T4> compose (final QuadtaryFunction<T0, T1,T2,T3,T4> quartaryFunction);
    public <T1,T2,T3,T4,T5> QuintaryPredicate<T1,T2,T3,T4,T5> compose (final QuintaryFunction<T0, T1,T2,T3,T4,T5> quintaryFunction);
    
    public Boolean any (final List<? extends T0> l);
    public Boolean every (final List<? extends T0> l);

    public List<T0> filter (final List<? extends T0> l);
    public <ANY>List<ANY> filter (final List<ANY> l, final UnaryFunction<? extends T0,ANY> key);
    
    public <ANY> List<ANY> filterBy (final List<? extends T0> toTest, final List<ANY> values);
    public <ANY0, ANY1> List<ANY1> filterBy (final List<ANY0> toTest, final List<ANY1> values, final UnaryFunction<? extends T0, ANY0> key);

    public T0 findIf (final List<? extends T0> l);
    public <ANY>ANY findIf (final List<ANY> l, final UnaryFunction<? extends T0, ANY> key);

    public T0 findIfNot (final List<? extends T0> l);
    public <ANY>ANY findIfNot (final List<ANY> l, final UnaryFunction<? extends T0, ANY> key);
    
    public Boolean any (final T0... l);
    public Boolean every (final T0... l);

    public List<T0> filter (final T0... l);
    public <ANY>List<ANY> filter (final ANY[] l, final UnaryFunction<? extends T0,ANY> key);
    
    public <ANY> List<ANY> filterBy (final T0[] toTest, final ANY[] values);
    public <ANY0, ANY1> List<ANY1> filterBy (final ANY0[] toTest, final ANY1[] values, final UnaryFunction<? extends T0, ANY0> key);
    
    public T0 findIf (final T0... l);
    public <ANY>ANY findIf (final ANY[] l, final UnaryFunction<? extends T0, ANY> key);

    public T0 findIfNot (final T0... l);
    public <ANY>ANY findIfNot (final ANY[] l, final UnaryFunction<? extends T0, ANY> key);
    
    public Pair<List<T0>, List<T0>> split (final List<? extends T0> l);
    public <ANY>Pair<List<ANY>, List<ANY>> split (final List<ANY> l, final UnaryFunction<? extends T0,ANY> key);
    
    public <ANY>Pair<List<ANY>, List<ANY>> splitBy (final List<? extends T0> toTest, final List<ANY> values);
    public <ANY0,ANY1>Pair<List<ANY1>, List<ANY1>> splitBy (final List<ANY0> toTest, final List<ANY1> values, final UnaryFunction<? extends T0,ANY0> key);
    
    public Pair<List<T0>, List<T0>> split (final T0... l);
    public <ANY>Pair<List<ANY>, List<ANY>> split (final ANY[] l, final UnaryFunction<? extends T0,ANY> key);
    
    public <ANY>Pair<List<ANY>, List<ANY>> splitBy (final T0[] toTest, final ANY[] values);
    public <ANY0,ANY1>Pair<List<ANY1>, List<ANY1>> splitBy (final ANY0[] toTest, final ANY1[] values, final UnaryFunction<? extends T0,ANY0> key);
    
}
