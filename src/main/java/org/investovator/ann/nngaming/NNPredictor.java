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

import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;
import org.investovator.ann.config.ConfigReceiver;
import org.investovator.ann.data.datanormalizing.DataNormalizer;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.io.File;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NNPredictor {

    private BasicNetwork network;
    private TradingDataAttribute predictingAttribute;
    private String basePath;

    public NNPredictor(){

        this.basePath = ConfigReceiver.getBasePath();

    }

    private void loadNeuralNetworkModel(String stockID){

        network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(basePath+"/resources/"
                +stockID+"/"+stockID+"_"+predictingAttribute));

    }

    public double getPredictedValue(String stockID, TradingDataAttribute attribute, double[] inputData){

        this.predictingAttribute = attribute;
        double[] outputArr = new double[1];
        double predictedValue;

        DataNormalizer dataNormalizer = new DataNormalizer(stockID);
        loadNeuralNetworkModel(stockID);

        network.compute(inputData, outputArr);

        predictedValue = dataNormalizer.getDenormalizedValue(outputArr[0], attribute);

        return predictedValue;

    }
}
