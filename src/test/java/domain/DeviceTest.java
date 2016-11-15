package domain;

import org.junit.Test;
import repository.exceptions.NonValidDeviceException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class DeviceTest {

    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String INVALID_FORM_FACTOR = "formfactor";
    private static final String ATTRIBUTE_VALUE = "attr value";
    private static final String ATTRIBUTE_NAME = "attribute name";

    @Test
    public void testInvalidFormFactor() throws Exception {
        try {
            new Device(BRAND, MODEL, INVALID_FORM_FACTOR);
            fail();
        } catch (final NonValidDeviceException nvde) {
            assertThat(nvde.getLocalizedMessage(), is("Form factor must be one of [CANDYBAR, SMARTPHONE, PHABLET, CLAMSHELL]"));
        }
    }

    @Test
    public void testEmptyBrandNameValidation() throws Exception {
        try {
            new Device("", MODEL, Device.FormFactor.SMARTPHONE);
            fail();
        } catch (final NonValidDeviceException nvde) {
            assertThat(nvde.getLocalizedMessage(), is("brand must be filled in - max length 50 characters"));
        }
    }

    @Test
    public void testOverMaxLengthBrandNameValidation() throws Exception {
        try {
            new Device("aaaaaaarrrreelllyyy loonnnggg bbrrraannndd naaaamee",
                    MODEL, Device.FormFactor.SMARTPHONE);
            fail();
        } catch (final NonValidDeviceException nvde) {
            assertThat(nvde.getLocalizedMessage(), is("brand must be filled in - max length 50 characters"));
        }
    }

    @Test
    public void testEmptyModelNameValidation() throws Exception {
        try {
            new Device(BRAND, "", Device.FormFactor.SMARTPHONE);
            fail();
        } catch (final NonValidDeviceException nvde) {
            assertThat(nvde.getLocalizedMessage(), is("model must be filled in - max length 50 characters"));
        }
    }

    @Test
    public void testOverMaxLengthModelNameValidation() throws Exception {
        try {
            new Device(BRAND,
                    "aaaaaaaaaaaaarrrrrrreeeealllllyyy loooonnnnngggggg mmmmoooodddellll naaaaaammmmeee", Device.FormFactor.PHABLET);
            fail();
        } catch (final NonValidDeviceException nvde) {
            assertThat(nvde.getLocalizedMessage(), is("model must be filled in - max length 50 characters"));
        }
    }

    @Test
    public void testAttributeNameNotEmptyValidation() throws Exception {
        final Device uut = new Device(BRAND, MODEL, Device.FormFactor.CANDYBAR);
        try {
            uut.addAttribute("", ATTRIBUTE_VALUE);
            fail();
        } catch (final NonValidDeviceException nvde) {
            assertThat(nvde.getLocalizedMessage(), is("attribute name must be filled in - max length 20 characters"));
        }
    }

    @Test
    public void testAttributeValueNotEmptyValidation() throws Exception {
        final Device uut = new Device(BRAND, MODEL, Device.FormFactor.CANDYBAR);
        try {
            uut.addAttribute(ATTRIBUTE_NAME, "");
            fail();
        } catch (final NonValidDeviceException nvde) {
            assertThat(nvde.getLocalizedMessage(), is("attribute value must be filled in - max length 100 characters"));
        }
    }

    @Test
    public void testOverMaxLengthAttributeNameValidation() throws Exception {
        final Device uut = new Device(BRAND, MODEL, Device.FormFactor.CLAMSHELL);
        try {
            uut.addAttribute("attrname 21chars long", ATTRIBUTE_VALUE);
            fail();
        } catch (final NonValidDeviceException nvde) {
            assertThat(nvde.getLocalizedMessage(), is("attribute name must be filled in - max length 20 characters"));
        }
    }

    @Test
    public void testOverMaxLengthAttributeValueValidation() throws Exception {
        final Device uut = new Device(BRAND, MODEL, Device.FormFactor.CLAMSHELL);
        try {
            uut.addAttribute(ATTRIBUTE_NAME, "super super super super super super super super super super super " +
                    "super super super super super super super super long attribute value");
            fail();
        } catch (final NonValidDeviceException nvde) {
            assertThat(nvde.getLocalizedMessage(), is("attribute value must be filled in - max length 100 characters"));
        }
    }
}