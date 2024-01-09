/**
 * 
 * Rishika Gautam 
 * 
 */
public class MazeSolver
{
    /**
     * Provides a solution for a given maze, if possible. A solution is a path from the start cell
     * to the finish cell (inclusive). If there is no solution to the maze then returns the static
     * instance {@link Path#NO_PATH}. If the maze is perfect then there must be only one solution.
     *
     * @param maze the maze to solve
     * @return a solution for the maze or {@link Path#NO_PATH} if there is no solution
     */
	
	//created a helper class that stores information about the cells in the maze
	//stores information about the current cell itself and the path taken to reach that current cell
	public class MazeInfo{
		//private field to store the cell and path
		private Cell cell;
		private Path path;
		
		public MazeInfo(Cell cell, Path path) {
			this.cell = cell;
			this.path = new Path();
			//for each loop to add the path that is in the parameter to the new path
			for(Cell c: path) {
				this.path.addLast(c);
			}
			//adding the current cell to the end of the path - stores the path to reach the current cell
			this.path.addLast(cell);
			
		}
		
		//returning the current cell
		public Cell getCell() {
			return cell;
		}
		
		//returning the path to reach the current cell
		public Path getPath() {
			return path;
		}
		
		
	}
	
    public Path solve(Maze maze)
    {
    	//setting the solution path to No solution to account for when there is no perfect solution path for a made
    	Path solution = Path.NO_PATH; 
    	
    	//setting 
        Queue<MazeInfo> queue = new Queue<MazeInfo>();
        Cell start  = maze.getStart(); //storing the begging of the solution path
        Cell end = maze.getEnd(); //storing the end of the solution path
        queue.enqueue(new MazeInfo(start, Path.NO_PATH));  //adding the start cell to a possible path
        
       
        //looping until the queue is empty
        while(!(queue.isEmpty())) {
        	MazeInfo curr = queue.dequeue(); //dequeuing the mazeInfo queue and acquiring all information regarding the current cell
        	Cell cell1 = curr.getCell(); //
        	int currX = cell1.getX();
        	int currY = cell1.getY();
        	maze.visit(currX, currY); //marking the cell as visited
        	
        	//if the current cell equal the end of the cell then the solution path had been found
        	if(cell1.equals(end)) {
        		solution = curr.getPath(); //storing the solution path
        		break; //exiting while loop
        	}
		
    
        	//Checking the neighbors and walls to the left
        	if(currX > 0 && !maze.isVisited(currX-1, currY)&& maze.isOpen(currX, currY, Direction.LEFT)) {
        		Cell newCell = new Cell(currX-1, currY); //creating new cell that is the unvisited neighbor in the left direction
        		queue.enqueue(new MazeInfo(newCell, curr.getPath())); //adding this unvisited new cell to the path
        		 
        	}
        	
        	//Checking the neighbors and walls to the right
        	if(currX < maze.size()-1 && !maze.isVisited(currX +1, currY) && maze.isOpen(currX, currY, Direction.RIGHT)) {
        		Cell newCell = new Cell(currX+1, currY); //creating a new cell that is the unvisited neighbor in the right direction
        		queue.enqueue(new MazeInfo(newCell, curr.getPath())); //adding this unvisited new cell to the path
        	}
        	
        	//Checking the neighbors and walls downward
        	if(currY > 0 && !maze.isVisited(currX, currY-1)  && maze.isOpen(currX, currY, Direction.DOWN)) {
        		Cell newCell = new Cell(currX, currY-1); //creating a new cell that is the unvisited neighbor in the downward direction
        		queue.enqueue(new MazeInfo(newCell, curr.getPath())); //adding this unvisited new cell to the path
        	}
        	
        	//Checking the neighbors and walls upward
        	if(currY < maze.size()-1 && !maze.isVisited(currX, currY+1) && maze.isOpen(currX, currY, Direction.UP)) {
        		Cell newCell = new Cell(currX, currY+1); //creating a new cell that is the unvisited neighbor in the upward direction
        		queue.enqueue(new MazeInfo(newCell, curr.getPath())); //adding this unvisited new cell to the path
        	}
        	
    	
        }
		return solution; //returning the final path of the maze
    }

    /**
     * Creates, solves, and draws a sample maze. Try solving mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        // First, generate a new maze.
        int size = 10; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.freeze();

        // Next, solve it!
        MazeSolver solver = new MazeSolver();
        maze.resetVisited();
        Path solutionPath = solver.solve(maze);
        maze.setSolution(solutionPath);

        // This is so we can see which cells were explored and in what order.
        maze.setDrawVisited(true);

        maze.draw();
    }
}
