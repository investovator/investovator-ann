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

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NNPredictor {

    private BasicNetwork network;
    private TradingDataAttribute predictingAttribute;

    public NNPredictor(){

    }

    private void loadNeuralNetworkModel(String stockID){

        network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File("resources/"
                +stockID+"/"+stockID+"_"+predictingAttribute));

    }

    public double getPredictedValue(String stockID, TradingDataAttribute attribute){   //remove date

        this.predictingAttribute = attribute;
        DataNormalizer dataNormalizer = new DataNormalizer();
        dataNormalizer.setSymbol(stockID);
        loadNeuralNetworkModel(stockID);

        double predictedPrice;
        double[][] dataArray;
        double[] input = {0.18020494876280926,
                0.18050065876152832,
                0.18607991642726562,
                0.01319851296753899,
                0.0527086383601757,
                0.013869213731226883


        };


       /* for(int j = 0;j < dataArray[0].length - 1; j++){
            input[j] =  dataNormalizer.getNormalizedValue(dataArray[0][j],types[j]);
        }*/
        double [] outputArr = new double[1];

        network.compute(input, outputArr);

        outputArr[0] = dataNormalizer.getDenormalizedValue(outputArr[0], attribute);

        System.out.println(outputArr[0]);
        return outputArr[0];

    }
}
