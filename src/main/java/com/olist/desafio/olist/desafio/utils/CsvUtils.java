package com.olist.desafio.olist.desafio.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.Reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvUtils {

    public static List<String[]> readCSV(String path) throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get(path));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        List<String[]> pessoas = csvReader.readAll();
        System.out.println("Nome");
        for (String[] pessoa : pessoas)
            System.out.println(pessoa[0]);

        return pessoas;
    }
}
