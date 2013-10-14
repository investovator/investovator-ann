package org.investovator.ann.neuralnet;

import org.encog.neural.networks.BasicNetwork;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author: hasala
 * @version: ${Revision}
 *
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
