package repository;

import domain.Device;
import org.junit.Before;
import org.junit.Test;
import repository.exceptions.DeviceNotFoundException;
import repository.exceptions.NonUniqueDeviceException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class DeviceRepositoryTest {

    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String FORM_FACTOR = "SMARTPHONE";
    private static final String MODEL_2 = "model2";
    private static final String BRAND_2 = "brand 2";
    private static final String BRAND_3 = "brand 3";

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

    @Test
    public void testUnableToAddNonUniqueDevice() throws Exception {
        try {
            uut.addDevice(new Device(BRAND, MODEL, FORM_FACTOR));
            fail();
        } catch (final NonUniqueDeviceException nude) {
            //pass
        }
    }

    @Test
    public void testRetrieveDeviceByBrand() throws Exception {
        uut.addDevice(new Device(BRAND, MODEL_2, Device.FormFactor.PHABLET));

        final List<Device> devicesByBrand = uut.getDevicesByBrand(BRAND);

        assertThat(devicesByBrand.size(), is(2));
    }

    @Test
    public void testRetrieveDeviceByModel() throws Exception {
        uut.addDevice(new Device(BRAND_2, MODEL, Device.FormFactor.CLAMSHELL));
        uut.addDevice(new Device(BRAND_3, MODEL, Device.FormFactor.SMARTPHONE));

        final List<Device> devicesByBrand = uut.getDevicesByModel(MODEL);

        assertThat(devicesByBrand.size(), is(3));
    }
}