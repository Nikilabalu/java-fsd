
import org.junit.jupiter.api.Test;
import service.AppService;
import org.junit.jupiter.api.Assertions;
public class AppServiceTest {

    private AppService appService = new AppService();

    @Test
    public void addTest() {
        Assertions.assertEquals(3, appService.add(1, 2));
        Assertions.assertEquals(1, appService.add(-1, 2));
        Assertions.assertEquals(0, appService.add(0, 0));
        Assertions.assertNotEquals(1, appService.add(2, 0));

    }
}
