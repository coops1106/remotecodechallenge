package integrationTests;

import Util.TestDeviceUtil;
import domain.Device;
import org.junit.Test;
import repository.DeviceRepository;
import services.DeviceReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

    private static final String WORKING_DIR = System.getProperty("user.dir");
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String PATH_TO_FILE = "src" + FILE_SEPARATOR + "test" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "devices.json";

    private final DeviceReader deviceReader = new DeviceReader();
    private final DeviceRepository deviceRepository = new DeviceRepository();

    private final TestDeviceUtil testDeviceUtil = new TestDeviceUtil();

    @Test
    public void testAbleToReadDevicesFromFileAndSearchByBrandAndModel() throws Exception {
        final List<Device> devices = deviceReader.readDevices(new File(WORKING_DIR + FILE_SEPARATOR + PATH_TO_FILE));
        deviceRepository.addDevices(devices);

        final Device deviceByBrandAndModel = deviceRepository.getDeviceByBrandAndModel("Mockia 5800");
        final Device deviceByBrandAndModel1 = deviceRepository.getDeviceByBrandAndModel("Phony X11");
        final Device deviceByBrandAndModel2 = deviceRepository.getDeviceByBrandAndModel("Samwrong Universe A1");

        final Map<String, String> expectedAttributes1 = new HashMap<>();
        expectedAttributes1.put("Screen Size", "128mm");
        testDeviceUtil.assertDeviceIsCorrect(deviceByBrandAndModel, "Mockia", "5800", "CANDYBAR", expectedAttributes1);

        final Map<String, String> expectedAttributes2 = new HashMap<>();
        expectedAttributes2.put("Bluetooth","0.1");
        expectedAttributes2.put("Raspberry","Pi");
        testDeviceUtil.assertDeviceIsCorrect(deviceByBrandAndModel1, "Phony", "X11", "SMARTPHONE", expectedAttributes2);

        final Map<String, String> expectedAttributes3 = new HashMap<>();
        expectedAttributes3.put("Frequencies", "GSM,LTE,Kenneth");
        expectedAttributes3.put("Memory", "333Mb");
        expectedAttributes3.put("Teasmaid", "true");
        testDeviceUtil.assertDeviceIsCorrect(deviceByBrandAndModel2, "Samwrong", "Universe A1", "PHABLET", expectedAttributes3);
    }
}