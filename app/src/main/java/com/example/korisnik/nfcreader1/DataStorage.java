package com.example.korisnik.nfcreader1;

import java.util.HashMap;

/**
 * Created by Korisnik on 22.1.2017..
 */

public class DataStorage {

    // For names of classes for the professor
    public static HashMap<Integer, String> classes = new HashMap<Integer, String>();
    //Ids of classes
    public static HashMap<Integer, String> classesId = new HashMap<Integer, String>();
   // Ids of student cards
    public static HashMap<Integer, String> students = new HashMap<Integer, String>();
    // all students that are in the class
    public static HashMap<Integer, String> allStudents = new HashMap<Integer, String>();
    //Ids of all students that are in the class
    public static HashMap<Integer, String> allStudentsID = new HashMap<Integer, String>();
}
