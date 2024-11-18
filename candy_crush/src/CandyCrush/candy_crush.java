package CandyCrush;

import java.util.*;
public class candy_crush {

    static int moves=0;
    static int score=0;
    static int scoreB=0,scoreG =0,scoreR =0;
    static String[][] tablo;
    public static void main(String [] args){

        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        System.out.println("Enter 's' to start the game or 'q' to quit: ");
        String basla = scan.next();
        if(basla.equals("s"))
            play();
        else
            System.out.println("Çıkış yaptınız.");



    }
    public static void play(){   // Random seçilen oyun moduna göre oyunun döngüsünü içeriyor.
        Random generator = new Random();
        Scanner scan = new Scanner(System.in);
        int mode = generator.nextInt(2);
        switch (mode)
        {
            case 0: // 200 puan 15 hamle
                System.out.println("You have to collect 200 points in 15 moves! ");
                System.out.println("Good luck! ");
                System.out.println("Enter the size of the matris: ");

                int m = scan.nextInt();
                int n = scan.nextInt();
                tablo = new String[m][n];
                System.out.println("You have 15 moves to reach the goal! ");
                initializeBoard();
                while(isMatchPossible())
                    initializeBoard();

                while(moves<15)
                {
                    print();
                    System.out.println("Enter the cell: ");
                    int x = scan.nextInt();
                    int y = scan.nextInt();
                    System.out.println("Enter the direction: ");
                    String yön = scan.next();
                    swapCells(x,y,yön);
                    System.out.println();

                    while(isMatchPossible())
                    {
                        clearAndFillMatches();
                        print();
                        System.out.println();

                    }
                    while(!(isMovePossible()))
                    {
                        shuffleBoard();
                        print();
                        System.out.println();
                        while(isMatchPossible())
                        {
                            clearAndFillMatches();
                            print();
                            System.out.println();

                        }
                    }
                    calculateScore();
                    if(checkWinCondition(1))
                    {
                        System.out.println("You win!");
                        break;
                    }
                    if(moves == 15 && score <200)
                        System.out.println("You lose!");
                    System.out.println("You have collected " + score + " points and you have " + (15-moves) + " moves left! ");

                }
                break;



            case 1: // her bir renk 100 puan 20 hamle
                System.out.println("You have to collect 100 points for each color in 20 moves! ");
                System.out.println("Good luck! ");
                System.out.println("Enter the size of the matris: ");

                m = scan.nextInt();
                n = scan.nextInt();
                tablo = new String[m][n];
                System.out.println("You have 20 moves to reach the goal! ");
                initializeBoard();
                while(isMatchPossible())
                    initializeBoard();

                while(moves<20)
                {
                    print();
                    System.out.println("Enter the cell: ");
                    int x = scan.nextInt();
                    int y = scan.nextInt();
                    System.out.println("Enter the direction: ");
                    String yön = scan.next();
                    swapCells(x,y,yön);
                    System.out.println();

                    while(isMatchPossible())
                    {
                        clearAndFillMatches();
                        print();
                        System.out.println();

                    }
                    while(!(isMovePossible()))
                    {
                        shuffleBoard();
                        print();
                        System.out.println();
                        while(isMatchPossible())
                        {
                            clearAndFillMatches();
                            print();
                            System.out.println();
                        }
                    }

                    if(checkWinCondition(2))
                    {
                        System.out.println("You win!");
                        break;
                    }
                    if(moves ==20  && (scoreB <100 || scoreG <100 || scoreR <100))
                        System.out.println("You lose!");
                    System.out.println("You have collected " + scoreB + " points for blue, "+ scoreG+" points for green, " +scoreR+" points for red "+  "and you have " + (20-moves) + " moves left! ");


                }
        }
    }

