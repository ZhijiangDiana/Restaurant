import com.restaurant.restaurant.service.FrequentCommentFilter;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class FrequentCommentFilterTest {
    @Test
    public void timeTest() throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void filterTest() {
        Queue<Date> queue = new LinkedList<>();
        for (int i=0;i<20;i++) {
            System.out.println(FrequentCommentFilter.frequencyCommentFilter(queue) + " " + queue.size());
        }
    }
}
