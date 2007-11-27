package net.homelinux.chaoswg.io.makejavauseful.lazyevaluation;

import net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions.ConstantFunction;

public class Delay<T> {
    private T result = null;
    protected ConstantFunction<T> cf = null;

    public Delay (ConstantFunction<T> cf) {
        this.cf = cf;
    }

    public synchronized T force () {
        if (result == null) {
            result = cf.apply ();
        }

        return result;
    }
}
