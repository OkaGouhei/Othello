import java.util.Random;
import java.util.Scanner;

public class Othello {

  public static void main(String[] args){

    int size = 4;
    int[][] cell = new int[size][size];

    for(int y=0; y<size ; y++){
    for(int x=0; x<size ; x++){
      cell[x][y] = 0;
    }
  }

  //最初の4つのコインを置く
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
//        System.out.println(i+":"+j+"は空白です");
          judge +=  count(cell, size , player, i, j, true);
        }
      }
    }
    if (judge == 0){
      System.out.println("Player "+mark[player]+"はどこにも置くことができません");
      gameEnd++;
      continue;
    }else if (judge > 0 ){
      gameEnd = 0; // 連続してjudgeが0以上の場合に終了できるようにするため
    }

//     System.out.println("〇  のコイン数は"+ pointCount(cell,size, 1));
//     System.out.println("×  のコイン数は"+ pointCount(cell,size, 2));

    System.out.println("Player "+mark[player]+" の番です");
    int putX= 0,putY= 0;
    int judge_2 = 0;
	  if(player == 1) {
	    do{
  			System.out.println("X座標を入力してください");
  			putX = sc.nextInt();
  			System.out.println("Y座標を入力してください");
  			putY = sc.nextInt();
  			System.out.println();
  			judge_2 = count( cell, size , player, putX, putY,true);
		  }while(judge_2 == 0  || putX<0 || putY<0 || putX>=size ||putY>=size ||cell[putX][putY]!=0);
    }else {
		Random r = new Random();
		do{
			putX = r.nextInt(4);
			putY = r.nextInt(4);
			judge_2 = count( cell, size , player, putX, putY,true);
    }while(judge_2 == 0  || putX<0 || putY<0 || putX>=size ||putY>=size ||cell[putX][putY]!=0);
    System.out.println("X座標は"+ putX + "です");
    System.out.println("Y座標は"+ putY + "です");
    }

    count(cell, size, player, putX, putY, false) ;

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

private static int count(int[][] cell,int size,int player,int putX,int putY,boolean hantei) {
	int judge = 0;
    for (int i = -1 ; i<2 ; i++) {
    	for (int j = -1 ; j<2 ; j++) {
    		if(i==0 && j == 0) {
    			continue;
    		}
		    for (int number = 0, k = putX+i , l = putY+j ;k >= 0 && k < size && l >= 0 && l < size  ;number++,k = k +i,l = l +j) {
		      if(cell[k][l] == 0)  {  // その方向が空白だった場合
		        break;
		      }
		      if(cell[k][l] == player) {
		    	  if(hantei== true){
              if(number == 0){  // その方向の1コイン目がplayer自身のコインだった場合
				        break;
				      }else{
//				            System.out.println(+ putX+":"+putY+"は置くことができます");
				        judge++;
				      }
		    	  }else {
              for(; k != putX || l != putY  ; k = k- i ,l = l - j) {
						    cell[k][l] = player;
              }
		    	  }
            break;
		      }
        }
      }
    }
    return judge;
  }

  private static int pointCount(int[][] cell,int size,int player){
    int count = 0;
    for (int i = 0;  i < size; i++) {
      for (int j = 0;  j < size; j++) {
        if(cell[i][j] ==player){
          count++;
        }
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
    System.out.println();
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
