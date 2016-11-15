package repository;

import domain.Device;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class DeviceRepository {

    private final Set<Device> devices = new HashSet<>();

    public void addDevice(final Device device) {
        devices.add(device);
    }

    public void addDevices(final List<Device> devices) {
        for (final Device device : devices) {
            addDevice(device);
        }
    }

    public Device getDeviceByBrandAndModel(final String brandAndModel) {
        for (final Device device : devices) {
            if(brandAndModel.equalsIgnoreCase(device.getBrand() + " " + device.getModel())) {
                return device;
            }
        }
        throw new DeviceNotFoundException("Unable to find device with brand and model [" + brandAndModel + "]");
    }
}
