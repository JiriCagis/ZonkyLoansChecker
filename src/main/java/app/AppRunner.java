package app;

import org.apache.log4j.Logger;
import service.ZonkyMarketServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Input class which init and launch the ZonkyChecker application.
 *
 * Created by Jiří Cága
 */
public class AppRunner {

    private final static Logger LOGGER = Logger.getLogger(AppRunner.class);

    public static void main(String[] args) {
        ZonkyCheckerApp app = new ZonkyCheckerApp();

        Properties configProperties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("src/main/resources/zonkyLoansChecker.properties");

            // load a properties file
            configProperties.load(input);
            final long checkInterval = Long.parseLong(configProperties.getProperty("checkInterval"));
            final String marketPlaceURL = configProperties.getProperty("marketPlaceURL");

            app.setCheckInterval(checkInterval);
            app.setZonkyMarketService(new ZonkyMarketServiceImpl(marketPlaceURL));
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        app.run();
    }
}
