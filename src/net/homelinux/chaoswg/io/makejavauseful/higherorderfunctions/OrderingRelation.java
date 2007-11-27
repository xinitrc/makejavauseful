package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;

/**
 * Interface for an ordering relation
 * @author martin
 */
public interface OrderingRelation<T> extends BinaryPredicate<T, T> {
    /**
     * Sorts the list according to the OrderingRelation, using equal for equality-tests and the object itself as key
     * @param <T> The type contained in the list and the returntype
     * @param l The list to sort
     * @return An (new) sorted version of l 
     */
    public List<T> sort (final List<T> l);
    
    /**
     * Sorts the list according to the OrderingRelation, using er for equality-tests and the object itself as key
     * @param <T> The type contained in the list and the returntype
     * @param l The list to sort
     * @param er The Equalityrelation to use to check for equality
     * @return An (new) sorted version of l 
     */
    public List<T> sort (final List<T> l, final EquivalenceRelation<T> er);
    
    /**
     * Sorts the list according to the OrderingRelation, using equal for equality-tests and the result of key applied to the object as key
     * @param <T> The type contained in the list and the returntype
     * @param l The list to sort
     * @param key The Function to apply to the object to get the key to sort 
     * @return An (new) sorted version of l
     */
    public <ANY> List<ANY> sort (final List<ANY> l, final UnaryFunction<T, ANY> key);
    
    /**
     * Sorts the list according to the OrderingRelation, using er for equality-tests and the result of key applied to the object as key
     * @param <T> The type contained in the list and the returntype
     * @param l The list to sort
     * @param key The Function to apply to the object to get the key to sort 
     * @param er The Equalityrelation to use to check for equality
     * @return An (new) sorted version of l
     */
    public <ANY> List<ANY> sort (final List<ANY> l, final UnaryFunction<T, ANY> key, final EquivalenceRelation<T> er);
    
    /**
     * Searches a maximum of the list according to the  OrderingRelation
     * @param <T> The type of the elements of the list and the returntype 
     * @param l The list to search the maximum in
     * @return A maximum-element of the list according to the OrderingRelation 
     */
    public T selectMaximum (final List<T> l);

    /**
     * Searches a minimum of the list according to the  OrderingRelation
     * @param <T> The type of the elements of the list and the returntype 
     * @param l The list to search the maximum in
     * @return A minimum-element of the list according to the OrderingRelation 
     */
    public T selectMinimum (final List<T> l);

    /**
     * Searches the upper-median of the values of the list
     * @param <T> The type of the elements of the list to search in 
     * @param l The list to search in
     * @return The upper-median of the list according to the ordering relation
     */
    public T selectUpperMedian (final List<T> l); 
    
    /**
     * Searches the lower-median of the values of the list
     * @param <T> The type of the elements of the list to search in 
     * @param l The list to search in
     * @return The lower-median of the list according to the ordering relation
     */
    public T selectLowerMedian (final List<T> l); 
    
    /**
     * Searches An element of th list which would be in nth position after ordering
     * @param <T> The type of the list to search in
     * @param n The index of the element to be found in the ordered list
     * @param l The list to search in
     * @return The n-th element in order
     */
    public T selectNthInOrder (final int n, final List<T> l);

    /**
     * Searches An element of the list whose key is a maximum according to the  OrderingRelation
     * @param <T> The type of the listelements and the returntype 
     * @param l The list to search in 
     * @param key The function to apply to the elements to get the key to search for
     * @return An element of the the list whos key is a maximum according to the OrderingRelation
     */
    public <ANY> ANY selectMaximum (final List<ANY> l, final UnaryFunction<T, ANY> key);

    /**
     * Searches An element of the list whose key is a minimun according to the  OrderingRelation
     * @param <T> The type of the listelements and the returntype 
     * @param l The list to search in 
     * @param key The function to apply to the elements to get the key to search for
     * @return An element of the the list whos key is a minimum according to the OrderingRelation
     */
    public <ANY> ANY selectMinimum (final List<ANY> l, final UnaryFunction<T, ANY> key);
    
    /**
     * Searches the element of the list whose key upper-median according to the ordering realtion
     * @param <T> The type of the elements of the list to search in 
     * @param <R> The return type of the key function 
     * @param l The list to search in
     * @return The upper-median of the list according to the ordering relation
     */
    public <ANY> ANY selectUpperMedian (final List<ANY> l, final UnaryFunction<T, ANY> key); 

    /**
     * Searches the element of the list whose key lower-median according to the ordering realtion
     * @param <T> The type of the elements of the list to search in 
     * @param <R> The return type of the key function 
     * @param l The list to search in
     * @return The lower-median of the list according to the ordering relation
     */
    public <ANY> ANY selectLowerMedian (final List<ANY> l, final UnaryFunction<T, ANY> key); 

    /**
     * Searches an element of the list whose key would be in nth position after ordering
     * @param <T> The type of the list to search in
     * @param <R> The return type of the key function 
     * @param n The index of the element to be found in the ordered list
     * @param l The list to search in
     * @return The n-th element in order
     */
    public <ANY> ANY selectNthInOrder (final int n, final List<ANY> l, final UnaryFunction<T, ANY> key);
}