package com.example.androidchess.chessboard.pieces;

import static com.example.androidchess.chessboard.Game.getFilename;
import static com.example.androidchess.chessboard.Game.possibleMoves;

public class Pawn {

    public void pawnCheck(int position) {
        if (getFilename(position).charAt(1) == 'w') {
            pawnCheckWhite(position);
        } else {
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
                possibleMoves[currentPos] = true;
                n--;
            }
        } else {
            currentPos = i + 8 * n;
            if (n >= 0 && getFilename(currentPos).charAt(0) == 't') {
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
                possibleMoves[currentPos] = true;
                n++;
            }
        } else {
            currentPos = i + 8 * n;
            if (n < 8 && getFilename(currentPos).charAt(0) == 't') {
                possibleMoves[currentPos] = true;
            }
        }
    }


}