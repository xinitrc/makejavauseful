package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

public interface ConstantPredicate extends ConstantFunction<Boolean>,NAryPredicate {
    public ConstantPredicate disjoin (final ConstantPredicate... unaryPredicates);
    public ConstantPredicate conjoin (final ConstantPredicate... unaryPredicates);
    public ConstantPredicate complement ();
}
