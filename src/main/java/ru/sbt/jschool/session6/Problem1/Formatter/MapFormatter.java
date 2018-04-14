package main.java.ru.sbt.jschool.session6.Problem1.Formatter;



import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapFormatter implements JSONTypeFormatter<Map>{
    @Override public String format(Map map, JSONFormatter formatter, Map<String, Object> ctx) {
        String json = "";

        if(ctx.get("name")!=null){
            json+= ctx.get("level")+"\"" + ctx.get("name") +"\": [\n";
        } else {
            json+= ctx.get("level")+ "[\n";
        }

        Map<String, Object> ctxMap = new HashMap<>();
        ctxMap.put("level", ctx.get("level")+"\t");
        Set<Map.Entry > set = map.entrySet();

        for (Map.Entry entry: set) {
            ctxMap.put("name", entry.getKey());
            json +=formatter.marshall(entry.getValue(), ctxMap);

            }
        if (set.isEmpty()) {
            return null;
        }

        json = Util.erase(json);
        return json+ ctx.get("level")+ "],\n";
    }
}
