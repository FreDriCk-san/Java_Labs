package source;

import source.options.JsonConvertOptions;
import source.types.JObject;

public interface IJsonConvert {

    String serializeObject(Object obj);
    String serializeObject(Object obj, JsonConvertOptions options);

    JObject deserialize(String data);
    <TResult> TResult deserializeTyped(String data);

}
