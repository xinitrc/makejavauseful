package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Arrays;
/**
 * Abstract implementation of the Predicate interface. Implementing all service-functions. So a Predicate-Author just needs to implement the apply method 
 * @author martin
 *
 */
public abstract class AbstractPredicate extends AbstractFunction<Boolean> implements Predicate {
    /**
     * Inner class to represent the Identity function
     */
    private final Function<Object> IDENTITY = new AbstractFunction<Object> () {
        //Whose apply method ...
        public final Object apply (final Object... args) {
            //... just returns it's first argument
            return args[0];
        }
    };
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#disjoin(net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate[])
     */
    public final Predicate disjoin (final Predicate... predicates) {
        /*
         * All this just works because of the magic of something similar to 
         * lexical closure, or let's say something Java thinks is lexical closure
         */
        
        //First we create a final reference to this predicate ...
        final Predicate thisOne = this;
        //The we create an anonymous AbstractPredicate to turn ...
        final AbstractPredicate ap = new AbstractPredicate () {
            //... whose apply function ...
            public final Boolean apply (Object... arguments) {
                // ... first tests if this predicate holds true ...
                if (thisOne.apply (arguments)) {
                    //... if so returns true ...
                    return true;
                }
                //... otherwise iterates over the other predicates ...
                for (final Predicate p : predicates) {
                    //... and checks if one of them is true ...
                    if (p.apply (arguments)) {
                        //... if so returns true
                        return true; 
                    }
                }
                //... otherwise we have not found a predicate which is true ...
                //... so the disjunction is false
                return false; 
            }
        };
        
        //... now we return the newly created AbstractPredicate
        return ap;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#conjoin(net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate[])
     */
    public final Predicate conjoin (final Predicate... predicates) {
        /*
         * All this just works because of the magic of something similar to 
         * lexical closure, or let's say something Java thinks is lexical closure
         */
        
        //Similarly to disjoin...
        
        //Conjoin first needs a final reference to this predicate ...
        final Predicate thisOne = this;
        
        //... and creates a new AbstractPredicate ...
        final AbstractPredicate ap = new AbstractPredicate () {
            //Whose apply function ... 
            public final Boolean apply (Object... arguments) {
                //... first checks if the predicate itself returns false ...
                if (!thisOne.apply (arguments)) {
                    //... in that case returns false ...
                    return false;
                }
                //... and otherwise iterates over the other predicaes ...
                for (final Predicate p : predicates) {
                    //... and if one of them returns false ...
                    if (!p.apply (arguments)) {
                        //... returns false ... 
                        return false;
                    }
                }
                //... otherwise all the predicates returned true so the 
                //... conjunction must be true also
                return true;
            }
        };
        
        //... and then returns the newly created AbstractPredicate
        return ap;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#complement()
     */
    public final Predicate complement () {
        /*
         * All this just works because of the magic of something similar to 
         * lexical closure, or let's say something Java thinks is lexical closure
         */
        
        //As with disjoin and conjoin ... 
        // Complement needs a reference to the predicate 
        final Predicate thisOne = this;
        
        //... and creates a new AbstractPredicate ... 
        final AbstractPredicate ap = new AbstractPredicate () {
            //... whose apply function ... 
            public final Boolean apply (Object... arguments) {
                //... applys the original predicate and returns ...
                //... the opposite boolean value 
                return ! thisOne.apply (arguments);
            }
        };
        
        //.. and returns the AbstractPredicate
        return ap;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#anyP(java.util.List)
     */
    public final <T>Boolean any (final List<T> l) {
        //To check if the predicate holds for any of the lists elements ... 
        //we iterate over the list 
        for (final T t : l) {
            //... and apply the predicate to the element
            if (apply (t)) {
                //... if it holds true, our quest is over and we found
                //... at least one element for which the predicate holds
                //... true ...
                return true;
            }
        }
        
        //... otherwise we are not so lukcy and return false       
        return false;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#every(java.util.List)
     */
    public final <T>Boolean every (final List<T> l) {
        //As with anyP... 
        //... To check if all elements satisfy the predicate ...
        //... we iterate over the list ...
        for (final T t : l) {
            //... if for the element the Predicate doesn't hold ... 
            if (! apply (t)) {
                //... we return false
                return false;
            }
        }
        //... otherwise we are lucky all the elements satisfy the predicate 
        //... so we return true
        return true;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#filter(java.util.List)
     */
    public final <T> List<T> filter (final List<T> l) {
        return filter (l, IDENTITY);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#filter(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <T,R> List<T> filter (final List<T> l, final Function<R> key) {
        //To filter the list by this predicate
        //.. we first make new list to return later
        final List<T> toReturn = new Vector<T> ();
        
        //... we iterate over the list ...
        for (final T t : l) {
            //... if the predicate holds for the key of the element ... 
            if (apply (key.apply (t))) {
                //... we collect it in the list to return ...
                toReturn.add (t);
            }
        }
        
        //... in the end we return the newly build list 
        return toReturn;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#filterBy(java.util.List, java.util.List)
     */
    public final <TT,T>List <T> filterBy (final List<TT> toTest, final List<T> values) {
        return filterBy (toTest, values, IDENTITY);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#filterBy(java.util.List, java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <TT,T,R> List<T> filterBy (final List<TT> toTest, final List<T> values, final Function<R> key) {
        //As with ordernary filtering ...
        //... if we are filtering the by another list ...
        //... we first create a new list to later return
        final List<T> toReturn = new Vector<T>();
        
        //... and then we need to iterate over the List ...
        //... so we first create iterators to iterate over the lists... 
        final Iterator<TT> ttIter = toTest.iterator();
        final Iterator<T> valIter = values.iterator();
        
        //... and while both have further values ...
        while (ttIter.hasNext() && valIter.hasNext()) {
            //... we take the next values ... 
            final TT toTst = ttIter.next();
            final T val = valIter.next();
            
            //... check if the predicate holds true for the key of the testValue ...
            if (apply (key.apply(toTst))) {
                //... and if so add the value to the list we are going ...
                //... to return
                toReturn.add (val);
            }
        }
        
        //.. in the end return the newly created list
        return toReturn;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#anyP(java.lang.Object[])
     */
    public final <T>Boolean any (final T... array) {
        //We just convert the array to list and use the anyP-Function for lists 
        return any (Arrays.asList (array));  
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#every(java.lang.Object[])
     */
    public final <T>Boolean every (final T... array) {
        //As with anyP we convert the array to a list and use the everyP function for lists
        return every (Arrays.asList (array));
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#filter(null[])
     */
    public final <T> List<T> filter (final T... array) {
        //As with both of the other functions we convert the array to a list and use the function for lists
        return filter (Arrays.asList (array));
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#filter(null[], net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <T,R> List<T> filter (final T[] array, final Function<R> key) {
        //As with the other array operations we just convert the array to lists and use the list functions
        return filter (Arrays.asList(array), key);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#filterBy(java.lang.Object[], null[])
     */
    public final <TT, T> List<T> filterBy (final TT[] toTest, final T[] values) {
        //As with the other functions we convert the arrays to lists and use the function for lists
        return filterBy (Arrays.asList (toTest), Arrays.asList (values));
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#filterBy(null[], null[], net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <TT,T,R> List<T> filterBy (final TT[] toTest, final T[] values, final Function<R> key) {
        //As with the other array operations we just convert the array to lists and use the list functions
        return filterBy (Arrays.asList(toTest), Arrays.asList(values), key);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#curry(java.lang.Object[])
     */
    public final Predicate curry (final Object... arguments) {
        /*
         * All this just works because of the magic of something similar to 
         * lexical closure, or let's say something Java thinks is lexical closure
         */
        
        
        /*
         * Obviously this is a little complicated, so first the idea behind this function:
         * Lets assume that the function we are calling curry an is called f and the function
         * curry returns is called r.
         * 
         * First r itself is a Predicate so it has an apply-method.
         * 
         * If we have called r = f.curry (x), and we call r.apply (y), our goal is 
         * to call f.apply (x, y). So what we do is we give r an reference to f
         * and to the arguments given to f.curry. In this case the array containing the x.
         * When r.apply is called, r creates a new array with the length of the array given
         * to curry plus the length of the array of arguments given to r.apply. Then first copies
         * the elements of the array of arguemtents given to f.curry in it and appends the elements
         * given to the r.apply function itself. As it has the reference to f it can call f.apply
         * with the newly created array, which contains all the arguments.  
         */

        //To accomplish that:
        //We first need a reference to this predicate ...
        final Predicate thisOne = this;
        //... then we create a new AbstractPredicate ... 
        final AbstractPredicate ap = new AbstractPredicate () {
            //... whose apply function ...
            public final Boolean apply (Object... args) {
                //... first creates a new array for the all the arguments ...
                //... the length is the length of the arguments of array of arguments ...
                //... that should be curryed in plus the number of arguments passed to ...
                //... this Function ... 
                Object allArgs [] = new Object [arguments.length + args.length];
                
                //... then start with the arguments that should be curryed in ... 
                int i = 0;
                //... iterate over them ...
                for (i = 0; i < arguments.length; ++i) {
                    //... and put them in the array of all arguments ...                    
                    allArgs[i] = arguments[i];
                }
                //... then add the arguments passed to newly created function ...
                for (int j = 0; j < args.length; ++j) {
                    //... and put them behind the other arguments in the array ... 
                    allArgs[i+j] = args[j];
                }
                //... then take the newly create array as the array of arguments for the ...
                //... original function
                return thisOne.apply (allArgs); 
            }
        };
        
        //... and then return the newly created AbstractPredicate
        return ap;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#rcurry(java.lang.Object[])
     */
    public final Predicate rcurry (final Object... arguments) {
        /*
         * Obviously this is a little complicated, so first the idea behind this function:
         * Lets assume that the function we are calling curry an is called f and the function
         * curry returns is called r.
         * 
         * First r itself is a Predicate so it has an apply-method.
         * 
         * If we have called r = f.rcurry (x), and we call r.apply (y), our goal is 
         * to call f.apply (y, x). So what we do is we give r an reference to f
         * and to the arguments given to f.rcurry. In this case the array containing the x.
         * When r.apply is called, r creates a new array with the length of the array given
         * to rcurry plus the length of the array of arguments given to r.apply. Then first copies
         * the elements of the array of arguemtents given to r.apply in it and appends the elements
         * given to the f.curry function itself. As it has the reference to f it can call f.apply
         * with the newly created array, which contains all the arguments.
         * (OBVIOUSLY JUST THE ORDER OF THE ELEMENTS IN THE ARRAY IS REVERSE COMPARED TO CURRY)  
         */
        
        //To accomplish this: 
        //We first need a reference to this predicate... 
        final Predicate thisOne = this;
        //... then we create a new AbstractPredicate ...
        final AbstractPredicate ap = new AbstractPredicate () {
            //... whose apply function ...
            public final Boolean apply (Object... args) {
                //... first creates a new Object-Array with the length ...
                //... of the sum of the arguments to rcurry and to this apply ... 
                Object allArgs [] = new Object [arguments.length + args.length];
                
                //... then we start iterating over the arguments given to this ...
                //... apply ...
                int i = 0;
                for (i = 0; i < args.length; ++i) {
                    //... and copy it to the array containing allArguments ...
                    allArgs [i] = args [i];
                }
                //... then we take the arguments given to curry ... 
                for (int j = 0; j < arguments.length; ++j) {
                    //... and copy them at the end of the new arry ... 
                    allArgs [i+j] = arguments [j];
                }
                
                //... and then apply the original function to the newly ...
                //... created array
                return thisOne.apply (allArgs);
            }
        };
        
        //... and return the newly created AbstractPredicate
        return ap;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#compose(net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate[])
     */
    public final Predicate compose (final Function... functions) {
        //First we need a reference to this predicate ... 
        final Predicate thisOne = this;
        //... then we create a new AbstractPredicate ... 
        final AbstractPredicate ap = new AbstractPredicate () {
            //... whose apply-Function ... 
            public final Boolean apply (Object... arguments) {
                //... then we need a array for arguments ... 
                //... we then initialise it with the arguments ...
                Object [] args = arguments;
                
                //... adn then we iterate over the functions given ...
                //... starting from the last one ...
                for (int i = functions.length - 1; i >= 0; --i) {
                    //... applying it to the arguments and save the ...
                    //... returnvalues for the next iteration ... 
                    args = new Object [] {functions[i].apply (args)};
                }
                //... at last we apply the predicate itesilf to the arguments ...
                return thisOne.apply (args);
            }     
        };
        //... and return the newly created AbstractPredicate
        return ap;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#findIf(java.util.List)
     */
    public final <T> T findIf (final List<T> l) {
        //We just need to call the same function with the identity as keyfunction
        return findIf (l, IDENTITY);
    }

    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#findIfNot(java.util.List)
     */
    public final <T> T findIfNot (final List<T> l) {
        //We just need to call the same function with the identity as keyfunction
        return findIfNot(l, IDENTITY);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#findIf(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <T,R> T findIf (final List<T> l, final Function<R> key) {
        /*
         * WORD OF CAUSSION: 
         *  Even though the list is traversed in left to right order algorithms shouldn't
         *  depend on this 
         */
        //We iterate through the list ...
        for (final T t : l) {
            //... if the predicate is satisfied by the key of the element ...
            if (apply(key.apply(t))) {
                //... we return it
                return t;
            }
        }
        //... otherwise null
        return null;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Predicate#findIfNot(java.util.List, net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function)
     */
    public final <T,R> T findIfNot (final List<T> l, final Function<R> key) {
        /*
         * WORD OF CAUSSION: 
         *  Even though the list is traversed in left to right order algorithms shouldn't
         *  depend on this 
         */
        //We iterate through the list ...
        for (final T t : l) {
            //... If we find an element not satisfying the predicate ... 
            if (!apply (key.apply(t))) {
                //... we return it ... 
                return t;
            }
        }
        
        //... null otherwise 
        return null;
    }
}
