package se701;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StudentSample {

    public static void main(String[] args) {
        sampleMethod();
    }

    public static void sampleMethod() {
        Map states1 =  new HashMap<String, String>();
        states1.put("AL", "Alabama");
        states1.put("AK", "Alaska");
        states1.put("AZ", "Arizona");
        states1.put("WY", "Wyoming");
        Map states2 =  new HashMap<String, String>();
        states2.put("AL", "Alabama");
        states2.put("AK", "Alaska");
        states2.put("AZ", "Arizona");
        states2.put("WY", "Wyoming");
        Map states3 =  new HashMap<String, String>();
        states3.put("AL", "Alabama");
        states3.put("AK", "Alaska");
        states3.put("AZ", "Arizona");
        states3.put("WY", "Wyoming");
    }
}
