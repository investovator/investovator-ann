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
import org.investovator.ann.data.datanormalizing.DataNormalizer;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.io.File;
import java.util.Date;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NNPredictor {

    private BasicNetwork network;

    public NNPredictor(){

    }

    private void loadNeuralNetworkModel(String stockID){

        network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File("src/main/resources/"+stockID+"/"+stockID));

    }

    public double getPredictedValue(String stockID, Date date, TradingDataAttribute attribute){

        DataNormalizer dataNormalizer = new DataNormalizer();
        loadNeuralNetworkModel(stockID);

        double predictedPrice;
        double[][] dataArray;
        double[] input;

       /* for(int j = 0;j < dataArray[0].length - 1; j++){
            input[j] =  dataNormalizer.getNormalizedValue(dataArray[0][j],types[j]);
        }*/
        double [] outputArr = new double[1];

        //network.compute(input, outputArr);

        dataNormalizer.getDenormalizedValue(outputArr[0], attribute);

        return outputArr[0];

    }
}