    public static boolean checkWinCondition( int mode)  // oyunun kazanılıp kazanılmadığını kontrol ediyor.
    {
        if(mode == 1)
           if(score>=200 && moves<15)
               return true;

        else if(mode == 2)
            if(scoreB >=100 && scoreR >=100 && scoreG >=100 && moves<=20)
                return true;


        return false;
    }
    public static String[][] initializeBoard(){ // oyunun başında tabloyu oluşturuyor.

        Random generator= new Random();
        int [][] matris = new int[tablo.length][tablo[0].length];

        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length; j++){
            int num = generator.nextInt(3);
            matris[i][j] = num;
        }}

        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length; j++){
                if(matris[i][j]==0)
                    tablo[i][j] = "B";
                else if(matris[i][j]==1)
                    tablo[i][j] = "R";
                else if(matris[i][j]==2)
                    tablo[i][j] = "G";
         }}

        return tablo ;
    }
    public static void print(){ //Tabloyu yazdırıyor renkli bir şekilde

        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length; j++){

             if(tablo[i][j].equals("B"))
             {
                 String B = Colors.BLUE + "B" + Colors.RESET;
                 System.out.print("|" +B );

             }
             else if(tablo[i][j].equals("G"))
             {
                 String G = Colors.GREEN + "G" + Colors.RESET;
                 System.out.print("|"+ G );

             }
             else if(tablo[i][j].equals("R"))
             {
                 String R = Colors.RED + "R" + Colors.RESET;
                 System.out.print("|"+ R );

             }
            }
            System.out.print("|");
            System.out.println();
    }

    }

    public static boolean isMatchPossible(){ // yan yana veya üst üste 3 elemanın aynı olup olmadığını kontrol ediyor
        boolean flag_yatay = false;
        boolean flag_dikey = false;
        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length-2; j++){

                if(tablo[i][j].equals(tablo[i][j+1]) && tablo[i][j].equals(tablo[i][j+2]))
                {
                    flag_yatay = true;
                    break;
                }

            }
            if(flag_yatay == true)
            break;
        }


        for(int i =0; i<tablo[0].length;i++){
            for(int j =0; j<tablo.length-2; j++){

                if(tablo[j][i].equals(tablo[j+1][i]) && tablo[j][i].equals(tablo[j+2][i]))
                {
                    flag_dikey = true;
                    break;
                }

            }
            if(flag_dikey == true)
                break;
        }
        return (flag_yatay || flag_dikey);
    }

    public static boolean isMovePossible(){ // olası hamle kalıp kalmadığını kontrol ediyor.

        boolean flag_yatay = false;
        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length-3; j++){

                if(tablo[i][j].equals(tablo[i][j+1])  && tablo[i][j].equals(tablo[i][j+3]) ) // left
                {
                    flag_yatay = true;
                    break;
                }
                else if(tablo[i][j].equals(tablo[i][j+2])  && tablo[i][j+3].equals(tablo[i][j+2])) // right
                {
                    flag_yatay = true;
                    break;
                }

            }
            if(flag_yatay == true)
                break;
        }

        boolean flag_1 = false;
        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length; j++){

                if(i>1 && j>0 && tablo[i][j].equals(tablo[i-1][j-1])  && tablo[i][j].equals(tablo[i-2][j-1]) )//left
                {
                    flag_1 = true;
                    break;
                }
                else if( i>1 && j<tablo[0].length-1 && tablo[i][j].equals(tablo[i-1][j+1])  && tablo[i][j].equals(tablo[i-2][j+1]) )//right
                {
                    flag_1 = true;
                    break;
                }
                else if(j<tablo[0].length-2 && i>0 && tablo[i][j].equals(tablo[i-1][j+1])  && tablo[i][j].equals(tablo[i-1][j+2]) )//up
                {
                    flag_1 = true;
                    break;
                }
                else if( i<tablo.length-1 && j>1 && tablo[i][j].equals(tablo[i+1][j-1])  && tablo[i][j].equals(tablo[i+1][j-2]) )//down
                {
                    flag_1 = true;
                    break;
                }
                else if( i<tablo.length-2 && j<tablo[0].length-1 && tablo[i][j].equals(tablo[i+1][j+1])  && tablo[i][j].equals(tablo[i+2][j+1]) )//right
                {
                    flag_1 = true;
                    break;
                }
                else if( i<tablo.length-2 && j>0 && tablo[i][j].equals(tablo[i+1][j-1])  && tablo[i][j].equals(tablo[i+2][j-1]) )//left
                {
                    flag_1 = true;
                    break;
                }
                else if( i<tablo.length-1 && j<tablo[0].length-2 && tablo[i][j].equals(tablo[i+1][j+1])  && tablo[i][j].equals(tablo[i+1][j+2]) )//down
                {
                    flag_1 = true;
                    break;
                }
                else if( i>0 && j>1 && tablo[i][j].equals(tablo[i-1][j-1])  && tablo[i][j].equals(tablo[i-1][j-2]) )//up
                {
                    flag_1 = true;
                    break;
                }
            }
            if(flag_1 == true)
                break;
        }

        boolean flag_2 = false;
        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length; j++){
              if( i>0 && i<tablo.length-1 && j>0 && tablo[i][j].equals(tablo[i-1][j-1]) && tablo[i][j].equals(tablo[i+1][j-1]) )//left
              {
                  flag_2 =true;
                  break;
              }
              else if(i>0 && j>0 && j< tablo[0].length-1 && tablo[i][j].equals(tablo[i-1][j-1]) && tablo[i][j].equals(tablo[i-1][j+1]))// up
              {
                  flag_2 =true;
                  break;
              }
              else if(i>0 && i< tablo.length-1 && j<tablo[0].length-1 && tablo[i][j].equals(tablo[i-1][j+1]) && tablo[i][j].equals(tablo[i+1][j+1])) // right
              {
                  flag_2 =true;
                  break;
              }
              else if(i< tablo.length-1 && j<tablo[0].length-1 && j>0 && tablo[i][j].equals(tablo[i+1][j+1]) && tablo[i][j].equals(tablo[i+1][j-1])) // down
              {
                  flag_2 =true;
                  break;
              }

            }
            if(flag_2)
                break;
        }
        boolean flag_dikey = false;
        for(int i =0; i<tablo[0].length;i++){
            for(int j =0; j<tablo.length-3; j++){

                if(tablo[j][i].equals(tablo[j+1][i])  && tablo[j][i].equals(tablo[j+3][i]) )//up
                {
                    flag_dikey = true;
                    break;
                }
                else if(tablo[j][i].equals(tablo[j+2][i])  && tablo[j+3][i].equals(tablo[j+2][i]) )//down
                {
                    flag_dikey = true;
                    break;
                }

            }
            if(flag_dikey == true)
                break;
        }
        return (flag_dikey || flag_yatay || flag_1 || flag_2);

    }
    public static void clearAndFillMatches(){ // eşleşmeleri temizliyip kalanları aşağıya kaydırıyor sonra da boş kalan yerleri random olarak dolduruyor, aynı zamanda eşleşmelerden
        Random rnd = new Random();            // elde edilen puanları renk renk topluyor.
        boolean flag = false;

        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length-2; j++){

                if( i == 0 && tablo[i][j].equals(tablo[i][j+1])  && tablo[i][j].equals(tablo[i][j+2]) )
                {
                    if(tablo[i][j].equals("B"))
                        scoreB +=10;
                    else if(tablo[i][j].equals("G"))
                        scoreG +=10;
                    else if(tablo[i][j].equals("R"))
                        scoreR +=10;
                    int ij= rnd.nextInt(3);
                    if(ij==0)
                        tablo[0][j] = "B";
                    else if(ij==1)
                        tablo[0][j] = "G";
                    else if(ij==2)
                        tablo[0][j] = "R";

                    int ij1= rnd.nextInt(3);
                    if(ij1==0)
                        tablo[0][j+1] = "B";
                    else if(ij1==1)
                        tablo[0][j+1] = "G";
                    else if(ij1==2)
                        tablo[0][j+1] = "R";

                    int ij2= rnd.nextInt(3);
                    if(ij2==0)
                        tablo[0][j+2] = "B";
                    else if(ij2==1)
                        tablo[0][j+2] = "G";
                    else if(ij2==2)
                        tablo[0][j+2] = "R";
                    flag = true;

                    break;
                }
                else if(tablo[i][j].equals(tablo[i][j+1]) && tablo[i][j].equals(tablo[i][j+2]))
                {

                    if(tablo[i][j].equals("B"))
                        scoreB +=10;
                    else if(tablo[i][j].equals("G"))
                        scoreG +=10;
                    else if(tablo[i][j].equals("R"))
                        scoreR +=10;

                    for(int n =0;n<3;n++)
                    {for(int m = 0; m<i;m++ )
                         tablo[i-m][j+n] =tablo[i-1-m][j+n];}

                    int ij= rnd.nextInt(3);
                    if(ij==0)
                        tablo[0][j] = "B";
                    else if(ij==1)
                        tablo[0][j] = "G";
                    else if(ij==2)
                        tablo[0][j] = "R";

                    int ij1= rnd.nextInt(3);
                    if(ij1==0)
                        tablo[0][j+1] = "B";
                    else if(ij1==1)
                        tablo[0][j+1] = "G";
                    else if(ij1==2)
                        tablo[0][j+1] = "R";

                    int ij2= rnd.nextInt(3);
                    if(ij2==0)
                        tablo[0][j+2] = "B";
                    else if(ij2==1)
                        tablo[0][j+2] = "G";
                    else if(ij2==2)
                        tablo[0][j+2] = "R";
                    flag = true;

                    break;
                }
            if(flag)
                break;
            }

        }

        boolean flag_dikey=false;
        for(int i =0; i<tablo[0].length;i++){
            for(int j =0; j<tablo.length-2; j++){

                if( j==0 && tablo[j][i].equals(tablo[j+1][i])  && tablo[j][i].equals(tablo[j+2][i]) )
                {
                    if(tablo[j][i].equals("B"))
                        scoreB +=10;
                    else if(tablo[j][i].equals("G"))
                        scoreG +=10;
                    else if(tablo[j][i].equals("R"))
                        scoreR +=10;

                    int ij= rnd.nextInt(3);
                    if(ij==0)
                        tablo[j][i] = "B";
                    else if(ij==1)
                        tablo[j][i] = "G";
                    else if(ij==2)
                        tablo[j][i] = "R";

                    int ij1= rnd.nextInt(3);
                    if(ij1==0)
                        tablo[j+1][i] = "B";
                    else if(ij1==1)
                        tablo[j+1][i] = "G";
                    else if(ij1==2)
                        tablo[j+1][i] = "R";

                    int ij2= rnd.nextInt(3);
                    if(ij2==0)
                        tablo[j+2][i] = "B";
                    else if(ij2==1)
                        tablo[j+2][i] = "G";
                    else if(ij2==2)
                        tablo[j+2][i] = "R";
                    flag_dikey = true;

                    break;

                }
                else if(tablo[j][i].equals(tablo[j+1][i])  && tablo[j][i].equals(tablo[j+2][i]) )
                {
                    if(tablo[j][i].equals("B"))
                        scoreB +=10;
                    else if(tablo[j][i].equals("G"))
                        scoreG +=10;
                    else if(tablo[j][i].equals("R"))
                        scoreR +=10;

                    for(int m =0; m<j;m++)
                        tablo[j+2-m][i] =tablo[j-m-1][i];

                    int ij= rnd.nextInt(3);
                    if(ij==0)
                        tablo[0][i] = "B";
                    else if(ij==1)
                        tablo[0][i] = "G";
                    else if(ij==2)
                        tablo[0][i] = "R";

                    int ij1= rnd.nextInt(3);
                    if(ij1==0)
                        tablo[1][i] = "B";
                    else if(ij1==1)
                        tablo[1][i] = "G";
                    else if(ij1==2)
                        tablo[1][i] = "R";

                    int ij2= rnd.nextInt(3);
                    if(ij2==0)
                        tablo[2][i] = "B";
                    else if(ij2==1)
                        tablo[2][i] = "G";
                    else if(ij2==2)
                        tablo[2][i] = "R";
                    flag_dikey = true;

                    break;
                }
        }
            if(flag_dikey)
                break;
        }



    }

    public static void calculateScore(){ // oyunun 1. modu için toplam puanı hesaplıyor.

        score = scoreB + scoreG + scoreR;
    }

    public static void swapCells(int x ,int y,String direction){ // kullanıcıdan alınan hamleye göre hücrelerdeki harflerin yerini değiştiriyor.
                                                                 // girişlerin valid veya invalid olmasını kontrol ediyor
        if((x==1 && direction.equals("up") ) || (x==tablo.length && direction.equals("down") ) || (y==1 && direction.equals("left") ) || (x==tablo[0].length && direction.equals("rigth") ))
        {
          System.out.println("invalid move");
          print();
        }
        else
        {
            String temp = tablo[x-1][y-1];
            moves ++;
            if(direction.equals("up"))
            {
                boolean flag = true;
                String temp_up = tablo[x-2][y-1];
                tablo[x-1][y-1] = temp_up  ;
                tablo[x-2][y-1] = temp;

                if(!(isMatchPossible()))
                {
                    tablo[x-1][y-1] = temp;
                    tablo[x-2][y-1] = temp_up;
                    moves --;
                    flag = false;
                    System.out.println("invalid move");
                    print();
                }
                if(flag)
                {System.out.println("Clearing Board: ");
                    print();}
            }
            else if(direction.equals("down"))
            {
                boolean flag = true;
                String temp_down = tablo[x][y-1];
                tablo[x-1][y-1] = temp_down;
                tablo[x][y-1] = temp;
                if(!(isMatchPossible()))
                {
                    tablo[x-1][y-1] = temp;
                    tablo[x][y-1] = temp_down;
                    moves --;
                    flag = false;
                    System.out.println("invalid move");
                    print();
                }
                if(flag)
                {
                    System.out.println("Clearing Board: ");
                    print();
                }
            }
            else if(direction.equals("right"))
            {
                boolean flag = true;
                String temp_right = tablo[x-1][y];
                tablo[x-1][y-1] = temp_right;
                tablo[x-1][y] = temp;
                if(!(isMatchPossible()))
                {
                    tablo[x-1][y-1] = temp;
                    tablo[x-1][y] = temp_right;
                    moves --;
                    System.out.println("invalid move");
                    print();
                    flag = false;
                }
                if(flag)
                {System.out.println("Clearing Board: ");
                    print();}
            }
            else if(direction.equals("left"))
            {
                boolean flag = true;
                String temp_left = tablo[x-1][y-2];
                tablo[x-1][y-1] = temp_left;
                tablo[x-1][y-2] = temp;
                if(!(isMatchPossible()))
                {
                    tablo[x-1][y-1] = temp;
                    tablo[x-1][y-2] = temp_left;
                    moves --;
                    flag = false;
                    System.out.println("invalid move");
                    print();
                }
                if(flag)
                {
                    System.out.println("Clearing Board: ");
                    print();}
            }
        }
    }

    public static void shuffleBoard(){ // tablodaki elemanların yerlerini random olarak karıyor.
        String eleman="";
        Random generate = new Random();
        int num = tablo.length * tablo[0].length;
        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length; j++){

              eleman += tablo[i][j];
            }
        }

        for(int i =0; i<tablo.length;i++){
            for(int j =0; j<tablo[0].length; j++){
                int sıra = generate.nextInt(num);
                tablo[i][j] = "" + eleman.charAt(sıra);
                num--;
                eleman = eleman.substring(0,sıra)+ eleman.substring(sıra+1);
            }
        }
    }
}
