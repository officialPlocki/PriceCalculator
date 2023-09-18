package xyz.plocki.demo.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtil {

    public HashMap<String, HashMap<String, String>> getWebResponse() throws IOException {
        BufferedInputStream stream = new BufferedInputStream(new URL("http://localhost:26/api").openStream());
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (stream.read(buffer) != -1) {
            baos.write(buffer, 0, 1024);
        }
        String response = baos.toString();
        HashMap<String, HashMap<String, String>> sorted = new HashMap<>();
        List<String> things = Arrays.stream(response.replaceFirst(":", "").split(":")).collect(Collectors.toList());
        things.forEach(s -> {
            if (!s.equalsIgnoreCase("")) {
                HashMap values = new HashMap();
                Arrays.stream(s.split(",")).collect(Collectors.toList()).forEach(comma -> {
                    String arg = comma.split("=")[0];
                    String value = comma.split("=")[1];
                    values.put(arg, value);
                });
                sorted.put((String)values.get("name"), values);
            }
        });
        return sorted;
    }

}
