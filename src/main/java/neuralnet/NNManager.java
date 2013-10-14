package neuralnet;

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

    public NNManager(HashMap newParameters,ArrayList inputParameters){
        this.newParameters = newParameters;
        this.inputParameters = inputParameters;
    }


}
