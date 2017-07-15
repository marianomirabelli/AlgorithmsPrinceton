import edu.princeton.cs.algs4.StdArrayIO;
import edu.princeton.cs.algs4.StdStats;

import java.util.*;

/**
 * Created by Mariano on 1/7/17.
 */
public class BruteCollinearPoints {

    private Point [] points;
    private Stack<Point> headers;
    private ArrayList<LineSegment> segments;
    private LineSegment [] arraySegments;
    private int totalSegments;

    public BruteCollinearPoints(Point[] points){
        if(points == null){
            throw new IllegalArgumentException("The input array can't be null");
        }

        for(int  i = 0; i < points.length; i++){
            if(points[i] == null){
                throw new IllegalArgumentException("Is not possible add null values");
            }

            for(int j = i+1; j< points.length; j++){
                if(points[j] == null){
                    throw new IllegalArgumentException("Is not possible add null values");
                }

                if(points[i].compareTo(points[j]) == 0){
                    throw new IllegalArgumentException("Is not possible add duplicated points");
                }
            }

        }

        this.points = points;
        this.headers = new Stack<Point>();
        this.segments = new ArrayList<LineSegment>((this.points.length*this.points.length-1)/2);
    }

    public int numberOfSegments(){
        return this.segments.size();
    }

    public LineSegment[] segments(){
        LinkedList<Point> collinearPoints;
        Point[] copy = Arrays.copyOf(this.points,this.points.length);
        for(int i=0;i <= copy.length-1;i++){
           for(int j=i+1; j <= copy.length-1;j++){
               double slopePtoQ = copy[i].slopeTo(copy[j]);
               for(int k=j+1; k <= copy.length-1;k++){
                   double slopeQtoR = copy[j].slopeTo(copy[k]);
                   if(slopePtoQ == slopeQtoR){
                       for(int h = k+1; h <= copy.length-1;h++){
                           double slopeRtoS = copy[k].slopeTo(copy[h]);
                           if(slopeRtoS == slopeQtoR){
                               collinearPoints  = new LinkedList<Point>();
                               collinearPoints.add(copy[i]);
                               collinearPoints.add(copy[j]);
                               collinearPoints.add(copy[k]);
                               collinearPoints.add(copy[h]);
                               this.segments.add(new LineSegment(collinearPoints.getFirst(),collinearPoints.getLast()));
                           }
                       }
                   }
               }
           }
        }
        arraySegments = new LineSegment[this.segments.size()];
        return this.segments.toArray(arraySegments);
    }

}
