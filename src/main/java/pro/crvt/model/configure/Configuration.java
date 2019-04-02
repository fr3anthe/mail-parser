package pro.crvt.model.configure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pro.crvt.model.entities.Contact;
import java.io.File;
import java.io.IOException;

public class Configuration {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final JsonNode ROOT = loadConfigFile();
    private static final String JSON = "types.json";

    /**
     * Method loadContactParam.
     * @param type message type
     * @return contact
     */
    public static Contact loadContactParam(String type) {
        return MAPPER.convertValue(ROOT.get(type), Contact.class);
    }

    /**
     * Method loadConfigFile.
     * @return config
     */
    private static JsonNode loadConfigFile() {
        JsonNode node = null;
        File json = new File(ClassLoader.getSystemResource(JSON).getFile());
        try {
            node = MAPPER.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }
}
