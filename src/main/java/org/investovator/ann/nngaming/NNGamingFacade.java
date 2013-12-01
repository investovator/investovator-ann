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

package org.investovator.ann.nngaming;

import org.investovator.ann.data.DataRetriever;
import org.investovator.ann.nngaming.util.GameTypes;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NNGamingFacade {

    private BidGenerator bidGenerator;
    private PredictionValueManager predictionValueManager;
    private float[] predictedValues;
    private ArrayList<Float> generatedBids;
    private int daysCount;
    private int speedFactor;

    private static NNGamingFacade instance;

    private NNGamingFacade(){

        bidGenerator = new BidGenerator();
        generatedBids = new ArrayList<>();

    }

    public static NNGamingFacade getInstance() {
        if(instance == null){
            synchronized(NNGamingFacade.class){
                if(instance == null)
                    instance = new NNGamingFacade();
            }
        }
        return instance;
    }


    public ArrayList<Float> getGeneratedOrders(int buyOrderCount, int sellOrderCount, String stockID, int currentDay){



        if(predictedValues == null){                      //only predictions are retrieved once

            predictionValueManager = new PredictionValueManager(stockID,daysCount);
            predictedValues = predictionValueManager.getAllPredictionValues(TradingDataAttribute.CLOSING_PRICE, GameTypes.TRADING_GAME);

        }

        generatedBids = bidGenerator.getOrders(predictedValues[currentDay - 1],buyOrderCount,sellOrderCount);

        predictedValues = null;

        return generatedBids;
    }

    public void startGame(){

        EventScheduler.getInstance().start();

    }

    public void stopGame(){

        EventScheduler.getInstance().stopService();

    }

    //Data retrieval methods for graphical purposes

    public HashMap<Date,String> getChartData(Date start, Date end, String stockID,
                                             ArrayList<TradingDataAttribute> attributes){

        DataRetriever dataRetriever = new DataRetriever();
        HashMap<Date, String> chartData;

        chartData = dataRetriever.getData(start, end, stockID, attributes);

        return chartData;
    }

    public Date[] getDateRange(String stockID){

        DataRetriever dataRetriever = new DataRetriever();
        Date[] dateRange;

        dateRange = dataRetriever.getDateRange(stockID);

        return dateRange;
    }

    public Set<Date> getDates(Date start, Date end, String stockID,
                              ArrayList<TradingDataAttribute> attributes){

        DataRetriever dataRetriever = new DataRetriever();
        Set<Date> dates;

        dates = dataRetriever.getDates(start, end, stockID, attributes);

        return dates;

    }

    public float[] getPredictedPrices(String stockID, TradingDataAttribute attribute){

        PredictionValueManager predictionValueManager = new PredictionValueManager(stockID,daysCount);
        float[] predictedPrices = predictionValueManager.getAllPredictionValues(attribute, GameTypes.TRADING_GAME);

        for(int i = 0; i < predictedPrices.length; i++){

            predictedPrices[i] = Float.valueOf(String.format("%.1f", predictedPrices[i]));

        }

        return predictedPrices;

    }

    //Returns data for Analysis Tab

    public ArrayList<ArrayList<Object>> getAnalysisData(Date start, Date end, String stockID, GameTypes gameType,
                                                        double analysisValue,String analysisStockID){

        AnalysisPredictionManager analysisPredictionManager = new AnalysisPredictionManager();

        return analysisPredictionManager.getGraphData(start,end,stockID,gameType,analysisValue,analysisStockID);

    }

    public void setDaysCount(int daysCount) {
        this.daysCount = daysCount;
    }

}
