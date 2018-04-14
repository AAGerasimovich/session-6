package main.java.ru.sbt.jschool.session6.Problem1.Formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author NIzhikov
 */
public class DateFormatter implements JSONTypeFormatter<Date> {
    @Override public String format(Date date, JSONFormatter formatter, Map<String, Object> ctx) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        if(ctx.get("name")!=null){
            return  ctx.get("level") +"\"" + ctx.get("name") +"\": " + format.format(date) +  ",\n";
        }
        return format.format(date);

    }
}
