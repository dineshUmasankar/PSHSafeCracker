package com.psu.dqu5021;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        // Storage for forbidden values
        HashSet<Integer> forbiddenValues = new HashSet<Integer>();

        // Storage for node discovery
        Queue<Node> nodeQueue = new ArrayDeque<Node>();

        // Storage for output
        Stack<Node> targetNodePath = new Stack<Node>();

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
        }
        else
        {
            // Starting Node
            Node startNode = new Node(startCode);
            nodeQueue.add(startNode);
            forbiddenValues.add(startNode.currNum);

//        System.out.println("Root Node");
//        printQueue(nodeQueue);
//        System.out.println("Forbidden Values");
//        printSet(forbiddenValues);


            boolean targetFound = false;
//        int level = 0;
            while (!targetFound)
            {
                if (nodeQueue.peek() == null)
                {
                    targetFound = true;
                }
                else
                {
                    createNextLevel(nodeQueue.poll(), digitCount, nodeQueue, forbiddenValues);

                    if (forbiddenValues.contains(targetCode))
                        targetFound = true;
                }
//            level++;

//            System.out.println("Level " + level);
//           printQueue(nodeQueue);

//            System.out.println("Forbidden Values");
//            printSet(forbiddenValues);
            }

            // Could also be done by just dequeue until target
            Iterator <Node> itr = nodeQueue.iterator();
            Node targetNode = null;
            while (itr.hasNext())
            {
                Node currentNode = itr.next();
                if (currentNode.currNum == targetCode)
                {
                    targetNode = currentNode;
                    break;
                }
            }

//        System.out.println("Target path");

            while (targetNode != null)
            {
                targetNodePath.push(targetNode);
                targetNode = targetNode.prevNode;
            }

            output.append(targetNodePath.size()-1 + "\n");

            if (targetNodePath.size() >= 1)
                targetNodePath.pop();

            while (!targetNodePath.empty())
            {
                Node printPath = targetNodePath.pop();
                String path = printPath.direction + "";
                path += printPath.digitMoved + " ";
                path += String.format("%0" + digitCount + "d" + "\n", printPath.currNum);
                output.append(path);
            }
            System.out.println(output);
        }

    }

    static void createNextLevel(Node fromNode, int digitCount, Queue<Node> nodeQueue, HashSet<Integer> forbiddenValues)
    {
        for (int i = 0; i<digitCount; i++)
        {
            Node down = fromNode.changeCode('D', i+1, digitCount);
            if (!forbiddenValues.contains(down.currNum))
            {
                nodeQueue.add(down);
                forbiddenValues.add(down.currNum);
            }
        }

        for (int i = 0; i<digitCount; i++)
        {
            Node up = fromNode.changeCode('U', i+1, digitCount);
            if (!forbiddenValues.contains(up.currNum))
            {
                nodeQueue.add(up);
                forbiddenValues.add(up.currNum);
            }
        }
    }

//    static void printQueue(Queue<Node> nodeQueue)
//    {
//        Iterator<Node> itr = nodeQueue.iterator();
//        while (itr.hasNext())
//        {
//            Node headQueue = itr.next();
//            System.out.print(headQueue.direction);
//            System.out.print(headQueue.digitMoved);
//            System.out.print("\t" + headQueue.currNum + "\n");
//        }
//    }

//    static void printSet(HashSet<Integer> forbiddenValues)
//    {
//        Iterator<Integer> itr = forbiddenValues.iterator();
//        while (itr.hasNext())
//        {
//            int headQueue = itr.next();
//            System.out.print("\t" + headQueue + "\n");
//        }
//    }
}

class Node {
    int currNum;        // Current Lock Code
    char direction;     // U or D - for the move that got us to this combination
    int digitMoved;     // Which digit was moved to get us here
    Node prevNode;      // Link to the previous state

    public Node(int currNum) {
        this.currNum = currNum;
    }

    public Node(int currNum, Node prevNode, char direction, int digitMoved) {
        this.currNum = currNum;
        this.prevNode = prevNode;
        this.direction = direction;
        this.digitMoved = digitMoved;
    }

    /**
     * Return number that results from taking the current state and moving a digit either
     * up or down.
     *
     * @param direction either 'U' or 'D' for the direction of movement (up or down)
     * @param digit the digit that was moved
     *
     * @return the new lock code
     */
    public Node changeCode(char direction, int digit, int digitCount) {
        int base = (int) Math.pow(10, digitCount - digit);
        int newDigit;
        int currentDigit = (currNum /base) % 10;

        if (direction == 'U') {
            newDigit = (currentDigit + 1) % 10;
        }
        else {
            newDigit = (currentDigit + 9) % 10;
        }

        return new Node(currNum + (newDigit - currentDigit) * base, this, direction, digit);
    }
}