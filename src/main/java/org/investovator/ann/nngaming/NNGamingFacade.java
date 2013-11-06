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

import org.investovator.ann.nngaming.util.GameTypes;

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

    public NNGamingFacade(GameTypes gameType){

         if(gameType == GameTypes.TRADING_GAME){

              bidGenerator = new BidGenerator();
              generatedBids = new ArrayList<>();


         }
         else{

         }
    }


    public ArrayList<Float> getGeneratedOrders(int buyOrderCount, int sellOrderCount, String stockID, int currentDay){
        System.setProperty("org.investovator.core.data.cassandra.url", "localhost:9160" );
        System.setProperty("org.investovator.core.data.cassandra.username", "admin" );
        System.setProperty("org.investovator.core.data.cassandra.password", "admin" );



        if(predictedValues == null){

            predictionValueManager = new PredictionValueManager(stockID);
            predictedValues = predictionValueManager.getAllPredictionValues();

        }

        generatedBids = bidGenerator.getOrders(predictedValues[currentDay - 1],buyOrderCount,sellOrderCount);

        return generatedBids;
    }
}