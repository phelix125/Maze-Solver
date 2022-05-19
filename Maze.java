import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.lang.Thread;
import java.util.*;
public class Maze{

    private int width;
    private int height;
    private int difficulty;
    private Node start;
    private Node end;
    private Node[][] Maze;
    
    


    public Maze(){

        userSettings();
    }
    private void initMaze(){
        Random rand = new Random();
        Node [][] Maze = new Node[this.height][this.width];
        for(int i=0; i<this.height; i++){
            for(int j=0; j<this.width; j++){
                Node n = new Node(i,j);
                if(rand.nextInt(100) < difficulty * 3) n.setWall();
                Maze[i][j] = n;
            }
            
        }
        Maze[start.getX()][start.getY()] = this.start;
        Maze[end.getX()][end.getY()] = this.end;
        this.Maze = Maze;
        
    }
    private void userSettings(){
        System.out.println("Enter a Width");
        this.width = validateInt();
        clearScreen();
        System.out.println("Enter a Height");
        this.height = validateInt();
        clearScreen();
        System.out.println("Enter a Difficult (1-10)");
        this.difficulty = validateInt();
        while(this.difficulty > 10){
            System.out.println("Not a valid Number");
            this.difficulty = validateInt();
        }
        System.out.println("Enter a Starting Node, example ( 1,2 )");
        this.start = nodeGetter();
        
        System.out.println("Enter a End node;");
        this.end = nodeGetter();
        initMaze();
        printMaze();
        this.start.setSymbol('0');
        if(BFS(this.start.getX(), this.start.getY())){
            System.out.println("Path Found");
        }else{
            System.out.println("No Path");
        }
        
    }
    private boolean BFS(int i, int j){
        if(i < 0 || j < 0 || i >= this.height || j>= this.width) return false;
        printMaze();
        Node n = this.Maze[i][j];
        if(n.getX() == this.end.getX() && n.getY() == this.end.getY()) return true;
        if(n.getSymbol() == 'X' || n.getSymbol() == 'O') return false;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clearScreen();
        n.specialNode();
        return BFS(i+1,j) || BFS(i-1,j) || BFS(i,j+1) || BFS(i,j-1);
    }


    private void printMaze(){

        for(int j=0; j<this.width; j++){
            System.out.print(ConsoleColors.WHITE + "-");
        }
        System.out.println();
        for(int i=0; i<this.height; i++){
            System.out.print(ConsoleColors.WHITE + "|");
            for(int j=0; j<this.width; j++){
                Maze[i][j].printSymbol();

            }
            System.out.print(ConsoleColors.WHITE + "|");
            System.out.println();
        }
        for(int j=0; j<this.width; j++){
            System.out.print(ConsoleColors.WHITE + "-");
        }

    }
    
    private Node nodeGetter(){
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        while(s.indexOf(',') == -1){
            System.out.println("Invalid input");
            s = scan.next();
        }
        int index = s.indexOf(',');
        int startX = Integer.valueOf(s.substring(0,index));
        int startY = Integer.valueOf(s.substring(index+1));
        if(startX > this.width){
            System.out.println("Width is too short");
            nodeGetter();
        }
        if(startY > this.height){
            System.out.println("Height is too short");
            nodeGetter();
        }
        Node start = new Node(startX,startY);
        start.specialNode();
        return start;
        
    
    }
    private  void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
    private int validateInt(){
         int s = 0;
        boolean isValid = false;
        while(!isValid){
            try{
                System.out.print("type a valid input: ");
                Scanner scan = new Scanner(System.in);
                s = scan.nextInt();
                if( s > 0){
                    isValid = true;
                }else{
                    System.out.println("Must be greater then 0");
                }
            }catch(InputMismatchException e){
                System.out.println("Not a valid input");
            }  
        }
        clearScreen();
        return s;
    }
    // You need to write setters and getters for all the variables, heres an example of a setter and getter for width

    //here, if you want to set the width as 20 you would call setWidth(20)
    public void setWidth(int width){
        this.width = width;
    }
    //      V this is an int, because were getting an int (the width) from this function so if the width was 20, and we called getWidth() a 20 would be returned to us.
    public int getWidth(){
        return this.width;
    }

}