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
import org.investovator.ann.nngaming.util.GameTypes;
import org.investovator.core.data.api.utils.StockTradingData;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.util.*;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class AnalysisDataManager {

    private StockTradingData stockTradingData;
    private NNTrainer nnTrainer;
    private DataRetriever dataRetriever;
    private DataNormalizer dataNormalizer;
    private DataPreprocessor dataPreprocessor;

    private HashMap<Date,HashMap<TradingDataAttribute,String>> tradingData;
    private HashMap<TradingDataAttribute,String> tradingValues;
    private Set<Date> dateSet;
    private ArrayList<TradingDataAttribute> tradingDataAttributes;

    private double marketData [][];
    private double normalizedData [][];
    private double preprocessedData[][];
    private double inputData [][];
    private double idealData [][];
    private String symbol;
    private GameTypes gameType;
    private ArrayList<TradingDataAttribute> analysisAttributes;
    private HashMap<Date, String> dataMap;
    private String analysisStockID;

    public AnalysisDataManager(String stockID,NNTrainer nnTrainer,ArrayList<TradingDataAttribute> inputParameters,
                               GameTypes gameType,String analysisStockID){
        this.nnTrainer = nnTrainer;
        this.dataRetriever = new DataRetriever();
        this.tradingDataAttributes = inputParameters;
        this.symbol = stockID;
        this.gameType = gameType;
        this.analysisAttributes = new ArrayList<>();
        analysisAttributes.add(TradingDataAttribute.CLOSING_PRICE);
        this.analysisStockID = analysisStockID;

        retrieveData();
    }

    private void retrieveData(){

        Date dates[] = dataRetriever.getDateRange(symbol);

        dateSet = dataRetriever.getDates(dates[0],dates[1],symbol,tradingDataAttributes);

        int halfIndex = dateSet.size()/2;
        Date endDate = (Date) dateSet.toArray()[halfIndex];

        dateSet = dataRetriever.getDates(dates[0],endDate,symbol,tradingDataAttributes);

        dataMap = dataRetriever.getData(dates[0],endDate,
                analysisStockID, analysisAttributes);

        stockTradingData = dataRetriever.retrieveTrainingData(symbol,tradingDataAttributes);
        tradingData = stockTradingData.getTradingData();

        tradingDataAttributes = stockTradingData.getAttributes();

        tradingDataAttributes.add(TradingDataAttribute.CLOSING_PRICE);

    }

    public void prepareData(TradingDataAttribute predictingAttribute){

        //iterate dates and get data
        int i = 0;
        int tradingAttributeCount = tradingDataAttributes.size() - 1;
        int rowCount = dateSet.size();

        //Prepare Data Set
        marketData = new double[rowCount][tradingAttributeCount + 1];

        for (Iterator<Date> iterator = dateSet.iterator(); iterator.hasNext();) {
            Date next = iterator.next();
            tradingValues = tradingData.get(next);
            for(int j = 0; j < tradingAttributeCount; j++){

                if(tradingValues.get(tradingDataAttributes.get(j)).isEmpty())
                    marketData[i][j] = 0.0;
                else
                    marketData[i][j] = Double.parseDouble(tradingValues.get(tradingDataAttributes.get(j)));

            }
            if(dataMap.get(next) == null){
                marketData[i][tradingAttributeCount] = marketData[i - 1][tradingAttributeCount];
            }
            else{
                marketData[i][tradingAttributeCount] = Double.parseDouble(dataMap.get(next));
            }
            i++;
        }


        //Pre-process Data Set
        dataPreprocessor = new DataPreprocessor();
        preprocessedData = dataPreprocessor.preProcessData(marketData,tradingDataAttributes,predictingAttribute);

        //Normalizing Data Set
        dataNormalizer = new DataNormalizer(symbol,gameType);
        normalizedData = dataNormalizer.getNormalizedData(preprocessedData,tradingDataAttributes);

        int normalizedDataRowCount = normalizedData.length;
        int normalizedDataColCount = normalizedData[0].length;

        inputData = new double[normalizedDataRowCount][normalizedDataColCount - 1];
        idealData = new double[normalizedDataRowCount][1];

        for(int j = 0; j < normalizedDataRowCount; j++){

            for(int k = 0; k < normalizedDataColCount - 1; k++){
                inputData[j][k] = normalizedData[j][k];

            }

            idealData[j][0] = normalizedData[j][normalizedDataColCount - 1];

        }

        //Passing Data Set to NNTrainer
        nnTrainer.setTrainingData(inputData,idealData);
        nnTrainer.setPredictingAttribute(predictingAttribute);

    }

}
