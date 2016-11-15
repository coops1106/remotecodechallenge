package repository;

import domain.Device;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class DeviceRepositoryTest {

    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String FORM_FACTOR = "formFactor";

    private DeviceRepository uut;

    @Before
    public void setUp() {
        uut = new DeviceRepository();
        final List<Device> devices = new ArrayList<>();
        final Device device = new Device(BRAND, MODEL, FORM_FACTOR);
        devices.add(device);
        uut.addDevices(devices);
    }

    @Test
    public void testGetDeviceByBrandAndModel() throws Exception {
        final Device deviceByBrandAndModel = uut.getDeviceByBrandAndModel("brand model");

        assertThat(deviceByBrandAndModel.getBrand(), is(BRAND));
        assertThat(deviceByBrandAndModel.getModel(), is(MODEL));
        assertThat(deviceByBrandAndModel.getFormFactor(), is(FORM_FACTOR));
    }

    @Test
    public void testGetDeviceByBrandAndModelNotFound() throws Exception {
        try {
            uut.getDeviceByBrandAndModel("");
            fail();
        } catch (final DeviceNotFoundException dnfe) {
            //pass
        }
    }
}
