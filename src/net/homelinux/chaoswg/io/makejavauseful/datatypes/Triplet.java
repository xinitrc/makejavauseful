package net.homelinux.chaoswg.io.makejavauseful.datatypes;

public final class Triplet<F, S, T> implements Tupel {
    private F first;
    private S second;
    private T third;
    
    public Triplet () {
    }

    public Triplet (F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public final F getFirst() {
        return first;
    }

    public final void setFirst(F first) {
        this.first = first;
    }

    public final S getSecond() {
        return second;
    }

    public final void setSecond(S second) {
        this.second = second;
    }

    public final T getThird() {
        return third;
    }

    public final void setThird(T third) {
        this.third = third;
    }
    
    public final boolean equals (Object obj) {
        if (obj instanceof Triplet) {
            Triplet<F, S, T> tmp = ((Triplet<F, S, T>)obj);
            return tmp.first.equals(this.first) && tmp.second.equals(this.second) && tmp.third.equals(this.third);
        }
        
        return false;
    }
    
    
    public final String toString () {
        return "{" + this.first.toString() + ", " + this.second.toString() + ", " + this.third.toString() + "}"; 
    }
    
    public int getSize () {
        return 3;
    }
    
    public int hashCode () {
        return first.hashCode() ^ second.hashCode() ^ third.hashCode();
    }
}
