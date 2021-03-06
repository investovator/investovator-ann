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

package org.investovator.ann.data.datanormalizing;

import org.investovator.ann.config.ConfigReceiver;
import org.investovator.ann.nngaming.util.GameTypes;

import java.io.*;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NormalizationModelSerializer {

    private NormalizationModel normalizationModel;
    private String basePath;

    public NormalizationModelSerializer(){

        this.basePath = ConfigReceiver.getBasePath();

    }

    public void saveModel(NormalizationModel model, String fileName, String symbol,GameTypes gameType){

        // Save the model to file
        FileOutputStream fos;
        ObjectOutputStream out;
        try {

            File modelSavePath = new File(basePath+"/resources/"+gameType.toString()+"/"+symbol);

            if (!modelSavePath.exists()) {
                modelSavePath.mkdirs();
            }

            fos = new FileOutputStream(modelSavePath+"/"+fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(model);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public NormalizationModel readModel(String fileName, String symbol,GameTypes gameType){

        // Read the model from file
        FileInputStream fis;
        ObjectInputStream in;
        try {
            fis = new FileInputStream(basePath+"/resources/"+gameType.toString()+"/"+symbol+"/"+fileName);
            in = new ObjectInputStream(fis);
            normalizationModel = (NormalizationModel) in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return normalizationModel;
    }
}
