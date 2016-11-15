package repository;

import domain.Device;
import repository.exceptions.DeviceNotFoundException;
import repository.exceptions.NonUniqueDeviceException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class DeviceRepository {

    private final Set<Device> devices = new HashSet<>();

    public void addDevice(final Device device) {
        if (devices.contains(device)) {
            throw new NonUniqueDeviceException();
        }
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

    public List<Device> getDevicesByBrand(final String brand) {
        final List<Device> foundDevices = new ArrayList<>();
        for (final Device device : devices) {
            if (brand.equalsIgnoreCase(device.getBrand())) {
                foundDevices.add(device);
            }
        }
        return foundDevices;
    }

    public List<Device> getDevicesByModel(final String model) {
        final List<Device> foundDevices = new ArrayList<>();
        for (final Device device : devices) {
            if (model.equalsIgnoreCase(device.getModel())) {
                foundDevices.add(device);
            }
        }
        return foundDevices;
    }
}
