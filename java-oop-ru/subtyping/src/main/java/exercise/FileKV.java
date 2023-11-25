package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {

    String path;
    Map<String, String> map;

    public FileKV(String path, Map<String, String> map) {
        this.path = path;
        this.map = new HashMap<>(map);
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public void set(String key, String value) {
        Utils.readFile(path);
        Utils.unserialize(Utils.serialize(map));
        map.put(key, value);
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        Utils.readFile(path);
        Utils.unserialize(Utils.serialize(map));
        map.remove(key);
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        Utils.readFile(path);
        Utils.unserialize(Utils.serialize(map));
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Utils.readFile(path);
        Utils.unserialize(Utils.serialize(map));
        return map;
    }

    public static void main(String[] args) {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        // Получение значения по ключу
        storage.set("key", "dfd");
        System.out.println(storage.get("key", "default"));
    }
}
// END
