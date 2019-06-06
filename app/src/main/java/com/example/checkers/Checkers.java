package com.example.checkers;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class Checkers extends AppCompatActivity implements View.OnClickListener  {
//first try to commit
    private ImageButton[][] ckeckersBoard;// all the buttons on the checker board
    private TableRow[] ckRowBoard; // help to create the form of the board
    private ManagerCheckers checkersM;// the conection to the manager checkers java class
    private ImageButton buttonB;//help button
    private int firsti, firstj;//keep the first place piece of the cheaker
    private boolean turn = false;//keep the turn of every player
    private String blackPlayer; //name of the black checker player
    private String whitePlayer;//name of the white checker player
    private AppPreference preference; // game preferce to update


    //show the options movement to the black piece
    public void optionMovementBlackPiece(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {
        //the borders of the bord that can show tow or one option movement
        if (column > 0 && column < 7) {
            if (ckeckersBoard[row + 1][column + 1].getTag() == "")
                ckeckersBoard[row + 1][column + 1].setBackgroundResource(R.color.mindaro);
            if (ckeckersBoard[row + 1][column - 1].getTag() == "")
                ckeckersBoard[row + 1][column - 1].setBackgroundResource(R.color.mindaro);
        } else {
            if (column == 7)
                if (ckeckersBoard[row + 1][column - 1].getTag() == "")
                    ckeckersBoard[row + 1][column - 1].setBackgroundResource(R.color.mindaro);
            if (column == 0)
                if (ckeckersBoard[row + 1][column + 1].getTag() == "")
                    ckeckersBoard[row + 1][column + 1].setBackgroundResource(R.color.mindaro);
        }
        this.firsti = row;
        this.firstj = column;
    }

    //show the options movement to the white piece
    public void optionMovementWhitePiece(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {
        if (column > 0 && column < 7) {
            if (ckeckersBoard[row - 1][column + 1].getTag() == "")
                ckeckersBoard[row - 1][column + 1].setBackgroundResource(R.color.mindaro);

            if (ckeckersBoard[row - 1][column - 1].getTag() == "")
                ckeckersBoard[row - 1][column - 1].setBackgroundResource(R.color.mindaro);
        } else {
            if (column == 7)
                if (ckeckersBoard[row - 1][column - 1].getTag() == "")
                    ckeckersBoard[row - 1][column - 1].setBackgroundResource(R.color.mindaro);

            if (column == 0)
                if (ckeckersBoard[row - 1][column + 1].getTag() == "")
                    ckeckersBoard[row - 1][column + 1].setBackgroundResource(R.color.mindaro);
        }

        this.firsti = row;
        this.firstj = column;
    }

    // all options movments of the white king checker
    public void whiteKingOptionMovement(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {

        int x = row;
        int y = column;
        boolean ok = true;
        for (int i = 1; (x + i < 8) && (y + i < 8) && ok; i++) {
            if (ckeckersBoard[x + i][y + i].getTag() == "") {
                ckeckersBoard[x + i][y + i].setBackgroundResource(R.color.bluelight);
            } else {
                if (!(ckeckersBoard[x + i][y + i].getTag() == "")) {
                    ok = false;

                    if (ckeckersBoard[x + i][y + i].getTag() == "blackPiece" && (x + i + 1 < 8) && (y + i + 1 < 8) && ckeckersBoard[x + i + 1][y + i + 1].getTag() == "") {
                        ckeckersBoard[x + i + 1][y + i + 1].setBackgroundResource(R.color.cocoabrown);
                    }
                }
            }
        }

        ok = true;
        for (int i = 1; (x - i >= 0) && (y - i >= 0) && ok; i++) {
            if (ckeckersBoard[x - i][y - i].getTag() == "") {
                ckeckersBoard[x - i][y - i].setBackgroundResource(R.color.bluelight);
            } else {
                if (!(ckeckersBoard[x - i][y - i].getTag() == "")) {
                    ok = false;

                    if (ckeckersBoard[x - i][y - i].getTag() == "blackPiece" && (x - i - 1 >= 0) && (y - i - 1 >= 0) && ckeckersBoard[x - i - 1][y - i - 1].getTag() == "") {
                        ckeckersBoard[x - i - 1][y - i - 1].setBackgroundResource(R.color.cocoabrown);
                    }
                }
            }
        }

        ok = true;
        for (int i = 1; (x - i >= 0) && (y + i < 8) && ok; i++) {
            if (ckeckersBoard[x - i][y + i].getTag() == "") {
                ckeckersBoard[x - i][y + i].setBackgroundResource(R.color.bluelight);
            } else {
                if (!(ckeckersBoard[x - i][y + i].getTag() == "")) {
                    ok = false;

                    if (ckeckersBoard[x - i][y + i].getTag() == "blackPiece" && (x - i - 1 >= 0) && (y + i + 1 < 8) && ckeckersBoard[x - i - 1][y + i + 1].getTag() == "") {
                        ckeckersBoard[x - i - 1][y + i + 1].setBackgroundResource(R.color.cocoabrown);
                    }
                }
            }
        }

        ok = true;
        for (int i = 1; (x + i < 8) && (y - i >= 0) && ok; i++) {
            if (ckeckersBoard[x + i][y - i].getTag() == "") {
                ckeckersBoard[x + i][y - i].setBackgroundResource(R.color.bluelight);
            } else {
                if (!(ckeckersBoard[x + i][y - i].getTag() == "")) {
                    ok = false;

                    if (ckeckersBoard[x + i][y - i].getTag() == "blackPiece" && (x + i + 1 < 8) && (y - i - 1 >= 0) && ckeckersBoard[x + i + 1][y - i - 1].getTag() == "") {
                        ckeckersBoard[x + i + 1][y - i - 1].setBackgroundResource(R.color.cocoabrown);
                    }
                }
            }
        }


        this.firsti = row;
        this.firstj = column;
    }

    // all options movments of the black king checker
    public void blackKingOptionMovement(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {

        int x = row;
        int y = column;
        boolean ok = true;
        for (int i = 1; (x + i < 8) && (y + i < 8) && ok; i++) {
            if (ckeckersBoard[x + i][y + i].getTag() == "") {
                ckeckersBoard[x + i][y + i].setBackgroundResource(R.color.bluelight);
            } else {
                if (!(ckeckersBoard[x + i][y + i].getTag() == "")) {
                    ok = false;

                    if (ckeckersBoard[x + i][y + i].getTag() == "whitePiece" && (x + i + 1 < 8) && (y + i + 1 < 8) && ckeckersBoard[x + i + 1][y + i + 1].getTag() == "") {
                        ckeckersBoard[x + i + 1][y + i + 1].setBackgroundResource(R.color.cocoabrown);
                    }
                }
            }
        }

        ok = true;
        for (int i = 1; (x - i >= 0) && (y - i >= 0) && ok; i++) {
            if (ckeckersBoard[x - i][y - i].getTag() == "") {
                ckeckersBoard[x - i][y - i].setBackgroundResource(R.color.bluelight);
            } else {
                if (!(ckeckersBoard[x - i][y - i].getTag() == "")) {
                    ok = false;

                    if (ckeckersBoard[x - i][y - i].getTag() == "whitePiece" && (x - i - 1 >= 0) && (y - i - 1 >= 0) && ckeckersBoard[x - i - 1][y - i - 1].getTag() == "") {
                        ckeckersBoard[x - i - 1][y - i - 1].setBackgroundResource(R.color.cocoabrown);
                    }
                }
            }
        }

        ok = true;
        for (int i = 1; (x - i >= 0) && (y + i < 8) && ok; i++) {
            if (ckeckersBoard[x - i][y + i].getTag() == "") {
                ckeckersBoard[x - i][y + i].setBackgroundResource(R.color.bluelight);
            } else {
                if (!(ckeckersBoard[x - i][y + i].getTag() == "")) {
                    ok = false;

                    if (ckeckersBoard[x - i][y + i].getTag() == "whitePiece" && (x - i - 1 >= 0) && (y + i + 1 < 8) && ckeckersBoard[x - i - 1][y + i + 1].getTag() == "") {
                        ckeckersBoard[x - i - 1][y + i + 1].setBackgroundResource(R.color.cocoabrown);
                    }
                }
            }
        }

        ok = true;
        for (int i = 1; (x + i < 8) && (y - i >= 0) && ok; i++) {
            if (ckeckersBoard[x + i][y - i].getTag() == "") {
                ckeckersBoard[x + i][y - i].setBackgroundResource(R.color.bluelight);
            } else {
                if (!(ckeckersBoard[x + i][y - i].getTag() == "")) {
                    ok = false;

                    if (ckeckersBoard[x + i][y - i].getTag() == "whitePiece" && (x + i + 1 < 8) && (y - i - 1 >= 0) && ckeckersBoard[x + i + 1][y - i - 1].getTag() == "") {
                        ckeckersBoard[x + i + 1][y - i - 1].setBackgroundResource(R.color.cocoabrown);
                    }
                }
            }
        }

        this.firsti = row;
        this.firstj = column;
    }

    //when the white king moves - change the place of the white king
    public void blueBecameKingWhite(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {
        ckeckersBoard[row][column].setImageResource(R.mipmap.kingwhitepiece);
        ckeckersBoard[row][column].setTag("whiteKingPiece");
        ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
        ckeckersBoard[firsti][firstj].setTag("");

        this.firsti = row;
        this.firstj = column;

    }

    //when the black king moves - change the black of the white king
    public void blueBecameKingBlack(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {
        ckeckersBoard[row][column].setImageResource(R.mipmap.kingblackpiece);
        ckeckersBoard[row][column].setTag("blackKingPiece");
        ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
        ckeckersBoard[firsti][firstj].setTag("");
        this.firsti = row;
        this.firstj = column;
    }

    //white checker became a white king
    public void whiteBecameKing(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {

        ckeckersBoard[row][column].setImageResource(R.mipmap.kingwhitepiece);
        ckeckersBoard[row][column].setTag("whiteKingPiece");
        ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
        ckeckersBoard[firsti][firstj].setTag("");
    }

    //black checker became a black king
    public void blackBecameKing(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {

        ckeckersBoard[row][column].setImageResource(R.mipmap.kingblackpiece);
        ckeckersBoard[row][column].setTag("blackKingPiece");
        ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
        ckeckersBoard[firsti][firstj].setTag("");
    }

    //doing the action of the black movement
    public void yellowBecameBlack(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {
        ckeckersBoard[row][column].setImageResource(R.mipmap.blackpiece);
        ckeckersBoard[row][column].setTag("blackPiece");
        ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
        ckeckersBoard[firsti][firstj].setTag("");
    }

    //doing the action of the white movement
    public void yellowBecameWhite(ImageButton[][] ckeckersBoard, int column, int row) {

        ckeckersBoard[row][column].setImageResource(R.mipmap.whitepiece);
        ckeckersBoard[row][column].setTag("whitePiece");
        ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
        ckeckersBoard[firsti][firstj].setTag("");

    }

    //black piece or king eat white piece
    public void redBecameBlack(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {
        if(ckeckersBoard[firsti][firstj].getTag().equals("blackPiece")){
            ckeckersBoard[row][column].setImageResource(R.mipmap.blackpiece);
            ckeckersBoard[row][column].setTag("blackPiece");
            ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
            ckeckersBoard[firsti][firstj].setTag("");

            //deleting the eaten piece from the board
            if (ckeckersBoard[firsti + 1][firstj - 1].getTag() == "whitePiece"
                    || ckeckersBoard[firsti + 1][firstj + 1].getTag() == "whitePiece"
                    || ckeckersBoard[firsti + 1][firstj - 1].getTag() == "whiteKingPiece"
                    || ckeckersBoard[firsti + 1][firstj + 1].getTag() == "whiteKingPiece")
                if (column < firstj) {
                    ckeckersBoard[firsti + 1][firstj - 1].setImageResource(R.mipmap.invisible);
                    ckeckersBoard[firsti + 1][firstj - 1].setTag("");
                } else if (column > firstj) {
                    ckeckersBoard[firsti + 1][firstj + 1].setImageResource(R.mipmap.invisible);
                    ckeckersBoard[firsti + 1][firstj + 1].setTag("");
                }
        }
        else if(ckeckersBoard[firsti][firstj].getTag().equals("blackKingPiece")){
            ckeckersBoard[row][column].setImageResource(R.mipmap.kingblackpiece);
            ckeckersBoard[row][column].setTag("blackKingPiece");


            //deleting the eaten piece from the board
            if(firstj < column && firsti < row){
                ckeckersBoard[row - 1][column - 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row - 1][column - 1].setTag("");
            }
            if(firstj<column && firsti>row){
                ckeckersBoard[row + 1][column - 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row + 1][column - 1].setTag("");
            }
            if(firstj>column && firsti>row){
                ckeckersBoard[row + 1][column + 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row + 1][column + 1].setTag("");
            }
            if(firstj>column && firsti<row){
                ckeckersBoard[row - 1][column + 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row - 1][column + 1].setTag("");
            }

            ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
            ckeckersBoard[firsti][firstj].setTag("");
        }

        clearButton();
    }

    //white piece or king eat black piece
    public void redBecameWhite(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {

        if(ckeckersBoard[firsti][firstj].getTag().equals("whitePiece")){
            ckeckersBoard[row][column].setImageResource(R.mipmap.whitepiece);
            ckeckersBoard[row][column].setTag("whitePiece");
            ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
            ckeckersBoard[firsti][firstj].setTag("");

            //deleting the eaten piece from the board
            if (ckeckersBoard[firsti - 1][firstj - 1].getTag() == "blackPiece"
                    || ckeckersBoard[firsti - 1][firstj + 1].getTag() == "blackPiece"
                    || ckeckersBoard[firsti - 1][firstj - 1].getTag() == "blackKingPiece"
                    || ckeckersBoard[firsti - 1][firstj + 1].getTag() == "blackKingPiece") {
                if (column < firstj) {
                    ckeckersBoard[firsti - 1][firstj - 1].setImageResource(R.mipmap.invisible);
                    ckeckersBoard[firsti - 1][firstj - 1].setTag("");
                }
                if (column > firstj) {
                    ckeckersBoard[firsti - 1][firstj + 1].setImageResource(R.mipmap.invisible);
                    ckeckersBoard[firsti - 1][firstj + 1].setTag("");
                }
            }
        }
        else if(ckeckersBoard[firsti][firstj].getTag().equals("whiteKingPiece")){
            ckeckersBoard[row][column].setImageResource(R.mipmap.kingwhitepiece);
            ckeckersBoard[row][column].setTag("whiteKingPiece");


            //deleting the eaten piece from the board
            if(firstj < column && firsti < row){
                ckeckersBoard[row - 1][column - 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row - 1][column - 1].setTag("");
            }
            if(firstj<column && firsti>row){
                ckeckersBoard[row + 1][column - 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row + 1][column - 1].setTag("");
            }
            if(firstj>column && firsti>row){
                ckeckersBoard[row + 1][column + 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row + 1][column + 1].setTag("");
            }
            if(firstj>column && firsti<row){
                ckeckersBoard[row - 1][column + 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row - 1][column + 1].setTag("");
            }

            ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
            ckeckersBoard[firsti][firstj].setTag("");
        }

        clearButton();
    }

    //the black piece eats the white piece (options of movement)
    public void eatingWhitePiece(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {
        if ((column == 0 || column == 1) && row < 6) {
            if ((ckeckersBoard[row + 1][column + 1].getTag() == "whitePiece" || ckeckersBoard[row + 1][column + 1].getTag() == "whiteKingPiece") && ckeckersBoard[row + 2][column + 2].getTag() == "")
                ckeckersBoard[row + 2][column + 2].setBackgroundResource(R.color.cocoabrown);
        }

        if ((column == 6 || column == 7) && row < 6) {
            if ((ckeckersBoard[row + 1][column - 1].getTag() == "whitePiece" || ckeckersBoard[row + 1][column - 1].getTag() == "whiteKingPiece") && ckeckersBoard[row + 2][column - 2].getTag() == "")
                ckeckersBoard[row + 2][column - 2].setBackgroundResource(R.color.cocoabrown);
        }

        if ((column >= 2 && column <= 5) && row < 6) {
            if ((ckeckersBoard[row + 1][column - 1].getTag() == "whitePiece" || ckeckersBoard[row + 1][column - 1].getTag() == "whiteKingPiece") && ckeckersBoard[row + 2][column - 2].getTag() == "")
                ckeckersBoard[row + 2][column - 2].setBackgroundResource(R.color.cocoabrown);
            if ((ckeckersBoard[row + 1][column + 1].getTag() == "whitePiece" || ckeckersBoard[row + 1][column + 1].getTag() == "whiteKingPiece") && ckeckersBoard[row + 2][column + 2].getTag() == "")
                ckeckersBoard[row + 2][column + 2].setBackgroundResource(R.color.cocoabrown);
        }

    }

    //the white piece eats the white piece (options of movement)
    public void eatingBlackPiece(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {
        if ((column == 0 || column == 1) && row > 1) {
            if ((ckeckersBoard[row - 1][column + 1].getTag() == "blackPiece" || ckeckersBoard[row - 1][column + 1].getTag() == "blackKingPiece") && ckeckersBoard[row - 2][column + 2].getTag() == "")
                ckeckersBoard[row - 2][column + 2].setBackgroundResource(R.color.cocoabrown);
        }

        if ((column == 6 || column == 7) && row > 1) {
            if ((ckeckersBoard[row - 1][column - 1].getTag() == "blackPiece" || ckeckersBoard[row - 1][column - 1].getTag() == "blackKingPiece") && ckeckersBoard[row - 2][column - 2].getTag() == "")
                ckeckersBoard[row - 2][column - 2].setBackgroundResource(R.color.cocoabrown);
        }

        if ((column >= 2 && column <= 5) && row > 1) {
            if ((ckeckersBoard[row - 1][column - 1].getTag() == "blackPiece" || ckeckersBoard[row - 1][column - 1].getTag() == "blackKingPiece") && ckeckersBoard[row - 2][column - 2].getTag() == "")
                ckeckersBoard[row - 2][column - 2].setBackgroundResource(R.color.cocoabrown);
            if ((ckeckersBoard[row - 1][column + 1].getTag() == "blackPiece" || ckeckersBoard[row - 1][column + 1].getTag() == "blackKingPiece") && ckeckersBoard[row - 2][column + 2].getTag() == "")
                ckeckersBoard[row - 2][column + 2].setBackgroundResource(R.color.cocoabrown);
        }

    }

    //change to white king when the white eating the last black
    public void redBecameWhiteKing(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {

        if (ckeckersBoard[firsti][firstj].getTag().equals("whitePiece")) {
            ckeckersBoard[row][column].setImageResource(R.mipmap.kingwhitepiece);
            ckeckersBoard[row][column].setTag("whiteKingPiece");
            ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
            ckeckersBoard[firsti][firstj].setTag("");

            //deleting the eaten piece from the board
            if (ckeckersBoard[firsti - 1][firstj - 1].getTag() == "blackPiece"
                    || ckeckersBoard[firsti - 1][firstj + 1].getTag() == "blackPiece"
                    || ckeckersBoard[firsti - 1][firstj - 1].getTag() == "blackKingPiece"
                    || ckeckersBoard[firsti - 1][firstj + 1].getTag() == "blackKingPiece") {
                if (column < firstj) {
                    ckeckersBoard[firsti - 1][firstj - 1].setImageResource(R.mipmap.invisible);
                    ckeckersBoard[firsti - 1][firstj - 1].setTag("");
                }
                if (column > firstj) {
                    ckeckersBoard[firsti - 1][firstj + 1].setImageResource(R.mipmap.invisible);
                    ckeckersBoard[firsti - 1][firstj + 1].setTag("");
                }
            }
        }
    }

    //change to  black king when the white eating the last white
    public void redBecameBlackKing(ImageButton[][] ckeckersBoard, ImageButton button, int column, int row) {

        if (ckeckersBoard[firsti][firstj].getTag().equals("blackPiece")) {
            ckeckersBoard[row][column].setImageResource(R.mipmap.kingblackpiece);
            ckeckersBoard[row][column].setTag("blackKingPiece");
            ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
            ckeckersBoard[firsti][firstj].setTag("");

            //deleting the eaten piece from the board
            if(firstj < column && firsti < row){
                ckeckersBoard[row - 1][column - 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row - 1][column - 1].setTag("");
            }
            if(firstj<column && firsti>row){
                ckeckersBoard[row + 1][column - 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row + 1][column - 1].setTag("");
            }
            if(firstj>column && firsti>row){
                ckeckersBoard[row + 1][column + 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row + 1][column + 1].setTag("");
            }
            if(firstj>column && firsti<row){
                ckeckersBoard[row - 1][column + 1].setImageResource(R.mipmap.invisible);
                ckeckersBoard[row - 1][column + 1].setTag("");
            }

            ckeckersBoard[firsti][firstj].setImageResource(R.mipmap.invisible);
            ckeckersBoard[firsti][firstj].setTag("");
        }
    }

    //checking if there a red button on the board
    public boolean ifRed() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((ckeckersBoard[i][j]).getBackground().getConstantState().equals(getResources()
                        .getDrawable(R.color.cocoabrown).getConstantState())) {
                    return true;
                }
            }
        }
        return false;
    }


    public void ifYellow() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((ckeckersBoard[i][j]).getBackground().getConstantState().equals(getResources()
                        .getDrawable(R.color.mindaro).getConstantState())) {
                    ckeckersBoard[i][j].setBackgroundResource(R.color.lilacluster);
                }
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkers);

        preference = new AppPreference(getApplicationContext());

        this.ckeckersBoard = new ImageButton[8][8];// the checkers board
        ckRowBoard = new TableRow[8]; // the numbers rows in the board checker's
        checkersM = new ManagerCheckers(this);
        createBoard();
        checkersM.setStartedBoard(); // placing the figures in the right place for the beginning of the game

         blackPlayer = getIntent().getStringExtra("blackPlayer");
         whitePlayer = getIntent().getStringExtra("whitePlayer");

        TextView text = findViewById(R.id.players);
        text.setText(blackPlayer + " " + whitePlayer);

        ckeckersBoard[5][0].performClick();
    }



    //clearing the board before the next button clicked
    public void clearButton() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    ckeckersBoard[i][j].setBackgroundResource(R.color.lilacluster);
                } else {
                    ckeckersBoard[i][j].setBackgroundResource(R.color.white);
                }
            }
        }
    }

    public ImageButton[][] ckeckersBoard() {
        return ckeckersBoard;
    }


    @Override
    public void onClick(View v) {

        int id = (int) (v.getId());
        int row = id / 10; //id row- the button that clicked
        int column = id % 10; //id column- the button that clicked
        ImageButton button = (ImageButton) v;// the id Imagebutton that clicked

        if (v.getTag() == "blackPiece" && turn) {
            clearButton();
            optionMovementBlackPiece(ckeckersBoard, button, column, row);
            eatingWhitePiece(ckeckersBoard, button, column, row);
        }

        if (v.getTag() == "whitePiece" && !turn) {
            clearButton();
            optionMovementWhitePiece(ckeckersBoard, button, column, row);
            eatingBlackPiece(ckeckersBoard, button, column, row);
           // Toast.makeText(getApplicationContext(), row + " ", Toast.LENGTH_LONG).show();
        }

        if (v.getTag() == "whiteKingPiece" && !turn) {
            clearButton();
            whiteKingOptionMovement(ckeckersBoard, button, column, row);
        }

        if (v.getTag() == "blackKingPiece" && turn) {
            clearButton();
            blackKingOptionMovement(ckeckersBoard, button, column, row);
        }


        //if you pressed on a yellow button
        if (((ImageButton) v).getBackground().getConstantState().equals(getResources()
                .getDrawable(R.color.mindaro).getConstantState())) {
            clearButton();
            if (ckeckersBoard[firsti][firstj].getTag() == "whitePiece")
                if (row == 0)
                    whiteBecameKing(ckeckersBoard, button, column, row);
                else
                    yellowBecameWhite(ckeckersBoard, column, row);
            if (ckeckersBoard[firsti][firstj].getTag() == "blackPiece")
                if (row == 7)
                    blackBecameKing(ckeckersBoard, button, column, row);
                else
                    yellowBecameBlack(ckeckersBoard, button, column, row);

                turn = !turn;

        }

        if (((ImageButton) v).getBackground().getConstantState().equals(getResources()
                .getDrawable(R.color.cocoabrown).getConstantState())) {
            clearButton();


            if (ckeckersBoard[2][firstj].getTag() == "whitePiece")
                redBecameWhiteKing(ckeckersBoard, button, column, row);
            if (ckeckersBoard[firsti][firstj].getTag() == "whitePiece" || ckeckersBoard[firsti][firstj].getTag() == "whiteKingPiece") {
                redBecameWhite(ckeckersBoard, button, column, row);
                v.performClick();
                if(ifRed()){
                    //turn = !turn;
                    ifYellow();
                }
                else clearButton();
            }

            if (ckeckersBoard[5][firstj].getTag() == "blackPiece")
                redBecameBlackKing(ckeckersBoard, button, column, row);
            if (ckeckersBoard[firsti][firstj].getTag() == "blackPiece" || ckeckersBoard[firsti][firstj].getTag() == "blackKingPiece") {
                redBecameBlack(ckeckersBoard, button, column, row);
                v.performClick();
                if(ifRed()){
                    //turn = !turn;
                    ifYellow();
                }
                else clearButton();
            }
            turn = !turn;

        }

        ///here problem (is there still a problem??)
        if (((ImageButton) v).getBackground().getConstantState().equals(getResources()
                .getDrawable(R.color.bluelight).getConstantState())) {
            clearButton();
            if (ckeckersBoard[firsti][firstj].getTag() == "whiteKingPiece") {
                blueBecameKingWhite(ckeckersBoard, button, column, row);
                Toast.makeText(getApplicationContext(), "" + ckeckersBoard[firsti][firstj].getTag(), Toast.LENGTH_LONG).show();
            }
            if (ckeckersBoard[firsti][firstj].getTag() == "blackKingPiece") {
                blueBecameKingBlack(ckeckersBoard, button, column, row);
            }

            turn = !turn;
        }

        if(blackWin()){

            new AlertDialog.Builder(this)
                    .setTitle("winner!")
                    .setMessage("congratulation " +  blackPlayer +" you won the game \n press ok to continue to ListBoard")
                    .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // save all the data
                            preference.update(blackPlayer, 1);

                            // go to statistic
                            Intent intent=new Intent(Checkers.this,Score.class);
                            startActivity(intent);
                        }
                    }).show();
// alert
        }
        else if(whiteWin()){
            new AlertDialog.Builder(this)
                    .setTitle("winner!")
                    .setMessage("congratulation " +  whitePlayer +" you won the game \n press ok to continue to ListBoard")
                    .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // save all the data
                            preference.update(whitePlayer, 1);

                            // go to statistic
                            Intent intent=new Intent(Checkers.this,Score.class);
                            startActivity(intent);
                        }
                    }).show();
// alert

        }

    }


    //creating the board game
    public void createBoard() {
        TableLayout tl = (TableLayout) findViewById(R.id.tlCheckers);//the id board in the layout

        //the size of the board
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                35, getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                35, getResources().getDisplayMetrics());

        //loop that doing the the number squers on the board
        for (int i = 0; i < 8; i++) {
            ckRowBoard[i] = new TableRow(this); // creating the rows in the TableLayout
            for (int j = 0; j < 8; j++) {
                ckeckersBoard[i][j] = new ImageButton(this); // creating the button and setting an id
                ckeckersBoard[i][j].setId(i * 10 + j);//the number of every squere on the board
                ckeckersBoard[i][j].setTag("");
                ckeckersBoard[i][j].setLayoutParams(new TableRow.LayoutParams(width, height)); // setting the size of every button

                // setting the background so it will be a checkers board (with 2 colors)
                if ((i + j) % 2 == 1) {
                    ckeckersBoard[i][j].setBackgroundResource(R.color.lilacluster);
                } else {
                    ckeckersBoard[i][j].setBackgroundResource(R.color.white);
                }

                ckeckersBoard[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP); // that way the image on the button is in the right size
                ckeckersBoard[i][j].setOnClickListener(this);
                ckRowBoard[i].addView(ckeckersBoard[i][j]);//add to the squere the row
            }
            tl.addView(ckRowBoard[i]);
        }

    }


    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //when selcting option in the menu
        // main --> go to menu
        //music --> stop/play music
        //instraction --> go to instraction
        // call --> go to phone call
        int id = item.getItemId();
        Intent intent = null;

        switch (id) {
            case R.id.music:
                if(MainMenu.isPlaying) {
                    MainMenu.musicService.pause();
                    item.setTitle("Unmute");
                }
                else {
                    MainMenu.musicService.resume();
                    item.setTitle("Mute");
                }
                MainMenu.isPlaying=!MainMenu.isPlaying;
                break;
            case R.id.manu_main:
                intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                finish();
                break;

            case R.id.call:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ""));
                startActivity(intent);
                break;
            case R.id.exit:
                finish();
                //System.exit(0);
                break;
        }
        return true;
    }

    public boolean whiteWin (){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(ckeckersBoard[i][j].getTag() == "blackPiece" || ckeckersBoard[i][j].getTag() == "blackKingPiece"){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean blackWin() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(ckeckersBoard[i][j].getTag() == "whitePiece" || ckeckersBoard[i][j].getTag() == "whiteKingPiece"){
                    return false;
                }
            }
        }
        return true;
    }
}
