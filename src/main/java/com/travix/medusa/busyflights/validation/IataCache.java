package com.travix.medusa.busyflights.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

@Component
public class IataCache {

    private HashSet<String> IATA_Cache;

    @PostConstruct
    public void loadCache() throws IOException {
        IATA_Cache = new HashSet<>();
        File file = ResourceUtils.getFile("classpath:iata_list.txt");
        InputStream in = new FileInputStream(file);
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            IATA_Cache.add(scanner.next().trim());
        }
        scanner.close();
        in.close();
    }
    boolean isValidIata(String input){
        return IATA_Cache.contains(input);
    }

}
