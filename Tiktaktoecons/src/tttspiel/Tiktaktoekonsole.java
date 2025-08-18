package tttspiel;


		import java.util.Random;
		import java.util.Scanner;

		public class Tiktaktoekonsole {

			private static char[] board = new char[9];
			private static char currentPlayer = 'X';
			private static boolean gameEnded = false;
			
			public static void main(String[] args) {
				// TODO Auto-generated method stub
				initBoard();
				printBoard();
				
				Scanner scanner = new Scanner(System.in);
				while(!gameEnded) {
					System.out.println("Player " + currentPlayer +", enter your move (1-9): ");
					int move = scanner.nextInt() - 1;
					if(isValidMove(move)) {
						board[move] = currentPlayer;
						printBoard();
						if(checkWin()) {
							System.out.println("Player " + currentPlayer + " wins!");
							gameEnded = true;
						} else if(isBoradFull()) {
							System.out.println("It's a Tie!");
							gameEnded = true;
						} else {
							switchPlayer();
						}
						
					} else {
						System.out.println("Invalid move. Try again!");
					}
				}
			}
			
			private static void switchPlayer() {
				if(currentPlayer == 'X'){
					currentPlayer = 'O';
					cpuPlayer();
				} else {
					currentPlayer = 'X';
				}
			}
			private static void cpuPlayer() {
				boolean play = true;
				Random rand = new Random();
				
				if(currentPlayer == 'O') {
					 play = true;
					while(play) {
						int move = rand.nextInt(9);
						if(isValidMove(move)) {
							board[move] = currentPlayer;
							printBoard();
							if(checkWin()) {
								System.out.println("Player " + currentPlayer + " wins!");
								gameEnded = true;
							} else if(isBoradFull()) {
								System.out.println("It's a Tie!");
								gameEnded = true;
							} else {
								switchPlayer();
								play = false;
							}
						} 
					}
				}
			}
			
			private static boolean isValidMove (int move) {
				if(move >=0 && move<9) {
					if(board[move] == '-') {
						return true;
					}
				}
				return false;
			}
			
			private static boolean checkHorz() {
				if(board[0] != '-' && board[0] == board[1] && board[0] == board[2]) {
					return true;
				}
				if(board[3] != '-' && board[3] == board[4] && board[3] == board[5]) {
					return true;
				}
				if(board[6] != '-' && board[6] == board[7] && board[6] == board[8]) {
					return true;
				}
				return false;
			}
			
			private static boolean checkVert() {
				if(board[0] != '-' && board[0] == board[3] && board[0] == board[6]) {
					return true;
				}
				if(board[1] != '-' && board[1] == board[4] && board[1] == board[7]) {
					return true;
				}
				if(board[2] != '-' && board[2] == board[5] && board[2] == board[8]) {
					return true;
				}
				return false;
			}
			
			private static boolean checkDiag() {
				if(board[0] != '-' && board[0] == board[4] && board[0] == board[8]) {
					return true;
				}
				if(board[2] != '-' && board[2] == board[4] && board[2] == board[6]) {
					return true;
				}
				return false;
			}
			
			private static boolean checkWin() {
				return(checkHorz() || checkVert() || checkDiag());
			}
			
			private static boolean isBoradFull() {
				for(int i= 0; i<9; i++) {
					if(board[i]=='-') {
						return false;
					}
				}
				return true;
			}
			
			private static void initBoard() {
				for(int i= 0; i<9; i++) {
					board[i]='-';
				}
			}
			
			private static void printBoard() {
				System.out.println("-------------");
				System.out.print("| ");
				for(int i=0; i<3;i++) {
					System.out.print(board[i] + " | ");	
				}
				System.out.println();
				System.out.print("| ");
				for(int i=3; i<6;i++) {
					System.out.print(board[i] + " | ");	
				}
				System.out.println();
				System.out.print("| ");
				for(int i=6; i<9;i++) {
					System.out.print(board[i] + " | ");	
				}
				System.out.println();
				System.out.println("-------------");

			}

		
	

}
