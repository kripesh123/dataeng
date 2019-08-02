package com.kripesh;

import com.kripesh.service.MasterDataSet;
import com.kripesh.service.MasterDataSetFactory;
import com.kripesh.utils.Dictionary;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        int fileSize = 2;
        String fileMetadata = "locations,64,sensors,138,devices,24";
        MasterDataSetFactory masterDatasetFactory = new MasterDataSetFactory();
        MasterDataSet masterDataset = masterDatasetFactory.getOperation(Dictionary.DATA_GENERATION);
        masterDataset.dataGenerator(Dictionary.FILE_LOCATION, fileSize, fileMetadata);

    }
}
