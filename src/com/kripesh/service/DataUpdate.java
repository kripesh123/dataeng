package com.kripesh.service;

import com.kripesh.utils.Dictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * This class is responsible for updating generated data and structure folder sub folder
 * @author kripesh
 */
public class DataUpdate extends MasterDataSet {


    /**
     * Method used for updating sub folder files according to user parameters
     * @param fileLocation File Location
     * @param fileSize File Size is always 0
     * @param fileMetadata File Metadata
     */
    @Override
    public void dataGenerator(String fileLocation, double fileSize, String fileMetadata) {
        if (null == fileLocation || fileSize != 0.0 || null == fileMetadata){
            return;
        }

        Path dirPath = Paths.get(fileLocation);
        boolean dirExist = Files.exists(dirPath);
        if(!dirExist){
            System.out.println("Cannot find the location path: " + fileLocation);
            return;
        }
        Map<String, Double> mapFileMetadata = parseFileMetadata(fileMetadata, 0);
        mapFileMetadata.keySet().forEach(key -> {
                    Path path = Paths.get(dirPath + Dictionary.BACK_SLASH+key);
                    File[] files = path.toFile().listFiles();
                    if (null == files) {
                        return;
                    }
                    double existingFileSize = getFileSize(files[0]);
                    double fileSizeAdd = mapFileMetadata.get(key);
                    while (fileSizeAdd != 0.0) {
                        String filename = convertToFileNameData(LocalDateTime.now())+Dictionary.FILE_EXTENSION;
                        try(BufferedWriter bf = Files.newBufferedWriter(Paths.get(path + Dictionary.BACK_SLASH + filename), StandardCharsets.UTF_8)){
                            double newFileSize;
                            if(existingFileSize >= fileSizeAdd){
                                newFileSize = fileSizeAdd;
                                fileSizeAdd = 0.0;
                            }else {
                                newFileSize = existingFileSize;
                                fileSizeAdd -= newFileSize;
                            }
                            String content = generateContent(newFileSize);
                            bf.write(content);
                            System.out.println("Written file: " + path.toString() + ": " + filename);
                        }catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }
                });

    }
}
