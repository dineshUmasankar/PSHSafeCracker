package com.psu.dqu5021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        // Read input for the problem
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int digitCount = Integer.valueOf(reader.readLine());
        int startCode = Integer.valueOf(reader.readLine()); // The starting lock code
        int targetCode = Integer.valueOf(reader.readLine()); // The target lock code

        int count = Integer.valueOf(reader.readLine());
        for (int i = 0; i < count; i++) {
            int taboo = Integer.valueOf(reader.readLine());
        }
    }
}