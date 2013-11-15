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

import java.util.ArrayList;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NNGamingFacade {

    private NNPredictor nnPredictor;
    private BidGenerator bidGenerator;
    private PredictionValueManager predictionValueManager;
    private float[] predictedValues;
    private ArrayList<Float> generatedBids;

    private static NNGamingFacade instance;

    public NNGamingFacade(){

         /*if(gameType == GameTypes.TRADING_GAME){

              bidGenerator = new BidGenerator();
              generatedBids = new ArrayList<>();


         }
         else{

         }*/

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



        if(predictedValues == null){                      //only if predictions are not retrieved once

            predictionValueManager = new PredictionValueManager(stockID);
            predictedValues = predictionValueManager.getAllPredictionValues();

        }

        generatedBids = bidGenerator.getOrders(predictedValues[currentDay - 1],buyOrderCount,sellOrderCount);

        return generatedBids;
    }

    public void startGame(){

        EventScheduler.getInstance().start();

    }

    public void stopGame(){

        EventScheduler.getInstance().stopService();

    }

}
