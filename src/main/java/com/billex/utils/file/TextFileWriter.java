package com.billex.utils.file;

import com.billex.utils.FilePath;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextFileWriter {

    public static void appendDataToTextFile(String fileName,String content) throws IOException {
//        FileWriter writer = new FileWriter(FilePath.CSV_DIR_PATH+fileName,true);
        String path = FilePath.TEXT_DIR_PATH+fileName;
        content = System.lineSeparator()+ content;
        Files.write(Paths.get(path),content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static void main(String[] args) throws IOException {
        TextFileWriter.appendDataToTextFile("pending.txt","1156208002911");
    }
}
