package services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import domain.Device;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DeviceReader {

    public List<Device> readDevices(final File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Device.class, new DeviceDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(file, new TypeReference<List<Device>>(){});
    }

    private class DeviceDeserializer extends JsonDeserializer<Device> {
        @Override
        public Device deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);
            final String brand = node.get("brand").asText();
            final String model = node.get("model").asText();
            final String formFactor = node.get("formFactor").asText();

            final Device device = new Device(brand, model, formFactor);
            final JsonNode attributes = node.get("attributes");
            for(JsonNode jsonNode : attributes) {
                final String name = jsonNode.get("name").asText();
                final String value = jsonNode.get("value").asText();
                device.addAttribute(name, value);
            }
            return device;
        }
    }
}
