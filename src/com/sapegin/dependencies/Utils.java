package com.sapegin.dependencies;

import com.sapegin.Main;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class Utils {

    private static final String CLASS_FILE_SUFFIX = ".class";
    private static final String defaultPackage = Main.class.getPackageName();

    public static List<Class<?>> findAllClasses() {
        return find(defaultPackage);
    }

    public static List<Class<?>> find(String scannedPackage) {
        String scannedPath = scannedPackage.replace(".", "/");
        URL scannedURL = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedURL == null) {
            throw new IllegalArgumentException(String.format("Incorrect path %s", scannedPath));
        }
        File scannedDir = new File(scannedURL.getFile());
        List<Class<?>> classes = new ArrayList<>();
        for (File f : scannedDir.listFiles()) {
            classes.addAll(find(f, scannedPackage));
        }
        return classes;
    }

    public static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = scannedPackage + "." + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }
}
