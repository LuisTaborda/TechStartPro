package com.olist.desafio.olist.desafio;

import com.olist.desafio.olist.desafio.utils.CsvUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

public class TestUtils {

    @Test
    public void TestReader() throws IOException {

        String path = "src/main/resources/csv/categorias.csv";
        List<String[]> categorias = CsvUtils.readCSV(path);

        Assert.notEmpty(categorias);
    }
}
