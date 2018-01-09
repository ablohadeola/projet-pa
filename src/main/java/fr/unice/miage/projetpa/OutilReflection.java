package fr.unice.miage.projetpa;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class OutilReflection {

	/**
	 * Permet d'utiliser une methode d'un plugin
	 * @param pluginName : le .class du plugin
	 * @param methodeName : le nom de la methode a charger
	 * @param cible : facultatif (premier argument de la methode a charger, s'il y en a)
	 * @param args : facultatif (autres arguments de la methode a charger, s'il y en a)
	 * @return : le resultat de la methode appele depuis le plugin
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static Object invokeMethod(Object pluginName, String methodeName, Object cible, Object... args) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = pluginName.getClass().getMethods(); 
        for(Method method : methods) { 
            for(Annotation annotation : method.getAnnotations()){ 
                Class<? extends Annotation> pluginInfos = annotation.annotationType();
                for (Method mt : pluginInfos.getDeclaredMethods()) { 
                    if(mt.invoke(annotation).equals(methodeName)){
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
	
	//Retourne une instance d'un plugin
	public static Object construire(Class<?> pluginClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = pluginClass.getDeclaredConstructor();
        return constructor.newInstance();
    }

    /** creer un objet avec nom de classe */
    public static Object construire(String className) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
        Class<?> c = null; 
        try {
            c = Class.forName(className); 
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        if(c == null) return null;
        return construire(c);
    }
	
}
