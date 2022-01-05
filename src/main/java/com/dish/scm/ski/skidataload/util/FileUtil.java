package com.dish.scm.ski.skidataload.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FileUtil {

    public static boolean deleteAllFiles(String destFolder,int cnt) {
        cnt++;
        log.info("delete Files in Folder iteration:{} {}",destFolder,cnt);
        if (!StringUtils.isEmpty(destFolder)) {
            boolean isSuccess = true;


            if (!Files.isDirectory(Paths.get(destFolder))) {
                try {
                    if (destFolder.contains("/") || destFolder.contains("\\"))
                        Files.createDirectories(Paths.get(destFolder));
                    else
                        Files.createDirectory (Paths.get(destFolder));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            try (Stream<Path> walk = Files.list(Paths.get(destFolder))) {

                List<String> lstFile =  walk.map(x -> x.toAbsolutePath().toString())
                        .collect(Collectors.toList());

                for (String fileName : lstFile) {

                    try {
                        Files.delete(Paths.get(fileName));
                    }
                    catch (Exception e) {
                        log.error("Unable to delete file:{}",fileName);
                        if (cnt<4)
                            deleteAllFiles(destFolder,cnt);
                        else
                            isSuccess = false;
                    }
                }
            }
            catch (IOException ee) {
                log.error("Error:{}",ee.getMessage());
                isSuccess = false;
            }
            return isSuccess;
        }
        return false;
    }


    public static List<String> getFiles(String input_folder)
    {

        log.info("Get Files From:{}",input_folder);
        try (Stream<Path> walk = Files.list(Paths.get(input_folder)))
        {
            return  walk.map(x -> x.toAbsolutePath().toString())
                    .filter(f -> f.toLowerCase().endsWith(".xlsx"))
                    .collect(Collectors.toList());

        } catch (IOException e)
        {
            log.info("No file in {}:",input_folder);
        }
        return null;
    }

    public static List<String> getHdrFromClass(Class<?> clazz) throws Exception {

        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }



}
