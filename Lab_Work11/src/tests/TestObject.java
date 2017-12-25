package tests;

import source.annotations.JsonIgnore;
import source.annotations.PropertyName;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestObject {

    public byte byteValue;
    public short shortvalue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
    public char charValue;
    public boolean booleanValue;
    public Date dateValue;
    public String stringValue;

    @JsonIgnore
    public String ignore;
    @PropertyName("Age")
    public long changeName;

    public Object nullReference;
    public Object notNullReference;
    public ArrayList<Object> rangePrimitives;
    public ArrayList<TestObject> range;
    public Map<String, TestObject> map;

    public TestObject(){

        byteValue = Byte.MAX_VALUE;
        shortvalue = Short.MAX_VALUE;
        intValue = Integer.MAX_VALUE;
        longValue = Long.MAX_VALUE;
        floatValue = Float.MAX_VALUE;
        doubleValue = Double.MAX_VALUE;
        charValue = 'f';
        booleanValue = true;
        dateValue = new Date();
        stringValue = "fuck!";

        ignore = "ignore";
        changeName = Long.MIN_VALUE;

        nullReference = null;
        notNullReference = null;
        rangePrimitives = new ArrayList<>();
        rangePrimitives.add(Long.MAX_VALUE);
        rangePrimitives.add(Double.MAX_VALUE);
        rangePrimitives.add(true);
        rangePrimitives.add("fuck2");
        range = new ArrayList<>();
        map = new HashMap<>();
    }
}