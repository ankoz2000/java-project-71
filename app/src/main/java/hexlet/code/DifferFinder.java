package hexlet.code;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class DifferFinder {
    public static List<DTO> getDiffers(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        List<DTO> differs = new ArrayList<>();

        Set<String> allKeys = new TreeSet<>(firstMap.keySet());
        Set<String> keysFromSecondMap = new TreeSet<>(secondMap.keySet());
        allKeys.addAll(keysFromSecondMap);

        for (String key : allKeys) {
            boolean keyContainsAtFirstMap = firstMap.containsKey(key);
            boolean keyContainsAtSecondMap = secondMap.containsKey(key);

            Object value1 = firstMap.get(key);
            Object value2 = secondMap.get(key);
            if (keyContainsAtFirstMap && !keyContainsAtSecondMap) {
                differs.add(new DTO(key, value1, "removed"));
            } else if (keyContainsAtFirstMap && keyContainsAtSecondMap && Objects.equals(value1, value2)) {
                differs.add(new DTO(key, value1, "unchanged"));
            } else if (keyContainsAtFirstMap && keyContainsAtSecondMap
                    && (!Objects.equals(value1, value2))) {
                differs.add(new DTO(key, value2, "changed", value1));
            } else if (!keyContainsAtFirstMap && keyContainsAtSecondMap) {
                differs.add(new DTO(key, value2, "added"));
            }
        }

        return differs;
    }
}
