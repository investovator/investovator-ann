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

package org.investovator.ann.neuralnet;

import org.encog.neural.networks.BasicNetwork;
import org.investovator.ann.data.DataManager;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 *
 */
public class NNManager {

    final int ITERATION_COUNT = 5000;
    final double ERROR = 0.001;
    final int INPUT_PARAM_COUNT = 6;

    private HashMap<String,String> newParameters;
    private ArrayList<TradingDataAttribute> inputParameters;
    private ArrayList<String> stockIDs;
    private NNCreator nnCreator;
    private NNTrainer nnTrainer;
    private boolean status;
    private int inputParamCount;
    private DataManager dataManager;

    public NNManager(HashMap newParameters,ArrayList<TradingDataAttribute> inputParameters,ArrayList<String> stockIDs){
        this.newParameters = newParameters;
        this.inputParameters = inputParameters;
        this.stockIDs = stockIDs;
        this.inputParamCount = inputParameters.size() + newParameters.size();
        status = false;
    }

    public NNManager(ArrayList<TradingDataAttribute> inputParameters,ArrayList<String> stockIDs){
        this.inputParameters = inputParameters;
        this.stockIDs = stockIDs;
        this.inputParamCount = inputParameters.size();
        this.inputParamCount = INPUT_PARAM_COUNT;
        status = false;
    }

    public boolean createNeuralNetwork(){

        nnCreator = new NNCreator(inputParamCount,1);
        nnTrainer = new NNTrainer();

        int inputParameterCount = inputParameters.size();

        nnTrainer.setIterationCount(ITERATION_COUNT);
        nnTrainer.setError(ERROR);

        for(int k = 0; k < stockIDs.size(); k++){

            dataManager = new DataManager(stockIDs.get(k),nnTrainer,inputParameters);

            for(int i = 0;i < inputParameterCount; i++){

                BasicNetwork network = nnCreator.createNetwork();
                dataManager.prepareData(inputParameters.get(i));            //specifies predicting attribute
                status = nnTrainer.TrainANN(network,stockIDs.get(k));

            }

        }

        return status;
    }


}
