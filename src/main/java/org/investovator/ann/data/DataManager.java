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
import org.investovator.ann.data.datapreprocessing.DataPreprocessor;
import org.investovator.ann.neuralnet.NNTrainer;
import org.investovator.core.data.api.utils.StockTradingData;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.util.*;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class DataManager {

    private StockTradingData stockTradingData;
    private NNTrainer nnTrainer;
    private DataRetriever dataRetriever;
    private HashMap<Date,HashMap<TradingDataAttribute,String>> tradingData;
    private HashMap<TradingDataAttribute,String> tradingValues;
    private Set<Date> dates;
    private ArrayList<TradingDataAttribute> tradingDataAttributes;
    private DataNormalizer dataNormalizer;
    private DataPreprocessor dataPreprocessor;

    private double marketData [][];
    private double normalizedData [][];
    private double preprocessedData[][];
    private double inputData [][];
    private double idealData [][];
    private String symbol;

    public DataManager(String stockID,NNTrainer nnTrainer,ArrayList<TradingDataAttribute> inputParameters){
         this.nnTrainer = nnTrainer;
         dataRetriever = new DataRetriever(stockID,inputParameters);
    }

    public void prepareData(){
        stockTradingData = dataRetriever.getTrainingData();
        tradingData = stockTradingData.getTradingData();
        dates = stockTradingData.getDates();

        tradingDataAttributes = stockTradingData.getAttributes();
        symbol = stockTradingData.getStockId();

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

        dataPreprocessor = new DataPreprocessor();
        preprocessedData = dataPreprocessor.preProcessData(marketData,tradingDataAttributes,TradingDataAttribute.CLOSING_PRICE);


        dataNormalizer = new DataNormalizer();
        dataNormalizer.setSymbol(symbol);
        tradingDataAttributes.add(TradingDataAttribute.CLOSING_PRICE);
        normalizedData = dataNormalizer.getNormalizedData(preprocessedData,tradingDataAttributes);

        int normalizedDataRowCount = normalizedData.length;
        int normalizedDataColCount = normalizedData[0].length;

        inputData = new double[normalizedDataRowCount][normalizedDataColCount];
        idealData = new double[normalizedDataRowCount][1];

        for(int j = 0; j < normalizedDataRowCount; j++){

            for(int k = 0; k < normalizedDataColCount; k++){
                inputData[j][k] = normalizedData[j][k];

            }

            idealData[j][0] = normalizedData[j][normalizedDataColCount - 1];

        }

        nnTrainer.setTrainingData(inputData,idealData);

    }


}
