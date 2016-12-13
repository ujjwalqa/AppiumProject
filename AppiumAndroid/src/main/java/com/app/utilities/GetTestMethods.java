package com.app.utilities;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.testng.annotations.Test;

public class GetTestMethods 
{
	public static void main(String[] args)
	{
		getMethods();
	}

	public static void getMethods()
	{

		Reflections reflections = new Reflections( new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("com.app.android")).setScanners(new MethodAnnotationsScanner()) );
		
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflectionClasses = new Reflections(new ConfigurationBuilder()
		    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
		    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
		    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("org.your.package"))));
		
		Set<Class<?>> classes = reflectionClasses.getSubTypesOf(Object.class);
		
		for (Class<?> class1 : classes)
		{
			System.out.println("Class Name: " + class1.getName());
			
		}

		Set<Method> resources = reflections.getMethodsAnnotatedWith(Test.class);

		for (Method method : resources) 
		{
			System.out.print("Test Method Name: " + method.getName());
			Test mXY = (Test)method.getAnnotation(Test.class);
			String[] groups = mXY.groups();
			System.out.print("  Category: ");
			for (String group : groups) 
			{
				System.out.print(group +" ");	
			}
			System.out.print("  DataProvider Class/Method: " + mXY.dataProviderClass().getSimpleName() + "/" + mXY.dataProvider());
			System.out.print("  Description: " + mXY.description());
			System.out.println();
		}

	}

}
