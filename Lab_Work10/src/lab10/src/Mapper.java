package lab10.src;

import lab10.connection.IMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.List;

public class Mapper<TDestination, TSource> implements IMapper<TDestination, TSource> {

    public Mapper() {

    }

    @Override
    public TDestination map(TSource source, Class<TDestination> destinationType) throws Exception {

        if (null == source || null == destinationType) {
            throw new NullPointerException();
        }
        if (!Modifier.isPublic(destinationType.getModifiers())){
            throw new AccessException("Not access to class.");
        }

        Class<?> sourceType = source.getClass();

        List<Field> fields = new ArrayList<>();
        for (Field field : sourceType.getFields()) {

            try {
                Field destination = destinationType.getField(field.getName());
                if (destination.getType() == field.getType() &&
                        Modifier.isPublic(destination.getModifiers())) {
                    fields.add(field);
                }
            } catch (NoSuchFieldException exception) {
            }
        }


        TDestination result = null;
        for (Constructor<?> ctor : destinationType.getConstructors()) {

            if (0 == ctor.getParameterCount()) {

                result = (TDestination) ctor.newInstance();
                break;
            }
        }
        if (null == result) {
            throw new AccessException("Not have public parameterless constructor.");
        }

        for (Field field : fields) {

            Field destination = destinationType.getField(field.getName());
            Class<?> type = field.getType();
            Object value = field.get(source);

            if (null == value) {
                destination.set(result, null);
            } else if (Byte.class.isAssignableFrom(type)) {
                destination.set(result, Byte.valueOf((Byte) value));
            } else if (Short.class.isAssignableFrom(type)) {
                destination.set(result, Short.valueOf((Short) value));
            } else if (Integer.class.isAssignableFrom(type)) {
                destination.set(result, Integer.valueOf((Integer) value));
            } else if (Long.class.isAssignableFrom(type)) {
                destination.set(result, Long.valueOf((Long) value));
            } else if (Float.class.isAssignableFrom(type)) {
                destination.set(result, Float.valueOf((Float) value));
            } else if (Double.class.isAssignableFrom(type)) {
                destination.set(result, Double.valueOf((Double) value));
            } else if (Boolean.class.isAssignableFrom(type)) {
                destination.set(result, Boolean.valueOf((Boolean) value));
            } else if (Character.class.isAssignableFrom(type)) {
                destination.set(result, Character.valueOf((Character) value));
            } else if (String.class.isAssignableFrom(type)) {
                destination.set(result, new String((String) value));
            } else {
                destination.set(result, value);
            }
        }

        return result;
    }
}