import com.restaurant.restaurant.pojo.entity.Report;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HashCodeTest {
    @Test
    public void hashCodeTest() {
        Report report = new Report(1, 1, 4, "114", "514");
        Set<Report> reportSet = ConcurrentHashMap.newKeySet();
        reportSet.add(report);
        Report report2 = new Report(1, 2, 4, "114", "514");
        System.out.println(reportSet.contains(report2));

    }
}
