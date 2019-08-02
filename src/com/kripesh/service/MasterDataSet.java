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
import java.util.HashMap;
import java.util.Map;

public abstract class MasterDataSet {

    public abstract void dataGenerator(String fileLocation, int fileSize, String fileMetadata) throws IOException;

    protected Path createFolder(String fileLocation) {
        if (null == fileLocation) {
            return null;
        }
        Path dirPath = Paths.get(fileLocation);
        boolean dirExist = Files.exists(dirPath);
        if (dirExist) {
            System.out.println("Directory Already Exists ........ ");
            return dirPath;
        } else {
            System.out.println("New Directory Successfully Created ........ !");
            try {
                return Files.createDirectories(dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void createFiles(int fileSize, Path path, int folderSize){
       String filename = convertToFileNameData(LocalDateTime.now())+Dictionary.FILE_EXTENSION;
        try(BufferedWriter bf = Files.newBufferedWriter(Paths.get(path + Dictionary.BACK_SLASH + filename), StandardCharsets.UTF_8)){
            String content = generateContent(fileSize);
            bf.write(content);
            System.out.println("Written file: " + path.toString() + ": " + filename);
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    private String generateContent(int size){
        final int fSize = (size * Dictionary.THOUSAND * Dictionary.THOUSAND) ;
        // chose a Character random from this String
        String AlphaNumericString = Dictionary.A_Z + Dictionary.NUMBER + Dictionary.a_z;
        StringBuilder sb = new StringBuilder();
        while((sb.toString().length()-1 < fSize)) {
            for (int i = 0; i < 100; i++) {
                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());
                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                        .charAt(index));
            }
            sb.append(Dictionary.NEW_LINE);
        }
        return sb.toString();
    }

    protected Map<String, Integer> parseFileMetadata(String fileMetadata){
        String[] fileMdata = fileMetadata.split(Dictionary.COMMA);
        Map<String, Integer> folderMapValue = new HashMap<>();

        for (int i = 0; i < fileMdata.length - 1; i++){
            if (fileMetadata.length()-1 == i){
                break;
            }
            String folderName = fileMdata[i];
            int fSize = Integer.parseInt(fileMdata[++i]);
            folderMapValue.put(folderName, fSize);
        }
        return folderMapValue;
    }

    protected void createSubFolderAndContent(Map<String, Integer> fileMetadata, Path path, int fileSize){
        fileMetadata.keySet()
                .forEach(key -> {
                    Path newPath = createFolder(path+Dictionary.BACK_SLASH+key);
                    createFiles(fileSize,newPath, fileMetadata.get(key));
                });
    }


    protected long getFileSize(File file) {
        return file.length();
    }

    private String convertToFileNameData(LocalDateTime localDateTime) {
        return localDateTime.format(Dictionary.FILE_DATE_FORMATTER);
    }

}
