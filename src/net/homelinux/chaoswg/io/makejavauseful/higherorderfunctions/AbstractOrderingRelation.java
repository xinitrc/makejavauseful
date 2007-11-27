package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.Comparator;
import java.util.Collections;

/**
 * Abstract implementation of the OrderingRelation interface, implementing all of the service functionality. So a OrderingRelation-Author has just to write the apply-method  
 * @author martin
 *
 */
public abstract class AbstractOrderingRelation<T> extends AbstractBinaryPredicate<T,T> implements OrderingRelation<T> {
    /**
     * Inner class representing the standard equality relation
     */
    private final EquivalenceRelation<T> EQUAL = new AbstractEquivalenceRelation<T> () {
        /**
         * Encapsulates an equal test of all the arguments
         */
        public final Boolean apply (final T t0, final T t1) {
            return t0.equals(t1);
        }
    };
    
    /**
     * Inner class representing th identity function
     */
    private final UnaryFunction<T,T> IDENTITY = new AbstractUnaryFunction<T,T> () {
        /**
         * Encapsulating the identity function
         */
        public final T apply (final T t0) {
            //Just return the first argument of the given array
            return t0;
        }
    };
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#sort(java.util.List)
     */
    public final List<T> sort (final List<T> l) {
        //Call the sort function with the list, the IDENTITY Function and EQUAL function
        return sort (l, IDENTITY, EQUAL);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#sort(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.EquivalenceRelation)
     */
    public final List<T> sort (final List<T> l, final EquivalenceRelation<T> er) {
        //Call the sort function with the list, the IDENTETY Function and er 
        return sort (l, IDENTITY, er);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#sort(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <ANY> List<ANY> sort (final List<ANY> l, final UnaryFunction<T,ANY> key) {
        //Call the sort function with the list, the key Function and EQUAL
        return sort (l, key, EQUAL);
    }

    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#sort(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function, net.homelinux.chaoswg.io.effizientealgorithmen.functional.EquivalenceRelation)
     */
    public final <ANY> List<ANY> sort (final List<ANY> l, final UnaryFunction<T, ANY> key, final EquivalenceRelation<T> er) {
        //First capture the orderingrelation for the inner class
        final OrderingRelation<T> thisOne = this;
        //And create a copy of the given list
        final List<ANY> toReturn = new Vector<ANY> (l);
        
        //Create an new comparator
        final Comparator<ANY> cmp = new Comparator<ANY> () {
            //Whose compare function ...
            public int compare (ANY o1, ANY o2) {
                //... first takes the keys of the given objects ...
                T k1 = key.apply (o1);
                T k2 = key.apply (o2);
                //... then applys the equivalence relation to them ...
                if (er.apply (k1, k2)) {
                    //... if it holds return 0 (signaling equality)
                    return 0;
                }
                //... otherwise ... 
                if (thisOne.apply (k1, k2)) {
                    //... if the predicate holds k1 is "smaller" than k2... 
                    //... so returns -1 
                    return -1;
                }
                //... or again otherwise returns 1 signaling k1 "bigger" than k2
                return 1;
            }
        };

        //Then sorts the copy with the JDK-Implementation of Merge-Sort
        //I HATE INPUTOUTPUT PARAMETERS, GRRRRRRRRRRRRRR
        Collections.sort (toReturn, cmp);
        
        //And returns the sorted list
        return toReturn;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectMaximum(java.util.List)
     */
    public final T selectMaximum (final List<T> l) {
        //Call the same method with IDENTITY as key function
        return selectMaximum(l, IDENTITY);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectMinimum(java.util.List)
     */
    public final T selectMinimum (final List<T> l) {
        //Call the same method with IDENTITY as key function
        return selectMinimum(l, IDENTITY);
    }

    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectUpperMedian(java.util.List)
     */
    public final T selectUpperMedian (final List<T> l) {
        return selectUpperMedian(l, IDENTITY);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectLowerMedian(java.util.List)
     */
    public final T selectLowerMedian (final List<T> l) {
        return selectLowerMedian(l, IDENTITY);
    }

    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectNthInOrder(int, java.util.List)
     */
    public final T selectNthInOrder (final int n, final List<T> l) {
        return selectNthInOrder(n, l, IDENTITY);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectMaximum(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <ANY> ANY selectMaximum (final List<ANY> l, final UnaryFunction<T, ANY> key) {
        //First we create an iterator ...
        final Iterator<ANY> iter = l.iterator();
        //... and take the first element of the list to be the maximum ...
        ANY maximum = iter.next();
        
        //... now we iterate over the rest of the list ...
        ANY next = null;
        while (iter.hasNext()) {
            //... taking the next element ...
            next = iter.next();
            //... if the ordering relation holds for the current maximums key and ...
            //... the next elements key ... 
            if (apply (key.apply(maximum), key.apply(next))) {
                //... the maximum becomes the next value ...
                maximum = next;
            }
        }
        
        //... after we are done iterating whe return the found maximum       
        return maximum;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectMinimum(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <ANY> ANY selectMinimum (final List<ANY> l, final UnaryFunction<T,ANY> key) {
        //As with the maximum ...
        //We first get an iterator for the list ...
        final Iterator<ANY> iter = l.iterator();
        //...take the first argument as minimum ...
        ANY minimum = iter.next();
        
        //Iterator over the list ...
        ANY next = null;
        while (iter.hasNext()) {
            //...takingt the next argument ... 
            next = iter.next();
            //... if the relation doesn't hold true for the keys of the ...
            //... current maximum and the next element ... 
            if (!apply (key.apply(minimum), key.apply (next))) {
                //... The minimum bekomes the next value ...
                minimum = next;
            }
        }
        //.. and we return the minimum
        return minimum;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectUpperMedian(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <ANY> ANY selectUpperMedian (final List<ANY> l, final UnaryFunction<T,ANY> key) {
        int listSize = l.size();
        
        if (listSize <= 5) {
            return upperMedianOf5(l,key);
        }
        
        return selectNthInOrder( ((int)Math.ceil(listSize/2)), l, key );        
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectLowerMedian(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <ANY> ANY selectLowerMedian (final List<ANY> l, final UnaryFunction<T,ANY> key) {
        int listSize = l.size();
        
        if (listSize <= 5) {
            return lowerMedianOf5(l,key);
        }
         
        return selectNthInOrder( ((int)Math.floor(listSize/2)), l, key );        
    }

    /* 
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.OrderingRelation#selectNthInOrder(int, java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <ANY> ANY selectNthInOrder (final int n, final List<ANY> l, final UnaryFunction<T,ANY> key) {
        //To find nTh in order we need the size of the list ...
        int listSize = l.size();
        //... if it is smaller than the n we are searching for ...
        if (listSize <= n) {
            //... we can't return anything ...
            return null;
        }
        //... if it is zero 
        if (listSize == 0) {
            //... we wan't to find the minimum really ...
            //... so we do ...
            return selectMinimum(l, key);
        }
        //... and if it is the size of the list minus one ...
        else if (listSize == (listSize - 1)) {
            //... we want to find the maximum so ...
            //... we do ...
            return selectMaximum(l, key);
        }
        //... otherwise we really need to search ...
        //... and we use the randomized algorithm ...
        //... with the expected runtime of O(n)
        return selectNthInOrderRandom(n, l, key);
    }
    
    private final <ANY> ANY selectNthInOrderRandom (final int n, final List<ANY> l, final UnaryFunction<T,ANY> key) {
        //To find it we need the size of the list ...
        int listSize = l.size();
        //... if there is just one element left ...
        if (listSize == 1) {
            //... this is it we return it 
            return l.get(0);
        }
        
        //... otherwise there is a little more to it ...
        //... we need a random generator 
        Random rand = new Random();
        //... to find a pivot element which is random ...
        //... elemen of the list 
        ANY pivot = l.get (rand.nextInt(listSize));
        
        //... then we partition ... 
        //... the elements smaller than the pivot ...
        List<ANY> lower = complement ().curry(key.apply (pivot)).filter(l, key);
        //... the elements larger than the pivot ...
        List<ANY> upper = complement ().rcurry(key.apply (pivot)).filter(l, key);
        //... and those that are the same as the pivot 
        List<ANY> same = new Vector<ANY>(l);
        same.removeAll(lower);
        same.removeAll(upper);
        
        //... and we need the sizes of the lists ...
        //... at least the first one of them ...
        int sizeOfLower = lower.size();
        int sizeOfSame = same.size();
        
        //... if the list of elements smaller than the pivot ... 
        if (n < sizeOfLower) {
            //... the element we want to find is in this list ... 
            //... so search on there ...
            return selectNthInOrderRandom( n, lower, key);
        }
        //... otherwise if is is larger than the lower list ...
        //... and inbetween the lower and the larger list ...
        else if (n < (sizeOfLower + sizeOfSame)) {
            //... it is safe to say the element we want to ...
            //... find is the pivot ...
            return pivot;
        }
        //... otherwise the element to search is in the upper list ...
        //... so we need to find the n - sizeOfLower - sizeOfSame ...
        //... of the upper list, because even if the searched element...
        //... is the first element of upper it is at least ... 
        //... the (sizeOfLower + sizeofSame)th element 
        return selectNthInOrderRandom( (n - sizeOfLower - sizeOfSame), upper, key);
    }
    
    private final <ANY> ANY upperMedianOf5 (final List<ANY> l, final UnaryFunction<T, ANY> key) {
        //To find the upper median of 5 elements we just sort the list
        List<ANY> tmpList = sort (l, key);

        //Take it's size ...
        int tmpSize = l.size();
        //Divide it by 2 and return the cell element at that position ...
        return tmpList.get ( ((int)Math.ceil(tmpSize / 2)) );
    }
    
    private final <ANY> ANY lowerMedianOf5 (final List<ANY> l, final UnaryFunction<T,ANY> key) {
        //For 5 elements we just sort  ...
        List<ANY> tmpList = sort (l, key);
        //... take the size of the list ...
        int tmpSize = l.size();
        //... and return the floor (size / 2)'s element of the list
        return tmpList.get ( ((int)Math.floor(tmpSize / 2.0)) );
    }   
}