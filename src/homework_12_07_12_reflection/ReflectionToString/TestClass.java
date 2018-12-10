package homework_12_07_12_reflection.ReflectionToString;

import homework_11_05_12_patterns.TextProcessor.Message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestClass {
    private int privateInt = 14;
    private String privateString = "not private enough";
    @Exclude
    private String reallyPrivateString = "not for you";
    private Integer objectInt = 88;
    public long[] longArray = new long[]{123L, 456L, 789L};
    public Message publicMessage = new Message("tester","Hello im tester");
    public Map<String, Integer> publicMap = new HashMap<>();
    protected Set<Message> protectedSet = new HashSet<>();
    public static Message staticMessage = null;
    public TestClass(){
        this.publicMap.put("first key",1);
        this.publicMap.put("second key",2);
        this.publicMap.put("third",3);
        this.publicMap.put(null,null);
        this.protectedSet.add(new Message("tester","Hello im tester"));
        this.protectedSet.add(new Message("user 1","Hello im user 1"));
        this.protectedSet.add(new Message("user 2","Hello im user 2"));
        this.protectedSet.add(null);
    }
}
