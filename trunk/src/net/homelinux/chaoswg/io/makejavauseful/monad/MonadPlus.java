package net.homelinux.chaoswg.io.makejavauseful.monad;

public interface MonadPlus<A> extends Monad<A> {
    /*
     * MonadePlus law
     * 
     * 1) mzero >>= f == mzero
     * 2) m >>= (\x -> mzero) == mzero
     * 3) mzero 'mplus' m == m
     * 4) m 'mplus' mzero == m
     * 
     * empty == mzero
     * plus == mplus
     * 
     * Think of mzero as 0, mplus as + and >>= as * then these are easy to remember 
     * 
     */
    
    public <T extends MonadPlus<A>> T empty ();
    public <T extends MonadPlus<A>> T plus (T t2);
}
