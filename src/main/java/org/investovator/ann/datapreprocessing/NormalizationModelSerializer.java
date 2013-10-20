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

package org.investovator.ann.datapreprocessing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NormalizationModelSerializer {

    private NormalizationModel normalizationModel;

    public NormalizationModelSerializer(){
    }

    public void saveModel(NormalizationModel model, String fileName){

        // Save the model to file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(model);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public NormalizationModel readModel(String fileName){

        // Read the model from file
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(fileName);
            in = new ObjectInputStream(fis);
            normalizationModel = (NormalizationModel) in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return normalizationModel;
    }
}
