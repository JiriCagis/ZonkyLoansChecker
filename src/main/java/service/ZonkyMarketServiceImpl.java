package service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Loan;
import org.apache.log4j.Logger;
import util.DateUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Jiří Cága
 */
public class ZonkyMarketServiceImpl implements ZonkyMarketService {

    private final static Logger LOGGER = Logger.getLogger(ZonkyMarketServiceImpl.class);
    private final String MARKET_PLACE_URL;



    public ZonkyMarketServiceImpl(String marketPlaceURL) {
        this.MARKET_PLACE_URL = marketPlaceURL;
    }

    public List<Loan> getAllPublishedLoans() {
        LOGGER.debug("Zonky market service start fetch all published loans.");

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(MARKET_PLACE_URL)
                    .queryString("X-Order","-datePublished")
                    .asJson();
            List<Loan> result = convertJsonArrayOfLoansToList(jsonResponse.getBody().toString());
            LOGGER.debug("Zonky market service load " + result.size() + " loan(s).");
            return result;
        } catch (UnirestException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public List<Loan> getPublishedLoansAfter(Date date) {
        LOGGER.debug("Zonky market service start fetch published loans after " + date + ".");

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(MARKET_PLACE_URL)
                    .queryString("datePublished__gt",DateUtil.getISO8601StringForDate(date))
                    .queryString("X-Order","-datePublished")
                    .asJson();
            List<Loan> result = convertJsonArrayOfLoansToList(jsonResponse.getBody().toString());
            LOGGER.debug("Zonky market service load " + result.size() + " loan(s).");
            return result;
        } catch (UnirestException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private List<Loan> convertJsonArrayOfLoansToList(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonString, new TypeReference<List<Loan>>(){});
    }
}
