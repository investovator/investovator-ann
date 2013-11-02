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

import org.encog.ml.train.MLTrain;
import org.encog.ml.train.strategy.ResetStrategy;
import org.encog.ml.train.strategy.StopTrainingStrategy;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.persist.EncogDirectoryPersistence;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.io.File;

/**
 *
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 *
 */
public class NNTrainer {
    private double [][] inputData ;
    private double [][] idealData;

    private TradingDataAttribute predictingAttribute;
    private int iterationCount;
    private double error;
    private boolean trainingSucceeded;

    public NNTrainer(){
        trainingSucceeded = true;
    }

    public boolean TrainANN(BasicNetwork network, String stockID){
        NeuralDataSet trainingSet = new BasicNeuralDataSet(inputData, idealData);

        // train the neural network
        final MLTrain train = new ResilientPropagation(network, trainingSet);

        ResetStrategy resetStrategy = new ResetStrategy(0.001,10000);
        StopTrainingStrategy stopTrainingStrategy = new StopTrainingStrategy(0.004,10000);

        train.addStrategy(resetStrategy);
        train.addStrategy(stopTrainingStrategy);

        do
        {
            train.iteration();
            if(train.getIteration() > iterationCount)
            {
                 trainingSucceeded = false;
                 break;
            }
        } while (train.getError() > error);

        double e = network.calculateError(trainingSet);
        System.out.println("Network trained to error: " + e);

        saveNetwork(stockID,network);

        return trainingSucceeded;
    }

    private void saveNetwork(String stockID,BasicNetwork network){

        System.out.println("Saving network");
        EncogDirectoryPersistence.saveObject(new File("resources/"+stockID+"/"+stockID+"_"+predictingAttribute),
                network);

    }

    public void setTrainingData(double[][] inputData,double[][] idealData){
        this.inputData = inputData;
        this.idealData = idealData;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    public void setError(double error) {
        this.error = error;
    }

    public void setPredictingAttribute(TradingDataAttribute predictingAttribute) {
        this.predictingAttribute = predictingAttribute;
    }

}
