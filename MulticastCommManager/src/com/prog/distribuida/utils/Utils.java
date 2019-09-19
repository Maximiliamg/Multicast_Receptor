/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.distribuida.utils;

import com.prog.distribuida.models.FilePart;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClients;

/**
 *
 */
public class Utils {
    
    public static void rebuildAndSaveFile(ArrayList<FilePart> file, String path){
        System.out.println(file);
        String fileName = path + "/" + file.get(0).getFileName();
        
        try {
            FileOutputStream fos = new FileOutputStream(fileName, true);
            for (FilePart fp : file) {
                fos.write(fp.getData());
            }
            fos.close();
            System.out.println("RECONSTRUIDO");
            
            notifyFileCreation(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void notifyFileCreation(String filename) throws Exception {

        String url = "http://localhost:8080/FileServer/api/files/" +filename;

        HttpClient client = HttpClients.createDefault();
        HttpPut request = new HttpPut(url);

        // add header
        request.setHeader("User-Agent", "Mozilla/5.0");

        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");

        System.out.println("[FS] Sending register request");
        HttpResponse response = client.execute(request);
        System.out.println(
            "[MULTICAST RECEPTOR] Response Code : " + 
            response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println("[MULTICAST RECEPTOR] "+result.toString());
    }
}
