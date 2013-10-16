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

import java.util.HashMap;

/**
 *
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 *
 */
public class NNManager {

    private HashMap<String,String> newParameters;
    private String [] inputParameters;
    private String stockID;
    private NNCreator nnCreator;
    private NNTrainer nnTrainer;
    private boolean status;

    public NNManager(HashMap newParameters,String [] inputParameters,String stockID){
        this.newParameters = newParameters;
        this.inputParameters = inputParameters;
        this.stockID = stockID;

        status = false;
    }

    public boolean createNeuralNetwork(){

        int inputParamCount = inputParameters.length + newParameters.size();
        nnCreator = new NNCreator(inputParamCount,1);
        nnTrainer = new NNTrainer();

        BasicNetwork network = nnCreator.createNetwork();
        status = nnTrainer.TrainANN(network,stockID);
        return status;
    }


}
