package com.prueba.personaltraining.controller;


import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LogsController {

    private static final Logger LOG = LogManager.getLogger(LogsController.class);

    @PostMapping("/logs")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<String>> checkLogs(@RequestBody String logPath) throws InterruptedException, IOException {
        LOG.info("Accediendo a /logs");

        // Envoltura del comando para ejecutarlo correctamente en un shell


        String command = "cat " + logPath;

        System.out.println(command);
        Runtime r = Runtime.getRuntime();
        String[] cmdArray = {"/bin/bash", "-c", command};
        Process p1 = r.exec(new String[]{"/bin/bash", "-c", "echo \" " + LocalDate.now() + " [main] INFO - Consultando logs de la ruta " + logPath + " \" >> /var/logs/ralfit/commands.log\n"});
        Process p2 = r.exec(new String[]{"/bin/bash", "-c", "echo \" " + LocalDate.now() + " [main] INFO - Usuario  ADMIN\" >> /var/logs/ralfit/commands.log\n"});
        Process p = r.exec(cmdArray);
        p.waitFor();

        BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line;
        List<String> list = new ArrayList<>();

        while ((line = b.readLine()) != null) {
            list.add(line);
            System.out.println(list);
        }
        b.close();

        String errorLine;
        while ((errorLine = errorReader.readLine()) != null) {
            System.err.println(errorLine);
        }
        errorReader.close();

        return ResponseEntity.ok(list);
    }


    @GetMapping("/version")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> getVersion() {

        return ResponseEntity.ok("Version: " + System.getProperty("java.version"));
    }
}
