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

/**
 * In this class we add all the common methods
 * @author kripesh
 */
public abstract class MasterDataSet {

    public abstract void dataGenerator(String fileLocation, double fileSize, String fileMetadata) throws IOException;

    /**
     * Create folder if doest exist
     * @param fileLocation File Location
     * @return path of the folder created
     */
    Path createFolder(String fileLocation) {
        if (null == fileLocation || fileLocation.isEmpty()) {
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

    /**
     * Create files in the given path with the fixed file size. Size of the total files should be equal to folderSize.
     * @param fileSize File Size
     * @param path Path of directory
     * @param folderSize Folder size
     */
    void createFiles(double fileSize, Path path, double folderSize){
        double size = convertMbToByte(fileSize);
        while(isFolderSizeAvailable( path, folderSize)){
            String filename = convertToFileNameData(LocalDateTime.now())+Dictionary.FILE_EXTENSION;
            try(BufferedWriter bf = Files.newBufferedWriter(Paths.get(path + Dictionary.BACK_SLASH + filename), StandardCharsets.UTF_8)){
                double availableSize = folderSize - getFolderSize(path);
                double newFileSize = Math.min(availableSize, size);
                String content = generateContent(newFileSize);
                bf.write(content);
                System.out.println("Written file: " + path.toString() + ": " + filename);
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method is used for generating the File content
     * @param size Size of content
     * @return generate string
     */
    String generateContent(double size){
        // chose a Character random from this String
        String AlphaNumericString = Dictionary.A_Z + Dictionary.NUMBER + Dictionary.a_z;
        StringBuilder sb = new StringBuilder();
        while(isFileSizeAvailable(sb.toString().length(), size)) {
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

    /**
     * This method is used to convert mb to byte
     * @param size mb to convert
     * @return bytes
     */
    private double convertMbToByte(double size) {
        return (size * Dictionary.THOUSAND * Dictionary.THOUSAND);
    }

    /**
     * This method is used to parse file metadata in string to map key value pairs.
     * Key is name of the folder and value is the size of that particular folder
     * If the file metadata size is less than file size, ignore the entry.
     * @param fileMetadata File meta data
     * @param size file Size
     * @return folder map value
     */
    Map<String, Double> parseFileMetadata(String fileMetadata, double size){
        String[] stringData = fileMetadata.split(Dictionary.COMMA);
        Map<String, Double> folderMapValue = new HashMap<>();
        for (int i = 0; i < stringData.length - 1; i++){
            if (fileMetadata.length()-1 == i){
                break;
            }
            String folderName = stringData[i];
            double folderSize = Double.parseDouble(stringData[++i]);
            if (size <= folderSize){
                folderMapValue.put(folderName, convertMbToByte(folderSize));
            }
        }
        return folderMapValue;
    }

    /**
     * Get the size of the file
     * @param file File
     * @return size of a file
     */
    double getFileSize(File file) {
        return file.length();
    }

    /**
     * Convert local date time to local date time formatter
     * @param localDateTime Local date time
     * @return data time formatter
     */
    String convertToFileNameData(LocalDateTime localDateTime) {
        return localDateTime.format(Dictionary.FILE_DATE_FORMATTER);
    }

    /**
     * Check file size
     * @param length content length
     * @param size size
     * @return boolean
     */
    private boolean isFileSizeAvailable(double length, double size) {
        return length < size;
    }

    /**
     * Check folder size
     * @param path Path of Folder
     * @param size size of Folder
     * @return boolean
     */
    private boolean isFolderSizeAvailable(Path path, double size) {
        double length = getFolderSize(path);
        return isFileSizeAvailable(length, size);
    }

    /**
     * Get size of the given path or directory
     * @param path Path of Folder
     * @return size of the folder
     */
    private double getFolderSize(Path path){
        double length = 0;
        File[] files = path.toFile().listFiles();
        if(null == files || files.length == 0){
            return 0;
        }
        for (File file : files) {
            if (file.isFile()) {
                length += getFileSize(file);
            }
        }
        return length;
    }

}
