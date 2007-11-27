package net.homelinux.chaoswg.io.makejavauseful.higherorderfunctions;

import java.util.List;
import java.util.Vector;
import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractFunction<R> implements Function<R> {
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#curry(java.lang.Object[])
     */
    public Function<R> curry(final Object... arguments) {
        /*
         * Obviously this is a little complicated, so first the idea behind this function:
         * Lets assume that the function we are calling curry on is called f and the function
         * curry returns is called r.
         * 
         * First r itself is a Function so it has an apply-method.
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
        
        //To accomplish this:
        //We first need a reference to this function ... 
        final Function<R> thisOne = this;
        //... then we create a new AbstractFunction ...
        final AbstractFunction<R> af = new AbstractFunction<R> () {
            //... whose apply-method ...
            public final R apply (Object... args) {
                //... first creates a array of length of the sum of ... 
                //... the arguments-array given to the apply and to ...
                //... the curry-method... 
                Object allArgs [] = new Object [arguments.length + args.length];
                
                //... then we first iterate over the array given to curry...
                int i = 0;
                for (i = 0; i < arguments.length; ++i) {
                    //... and put it in the new allArgs-Array ...
                    allArgs[i] = arguments[i];
                }
                //... and then iterate over the array given to apply ...
                for (int j = 0; j < args.length; ++j) {
                    //... and put these at the end of the allArgs ...s
                    allArgs[i+j] = args[j];
                }
                //... and then apply the curryed function to the arguemnts
                return thisOne.apply (allArgs);	
            }
        };
        //... in the end return the newly created AbstractFunction
        return af;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#rcurry(java.lang.Object[])
     */
    public Function<R> rcurry (final Object... arguments) {
        /*
         * Obviously this is a little complicated, so first the idea behind this function:
         * Lets assume that the function we are calling curry an is called f and the function
         * curry returns is called r.
         * 
         * First r itself is a Function so it has an apply-method.
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
        //We first need a reference to the Function ...
        final Function<R> thisOne = this;
        //... then we create a new AbstracFunctions ... 
        final AbstractFunction<R> af = new AbstractFunction<R> () {
            //... Whose apply-method ... 
            public final R apply (Object... args) {
                //... First creates a new array with a lenght ...
                //... of the sum of the arrays given to apply ...
                //... and rcurry ...
                Object allArgs [] = new Object [arguments.length + args.length];
                
                //... And then iterates over the array given to ...
                //... apply ...
                int i = 0;
                for (i = 0; i < args.length; ++i) {
                    //... and copys them into the allArgs array ...
                    allArgs [i] = args [i];
                }
                
                //... then iterates over the array given to rcurry ...
                for (int j = 0; j < arguments.length; ++j) {
                    //... and appends them ...
                    allArgs [i+j] = arguments [j];
                }
                
                //...then applies the function to the newly created array ...
                return thisOne.apply (allArgs);
            }
        };
        
        //... and returns the newly created AbstractFunction 
        return af;
    }

    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#compose(net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function[])
     */
    public Function<R> compose (final Function... functions) {
        //First we need a reference to the function ...
        final Function<R> thisOne = this;
        //... then we create a new AbstractFunction ...
        final AbstractFunction<R> af = new AbstractFunction<R> () {
            //... whose apply-method ... 
            public final R apply (Object... arguments) {
                //... first creates a new args-array ...
                Object [] args = arguments;
                
                //... then iterates over the functions starting with 
                //... the last ...
                for (int i = functions.length - 1; i >= 0; --i) {
                    //... applying the function and save the returnvalues ...
                    //... for the next iteration ...
                    args = new Object [] {functions[i].apply (args)};
                }
                //.. in the end apply this function ...
                return thisOne.apply (args);
            }			
        };
        
        //... and returns the newly created AbstractFunction 
        return af;
    }
    /*
    private boolean allHaveNext (final Iterator [] iters) {
        //Method iterating over the iterators ... 
        for (Iterator iter : iters) {
            //... and checks if the iterator has a next element ...
            if (!iter.hasNext ()) {
                //... if not, obiously not all iterators have a next ... 
                return false;
            }
        }
        //... otherwise return true
        return true;
    }
    */
    
    /*
     * This function returns the array of iterators for all the
     * lists given as arguemnts
     */
    private final Iterator[] collectIterators (final List... lists) {
        //First we need an array to collect the iterators...
        Iterator iters [] = new Iterator [lists.length];
        
        //... then we iterate over the array of ...
        //... lists given as parametera ...
        int i = 0;
        for (final List l : lists) {
            //... collect the iterator ...
            iters [i] = l.iterator();
            //... count one up, to add ...
            //... the next iterator in ...
            //... the right place
            i++;
        }
        //...and return the iterators
        return iters;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#map(java.util.List[])
     */
    public final List<R> mapcar (final List... lists) {
        UnaryPredicate<Iterator> hasNext = new HasNext ();
        //To iterate over all the lists we first need ...
        //... an array holding all the iterators for the lists ...
        Iterator iters [] = collectIterators(lists);
        
        //... Then we iterate over the array holding the lists ...
        for (int i = 0; i < lists.length; ++i) {
            //... Putting the iterartor in the array ...
            iters [i] = lists[i].iterator ();
        }
        
        //... Then create a new Vector to return ... 
        final List <R> result = new Vector<R> ();
        
        //... we create a new array of arguments ... 
        Object args [] = new Object [lists.length];
        //While there are more values in all lists ...
        while (hasNext.every(iters)) {
            //... put the all the next elements ... 
            for (int i = 0; i < lists.length; ++i) {
                // ... in the argumentsarry ...
                args [i] = iters[i].next ();
            }
            //... and then we add the result of the application ...
            //... of the function to the argumentsarray to the ...
            //... resultvector
            result.add (apply (args));
        }
        
        //... and return the resultvector
        return result;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#maplist(java.util.List[])
     */
    /*
    public final List<R> maplist (final List... lists) {
        return maplistWorker(new Vector<R> (), lists);
    }
    
    private final List<R> maplistWorker (final List<R> toReturn, final List... lists) {
        if (lists.length > 0) {
            toReturn.add (apply(lists));
            return maplistWorker(toReturn, allNextSublist (lists));
        }
        return toReturn;     
    }
    
    private final List[] allNextSublist (final List[] lists) {
        List [] nextSublists = new List [lists.length];
        
        for (int i = 0; i < lists.length; ++i) {
            nextSublists [i] = lists[i].subList(1, lists[i].size());
        }
        return nextSublists;
    } */
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#reduce(java.util.List)
     */
    public final R reduce (final List<? extends R> l) {
        //To reduce the function we first need an iterator ...
        final Iterator<? extends R> iter = l.iterator (); 
        
        //if there is a first argument ...
        if (iter.hasNext ()) {
            //... we take it ... 
            final R tmp1 = iter.next ();
            //... take a look if there is at least one more ... 
            if (iter.hasNext ()) {
                //... we take this element to ...
                final R tmp2 = iter.next ();
                //... apply the function ...                
                R result = apply (tmp1, tmp2);
                
                //... and iterate over the rest of the list ...
                while (iter.hasNext ()) {
                    //... and apply the function to the already ... 
                    //... computed result and the next element of ...
                    //... the list, saving the result back in the ...
                    //... result
                    result = apply (result, iter.next ());
                }
                //.. and return the result ...
                return result;
            }
        }
        //... if there are not at least 2 elements we can't return anything
        return null;
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#map(java.lang.Object[][])
     */
    public final List<R> mapcar (final Object[]... arrays) {
        //For array we just take a new vector ...
        final List arg = new Vector ();
        
        //... and iterate over the arrays ...
        for (final Object[] arr : arrays) {
            //... and copy the array as list ...
            //... into the Vector ...
            arg.add (Arrays.asList (arr));
        }
        //... and use the function for lists to map
        return mapcar (arg);
    }
    
    /*
     * @see net.homelinux.chaoswg.io.effizientealgorithmen.functional.Function#maplist(java.lang.Object[][])
     */
    /*public final List<R> maplist (final Object[]... arrays) {
        //We use the same technique we used with the other array ...
        //... functions ...
        
        //... we take a vector ... 
        final List arg = new Vector ();
        
        //...Take every array ...
        for (final Object[] arr : arrays) {
            //...convert it to a list and add it ...
            //... to the vector ... 
            arg.add(Arrays.asList(arr));
        }
        //... and return the result we would have ... 
        //... if the arguments had been lists in ...
        //... in the first place
        return maplist (arg);
    }*/
    
    public final R reduce (final R... array) {
        //As with map we just convert the array to a list ...
        //... and use the function to reduce a list 
        return reduce (Arrays.asList (array));
    }
}
