package com.kripesh.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataUpdate extends MasterDataSet {


    @Override
    public void dataGenerator(String fileLocation, int fileSize, String fileMetadata) {
        if (null == fileLocation || fileSize != 0 || null == fileMetadata){
            return;
        }

        Path dirPath = Paths.get(fileLocation);
        boolean dirExist = Files.exists(dirPath);
        if(dirExist){
            File file = null;
            long fSize = getFileSize(file);

        }
    }
}
