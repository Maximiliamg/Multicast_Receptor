/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.distribuida.utils;

import com.prog.distribuida.models.FilePart;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("RECONSTRUIDO");
    }
}
