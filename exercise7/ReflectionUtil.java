package Aufgabe7;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

// this class was made together by Varvara Grebenetska and Iryna Hontsovska
public class ReflectionUtil {
    //scanning all classes
    private static final List<Class<?>> ALL_CLASSES = List.of(
            Author.class, Invariant.class, PreCondition.class, PostCondition.class,
            ClientHistoryConstraint.class, ServerHistoryConstraint.class,
            BureauOperator.class, BureauUnit.class, BureauUnitLarge.class,
            BureauUnitMedium.class, BureauUnitSmall.class, BureauUnitLargeUnderfloorHeating.class,
            BureauUnitMediumRibbedRadiator.class, Pump.class, PumpLarge.class,
            PumpMedium.class, PumpSmall.class, Test.class
    );

    public static void extractAndDisplayMetadata() {
        Map<String, List<String>> authorToClasses = new HashMap<>();
        Map<String, Integer> methodCountPerAuthor = new HashMap<>();
        Map<String, Integer> guaranteesCountPerAuthor = new HashMap<>();

        System.out.println("\n===== Kontext B: Reflection Metadata =====\n");

        for (Class<?> clazz : ALL_CLASSES) {
            System.out.println("\nclass: " + clazz.getName());
            Author author = clazz.getAnnotation(Author.class);
            if (author != null) {
                String authorName = author.value();
                authorToClasses.computeIfAbsent(authorName, k -> new ArrayList<>()).add(clazz.getName());

                // counting methods and constructors
                Method[] methods = clazz.getDeclaredMethods();
                Constructor<?>[] constructors = clazz.getDeclaredConstructors();
                int methodCount = methods.length + constructors.length;
                methodCountPerAuthor.merge(authorName, methodCount, Integer::sum);

                // count zusicherungen(annotations on methods)
                int guaranteeCount = countGuarantees(methods, constructors);
                guaranteesCountPerAuthor.merge(authorName, guaranteeCount, Integer::sum);
            }

            // displaying all non-private methods and constructors
            displayMethodsAndAnnotations(clazz);
        }

        // summary per author
        System.out.println("\n===== summary per author =====");
        authorToClasses.forEach((author, classes) -> {
            System.out.println("Author: " + author);
            System.out.println("  - classes/interfaces/annotations: " + classes.size());
            System.out.println("  - classes: " + classes);
            System.out.println("  - methods/constructors: " + methodCountPerAuthor.getOrDefault(author, 0));
            System.out.println("  - guarantees: " + guaranteesCountPerAuthor.getOrDefault(author, 0));
        });

        System.out.println("\n===== End of Kontext B =====");
    }

    private static int countGuarantees(Method[] methods, Constructor<?>[] constructors) {
        int count = 0;
        for (Method method : methods) {
            count += method.getDeclaredAnnotations().length;
        }
        for (Constructor<?> constructor : constructors) {
            count += constructor.getDeclaredAnnotations().length;
        }
        return count;
    }

    private static void displayMethodsAndAnnotations(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        System.out.println("  methods and annotations:");
        for (Method method : methods) {
            System.out.println("    - " + method.getName());
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                System.out.println("      @ " + annotation.annotationType().getSimpleName() + ": " + annotation);
            }
        }

        System.out.println("  constructors and annotations:");
        for (Constructor<?> constructor : constructors) {
            System.out.println("    - " + constructor.getName());
            for (Annotation annotation : constructor.getDeclaredAnnotations()) {
                System.out.println("      @ " + annotation.annotationType().getSimpleName() + ": " + annotation);
            }
        }
    }
}

