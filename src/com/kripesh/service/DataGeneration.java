package com.kripesh.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class DataGeneration extends MasterDataSet {
    @Override
    public void dataGenerator(String fileLocation, int fileSize, String fileMetadata) throws IOException {
        if (null == fileLocation || fileSize == 0 || null == fileMetadata){
            return;
        }
        Path path = createFolder(fileLocation);
        Map<String, Integer> fMetadata= parseFileMetadata(fileMetadata);
        createSubFolderAndContent(fMetadata, path, fileSize);
    }


}
