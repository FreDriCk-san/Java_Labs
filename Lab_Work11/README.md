# Lab_work11

Write the serializer for objects in JSON and vice versa.

public interface IJsonConvert {
 
    String serializeObject(Object obj);
    String serializeObject(Object obj, JsonConveterOptions options);
 
    JObject deserialize(String data);
    <TResult> TResult deserializeTyped(String data);
}
