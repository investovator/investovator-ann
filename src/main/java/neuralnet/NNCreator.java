package neuralnet;

import org.encog.engine.network.activation.ActivationLinear;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

/**
 * Created with IntelliJ IDEA.
 * User: hasala
 * Date: 10/14/13
 * Time: 6:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class NNCreator {

        private BasicNetwork network;
        private int inputParameterCount = 0;
        private int hiddenLayerNeuronCount = 10;
        private int outputParameterCount = 0;


    public BasicNetwork createNetwork(){
        network = new BasicNetwork();

        network.addLayer(new BasicLayer(new ActivationLinear(), true,inputParameterCount));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true,hiddenLayerNeuronCount));
        network.addLayer(new BasicLayer(new ActivationTANH(), false,outputParameterCount));
        network.getStructure().finalizeStructure();
        network.reset();
        return network;
    }


}
