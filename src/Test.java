import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String args[]) {
       // System.out.println("DD");

        A a = new A();
        a.setA("DD");
        B b = new B();

        copyAllFields(b, a);

        System.out.println(b.getA());
    }


    public static <T> void copyAllFields(T to, T from) {
        Class<T> clazz = (Class<T>) from.getClass();
        // OR:
        // Class<T> clazz = (Class<T>) to.getClass();
        List<Field> fields = getAllModelFields(clazz);

        if (fields != null) {
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    field.set(to, field.get(from));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<Field> getAllModelFields(Class aClass) {
        List<Field> fields = new ArrayList<>();
        do {
            Collections.addAll(fields, aClass.getDeclaredFields());
            aClass = aClass.getSuperclass();
        } while (aClass != null);
        return fields;
    }
}
class A {
     public String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}

class B {
    public String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }


}