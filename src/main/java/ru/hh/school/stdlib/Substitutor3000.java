package ru.hh.school.stdlib;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Substitutor3000 {
    private HashMap<String,String> map;
    public Substitutor3000(){
        map = new HashMap<String, String>();
    }

    private String process(String val){
        Pattern p = Pattern.compile("\\$\\{(.+?)\\}");
        Matcher m = p.matcher(val);
        while(m.find()){
            String lex = "";
            if (map.containsKey(m.group(1)))
                lex = map.get(m.group(1));
            m = p.matcher(m.replaceFirst(lex));
        }
        return m.replaceFirst("");
    }

    public void put(String key, String value) {
        map.put(key,value);
    }

    public String get(String key) {
        return process(map.get(key));
    }
}
