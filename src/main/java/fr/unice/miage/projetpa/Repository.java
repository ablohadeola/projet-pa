package fr.unice.miage.projetpa;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Repository {

	private MyClassLoader mCl;
	private File baseRepo;
	private ArrayList<File> listClass;

	public Repository(File base) {
		this.baseRepo = base;
		ArrayList<File> list_repository = new ArrayList<File>();
		list_repository.add(baseRepo);
		this.mCl = new MyClassLoader(list_repository);
		this.listClass = new ArrayList<File>();
	}

	public List<Class<?>> load() {
		List<Class<?>> listeClasses = new ArrayList<Class<?>>();
		ArrayList<File> listeClassesRep = browseInDepth(this.baseRepo);
		for (File file : listeClassesRep) {			
			String pathRep = this.baseRepo.getPath();	
			int result = file.getPath().compareTo(pathRep) + 1;
			String nomPackage = "";
			if(result > 0) {
				String pathPackage = file.getPath().substring(pathRep.length());
				pathPackage = pathPackage.substring(0, pathPackage.lastIndexOf(File.separator));
				pathPackage = pathPackage.substring(pathPackage.indexOf(File.separator) + 1);
				nomPackage = pathPackage.replace(File.separator, ".");
			}
			String nomClasse = file.getName().substring(0, file.getName().lastIndexOf("."));
			try {
				listeClasses.add(mCl.loadClass(nomPackage + "." + nomClasse));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return listeClasses;
	}

	public ArrayList<File> browseInDepth(File file) {	
		if(file.isDirectory()) {
			File[] list_files = file.listFiles(new FilenameFilter() {	
				public boolean accept(File dir, String name) {
					Pattern p = Pattern.compile(".*.class");
					Matcher m = p.matcher(name);
					return m.matches();
				}
			});
			if(list_files.length == 0) {
				list_files = file.listFiles();
				for (File f : list_files) {
					browseInDepth(f);				
				}
			} else {
				for (File f : list_files) {
					listClass.add(f);
					browseInDepth(f);				
				}
			}
		}
		return listClass;
	}
	
	public MyClassLoader getMcl() {
		return mCl;
	}

	public File getRepBase() {
		return baseRepo;
	}

	public ArrayList<File> getListeClasses() {
		return listClass;
	}
}
