import java.util.Scanner;

public class Othello {

 public static void main(String[] args){

   int size = 4;
   int[][] cell = new int[size][size];

   for(int y=0; y<size; y++){
     for(int x=0; x<size; x++){
       cell[x][y] = 0;
     }
   }

   for (int i = 0;  i < 2; i++) {
       for (int j = 0;  j < 2; j++){
        cell[size/2-i][size/2-j] = 1 + (i+j)%2 ;
       }
   }

   String mark[] = {" ", "O", "X"};

   hyouji( cell,mark,size);

   Scanner sc = new Scanner(System.in);
   int gameEnd = 0;
   for(int turn = 0; ; turn++){
     int player = turn%2+1;
     if (gameEnd == 2){
       System.out.println("どちらも置けないのでgame終了です");
       break;
     }

     int judge = 0;
     for (int i = 0;  i < size; i++) {
       for (int j = 0;  j < size; j++){

         if(cell[i][j] == 0){
           System.out.println(i+":"+j+"は空白です");

           for (int k = -1 ; k<2 ; k++) {
            for (int l = -1 ; l<2 ; l++) {
              if(k==0 && l==0) {
                continue;
              }
                   judge =judge(k ,l ,cell,size, player, i, j,  judge);
            }
           }
         }
       }
     }
     if (judge == 0){
       System.out.println("Player "+mark[player]+"はどこにも置くことができません");
       gameEnd++;
       continue;
     }else if (judge > 0 ){
       gameEnd = 0; // 連続してjudgeが0以上の場合に終了できるようにするためのからくり
     }

     System.out.println("〇  のコイン数は"+ pointCount(cell,size, 1));
     System.out.println("×  のコイン数は"+ pointCount(cell,size, 2));

     System.out.println("Player "+mark[player]+" の番です");
     int putX= 0,putY= 0;
     int othello = 0;

     do{
       System.out.println("X座標を入力してください");
       putX = sc.nextInt();
       System.out.println("Y座標を入力してください");
       putY = sc.nextInt();
       System.out.println();

       for (int i = -1 ; i<2 ; i++) {
        for (int j = -1 ; j<2 ; j++) {
          if(i==0 && j==0) {
            continue;
          }
               othello = judge(i,j, cell, size , player, putX, putY,
othello);
               cell = count(i, j,cell, size, player, putX, putY) ;
        }
       }
     }while(othello == 0  || putX<0 || putY<0 || putX>=size ||putY>=size ||cell[putX][putY]!=0);

     cell[putX][putY] = player;
     hyouji( cell, mark, size);
     int endPoint = pointCount( cell, size, 0);

     if(endPoint == 0) {
       break;
     }
   }

   if( pointCount(cell,size, 1) ==  pointCount(cell,size, 2)) {
   System.out.println("引き分けです");
   }else if ( pointCount(cell,size, 1) > pointCount(cell,size, 2)) {
   System.out.println(mark[1]+" の勝ちです");
   }else {
   System.out.println(mark[2]+" の勝ちです");
   }
   sc.close(); // このコードは必要なのか？エラーが出るから一応書いた
 }

 private static int judge(int yoko ,int tate,int[][] cell,int size,int
player,int i,int j, int judge) {
    for ( int number = 0, k = i + yoko ,l = j + tate;k >= 0&& k < size&& l >= 0 && l < size  ;number++,k = k + yoko ,l= l + tate) {
        if(cell[k][l] == 0) {
          break;
        }
        if(cell[k][l] == player) {
          if(number == 0) {
            break;
          }else{
            System.out.println(+i+":"+j+"は置くことができます");
            judge++;
          }
        }
      }
    return judge;
 }

 private static int[][] count(int yoko ,int tate,int[][] cell,int size,int
player,int putX,int putY) {
    for (int x = putX+yoko , y = putY + tate;x >= 0 && x < size && y >= 0 && y < size  ;x = x +yoko,y = y + tate) {
      if(cell[x][y] == 0) {
        break;
      }
      if(cell[x][y] == player|| cell[x][y] == player) {
        for(; x != putX || y != putY  ; x = x- yoko ,y = y - tate) {
          if(yoko !=0 ) {
            cell[x][y] = player;
          }else if(tate != 0 ){
            cell[x][y] = player;
          }
        }
        break;
      }
    }
    return cell;
 }

 private static int pointCount(int[][] cell,int size,int player){
   int count = 0;
   for (int i = 0;  i < size; i++) {
   for (int j = 0;  j < size; j++)
     if(cell[i][j] ==player){
       count++;
     }
   }
   return count;
 }

 private static void hyouji(int[][] cell,String[] mark,int size){
    //マス目をかく
    String hyouji = " |";
    for(int x=0; x<size; x++){
      hyouji += x +"|";
    }
    System.out.println(hyouji);
    for(int y=0; y<size; y++){
      String row = y +"|";
      for(int x=0; x<size; x++){
        row+=mark[cell[x][y]]+"|";
      }
      System.out.println(row);
    }
    System.out.println();
 }
}
