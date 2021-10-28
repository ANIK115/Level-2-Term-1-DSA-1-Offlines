package company05115;

import java.util.Scanner;

class Player {
    private int playerNo;
    private int reflexTime;
    private Player prev;
    private Player next;

    public Player(int playerNo, int reflexTime) {
        this.playerNo = playerNo;
        this.reflexTime = reflexTime;
        this.prev = null;
        this.next = null;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public int getReflexTime() {
        return reflexTime;
    }

    public Player getPrev() {
        return prev;
    }

    public Player getNext() {
        return next;
    }

    public void setPrev(Player prev) {
        this.prev = prev;
    }

    public void setNext(Player next) {
        this.next = next;
    }
}

public class PillowPassingGame {
    private Player head;
    private Player currentPlayer;
    private boolean passDirectionClockWise = true;
    private int playerCount = 0;
    private int players = 0;
    private int passTime =0; //keeps the record of passing times that has already been completed!

    public PillowPassingGame() {
        System.out.println("Welcome to Pillow Passing Game.\nFirst insert n number of players with their positive reflex times and then the game will be " +
                "automatically started! Follow the Following commands for the game.\n" +
                "time P command gives the no. of the player currently holding the pillow\n" +
                "time M command changes music and eliminates the player holding the pillow at that time\n" +
                "time R command reverses the direction of passing the pillow\n" +
                "time I reflexTime command inserts a new player at that time\n" +
                "time F command ends the game!\n" +
                "The time values should be in increasing order\n" +
                "Follow the correct command formats and enjoy the game! Thank you!");
    }

