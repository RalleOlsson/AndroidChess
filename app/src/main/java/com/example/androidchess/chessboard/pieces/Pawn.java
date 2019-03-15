package com.example.androidchess.chessboard.pieces;

import android.util.Log;
import com.example.androidchess.R;

import static com.example.androidchess.chessboard.Game.*;

public class Pawn {

    public void pawnCheck(int position) {
        if (getFilename(position).charAt(1) == 'w' && whiteTurn) {
            pawnCheckWhite(position);
        } else if (getFilename(position).charAt(1) == 'b' && !whiteTurn){
            pawnCheckBlack(position);
        }
    }

    public void pawnCheckWhite(int position) {
        int x = position % 8;
        int y = position / 8;

        int i = x;
        int n = y - 1;
        int currentPos;
        if (y == 6) {
            boolean obstacle = false;
            while (n >= 4 && !obstacle) {
                currentPos = i + 8 * n;
                if (getFilename(currentPos).charAt(0) != 't') {

                    obstacle = true;
                }
                if (getFilename(currentPos).charAt(0) == 't') {
                    possibleMoves[currentPos] = true;
                }
                n--;
            }
            i = x - 1;
            n = y - 1;
            currentPos = i + 8 * n;
            if (n >= 0 && i >= 0 && getFilename(currentPos).charAt(1) == 'b') {
                getCell(currentPos).setBackgroundResource(R.drawable.redbackground);
                possibleMoves[currentPos] = true;
            }
            i = x + 1;
            n = y - 1;
            currentPos = i + 8 * n;
            if (n >= 0 && i < 8 && getFilename(currentPos).charAt(1) == 'b') {
                getCell(currentPos).setBackgroundResource(R.drawable.redbackground);
                possibleMoves[currentPos] = true;
            }
        } else {
            currentPos = i + 8 * n;

            if (n >= 0 && getFilename(currentPos).charAt(0) == 't') {
                possibleMoves[currentPos] = true;
            }
            i = x - 1;
            n = y - 1;
            currentPos = i + 8 * n;
            if (n >= 0 && i >= 0 && getFilename(currentPos).charAt(1) == 'b') {
                getCell(currentPos).setBackgroundResource(R.drawable.redbackground);
                possibleMoves[currentPos] = true;
            }
            i = x + 1;
            n = y - 1;
            currentPos = i + 8 * n;
            if (n >= 0 && i < 8 && getFilename(currentPos).charAt(1) == 'b') {
                getCell(currentPos).setBackgroundResource(R.drawable.redbackground);
                possibleMoves[currentPos] = true;
            }
        }
    }

    public void pawnCheckBlack(int position) {
        int x = position % 8;
        int y = position / 8;

        int i = x;
        int n = y + 1;
        int currentPos;
        if (y == 1) {
            boolean obstacle = false;
            while (n <= 3 && !obstacle) {
                currentPos = i + 8 * n;
                if (getFilename(currentPos).charAt(0) != 't') {

                    obstacle = true;
                }
                if (getFilename(currentPos).charAt(0) == 't') {
                    possibleMoves[currentPos] = true;
                }
                n++;
            }
            i = x + 1;
            n = y + 1;
            currentPos = i + 8 * n;
            if (n < 8 && i < 8 && getFilename(currentPos).charAt(1) == 'w') {
                possibleMoves[currentPos] = true;
                getCell(currentPos).setBackgroundResource(R.drawable.redbackground);
            }
            i = x - 1;
            n = y + 1;
            currentPos = i + 8 * n;
            if (n < 8 && i >=0 && getFilename(currentPos).charAt(1) == 'w') {
                possibleMoves[currentPos] = true;
                getCell(currentPos).setBackgroundResource(R.drawable.redbackground);
            }
        } else {
            currentPos = i + 8 * n;
            if (n < 8 && getFilename(currentPos).charAt(0) == 't') {
                possibleMoves[currentPos] = true;
            }

            i = x + 1;
            n = y + 1;
            currentPos = i + 8 * n;
            if (n < 8 && i < 8 && getFilename(currentPos).charAt(1) == 'w') {
                possibleMoves[currentPos] = true;
                getCell(currentPos).setBackgroundResource(R.drawable.redbackground);
            }
            i = x - 1;
            n = y + 1;
            currentPos = i + 8 * n;
            if (n < 8 && i >=0 && getFilename(currentPos).charAt(1) == 'w') {
                possibleMoves[currentPos] = true;
                getCell(currentPos).setBackgroundResource(R.drawable.redbackground);
            }
        }
    }


}
