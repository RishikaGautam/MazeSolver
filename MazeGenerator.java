/**
 * Rishika Gautam
 *
 */
public class MazeGenerator
{
    /**
     * Randomly generates a perfect maze of {@param size}.
     *
     * @param size the size of the maze to generate
     * @return the generated maze
     */
    public Maze generate(int size)
    {	
    	//creating a new maze of the specified size
        Maze m = new Maze(size);
        
        //initializing a stack and starting at (0,0)
        Stack<Cell> stack1 = new Stack<Cell>();
        stack1.push(new Cell(0,0));
        
        //looping through the stack until it is empty
        while(!(stack1.isEmpty())) {
        	
        	//popping the current cell off of the stack and marking it as visited
        	Cell curr = stack1.pop();
        	int currX = curr.getX();
        	int currY = curr.getY();
        	m.visit(currX, currY); //marking the current cell as visited
        	
        	//checking whether the neighbors of the current cells are unvisited or not
        	
        	//storing the number of unvisitedCells
        	int unvisitedCell = 0;
        	//storing all the unvisited directions 
        	Direction[] unvisitedNeighbor = new Direction[4];
        	
        	//Left Direction Neighbor
        	if(currX > 0) {
        		if(!m.isVisited(currX-1, currY)) {
        			unvisitedNeighbor[unvisitedCell] = Direction.LEFT;
        			unvisitedCell++ ;
        		}
        	}
        	
        	//Right Direction Neighbor
        	if(currX < size-1) {
        		if(!m.isVisited(currX+1, currY)) {
        			unvisitedNeighbor[unvisitedCell] = Direction.RIGHT;
        			unvisitedCell++;
        		}
        	}
        	
        	//Upward Direction Neighbor
        	if(currY < size-1) {
        		if(!m.isVisited(currX, currY+1)) {
        			unvisitedNeighbor[unvisitedCell] = Direction.UP;
        			unvisitedCell++;
        		}
        	}
        	
        	//Downward Direction Neighbor
        	if(currY > 0) {
        		if(!m.isVisited(currX, currY-1)) {
        			unvisitedNeighbor[unvisitedCell] = Direction.DOWN;
        			unvisitedCell++;
        		}
        	}

        	
        	//choosing a random unvisited neighbor if there are any unvisited neighbors
        	if(unvisitedCell >0) {
        		//creating an integer value to choose a random index from the array of all existing unvisited neighbors
        		int chosen = (int)(Math.random()* unvisitedCell); 
        		m.removeWall(currX, currY, unvisitedNeighbor[chosen]); //removing wall between current cell and chosen random unvisited neighbor
        		stack1.push(curr); //adding the current cell back into the stack
        		
        		
        		//adding the randomly chosen unvisited neighbor cell back into the stack based on the direction of the cell
        		if(unvisitedNeighbor[chosen] == Direction.LEFT) {
        			stack1.push(new Cell(currX-1, currY));
        		}else if(unvisitedNeighbor[chosen] == Direction.RIGHT) {
        			stack1.push(new Cell(currX+1, currY));
        		}else if(unvisitedNeighbor[chosen] == Direction.UP) {
        			stack1.push(new Cell(currX, currY+1));
        		}else if(unvisitedNeighbor[chosen] == Direction.DOWN) {
        			stack1.push(new Cell(currX, currY-1));
        		}
        		
        	}
        	
        	
        	
        }
        
        
        //randomly choosing a start cell and an end cell
        int startX, startY, endX, endY; 
        do{
            startX = (int)(Math.random()*size); 
            startY = (int)(Math.random()*size);
            endX = (int)(Math.random()*size);
            endY = (int)(Math.random()*size);
        }while((startX==endX) && (startY==endY));//Continuously finding random cell coordinates until the start cell is not equal to end cell
        
        m.setStart(startX, startY); //setting the start of the maze
        m.setEnd(endX, endY); //setting the end of the maze
        
        return m; //returning the maze
        
        
    	
        
    }

    /**
     * Creates and draws a sample maze. Try generating mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        StdRandom.setSeed(34);
        int size = 10; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.draw();
    }
}
