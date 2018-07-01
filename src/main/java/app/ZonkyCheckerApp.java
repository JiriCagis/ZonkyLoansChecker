package app;

import model.Loan;
import org.apache.log4j.Logger;
import service.ZonkyMarketService;
import service.ZonkyMarketServiceImpl;
import util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 *
 * Application ZonkyChecker is compose from two steps. First step fetch  all published loans from the market
 * for this day and display loans into console.
 * Second step periodically check new loans on market by user specified time and display into console.
 *
 * Note: Check interval time must be entered in mili-seconds (Example 1 minute interval = 60 000 mili-seconds)
 *
 * Created by Jiří Cága
 */
public class ZonkyCheckerApp {

    private final static Logger LOGGER = Logger.getLogger(ZonkyMarketServiceImpl.class);

    private ZonkyMarketService zonkyMarketService;
    private long checkInterval;



    public void run() {
        LOGGER.info("----------------------------------------------------");
        LOGGER.info("Zonky checker app");
        LOGGER.info("----------------------------------------------------");
        LOGGER.info("Loans added this day:");

        Date actualDate = DateUtil.truncateTime(new Date());
        List<Loan> loans = zonkyMarketService.getPublishedLoansAfter(actualDate);
        if(!loans.isEmpty()){
            for(Loan loan:loans){
                LOGGER.info(loan);
            }
        } else {
            LOGGER.info("Market actually does not contains any loans for this day.");
        }

        LOGGER.info("\n\n\n");
        LOGGER.info("App periodically check new loans on market each " + checkInterval/60/1000 + " minute(s).");
        LOGGER.info("Note: For terminate app press CTRL+C");
        while(true){
            try {
                Date starTime = new Date();
                Thread.sleep(checkInterval);
                List<Loan> newLoans = zonkyMarketService.getPublishedLoansAfter(starTime);
                if(!newLoans.isEmpty()){
                    LOGGER.info("On the market was add this new loan(s):");
                    for(Loan loan:newLoans){
                        LOGGER.info(loan);
                    }
                } else {
                    LOGGER.info("On the market not come new loans.");
                }
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }

        }
    }



    // Getters and setters
    public void setCheckInterval(long checkInterval) {
        this.checkInterval = checkInterval;
    }

    public void setZonkyMarketService(ZonkyMarketService zonkyMarketService) {
        this.zonkyMarketService = zonkyMarketService;
    }

}
