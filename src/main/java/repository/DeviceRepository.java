package repository;

import domain.Device;
import repository.exceptions.DeviceNotFoundException;
import repository.exceptions.NonUniqueDeviceException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return devices.stream().filter(device -> brand.equalsIgnoreCase(device.getBrand())).collect(Collectors.toList());
    }

    public List<Device> getDevicesByModel(final String model) {
        return devices.stream().filter(device -> model.equalsIgnoreCase(device.getModel())).collect(Collectors.toList());
    }
}
