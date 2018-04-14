package main.java.ru.sbt.jschool.session6.Problem1.Formatter;

import java.util.Map;

public class StringFormatter implements JSONTypeFormatter<String>{
    @Override public String format(String str, JSONFormatter formatter, Map<String, Object> ctx) {

        if(ctx.get("name")!=null){
            return ctx.get("level") + formatter.marshall(ctx.get("name")) +": \""  + str +"\",\n";
        }
        return  ctx.get("level")+  "\"" + str+"\"" ;
    }
}