    public void start()
    {
        Scanner scanner = new Scanner(System.in);
        int n=0;
        System.out.println("How many Players?");
        while(n<=0)
        {
            n = scanner.nextInt();
            if(n<1)
            {
                System.out.println("You need at least 1 player to start the game! Try again");
            }
        }
        for(int i=0; i<n; i++)
        {
            int x=0;
            System.out.println("Enter reflex time of "+(i+1)+"th player: ");
            while(x<=0)
            {
                x = scanner.nextInt();
                if(x<=0)
                {
                    System.out.println("Can't insert this player! Reflex time must be greater than 0. Try again!");
                }
            }
            insertPlayer(x);
        }
        System.out.println("Start the game with appropriate commands!");
        scanner = new Scanner(System.in);
        while(true)
        {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            if(tokens.length==2)
            {
                gameCommand(tokens[0], tokens[1]);
                if(tokens[1].equals("F"))
                    break;
            }
            else if(tokens.length==3)
            {
                gameCommand(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            }else
            {
                System.out.println("Invalid number of commands!");
            }
        }
    }

    private Player createPlayer(int n, int x)
    {
        return new Player(n,x);
    }
    private void insertPlayer(int x) // inserts player at the end of the linked list before the game initializes
    {
        Player player = createPlayer(++players,x); //players denotes the Player No.
        if(head == null)
        {
            head = player;
            head.setPrev(head);
            head.setNext(head);
            currentPlayer = head;
            playerCount++;
            return;
        }
        Player buf = head;
        while(buf.getNext()!=head)
        {
            buf = buf.getNext();
        }
        //inserting player between the prev last player and player 1 which is denoted as head
        buf.setNext(player);
        player.setPrev(buf);
        head.setPrev(player);
        player.setNext(head);
        playerCount++;
    }
    private void randomInsert(int x, int time) //this function inserts player while the game is on play and there has to be more than one player in the game
    {
        if(time < passTime)
        {
            System.out.println("Invalid Time input! Time inputs should be in increasing order! Try again!");
            return;
        }
        if(x<=0)
        {
            System.out.println("Can't insert this player! Reflex time must be greater than 0");
            return;
        }
        if(playerCount>1)
        {
            updateTimeTrac(time); //this function is called to update the time and passing direction
            Player player = createPlayer(++players,x);
            if(passDirectionClockWise) //new player is inserted in the opposite of passing direction
            {
                //System.out.println("Current Player: "+currentPlayer.getPlayerNo());
                player.setPrev(currentPlayer.getPrev());
                player.setNext(currentPlayer);
                currentPlayer.setPrev(player);
                player.getPrev().setNext(player);
            }else
            {
                player.setNext(currentPlayer.getNext());
                player.setPrev(currentPlayer);
                currentPlayer.setNext(player);
                player.getNext().setPrev(player);
            }
            playerCount++;
        }

    }
    private void changeDirection(int time)
    {
        if(playerCount>1)
        {
            updateTimeTrac(time);
            if(passDirectionClockWise) //reversing the passing direction
            {
                passDirectionClockWise = false;
                return;
            }
            if(!passDirectionClockWise)
            {
                passDirectionClockWise = true;
                return;
            }
        }
    }
    private void currentlyHolding(int time)
    {
        if(time < passTime)
        {
            System.out.println("Invalid Time input! Time inputs should be in increasing order! Try again!");
            return;
        }
        updateTimeTrac(time);
        System.out.println("Player "+currentPlayer.getPlayerNo()+" is holding the pillow at t= "+time);
    }
    private void updateTimeTrac(int time)
    {
        while((passTime +currentPlayer.getReflexTime())<time)
        {
            passTime += currentPlayer.getReflexTime() ;
            if(passDirectionClockWise)
            {
                currentPlayer = currentPlayer.getNext();
            }else
            {
                currentPlayer = currentPlayer.getPrev();
            }
        }
    }
    private void changeMusic(int time)
    {
        if(time < passTime)
        {
            System.out.println("Invalid Time input! Time inputs should be in increasing order! Try again!");
            return;
        }
        if(playerCount>1)
        {
            updateTimeTrac(time);
            System.out.println("Previous Player of Currently pillow holder: "+currentPlayer.getPrev().getPlayerNo());
            System.out.println("Next Player of Currently pillow holder: "+currentPlayer.getNext().getPlayerNo());
            passTime = time;
            System.out.println("Player "+currentPlayer.getPlayerNo()+" has been eliminated at t= "+time);
            playerCount--;
            Player buf = currentPlayer; // Storing the eliminated player
            if(passDirectionClockWise)
                currentPlayer = currentPlayer.getNext();
            else
                currentPlayer = currentPlayer.getPrev();
            buf.getPrev().setNext(buf.getNext()); //creating next to next link between the prev and later player of buf ( eliminated player )
            buf.getNext().setPrev(buf.getPrev()); //creating prev to prev link between the prev and later player of buf (eliminated player )
        }

    }
    private void endGame(int time)
    {
        if(time < passTime)
        {
            System.out.println("Invalid Time input! Time inputs should be in increasing order! Try again!");
            return;
        }
        if(playerCount>1)
        {
            updateTimeTrac(time);
            System.out.print("Game over : Player "+currentPlayer.getPlayerNo()+" is holding the pillow at t= "+time+", pillow passing sequence = Player ");
            print(); //prints the passing sequence at time "time"
        }else
        {
            System.out.println("Game over: Player "+currentPlayer.getPlayerNo()+" wins!!");
        }

    }
    private void print()
    {
        Player buf = currentPlayer;
        if(passDirectionClockWise)
        {
            while(buf.getNext()!=currentPlayer)
            {
                System.out.print(buf.getPlayerNo()+", ");
                buf = buf.getNext();
            }
            System.out.println(buf.getPlayerNo());
        }else
        {
            while(buf.getPrev()!=currentPlayer)
            {
                System.out.print(buf.getPlayerNo()+", ");
                buf = buf.getPrev();
            }
            System.out.println(buf.getPlayerNo());
        }
    }
    private void gameCommand(String time, String command)
    {
        int t;
        try{
            t=Integer.parseInt(time);
        }catch(NumberFormatException e)
        {
            System.out.println("First part of your input should be a positive integer! Try again!");
            return;
        }
        switch (command) {
            case "P":
                currentlyHolding(t);
                break;
            case "M":
                changeMusic(t);
                break;
            case "R":
                changeDirection(t);
                break;
            case "F":
                endGame(t);
                break;
            default:
                System.out.println("Invalid command!");
                break;
        }
    }
    private void gameCommand(String time, String command, int reflexTime)
    {
        int t;
        try{
            t=Integer.parseInt(time);
        }catch(NumberFormatException e)
        {
            System.out.println("First part of your input should be a positive integer! Try again!");
            return;
        }
        if(command.equals("I"))
            randomInsert(reflexTime,t);
        else
            System.out.println("Invalid command!");
    }
}