package net.homelinux.chaoswg.io.makejavauseful.datatypes;

public final class Pair<F, S> implements Tupel {
    private F first = null;
    private S second = null;
    
    public Pair () {
    }

    public Pair (F first, S second) {
        this.first = first;
        this.second = second;
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
    
    public final boolean equals (Object obj) {
        if (obj instanceof Pair) {
            Pair<F, S> tmp = ((Pair<F, S>)obj);
            
            return tmp.first.equals(this.first) && tmp.second.equals(this.second); 
        }
        
        return false;
    }
    
    
    public final String toString () {
        return "{" + this.first.toString() + ", " + this.second.toString() + "}"; 
    }
    
    public int getSize () {
        return 2;
    }
    
    public int hashCode () {
        return first.hashCode() ^ second.hashCode(); 
    }
}
