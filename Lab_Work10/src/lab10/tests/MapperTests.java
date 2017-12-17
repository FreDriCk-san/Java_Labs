package lab10.tests;

import lab10.src.Mapper;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.rmi.AccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class MapperTests {

    @Test
    public void mapWithNull() throws Exception {

        Mapper<DestinationClass, SourceClass> mapper = new Mapper<>();
        try {
            mapper.map(null, null);
            fail("Process null instance");
        } catch (NullPointerException exception) {
        }
        try {
            mapper.map(new SourceClass(), null);
            fail("Process with nullable type");
        } catch (NullPointerException exception) {
        }
    }

    @Test
    public void mapWithoutNeedCtor() throws Exception {

        Mapper<NotPublicCtor, SourceClass> mapper = new Mapper<>();
        try {
            mapper.map(new SourceClass(), NotPublicCtor.class);
            fail("Process without access to ctor");
        } catch (AccessException exception) {
        }
    }

    @Test
    public void mapWithExceptionCtor() throws Exception {

        Mapper<ExceptionCtor, SourceClass> mapper = new Mapper<>();
        try {
            mapper.map(new SourceClass(), ExceptionCtor.class);
            fail("Process with exception in ctor");
        } catch (InvocationTargetException exception) {
        }
    }

    @Test
    public void mapNotAccessClass() throws Exception {

        Mapper<NotAccessClass, SourceClass> mapper = new Mapper<>();
        try {
            mapper.map(new SourceClass(), NotAccessClass.class);
            fail("Process not access class");
        } catch (AccessException exception) {
        }
    }

    @Test
    public void map() throws Exception {

        Mapper<DestinationClass, SourceClass> mapper = new Mapper<>();
        SourceClass source = new SourceClass();

        DestinationClass result = mapper.map(source, DestinationClass.class);

        //Copy
        assertEquals(source.Byte, result.Byte);
        assertEquals(source.Short, result.Short);
        assertEquals(source.Int, result.Int);
        assertEquals(source.Long, result.Long);
        assertEquals(source.Float, result.Float, 0.0001);
        assertEquals(source.Double, result.Double, 0.0001);
        assertEquals(source.Boolean, result.Boolean);
        assertEquals(source.Char, result.Char);
        assertTrue(source.String.equals(result.String));
        assertTrue(source.Reference == result.Reference);

        //Not copy
        assertEquals(0, result.Private);
        assertTrue(result.AnotherType.equals("fuck"));
    }
}

class NotAccessClass {
    public NotAccessClass() {
    }
}