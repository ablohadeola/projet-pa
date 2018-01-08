package fr.unice.miage.projetpa;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import fr.unice.miage.projetpa.plugins.core.Plugin;

public class OutilReflection {

	public static Object invokeMethod(Object pluginName, String name, Object cible, Object... args) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = pluginName.getClass().getMethods();
        for(Method method : methods) {
            for(Annotation annotation : method.getAnnotations()){
                Class<? extends Annotation> pluginInfos = annotation.annotationType();
                for (Method mt : pluginInfos.getDeclaredMethods()) {
                    if(mt.invoke(annotation).equals(name)){
 
//                        if(args.length > 0)
//                            System.out.println("Invoke " + name + " | with : "+ Arrays.toString(args));
//                        else if(cible != null)
//                            System.out.println("Invoke " + name + " | on : " + pluginName + " | with :" + cible);
//                        else
//                            System.out.println("Invoke " + name);
 
                        if(args.length == 1)
                            return method.invoke(pluginName, cible, args[0]);
                        else if(args.length == 2)
                            return method.invoke(pluginName, cible, args[0], args[1]);
                        else if(cible == null)
                            return method.invoke(pluginName);
                        else
                            return method.invoke(pluginName, cible);
                    }
                }
            }
        }
        return null;
    }
	
	public static Object construire(Class pluginClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = pluginClass.getDeclaredConstructor();
        return constructor.newInstance();
    }
	
}
