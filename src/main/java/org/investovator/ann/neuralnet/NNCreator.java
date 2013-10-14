package org.investovator.ann.neuralnet;

import org.encog.engine.network.activation.ActivationLinear;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

/**
 *
 * @author: hasala
 * @version: ${Revision}
 *
 */
public class NNCreator {

        private int inputParameterCount = 0;
        private int outputParameterCount = 0;

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
