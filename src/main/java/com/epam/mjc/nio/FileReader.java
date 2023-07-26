package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        return createProfileFromDataMap(parseFileToMap(file));
    }

    private Map<String, String> parseFileToMap(File file) {
        Map<String, String> dataMap = new HashMap<>();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            while(raf.getFilePointer() < raf.length()) {
                String[] typeAndValue = raf.readLine().split("\\s*:\\s+", 2);
                dataMap.put(typeAndValue[0], typeAndValue[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    private Profile createProfileFromDataMap(Map<String, String> dataMap) {
        String name = dataMap.get("Name");
        Integer age = Integer.parseInt(dataMap.get("Age"));
        String email = dataMap.get("Email");
        Long phone = Long.parseLong(dataMap.get("Phone"));

        return new Profile(name, age, email, phone);
    }
}