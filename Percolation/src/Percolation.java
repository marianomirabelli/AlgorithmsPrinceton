import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Mariano on 3/6/17.
 */
public class Percolation {

    private boolean [][] grid;
    private int numberOfOpenSites;
    private int numberOfElementsPerRowAndCol;
    private int totalElements;
    private WeightedQuickUnionUF unionUF;

    public Percolation(int n){
        if(n <= 0){
            throw new IllegalArgumentException("The n value must be greather than 0");
        }
        this.numberOfElementsPerRowAndCol = n;
        this.totalElements = this.numberOfElementsPerRowAndCol*this.numberOfElementsPerRowAndCol;
        this.numberOfOpenSites = 0;
        this.grid = new boolean[n+1][n+1];
        this.unionUF = new WeightedQuickUnionUF(this.totalElements+2);
    }

    public void open(int row, int col){
        if(isValidRowColumn(row,col)){
            if(!isOpen(row,col)){
                grid[row][col] = true;
                int rowMinusOne = row-1;
                int rowPlusOne = row +1;
                int colMinusOne = col-1;
                int colPlusOne = col+1;
                int openedPosition = translate2DTo1D(row,col);

                if(row==1){
                    unionUF.union(0,openedPosition);
                    unionUF.connected(0,openedPosition);
                }

                if(row==numberOfElementsPerRowAndCol){
                    unionUF.union(this.totalElements+1,openedPosition);
                }

                if(row!=1 && isValidPosition(rowMinusOne)){
                    if(isOpen(rowMinusOne,col)){
                        unionUF.union(openedPosition,translate2DTo1D(rowMinusOne,col));
                    }
                }
                if(row!=numberOfElementsPerRowAndCol && isValidPosition(rowPlusOne)){
                    if(isOpen(rowPlusOne,col)){
                        unionUF.union(openedPosition,translate2DTo1D(rowPlusOne,col));
                    }
                }
                if(isValidPosition(colMinusOne)){
                    if(isOpen(row,colMinusOne)){
                        unionUF.union(openedPosition,translate2DTo1D(row,colMinusOne));
                    }
                }
                if(isValidPosition(colPlusOne)){
                    if(isOpen(row,colPlusOne)){
                        unionUF.union(openedPosition,translate2DTo1D(row,colPlusOne));
                    }
                }
                numberOfOpenSites++;
            }

        }

    }

    public boolean isOpen(int row, int col){
        boolean isOpen = false;
        if(isValidRowColumn(row,col)){
            isOpen =  grid[row][col];
        }
        return isOpen;
    }

    public boolean isFull(int row, int col){
        boolean isFull = false;
        if(isValidRowColumn(row,col)){
           return unionUF.connected(0,translate2DTo1D(row,col));
        }
        return isFull;

    }

    public int numberOfOpenSites(){

        return numberOfOpenSites;
    }

    public boolean percolates(){

        return unionUF.connected(0,this.totalElements+1);

    }



    private boolean isValidRowColumn(int row, int column){
        if( (row> numberOfElementsPerRowAndCol || row <=0) || (column > numberOfElementsPerRowAndCol || column <=0)){
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    private boolean isValidPosition(int index) {

        if(index> numberOfElementsPerRowAndCol || index <=0){
            return false;
        }else{
            return true;
        }

    }

    private int translate2DTo1D(int row, int col){
        return (row-1)* numberOfElementsPerRowAndCol + (col-1);
    }


    public static void main(String [] args){
        Percolation percolation = new Percolation(3);
    }
}
