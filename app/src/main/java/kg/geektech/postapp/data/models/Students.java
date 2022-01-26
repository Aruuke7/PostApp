package kg.geektech.postapp.data.models;

import java.util.HashMap;
import java.util.Map;

public class Students {
    private static Map<Integer, String> students = new HashMap<>();

    public static void addAllStudents() {
        students.put(1, "Руслан");
        students.put(2, "Бекбол");
        students.put(3, "Акжол");
        students.put(4, "Эдельвейс");
        students.put(5, "Арууке");
    }

    public static Map<Integer, String> getStudents() {
        return students;
    }
}
