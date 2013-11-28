/*
 * investovator, Stock Market Gaming Framework
 *     Copyright (C) 2013  investovator
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.investovator.ann.data;

import org.investovator.core.data.api.CompanyStockTransactionsData;
import org.investovator.core.data.api.CompanyStockTransactionsDataImpl;
import org.investovator.core.data.api.utils.StockTradingData;
import org.investovator.core.data.api.utils.TradingDataAttribute;
import org.investovator.core.data.exeptions.DataAccessException;
import org.investovator.core.data.exeptions.DataNotFoundException;

import java.util.*;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class DataRetriever {

    private StockTradingData stockTradingData;

    private int NUM_OF_ROWS = 10000;
    private double[] gameData;

    public DataRetriever(){


    }


    public StockTradingData retrieveTrainingData(String symbol, ArrayList<TradingDataAttribute> attributes){

        CompanyStockTransactionsDataImpl companyStockTransactionsData = new CompanyStockTransactionsDataImpl();

        try {

            Date[] dateRange = companyStockTransactionsData.getDataDaysRange(CompanyStockTransactionsData.DataType.OHLC,
                    symbol);
            Date startingDate = dateRange[0];
            Date endDate = dateRange[1];

            stockTradingData = companyStockTransactionsData.getTradingData(CompanyStockTransactionsData.DataType.OHLC,
                    symbol,startingDate,endDate,NUM_OF_ROWS,attributes);

        } catch (DataNotFoundException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return stockTradingData;
    }

    public double[] getGamingData(String symbol, ArrayList<TradingDataAttribute> attributes){

        CompanyStockTransactionsData companyStockTransactionsData = new CompanyStockTransactionsDataImpl();
        Date[] dateRange;
        Date endDate;

        try {
            dateRange = companyStockTransactionsData.getDataDaysRange(CompanyStockTransactionsData.DataType.OHLC,
                    symbol);
            endDate = dateRange[1];

            stockTradingData = companyStockTransactionsData.getTradingData(CompanyStockTransactionsData.DataType.OHLC,
                    symbol,endDate,endDate,1,attributes);

            int attributeCount = attributes.size();
            gameData = new double[attributeCount];

            for(int i = 0; i < attributeCount;i++){

                gameData[i] = Double.parseDouble(stockTradingData.getTradingDataEntry(endDate).get(attributes.get(i)));

            }

        } catch (DataNotFoundException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return gameData;

    }

    public Date[] getDateRange(String stockID){

        CompanyStockTransactionsDataImpl companyStockTransactionsData = new CompanyStockTransactionsDataImpl();

        Date[] dateRange;
        try {
            dateRange = companyStockTransactionsData.getDataDaysRange(CompanyStockTransactionsData.DataType.OHLC,
                    stockID);

            return dateRange;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Date, String> getData(Date start, Date end, String stockID,
                                              ArrayList<TradingDataAttribute> attributes){

        CompanyStockTransactionsDataImpl companyStockTransactionsData = new CompanyStockTransactionsDataImpl();
        HashMap<Date, String> chartData = new HashMap<>();
        StockTradingData stockTradingData;

        try {
            stockTradingData = companyStockTransactionsData.getTradingData(CompanyStockTransactionsData.DataType.OHLC,
                    stockID, start, end,NUM_OF_ROWS,attributes);

            Set<Date> dates = stockTradingData.getDates();


            for (Iterator<Date> iterator = dates.iterator(); iterator.hasNext();) {
                Date next = iterator.next();

                chartData.put(next,stockTradingData.getTradingDataAttributeValue(next,attributes.get(0)));
            }

        } catch (DataNotFoundException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }


        return chartData;
    }

    public Set<Date> getDates(Date start, Date end, String stockID, ArrayList<TradingDataAttribute> attributes){

        CompanyStockTransactionsDataImpl companyStockTransactionsData = new CompanyStockTransactionsDataImpl();
        Set<Date> dates;
        StockTradingData stockTradingData;

        try {
            stockTradingData = companyStockTransactionsData.getTradingData(CompanyStockTransactionsData.DataType.OHLC,
                    stockID, start, end,NUM_OF_ROWS,attributes);

            dates = stockTradingData.getDates();

            return dates;

        } catch (DataNotFoundException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return null;

    }


}