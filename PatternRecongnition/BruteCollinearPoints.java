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
        Arrays.sort(this.points);
        for(int i=0;i <= points.length-1;i++){
           for(int j=i+1; j <= points.length-1;j++){
               double slopePtoQ = points[i].slopeTo(points[j]);
               for(int k=j+1; k <= points.length-1;k++){
                   double slopeQtoR = points[j].slopeTo(points[k]);
                   if(slopePtoQ == slopeQtoR){
                       for(int h = k+1; h <= points.length-1;h++){
                           double slopeRtoS = points[k].slopeTo(points[h]);
                           if(slopeRtoS == slopeQtoR){
                               LinkedList<Point> collinearPoints  = new LinkedList<Point>();
                               collinearPoints.add(points[i]);
                               collinearPoints.add(points[j]);
                               collinearPoints.add(points[k]);
                               collinearPoints.add(points[h]);
                               Collections.sort(collinearPoints);
                               if(headers.isEmpty() || headers.pop().compareTo(collinearPoints.getFirst())!=0){
                                   this.segments.add(new LineSegment(collinearPoints.getFirst(),collinearPoints.getLast()));
                               }

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
