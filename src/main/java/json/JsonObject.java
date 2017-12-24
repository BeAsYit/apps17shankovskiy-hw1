package json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final ArrayList<JsonPair> jsons = new ArrayList<>();

    public JsonObject(JsonPair... jsonPairs) {
        this.jsons.addAll(Arrays.asList(jsonPairs));
    }

    @Override
    public String toJson() {
        String content = "{";
        int a = 0;
        for (JsonPair json: jsons) {
            content = content + json.key + ": " + json.value.toJson();
            if (a<jsons.size()-1){
                content = content +", ";
                a += 1;

            }

        }
        return content + "}";
    }

    public void add(JsonPair jsonPair) {
        jsons.add(jsonPair);
    }
    

    public Json find(String name) {
        for (JsonPair json :jsons) {
            if(json.key.equals(name)){
                return json.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject project = new JsonObject();
        for (JsonPair json:this.jsons) {
            if(Arrays.asList(names).contains(json.key)){
                project.jsons.add(json);
            }
        }
        return project;
    }
}
