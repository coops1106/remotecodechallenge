package domain;

import java.util.HashMap;
import java.util.Map;

public final class Device {

    private final String brand;
    private final String model;
    private final String formFactor;
    private final Map<String, String> attributes;

    public Device(final String brand, final String model, final String formFactor) {
        this.brand = brand;
        this.model = model;
        this.formFactor = formFactor;
        this.attributes = new HashMap<>();
    }

    public void addAttribute(final String attributeName, final String attributeValue) {
        this.attributes.put(attributeName, attributeValue);
    }

    public String getAttribute(final String attributeName) {
        return this.attributes.get(attributeName);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getFormFactor() {
        return formFactor;
    }

    @Override
    public String toString() {
        return "Device{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
