package net.homelinux.chaoswg.io.makejavauseful.datatypes;

public final class Quad<F, S, T, FO> implements Tupel {
    private F first;
    private S second;
    private T third;
    private FO forth;
    
    public Quad() {
    }
    
    public Quad(F first, S second, T third, FO forth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
    }
    
    public final F getFirst() {
        return first;
    }
    public final void setFirst(F first) {
        this.first = first;
    }
    public final FO getForth() {
        return forth;
    }
    public final void setForth(FO forth) {
        this.forth = forth;
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
        if (obj instanceof Quad) {
            Quad<F, S, T, FO> tmp = ((Quad<F, S, T, FO>)obj);
            return tmp.first.equals(this.first) && tmp.second.equals(this.second) && tmp.third.equals(this.third) 
                && tmp.forth.equals(this.forth);
        }
        
        return false;
    }
    
    
    public final String toString () {
        return "{" + this.first.toString() + ", " + this.second.toString() + ", " + this.third.toString() + ", " + this.forth.toString() + "}"; 
    }
    
    public int getSize () {
        return 4;
    }

    public int hashCode () {
        return first.hashCode() ^ second.hashCode() ^ third.hashCode() ^ forth.hashCode(); 
    }
}
