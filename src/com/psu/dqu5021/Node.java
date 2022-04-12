package com.psu.dqu5021;

public class Node {
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
