package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private ArrayList<Tuple<String, Integer>> exams = new ArrayList<>();

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name,surname,year);
        Collections.addAll(this.exams, exams);
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();
        ArrayList<Json> array = new ArrayList<>();
        for (Tuple<String, Integer> exam : exams) {
            array.add(new JsonObject(
                        new JsonPair("course", new JsonString(exam.key)),
                        new JsonPair("mark", new JsonNumber(exam.value)),
                        new JsonPair("passed", new JsonBoolean(exam.value>=3)))
                );
        }
        JsonArray array1 = new JsonArray(array);
        jsonObject.add(new JsonPair("exams", array1));
        
        return jsonObject;
    }
}
