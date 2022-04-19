package com.psu.dqu5021;

import java.io.*;
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
        // Storage for forbidden values
        HashSet<Integer> forbiddenValues = new HashSet<Integer>();

        // Storage for node discovery
        TreeSet<Node> nodeTree = new TreeSet<Node>();
        StringBuilder output = new StringBuilder();

        // Read input for the problem
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int digitCount = Integer.parseInt(reader.readLine());
        int startCode = Integer.parseInt(reader.readLine()); // The starting lock code
        int targetCode = Integer.parseInt(reader.readLine()); // The target lock code

        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            forbiddenValues.add(Integer.parseInt(reader.readLine()));
        }

        if (forbiddenValues.contains(startCode) || forbiddenValues.contains(targetCode))
        {
            output.append(-1);
            System.out.println(output);
        }

        // Starting Node
        Node startNode = new Node(startCode);
        for (int i = 0; i<digitCount; i++)
        {
            Node down = startNode.changeCode('D', i+1, digitCount);
            System.out.print(down.direction);
            System.out.print(down.digitMoved);
            System.out.print("\t" + down.currNum + "\n");
        }

        for (int i = 0; i<digitCount; i++)
        {
            Node up = startNode.changeCode('U', i+1, digitCount);
            System.out.print(up.direction);
            System.out.print(up.digitMoved);
            System.out.print("\t" + up.currNum + "\n");
        }


//        System.out.println(nodeTree.size());
//        nodeTree.add(startNode);
//        System.out.println(nodeTree.size());

    }
}