package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;

/**
 * An Interface holding a Predicat
 * @author martin
 *
 */
public interface Predicate extends Function<Boolean> {
    /**
     * Returns a Predicate which is the disjunction of this and all given predicates
     * @param predicates The predicates to disjoin this function with
     * @return A new Predicate which is the disjunction of this and all given predicates
     */
    public Predicate disjoin (final Predicate... predicates);
    
    /**
     * Returns a Predicate which is the conjunction of this and all given predicates
     * @param predicates The predicates to conjoin this function with
     * @return A new Predicate which is the conjunction of this and all given predicates
     */
    public Predicate conjoin (final Predicate... predicates);
    
    /**
     * Returns a Predicate which is the complement of this
     * @return The complement Predicate to this
     */
    public Predicate complement ();

    /**
     * Returns as predicate with the arguments already set as inputs for this predicate (left-to-right order, starting with the leftmost)
     * @param arguments The arguments to put into the leftmost values
     * @return A Predicate with the leftmost arguments already set to the parameters  
     */
    /*
     * For further explanation of currying see Function (or any good book about haskell or dylan)
     */
    public Predicate curry (final Object... arguments);
    /**
     * Returns a predicate with the arguments already set as inputs for this predicate (left-to-right order, starting with last-#arguments)
     * @param arguments The arguments to put into the righmost values
     * @return A Predicate with the rightmost arguments already set to the parameters
     */
    /*
     * For further explanation of rcurrying see Function (or any good book about dylan (haskell has no such concept))  
     */
    public Predicate rcurry (final Object... arguments);
    
    /**
     * Composes this predicate with the given functions and returns a new Predicate applying this composition
     * @param functions The functions to compose this Predicate with
     * @return A predicate which is the composition of the Predicate with all the functions 
     */
    public Predicate compose (final Function... functions);
    
    /**
     * Finds out if this predicate holds true for any of the elements of the list
     * @param <T> The type of the elements of the list
     * @param l The list to check on
     * @return True if there is at least one value for which this predicate returns true
     */
    public <T> Boolean any (final List<T> l);
    
    /**
     * Finds out if the predicate holds true for every elements of the list
     * @param <T> The type of the elements of the list
     * @param l The list ot check on 
     * @return True if all elements the predicate holds true
     */
    public <T>Boolean every (final List<T> l);
    
    /**
     * Filters out every element for which the predicate is false
     * @param <T> The type of elements in the list
     * @param l The list to filter
     * @return A (new) list with all elements from the inputed list removed for which the predicate is false
     */
    public <T> List<T> filter (final List<T> l);

    /**
     * Filters out every element for which the predicate is false
     * @param <T> The type of elements in the list
     * @param <R> The returntype of the key function
     * @param l The list to filter
     * @param key The function to get the key of the value 
     * @return A (new) list with all elements from the inputed list removed for which the predicate is false for the key of the element
     */
    public <T,R> List<T> filter (final List<T> l, final Function<R> key);
    
    /**
     * Filters out every element for which the corresponding testValue is false 
     * @param <T> The type of the list to return and the values are 
     * @param toTest The list the predicate is applied to
     * @param values The list the values are selected from
     * @return A (new) list containing only the values from the values list for whose indexes the predicate applied to the testlist holds true
     */
    public <TT, T> List<T> filterBy (final List<TT> toTest, final List<T> values);
    
    /**
     * Filters out every element for which teh corresponding testValue is false
     * @param <TT> The type of the testvalues
     * @param <T> The type of the listvalues and the returned list
     * @param <R> The returntype of the keyfunction
     * @param toTest The list to test the test
     * @param values The list to take the values from
     * @param key The function to get the key to filter by
     * @return A (new) list containing only those values from the values list for whose indexes the predicate holds true for key of the testvalue
     */
    public <TT, T, R> List<T> filterBy (final List<TT> toTest, final List<T> values, final Function<R> key);
    
    /**
     * Finds out if this predicate holds true for any of the elements of the array
     * @param <T> The type of the elements of the array
     * @param array The array to check on
     * @return True if there is at least one value for which this predicate returns true
     */
    public <T>Boolean any (final T... array);

    /**
     * Finds out if the predicate holds true for every elements of the aray
     * @param T The type of the elements of the array
     * @param array The list ot check on 
     * @return True if all elements the predicate holds true
     */
    public <T>Boolean every (final T... array);

    /**
     * Filters out every element for which the predicate is false
     * @param <T> The type of elements in the array
     * @param l The list to filter
     * @return A (new) list with all elements from the inputed list removed for which the predicate is false
     */
    public <T> List<T> filter (final T... array);
    
    /**
     * Filters out every element for which the predicate is false
     * @param <T> The type of elements in the array
     * @param <R> The returntype of the key function
     * @param array The array to filter
     * @param key The function to get the key of the value 
     * @return A (new) list with all elements from the inputed array removed for which the predicate is false for the key of the element
     */
    public <T,R> List<T> filter (final T[] array, final Function<R> key);
    
    /**
     * Filters out every element for which the correspondig testValue is false 
     * @param <T> The type of the list to return and the values are 
     * @param toTest The array the predicate is applied to
     * @param values The array the values are selected from
     * @return A (new) list containing only the values from the values array for whose indexes the predicate applied to the testarray holds true
     */
    public <TT,T> List<T> filterBy (final TT[] toTest, final T[] values);
    
    /**
     * Filters out every element for which the corresponding testValue is false
     * @param <TT> The type of the testvalues
     * @param <T> The type of the arrayvalues and the returned list
     * @param <R> The returntype of the keyfunction
     * @param toTest The array to test the test
     * @param values The array to take the values from
     * @param key The function to get the key to filter by
     * @return A (new) list containing only those values from the values array for whose indexes the predicate holds true for key of the testvalue
     */
    public <TT,T,R> List<T> filterBy (final TT[] toTest, final T[] values, final Function<R> key);
    
    /**
     * Method finding an element of the list that satisfies the Predicate
     * @param <T> The type of the listelements
     * @param l The list containing the elements to be searched
     * @return An element of the list that satisfying the predicate or null
     */
    public <T> T findIf (final List<T> l);
    
    /**
     * Method finding an element of the list that doesn't satisfies the Predicate
     * @param <T> The type of the listelements
     * @param l The list containing the elements to be searched
     * @return An element of the list that doesn't satisfying the predicate
     */
    public <T> T findIfNot (final List<T> l);

    /**
     * Method finding an element of the list whoese key satisfies the Predicate
     * @param <T> The type of the listelements
     * @param <R> The returntype of the keyfunction
     * @param l The list containing the elements to be searched
     * @return An element of the list whoes key is satisfying the predicate
     */
    public <T,R> T findIf (final List<T> l, final Function<R> key);
    
    /**
     * Method finding an element of the list whose key doesn't satisfies the Predicate
     * @param <T> The type of the listelements
     * @param <R> The returntype of the keyfunction
     * @param l The list containing the elements to be searched
     * @return An element of the list whoes key doesn't satisfying the predicate
     */
    public <T,R> T findIfNot (final List<T> l, final Function<R> key);
}
