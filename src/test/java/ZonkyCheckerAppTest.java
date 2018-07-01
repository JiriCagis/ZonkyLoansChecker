import app.ZonkyCheckerApp;
import model.Loan;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import service.ZonkyMarketService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jiří Cága
 */
public class ZonkyCheckerAppTest {

    private ZonkyCheckerApp app;
    private ZonkyMarketService zonkyMarketService;

    private final static long CHECK_INTERVAL = 1000; //1 seconds

    @Before
    public void init(){
        zonkyMarketService = mock(ZonkyMarketService.class);
        when(zonkyMarketService.getAllPublishedLoans()).thenReturn(generateDummyLoans(20));
        when(zonkyMarketService.getPublishedLoansAfter(any(Date.class))).thenReturn(generateDummyLoans(5));

        app = new ZonkyCheckerApp();
        app.setCheckInterval(CHECK_INTERVAL);
        app.setZonkyMarketService(zonkyMarketService);
    }

    @Test
    public void testCallMethodForGetNewLoansAfterTimeInterval() throws InterruptedException {
        Thread testThread = new Thread(() -> app.run());
        testThread.start();
        Thread.sleep((long) (CHECK_INTERVAL*1.20));
        verify(zonkyMarketService,times(2)).getPublishedLoansAfter(any(Date.class));
    }

    @Test
    public void testDoesNotCallMethodGettingNewLoansBeforeTimeInterval() throws InterruptedException {
        Thread testThread = new Thread(() -> app.run());
        testThread.start();
        Thread.sleep((long) (CHECK_INTERVAL*0.20));
        verify(zonkyMarketService,atMost(1)).getPublishedLoansAfter(any(Date.class));
    }

    @Test
    public void testCallMethodGettingNewLoansForManyTick() throws InterruptedException {
        Thread testThread = new Thread(() -> app.run());
        testThread.start();
        Thread.sleep((long) (3*CHECK_INTERVAL*1.20));
        verify(zonkyMarketService,times(4)).getPublishedLoansAfter(any(Date.class));
    }

    private List<Loan> generateDummyLoans(int count){
        List<Loan> loans = new ArrayList<>();

        for(int i=0;i<count;i++){
            Loan loan = new Loan();
            loan.setId((long) i);
            loan.setStory("Story"+i);
            loan.setName("Name"+i);
            loan.setAmount(BigDecimal.ONE);
            loan.setRating("AAA");
            loan.setPublishDate(new Date());
            loan.setDeadLineDate(new Date());
            loan.setUserId("UserId" + i);
            loan.setNickName("NickName"+i);
            loans.add(loan);
        }

        return loans;
    }
}
