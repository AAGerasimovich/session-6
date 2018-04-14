package main.java.ru.sbt.jschool.session6.Problem1.Formatter;



import java.lang.reflect.Array;
import java.util.*;


/**
 * @author NIzhikov
 */
public class JSONFormatterImpl implements JSONFormatter {
    private Map<Class, JSONTypeFormatter> types = new HashMap<>();

    public JSONFormatterImpl() {
        types.put(Date.class, new DateFormatter());
        types.put(Object.class, new ObjectFormatter());
        types.put(String.class, new StringFormatter());
    }

    public String marshall(Object obj, Map ctx) {
        if (obj == null)
            return ctx.get("level") + "null";

        if (types.containsKey(obj.getClass())) {
           return types.get(obj.getClass()).format(obj, this, ctx);
        }

        if (obj instanceof Number){
            types.put(obj.getClass(), new NumberFormatter());
            return types.get(obj.getClass()).format(obj, this, ctx);
        }

        if (obj instanceof Map){
            types.put(obj.getClass(), new MapFormatter());
            return types.get(obj.getClass()).format(obj, this, ctx);
        }

        if (obj instanceof Collection){
            types.put(obj.getClass(), new CollectionFormatter());
            return types.get(obj.getClass()).format(obj, this, ctx);
        }

        if (obj.getClass().isArray()){
            ctx.put("length", Array.getLength(obj));
            types.put(obj.getClass(), new ArrayFormatter());
            return types.get(obj.getClass()).format(obj, this, ctx);

        }

     return types.get(Object.class).format(obj, this, ctx);

    }

    @Override public String marshall(Object obj) {
        if (obj == null)
            return null;

        Map<String, Object> ctx = new HashMap<>();
        ctx.put("level", "");

        if (!types.containsKey(obj.getClass())) {
            return types.get(Object.class).format(obj, this, ctx);
        }
        return types.get(obj.getClass()).format(obj, this, ctx);
    }


    @Override public <T> boolean addType(Class<T> clazz, JSONTypeFormatter<T> format) {
        types.put(clazz.getClass(), format);
        return true;
    }

    public static void main(String[] args){



    }
}
