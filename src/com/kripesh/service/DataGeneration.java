package com.kripesh.service;

import com.kripesh.utils.Dictionary;

import java.nio.file.Path;
import java.util.Map;

/**
 * This class is responsible for generating data and structure folder sub folder
 * @author kripesh
 */
public class DataGeneration extends MasterDataSet {

    /**
     * Method used for creating folder , subfolder and files according to user parameters
     * @param fileLocation File Location
     * @param fileSize File Size
     * @param fileMetadata File Metadata
     */
    @Override
    public void dataGenerator(String fileLocation, double fileSize, String fileMetadata){
        if (null == fileLocation || fileSize == 0 || null == fileMetadata){
            return;
        }
        Path path = createFolder(fileLocation);
        Map<String, Double> mapFileMetadata= parseFileMetadata(fileMetadata, fileSize);
        mapFileMetadata.keySet().forEach(key -> {
                    Path newPath = createFolder(path+ Dictionary.BACK_SLASH+key);
                    createFiles(fileSize,newPath, mapFileMetadata.get(key));
                });
    }


}
