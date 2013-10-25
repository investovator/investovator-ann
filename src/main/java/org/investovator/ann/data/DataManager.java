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

import org.investovator.ann.data.datanormalizing.DataNormalizer;
import org.investovator.core.data.api.utils.StockTradingData;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.util.*;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class DataManager {

    private StockTradingData stockTradingData;
    private DataRetriever dataRetriever;
    private HashMap<Date,HashMap<TradingDataAttribute,String>> tradingData;
    private HashMap<TradingDataAttribute,String> tradingValues;
    private Set<Date> dates;
    private ArrayList<TradingDataAttribute> tradingDataAttributes;
    private double marketData [][];
    private DataNormalizer dataNormalizer;
    private double normalizedData [][];
    private double inputData [][];
    private double idealData [][];

    public DataManager(String stockID){
         dataRetriever = new DataRetriever(stockID);
    }

    public void prepareData(){
        stockTradingData = dataRetriever.getTrainingData();
        tradingData = stockTradingData.getTradingData();
        dates = stockTradingData.getDates();

        tradingDataAttributes = new ArrayList<TradingDataAttribute>(); //only for now, should give reference

        tradingDataAttributes.add(TradingDataAttribute.HIGH_PRICE);
        tradingDataAttributes.add(TradingDataAttribute.LOW_PRICE);
        tradingDataAttributes.add(TradingDataAttribute.CLOSING_PRICE);

        prepareTradingData();

    }

    private void prepareTradingData(){

        //iterate dates and get data
        int i = 0;
        int tradingAttributeCount = tradingDataAttributes.size();
        int rowCount = dates.size();
        int colCount = tradingDataAttributes.size();
        marketData = new double[rowCount][colCount];

        for (Iterator<Date> iterator = dates.iterator(); iterator.hasNext();) {
            Date next = iterator.next();
            tradingValues = tradingData.get(next);
            for(int j = 0; j < tradingAttributeCount; j++){

                marketData[i][j] = Double.parseDouble(tradingValues.get(tradingDataAttributes.get(j)));

            }
            i++;
        }

        dataNormalizer = new DataNormalizer();

        normalizedData = dataNormalizer.getNormalizedData(marketData,tradingDataAttributes);

        int closingPriceIndex = tradingDataAttributes.indexOf(TradingDataAttribute.CLOSING_PRICE);
        inputData = new double[rowCount - 1][tradingAttributeCount];
        idealData = new double[rowCount - 1][0];

        for(int j = 0; j < rowCount - 1; j++){

            for(int k = 0; k < colCount; k++){
                inputData[j][k] = normalizedData[j][k];
            }
            idealData[j][0] = normalizedData[j + 1][closingPriceIndex];
        }



    }

}
