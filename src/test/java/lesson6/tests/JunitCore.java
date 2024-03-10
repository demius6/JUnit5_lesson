package lesson6.tests;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JunitCore {

    public static void main(String[] args) throws Exception {
        //lookup classes with annotation @Test
        // here we go with class SimpleTest.class

        Class clazz = SimpleTest.class;
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            Test methodAnnotation = declaredMethod.getAnnotation(Test.class);
            if (methodAnnotation != null) {

                // run method with @Test

                try {
                    declaredMethod.invoke(clazz.getConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println( " Test failed: " + declaredMethod.getName());
                        continue;
                    } else {
                        System.out.println("Test broken: " + declaredMethod.getName());
                        continue;
                    }
                }
                System.out.println("Test passed: " + declaredMethod.getName());
            }
        }


        // run all methods with @Test

        // print results

    }
}
