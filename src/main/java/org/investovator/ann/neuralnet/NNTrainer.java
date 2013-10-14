package org.investovator.neuralnet;

import org.encog.ml.train.MLTrain;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.persist.EncogDirectoryPersistence;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: hasala
 * Date: 10/14/13
 * Time: 6:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class NNTrainer {
    private double [][] inputData ;
    private double [][] idealData;

    private int iterationCount;
    private double error;
    private boolean trainingSucceeded;

    public NNTrainer(){
        trainingSucceeded = true;
    }

    public boolean TrainANN(BasicNetwork network, String companyName){
        NeuralDataSet trainingSet = new BasicNeuralDataSet(inputData, idealData);

        // train the neural network
        final MLTrain train = new ResilientPropagation(network, trainingSet);

        do
        {
            train.iteration();
            if(train.getIteration() > iterationCount)
            {
                 trainingSucceeded = false;
            }
        } while (train.getError() > error);

        double e = network.calculateError(trainingSet);
        System.out.println("Network trained to error: " + e);

        saveNetwork(companyName,network);

        return trainingSucceeded;
    }

    private void saveNetwork(String companyName,BasicNetwork network){
        System.out.println("Saving network");
        EncogDirectoryPersistence.saveObject(new File(companyName), network);
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


}
