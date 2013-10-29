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

import org.encog.engine.network.activation.ActivationLinear;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

/**
 *
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 *
 */
public class NNCreator {

        final private int inputParameterCount;
        final private int outputParameterCount;

        public NNCreator(int inputParameterCount,int outputParameterCount){
            this.inputParameterCount = inputParameterCount;
            this.outputParameterCount = outputParameterCount;
        }

    public BasicNetwork createNetwork(){

        BasicNetwork network = new BasicNetwork();
        int hiddenLayerNeuronCount = 10;

        network.addLayer(new BasicLayer(new ActivationLinear(), true,inputParameterCount));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true,hiddenLayerNeuronCount));
        network.addLayer(new BasicLayer(new ActivationTANH(), false,outputParameterCount));
        network.getStructure().finalizeStructure();
        network.reset();
        return network;
    }


}
