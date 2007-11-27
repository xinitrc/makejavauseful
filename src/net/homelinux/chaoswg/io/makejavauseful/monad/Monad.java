package net.homelinux.chaoswg.io.makejavauseful.monad;

import net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions.UnaryFunction;

public interface Monad<A> {
    /* _Monade laws_
     * 
     * 1) (return x) >>= f == f x
     * 2) m >>= return == m
     * 3) (m >>= f) >>= g == m >>= (\x -> f x >>= g)
     * 
     * wrap == return
     * pass == bind == >>=
     * 
     */
    
    
    public <T extends Monad<A>> T wrap (A a);
    public <T extends Monad<A>> T pass (UnaryFunction<T, A> f); 
}
