package fr.unice.miage.projetpa;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.security.SecureClassLoader;
import java.util.HashMap;

import fr.plugins.appPlugins.Plugin;

public class MyClassLoader extends SecureClassLoader {
	
	public HashMap<String, File> findAllPlugins(File file, HashMap<String, File> map) throws IOException, ClassNotFoundException {
        if(file.isDirectory()) {
        	if(map != null)
	            for(File f : file.listFiles()) {
	            	findAllPlugins(f, map);
	            }
        } else if(file.isFile() && file.getName().endsWith(".class")) { 
            byte[] pluginBytecode = fileToByteTab(file);
            String pluginName = file.getAbsolutePath();
            if(pluginName.contains("Plugin$Type.class") || pluginName.contains("Plugin.class") || pluginName.contains("Robot$DepType.class")){
	        	return null;
	        }
            pluginName = pluginName.replace(".class", "");
            int index = pluginName.lastIndexOf("fr");
            pluginName = pluginName.replace("\\", ".");
            String str = pluginName.substring(index, pluginName.length());
            System.out.println(str);
        	Class<?> cl = defineClass(str, pluginBytecode, 0, pluginBytecode.length);
	        	for(Annotation annot : cl.getAnnotationsByType(Plugin.class)) {
	        		map.put(cl.getSimpleName(), file);
		        }
            } 
        return map;
    }
	
	public Class<?> loadPluginFromPluginFile(File pluginFile) throws IOException, ClassNotFoundException {
        String pluginName = pluginFile.getAbsolutePath().replace(".class", "");
        pluginName = pluginName.replace(".class", "");
        int index = pluginName.lastIndexOf("fr");
        pluginName = pluginName.replace("\\", ".");
        String str = pluginName.substring(index, pluginName.length());
        if(pluginName.contains("Plugin$Type") || pluginName.contains("MyClassLoader")){
        	return null;
        }
        
        File file = new File(pluginFile.getAbsolutePath());
//        byte[] pluginBytecode = fileToByteTab(pluginFile);
    	return Class.forName(str);
    }
	
	private byte[] fileToByteTab(File file) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(file.getAbsoluteFile());
        BufferedInputStream bis = new BufferedInputStream(fileInputStream);
        boolean good = false;
        while (!good) {
            int i = bis.read();
            if (i == -1) {
            	good = true;
            } else {
            	byteArrayOutputStream.write(i);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
	
}
