public class Node{

    private int xCoord;
    private int yCoord;
    private boolean isWall;
    private boolean visited;
    private char symbol;
    private boolean special; // When you print the maze, you can't actually print a node object, so you're just going to print the symbol instead
    // you can choose whatever symbols you want, ie X for walls, or O's for Nodes, S' for start, etc it's up to you.
    

    public Node(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.symbol = '*';
    }
    public void specialNode(){
        this.special = true;
        this.symbol = 'X';
    }
    public int getX(){
        return this.xCoord;
    }
    public int getY(){
        return this.yCoord;
    }
    public char getSymbol(){
        return this.symbol;
    }
    public void setSymbol(char c){
        this.symbol = c;

    }
    public void setWall(){
        this.isWall = true;
        this.symbol = 'O';
    }
    public void printSymbol(){
        switch(this.symbol){
            case '*':
                System.out.print(ConsoleColors.BLACK + "*" + ConsoleColors.RESET);
                break;
            case 'X':
                System.out.print(ConsoleColors.GREEN + "X" +ConsoleColors.RESET);
                break;
            case 'O':
                System.out.print(ConsoleColors.RED + "O" + ConsoleColors.RESET);
                break;
        }

    }

}