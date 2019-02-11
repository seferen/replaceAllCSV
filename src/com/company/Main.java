package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class Main {
    public static void main(String[] args) {
        String rregEx = args[0]; //тип разделителя
        String path = args[1]; //принимаемый путь до файлов или папок
        String fileResult = args[2]; //имя нового файла



        try(BufferedWriter bf = new BufferedWriter(new FileWriter(new File(fileResult + ".csv")))) {

            Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    for (String s : Files.readAllLines(file)) {

                        bf.write(s.replaceAll(rregEx, ";"));
                        bf.newLine();

                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        }catch (IOException e){
            System.out.print("Произошлк какая то фигня возможно файл не полный");
        }
    }
}
