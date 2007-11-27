package net.homelinux.chaoswg.io.makejavauseful.ducktyping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* Example
 * 
 *   interface IImageHolder {
 *      void setImage(Image image);
 *     void addDisposeListener(DisposeListener disposeListener);
 *  }
 *
 * public ImageMgr(Object receiver, Image image) {
 *      if (!DuckType.instanceOf(IImageHolder.class, receiver)) {
 *          throw new ClassCastException("Cannot implement IImageHolder");
 *      }
 *
 *      this.image = image;
 *
 *  IImageHolder imageHolder = (IImageHolder)
 *          DuckType.implement(IImageHolder.class, receiver);
 *  imageHolder.setImage(image);
 *  imageHolder.addDisposeListener(this);
 *  }
 */

/**
 * DuckType. Implements Duck Typing for Java.  ("If it walks like a duck, 
 * quacks like a duck, it...").  Essentially allows programs to treat
 * objects from separate hierarchies as if they were designed with common
 * interfaces as long as they adhere to common naming conventions.
 * 
 * This version is the strict DuckType.  All methods present in
 * interfaceToImplement must be present on the target object.
 *
 * Some additions and costumersation by martin
 * @author djo, martin
 */
public final class DuckType implements InvocationHandler {
    protected final Object object;
    protected final Class objectClass;

    protected DuckType(final Object object) {
        this.object = object;
        this.objectClass = object.getClass();
    }
    /**
     * Causes object to implement the interfaceToImplement and returns
     * an instance of the object implementing interfaceToImplement even
     * if interfaceToImplement was not declared in object.getClass()'s 
     * implements declaration.
     * 
     * This works as long as all methods declared in interfaceToImplement
     * are present on object.
     * 
     * @param interfaceToImplement The Java class of the interface to implement
     * @param object The object to force to implement interfaceToImplement
     * @return object, but now implementing interfaceToImplement
     */
    public static final Object implement(final Class interfaceToImplement, final Object object) {
        return Proxy.newProxyInstance(interfaceToImplement.getClassLoader(), 
                new Class[] {interfaceToImplement}, new DuckType(object));
    }

    /**
     * Indicates if object is a (DuckType) instace of interface.  That is,
     * is every method in intrface present on object.
     * 
     * @param intrface The interface to implement
     * @param object The object to test
     * @return true if every method in intrface is present on object.  false otherwise
     */
    public static final boolean instanceOf(final Class intrface, final Object object) {
        final Class candclass = object.getClass();
        for (final Method method : intrface.getMethods()) {
            try {
                candclass.getMethod(method.getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                return false;
            }
        }
        return true;
    }

    public final Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final Method realMethod = objectClass.getMethod(method.getName(), method.getParameterTypes());
        return realMethod.invoke(object, args);
    }
}