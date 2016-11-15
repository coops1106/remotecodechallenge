package domain;

import org.apache.commons.lang3.StringUtils;
import repository.exceptions.NonValidDeviceException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Device {

    private static final int MAX_BRAND_NAME_LENGTH = 50;
    private static final int MAX_MODEL_NAME_LENGTH = 50;
    private static final String PROPERTY_INVALID_MESSAGE_TEMPLATE = "%s must be filled in - max length %s characters";
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String FORM_FACTOR_VALIDATION_ERROR_MESSAGE = "Form factor must be one of " + Arrays.asList(FormFactor.values());
    private static final int MAX_ATTRIBUTE_NAME_LENGTH = 20;
    private static final int MAX_ATTRIBUTE_VALUE_LENGTH = 100;
    private static final String ATTRIBUTE_NAME = "attribute name";
    private static final String ATTRIBUTE_VALUE = "attribute value";

    private final String brand;
    private final String model;
    private final FormFactor formFactor;
    private final Map<String, String> attributes;

    public enum FormFactor {
        CANDYBAR, SMARTPHONE, PHABLET, CLAMSHELL
    }

    public Device(final String brand, final String model, final FormFactor formFactor) {
        this(brand, model, formFactor.name());
    }

    public Device(final String brand, final String model, final String formFactorAsString) {
        if (!isBrandNameValid(brand)) {
            throw new NonValidDeviceException(String.format(PROPERTY_INVALID_MESSAGE_TEMPLATE, BRAND, MAX_BRAND_NAME_LENGTH));
        } else {
            this.brand = brand;
        }

        if (!isModelNameValid(model)) {
            throw new NonValidDeviceException(String.format(PROPERTY_INVALID_MESSAGE_TEMPLATE, MODEL, MAX_MODEL_NAME_LENGTH));
        } else {
            this.model = model;
        }

        try {
            this.formFactor = FormFactor.valueOf(formFactorAsString.toUpperCase());
        } catch (final IllegalArgumentException iae) {
            throw new NonValidDeviceException(FORM_FACTOR_VALIDATION_ERROR_MESSAGE);
        }
        this.attributes = new HashMap<>();
    }

    public void addAttribute(final String attributeName, final String attributeValue) {
        if (!isAttributeNameValid(attributeName)) {
            throw new NonValidDeviceException(String.format(PROPERTY_INVALID_MESSAGE_TEMPLATE, ATTRIBUTE_NAME, MAX_ATTRIBUTE_NAME_LENGTH));
        }
        if (!isAttributeValueValid(attributeValue)) {
            throw new NonValidDeviceException(String.format(PROPERTY_INVALID_MESSAGE_TEMPLATE, ATTRIBUTE_VALUE, MAX_ATTRIBUTE_VALUE_LENGTH));
        }
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
        return formFactor.name();
    }

    private boolean isModelNameValid(final String modelName) {
        return isNotEmptyAndUnderMaxLength(modelName, MAX_MODEL_NAME_LENGTH);
    }

    private boolean isBrandNameValid(final String brandName) {
        return isNotEmptyAndUnderMaxLength(brandName, MAX_BRAND_NAME_LENGTH);
    }

    private boolean isAttributeNameValid(final String attributeName) {
        return isNotEmptyAndUnderMaxLength(attributeName, MAX_ATTRIBUTE_NAME_LENGTH);
    }

    private boolean isAttributeValueValid(final String attributeValue) {
        return isNotEmptyAndUnderMaxLength(attributeValue, MAX_ATTRIBUTE_VALUE_LENGTH);
    }

    private boolean isNotEmptyAndUnderMaxLength(final String modelName, final int maxLength) {
        return !StringUtils.isEmpty(modelName) && modelName.length() <= maxLength;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Device device = (Device) o;

        if (!brand.equals(device.brand)) return false;
        if (!model.equals(device.model)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }
}
