package org.investovator.neuralnet;

import org.encog.neural.networks.BasicNetwork;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: hasala
 * Date: 10/14/13
 * Time: 7:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class NNManager {

    private HashMap<String,String> newParameters;
    private ArrayList<String> inputParameters;
    private String stockID;
    private NNCreator nnCreator;
    private NNTrainer nnTrainer;
    private boolean status;

    public NNManager(){
        nnCreator = new NNCreator(inputParameters.size(),1);
        nnTrainer = new NNTrainer();
        status = false;
    }

    public boolean createNeuralNetwork(HashMap newParameters,ArrayList inputParameters,String stockID){
        this.newParameters = newParameters;
        this.inputParameters = inputParameters;
        this.stockID = stockID;

        BasicNetwork network = nnCreator.createNetwork();
        status = nnTrainer.TrainANN(network,stockID);
        return status;
    }


}
