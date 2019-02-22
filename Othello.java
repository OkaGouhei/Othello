import java.util.Scanner;

public class Othello {

   public static void main(String[] args){
       System.out.println("置くところがなくパスの場合、999 ・ 999 と入れてください");
       System.out.println("どちらも置くところがない場合、888 ・ 888 と入れてください");
       System.out.println();
       int size = 4;
       int[][] cell = new int[size][size];

       for(int y=0; y<size; y++){
           for(int x=0; x<size; x++){
               cell[x][y] = 0;
           }
       }
       cell[size/2-1][size/2-1]=1;
       cell[size/2][size/2]    =1;
       cell[size/2-1][size/2]  =2;
       cell[size/2][size/2-1]  =2;
       String mark[] = {" ", "O", "X"};

       String initiate = " |";
       for(int x=0; x<size; x++){
        initiate += x +"|";
       }
       System.out.println(initiate);
       for(int y=0; y<size; y++){
           String row = y + "|";
           for(int x=0; x<size; x++){
               row+=mark[cell[x][y]]+"|";
           }
           System.out.println(row);
       }
       System.out.println();
       Scanner sc = new Scanner(System.in);

       for(int turn = 0; ; turn++){
           int player = turn%2+1;
           System.out.println("Player "+mark[player]+" の番です");
          int putX= 0,putY= 0;
          boolean othello = false;
          do{
              System.out.println("X座標を入力してください");
              putX = sc.nextInt();

              System.out.println("Y座標を入力してください");
              putY = sc.nextInt();
              System.out.println();
              if((putX == 999 & putY == 999)||(putX == 888 & putY ==888)){
                othello = true;
                break;
              }
            //右横を反転させる
            for (int number = 0, x = putX+1; x < size ;number++,x++) {
              if(cell[x][putY] == 0) {
                break;
              }
              if(cell[x][putY] == player) {
                for(; x > putX; x--) {
                  cell[x][putY] = player;
                }
                if(number != 0) {
                  othello = true;
                }
                break;
              }
            }
            //左横を反転させる
            for (int number = 0,x = putX-1; x >= 0 ;  number++,x--) {
              if(cell[x][putY] == 0) {
                break;
              }
              if(cell[x][putY] == player) {
                for(; x < putX; x++) {
                  cell[x][putY] = player;
                }
                if(number != 0) {
                  othello = true;
                }

                break;
              }
            }
            //下を反転させる
            for (int number = 0,y = putY+1; y < size ; number++,y++) {
              if(cell[putX][y] == 0) {
                break;
              }
              if(cell[putX][y] == player) {
                for(; y > putY; y--) {
                  cell[putX][y] = player;
                }
                if(number != 0) {
                  othello = true;
                }
                break;
              }
            }
            //上を反転させる
            for (int number = 0, y = putY-1; y >= 0 ; number++,y--) {
              if(cell[putX][y] == 0) {
                break;
              }
              if(cell[putX][y] == player) {
                for(; y < putY; y++) {
                  cell[putX][y] = player;
                }
                if(number != 0) {
                  othello = true;
                }
                break;
              }
            }
            //右斜め上を反転させる
            for (int number = 0, x = putX +1, y = putY-1 ;x <size && y >= 0;number++, x++, y--) {
              if(cell[x][y] == 0) {
                break;
              }
              if(cell[x][y] == player) {
                for(; x > putX && y < putY; x--,y++) {
                  cell[x][y] = player;
                }
                if(number != 0) {
                  othello = true;
                }
                break;
              }
            }
            //右斜め下を反転させる
            for (int  number = 0,x = putX +1, y = putY+1 ;x < size && y < size; number++, x++, y++) {
              if(cell[x][y] == 0) {
                break;
              }
              if(cell[x][y] == player) {
                for(; x > putX && y > putY; x--, y--) {
                  cell[x][y] = player;
                }
                if(number != 0) {
                  othello = true;
                }
                break;
              }
            }
            //左斜め上を反転させる
            for (int  number = 0,x = putX -1, y = putY-1 ;x >= 0 && y >= 0; number++, x--, y--) {
              if(cell[x][y] == 0) {
                break;
              }
              if(cell[x][y] == player) {
                for(; x < putX && y < putY; x++, y++) {
                  cell[x][y] = player;
                }
                if(number != 0) {
                  othello = true;
                }
                break;
              }
            }
            //左斜め下を反転させる
            for (int number = 0,x = putX -1, y = putY+1 ;x >= 0 && y < size ; number++, x--, y++) {
              if(cell[x][y] == 0) {
                break;
              }
              if(cell[x][y] == player) {
                for(; x > putX && y < putY; x++,y--) {
                  cell[x][y] = player;
                }
                if(number != 0) {
                  othello = true;
                }
                break;
              }
            }

          }while(!othello || putX<0 || putY<0 || putX>=size ||putY>=size || cell[putX][putY]!=0);
          //パスの場合
          if(putX == 999 & putY == 999) {
            continue;
           }
           if(putX == 888 & putY == 888) {
            break;
           }

          cell[putX][putY] = player;

          //マス目をかく
          String initiate2 = " |";
          for(int x=0; x<size; x++){
            initiate2 += x +"|";
          }
          System.out.println(initiate2);
          for(int y=0; y<size; y++){
              String row = y +"|";
              for(int x=0; x<size; x++){
                  row+=mark[cell[x][y]]+"|";
              }
              System.out.println(row);
          }
          System.out.println();

          int endPoint = 0;
          for (int i = 0;  i < cell.length; i++) {
            for (int j = 0;  j < cell.length; j++)
              if(cell[i][j] == 0){
                endPoint++;
              }
          }
          if(endPoint == 0) {
            break;
          }
       }
       int countMaru = 0;
       for (int i = 0;  i < cell.length; i++) {
        for (int j = 0;  j < cell.length; j++)
           if(cell[i][j] == 1){
            countMaru++;
           }
       }
       int countBatsu = 0;
       for (int i = 0;  i < cell.length; i++) {
        for (int j = 0;  j < cell.length; j++)
           if(cell[i][j] == 2){
            countBatsu++;
           }
       }

       if(countMaru == countBatsu) {
        System.out.println("引き分けです");
       }else if (countMaru > countBatsu ) {
        System.out.println(mark[1]+" の勝ちです");
       }else {
        System.out.println(mark[2]+" の勝ちです");
       }
   }
}