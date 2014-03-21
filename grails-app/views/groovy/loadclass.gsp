<%@ page import="java.util.*" %>
<%@ page import="java.lang.reflect.*" %>
<%@ page import="java.net.*" %>

<%	
 
 	  // ClassLoader  
        Class c1;  
  
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();  
  
        try {  
  
            // 加载student类  
            String className = "kxg.debug.Groovydebug";  
            //c1 = classLoader.loadClass("yerasel.Student");  
            c1 = Class.forName(className);  
            /* 
             * Class.forName("xx.xx")等同于Class.forName("xx.xx",true,CALLClass.class.getClassLoader())， 
             * 第二个参数(boolean)表示装载类的时候是否初始化该类，即调用类的静态块的语句及初始化静态成员变量。 
             * ClassLoader loader = Thread.currentThread.getContextClassLoader();  
             * 也可以用(ClassLoader.getSystemClassLoader()) 
             */  
  
            out.println(c1);  
         } catch (Exception e) {  
  
            // TODO Auto-generated catch block  
  
           out.println(e);  
  
        }  
	 
%>

 