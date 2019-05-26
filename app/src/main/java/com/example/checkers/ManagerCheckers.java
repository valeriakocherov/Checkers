package com.example.checkers;

public class ManagerCheckers {
    private Checkers checkersA;//the conection to the checkers class


    public ManagerCheckers(Checkers checkers) {
        this.checkersA = checkers;
    }

    //replacing all the pieces in startble place
    public void setStartedBoard() {

        //put the pieces of one side - the black pieces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 1 && j % 2 == 0) {
                    checkersA.ckeckersBoard()[i][j].setImageResource(R.mipmap.blackpiece);// creating the button and setting an id
                    checkersA.ckeckersBoard()[i][j].setTag("blackPiece");
                }
                if (i % 2 != 1 && j % 2 != 0) {
                    checkersA.ckeckersBoard()[i][j].setImageResource(R.mipmap.blackpiece);
                    checkersA.ckeckersBoard()[i][j].setTag("blackPiece");// set tag to the black piece
                }
            }
        }
        //second side - the white pieces
        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 1 && j % 2 == 0) {
                    checkersA.ckeckersBoard()[i][j].setImageResource(R.mipmap.whitepiece);// creating the button and setting an id
                    checkersA.ckeckersBoard()[i][j].setTag("whitePiece");
                }
                if (i % 2 != 1 && j % 2 != 0) {
                    checkersA.ckeckersBoard()[i][j].setImageResource(R.mipmap.whitepiece);
                    checkersA.ckeckersBoard()[i][j].setTag("whitePiece");//set tag to the white piece
                }
            }
        }
    }
}
