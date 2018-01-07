package fr.unice.miage.projetpa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class MyClassLoader extends SecureClassLoader {

	private ArrayList<File> path;

	public MyClassLoader(ArrayList<File> path) {
		this.path = path;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = loadClassData(name);
		if(b == null) {
			throw new ClassNotFoundException();
		}
		return defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) throws ClassNotFoundException {
		for (File file : path) {		
			if (file.isDirectory()) {
				try {
					String package_name = name.replace(".", File.separator);
					String repository_name = file.getPath();
					Path file_path = Paths.get(repository_name + File.separator + package_name + ".class");
					byte[] file_data = Files.readAllBytes(file_path);
					return file_data;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(file.getPath().endsWith(".zip") ||  file.getPath().endsWith(".jar")) {
				try {
					ZipFile zip_file = new ZipFile(file);
					Enumeration<? extends ZipEntry> entries = zip_file.entries();
					while(entries.hasMoreElements()){ 
						ZipEntry entry = entries.nextElement();
						if(entry.toString().endsWith(".class")) { 
							String package_name = file.getPath();
							package_name = package_name.replace(file.getName(), "");
							String file_name = entry.toString();
							String class_name = file_name.replace(File.separator, ".");
							class_name = class_name.substring(0, class_name.lastIndexOf('.'));
							if(class_name.equals(name)) {
								Path file_path = Paths.get(package_name + file_name);
								byte[] file_data = Files.readAllBytes(file_path);
								return file_data;
							}
						} 
					} 
				} catch (ZipException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				try {
					ZipFile zip_file = new ZipFile(file);
					Enumeration<? extends ZipEntry> entries = zip_file.entries();
					while(entries.hasMoreElements()){ 
						ZipEntry entry = entries.nextElement();
						if(entry.toString().endsWith(".class")) { 
							String package_name = file.getPath();
							package_name = package_name.replace(file.getName(), "");
							String file_name = entry.toString();
							String class_name = file_name.replace(File.separator, ".");
							class_name = class_name.substring(0, class_name.lastIndexOf('.'));
							if(class_name.equals(name)) {
								Path file_path = Paths.get(package_name + file_name);
								byte[] file_data = Files.readAllBytes(file_path);
								return file_data;
							}
						}
					}
				} catch (ZipException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public ArrayList<File> getPath() {
		return path;
	}
	
}
