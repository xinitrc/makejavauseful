package net.homelinux.chaoswg.io.makejavauseful.datatypes.monads;

import net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions.UnaryFunction;
import net.homelinux.chaoswg.io.makejavauseful.monad.Monad;

public class Identity<A> implements Monad<A> {
    A content = null;
    
    private Identity (A a) {
        this.content = a;
    }
    
    public <T extends Monad<A>> T pass (UnaryFunction<T, A> f) throws IllegalArgumentException {
        throw new IllegalArgumentException ();
    }
    
    public <B> Identity<B> pass(UnaryFunction<Identity<B>, A> f) {
        return f.apply(content);
    }
   
   public <T extends Monad<A>> T wrap (A a) {
        return ((T)new Identity<A>(a));
    }
}
