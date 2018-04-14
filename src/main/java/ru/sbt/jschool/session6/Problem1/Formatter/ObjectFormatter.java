package main.java.ru.sbt.jschool.session6.Problem1.Formatter;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ObjectFormatter implements JSONTypeFormatter<Object>  {
    @Override public String format(Object obj, JSONFormatter formatter, Map<String, Object> ctx) {
        if (obj==null){
            return null;
        }
        String json = "";
        if (ctx.get("name")!=null){
            json+=  ctx.get("level") + formatter.marshall(ctx.get("name"))+ ": ";
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        if (fields.length>0){
            json+=  ctx.get("level") +"{\n";
        }
        for (Field field: fields){

            int mod = field.getModifiers();
            if(!Modifier.isTransient(mod) && !Modifier.isStatic(mod)){

                Map<String, Object> ctxField = new HashMap<>();
                ctxField.put("name", field.getName());
                ctxField.put("level", ctx.get("level")+"\t");
                try {
                    field.setAccessible(true);
                    json+=  formatter.marshall(field.get(obj),  ctxField);
                } catch (IllegalAccessException e){

                }
            }
        }
        json = Util.erase(json);

        if (ctx.get("name")==null){
            json += ctx.get("level") + "}\n";
        } else {
            if (fields.length > 0) {
                json += ctx.get("level") + "},\n";
            }
        }
        return json;
    }

}
