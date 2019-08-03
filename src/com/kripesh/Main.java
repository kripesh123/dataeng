package com.kripesh;

import com.kripesh.service.MasterDataSet;
import com.kripesh.service.MasterDataSetFactory;
import com.kripesh.utils.Dictionary;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        double fileSize = Dictionary.FILE_SIZE;
        String generationFileMetadata = Dictionary.GENERATE_DATA_SET ;
        String updateFileMetadata = Dictionary.UPDATE_DATA_SET;
        MasterDataSetFactory masterDatasetFactory = new MasterDataSetFactory();
        MasterDataSet masterDataSet = masterDatasetFactory.getOperation(Dictionary.DATA_GENERATION);
        masterDataSet.dataGenerator(Dictionary.FILE_LOCATION, fileSize, generationFileMetadata);
        MasterDataSet updateMasterDataSet = masterDatasetFactory.getOperation(Dictionary.DATA_UPDATE);
        updateMasterDataSet.dataGenerator(Dictionary.FILE_LOCATION, 0, updateFileMetadata);
    }
}
