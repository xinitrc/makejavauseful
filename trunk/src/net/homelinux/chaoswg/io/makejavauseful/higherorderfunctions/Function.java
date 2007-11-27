package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;
/**
 * Interface for a higher order function.
 * Through this interface it is possible to generate a higher order function
 * these functions can be passed as arguments or return as result of a function
 * 
 * This allows a functional programming style, as well as makes it possible to 
 * write classes just implementing one or more of the algorithms.
 *  
 * @author martin
 *
 * @param <R> The returntype of the function
 */
public interface Function<R> {
    /**
     * Applys the function an the arguments
     * @param arguments The arguemnts to apply the function on to my great regreds this can't be made typesafe in the interface
     * @return Returns a value of the type specified as <R>
     */
    public R apply (final Object... arguments) throws IllegalArgumentException;
    /**
     * This methods insertes the given arguemnts left-to-right order from the left in the functions apply-method and returns a Function whose apply-function is altered this way  
     * @param arguments Arguments which should be inserted into the functions apply method
     * @return Function with the parameters already inserted as arguments
     */
    /*Example:  Let Function f be f: Number x Number |-> Number
     *                               x,y -> x + y
     *          g = f.curry (5) returns a function where the leftmost value is set to five
     *          so                g: Number |-> Number
     *                               y -> 5 + y 
     */ 
    public Function<R> curry (final Object... arguments);
    /**
     * This method inserts the given arguments left-to-right order to the right side of the arguments and returns a Function whose apply-method has is altered this way 
     * @param arguments Arguments which should be inserted into the functions apply method
     * @return Function with the parametes already inserted as arguments
     */
    /*Example:  Let Function f be f: Number x Number |-> Number
     *                               x,y -> x + y
     *          g = f.rcurry (5) returns a function where the rightmost value is set to five
     *          so                g: Number |-> Number
     *                               x -> x + 5 
     */ 
    public Function<R> rcurry (final Object... arguments);
    /**
     * This method implements the mathmatical compose operation on functions. As a shortcut it allows more than one function to be composed with
     * @param functions The function(s) to compose this function with
     * @return New function which is the composition of the function and the given arguments
     */
    /* The compose operation works like the mathmatical composition-operation 
     * Example: Let f,g,h be functions
     *              f.compose (g, h).apply (x) \equiv f \circle g \circle h (x) \equiv f ( g ( h (x)))   
     */
    public Function<R> compose (final Function... functions);
    
    /**
     * This method applies the function to all the arguments of the list
     * @param lists Lists to apply the function to 
     * @return A (new) list with the function applied to every element of the parameter
     */
    /* If f is a n-arry function and n lists are given as parameters 
     * f is applied to the first element of every list the result is saved in the list to be returned
     * the shortes inputlist determins the length of the resultlist
     */
    public List<R> mapcar (final List... lists);
    
    /**
     * Works almost like mapcar, but works on consecutive sublists
     * @param lists The lists to operate the function on
     * @return The list returned by applying the function on consecutive sublists
     */
//    public List<R> maplist (final List... lists);
    
    /**
     * The function applies a binary operation to the first two elements of the list then apply the function to the result and the next element of the list and so on
     * @param l The list to reduce
     * @return The result of reducing the list left-to-right order
     */
    public R reduce (final List<? extends R> l);
    
    /**
     * This method applies the function to all the arguments of the array
     * @param arrays Arrays to apply the function to
     * @return A (new) list with the function applied to every element of the parameter
     */
    public List<R> mapcar (final Object[]... arrays);
    
    /**
     * Works almost like mapcar, but works on consecutive sublists
     * @param arrays The arrays to operate the function on
     * @return The list returned by applying the function on consecutive sublists
     */
//    public List<R> maplist (final Object[]... arrays);
    
    /**
     * The function applies a binary operation to the first two elements of the array then apply the function to the result and the next element of the array and so on
     * @param l The list to reduce
     * @return The result of reducing the list left-to-right order
     */
    public R reduce (final R... array);
}
