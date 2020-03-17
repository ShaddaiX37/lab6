import java.util.Scanner;

public class ConnectFour {
    public static void main(String args[]){
        //Base Board Function
        Scanner scan = new Scanner(System.in);
        System.out.print("What would you like the height of the board to be? ");
        int height = scan.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int width = scan.nextInt();
        char[][] board =  new char[height][width];
        initializeBoard(board);
        boolean gameState = true;
        boolean winFlag = false;
        int turn = 1;
        char chipType = '-';

        //Game Loop (Filled board and win condition result in loop termination)
        while (gameState && winFlag == false) {
            if (turn == height*width+1){
                gameState = false;
                break;
            }
            if (turn%2 != 0) {
                chipType = 'x';
                System.out.print("Player 1: Which column would you like to choose? ");
            }
            else{
                chipType = 'o';
                System.out.print("Player 2: Which column would you like to choose? ");
            }
            int col = scan.nextInt();
            int row = insertChip(board, col, chipType);
            printBoard(board);
            winFlag = checkIfWinner(board,col,row,chipType);
            turn+=1;
        }

        //End Conditions
        if (winFlag == true) {
            if (chipType == 'x') {
                System.out.println("Player 1 won the game!");
            } else {
                System.out.println("Player 2 won the game!");
            }
        }
        else{
            System.out.println("Draw. Nobody wins.");
        }

    }

    //Board Printing Method (Prints Array)
    public static void printBoard(char[][] array){
        for(int i=array.length-1;i>=0; i--){
            for(int j=0;j<array[0].length;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println("");
        }
    }
    //Initialize Board Method (Fills array with -'s)
    public static void initializeBoard(char[][] array){
        for(int i=0;i<array.length; i++){
            for(int j=0;j<array[0].length;j++){
                array[i][j] = '-';
                System.out.print(array[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("Player 1: x\nPlayer 2: o");
    }
    //Insert Chip Function (Includes reverse order)
    public static int insertChip(char[][] array, int col, char chipType){
        int row = 0;
        for (int i=0;i<array.length;i++){
            if (array[i][col]=='-'){
                row = i;
                array[i][col] = chipType;
                break;
            }
        }
        return row;
    }
    //Check Win conditions
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int chipCount = 0;
        boolean winFlag = false;
        //Creates two separate arrays for row and column data
        char rowArray[] = array[row] ;
        char colArray[] = new char [array.length];
        for (int i=array.length-1;i>=0;i--){
            colArray[i] = array[i][col];
        }

        //Column Check
        for (int i=0; i<colArray.length;i++) {
            if (colArray[i] == chipType){
                chipCount = chipCount + 1;
                if (chipCount >= 4){
                    winFlag= true;
                    break;
                }
            }
            else{
                chipCount = 0;
            }
        }
        //Row Check
        chipCount = 0;
        for (int j=0; j<rowArray.length;j++){
                if (rowArray[j] == chipType){
                    chipCount = chipCount + 1;
                    if (chipCount >= 4){
                        winFlag = true;
                        break;
                    }
                }
                else{
                    chipCount = 0;
                }
            }


        return winFlag;
    }

}

