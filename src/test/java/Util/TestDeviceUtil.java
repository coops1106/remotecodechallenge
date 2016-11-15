package Util;

import domain.Device;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestDeviceUtil {

    public void assertDeviceIsCorrect(final Device device,
                                       final String expectedBrand,
                                       final String expectedModel,
                                       final String expectedFormFactor,
                                       final Map<String, String> expectedAttributes) {
        assertThat(device.getBrand(), is(expectedBrand));
        assertThat(device.getModel(), is(expectedModel));
        assertThat(device.getFormFactor(), is(expectedFormFactor));
        for(final Map.Entry<String, String> keySet : expectedAttributes.entrySet()) {
            assertThat(device.getAttribute(keySet.getKey()), is(keySet.getValue()));
        }
    }
}
