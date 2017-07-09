import edu.princeton.cs.algs4.StdArrayIO;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Mariano on 1/7/17.
 */
public class BruteCollinearPoints {

    private Point [] points;
    private LineSegment[] segments;
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
                if(points[i].equals(points[j])){
                    throw new IllegalArgumentException("Is not possible add duplicated points");
                }
            }

        }

        this.points = points;
        this.segments = new LineSegment[points.length/4];
        this.totalSegments = 0;
    }

    public int numberOfSegments(){
        return this.totalSegments;
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
                           if((slopeRtoS == slopeQtoR) && (this.totalSegments < points.length/4)){
                               Point [] collinearPoints  = new Point [4];
                               collinearPoints[0] = points[i];
                               collinearPoints[1] = points[j];
                               collinearPoints[2] = points[k];
                               collinearPoints[3] = points[h];
                               this.segments[this.totalSegments] = new LineSegment(collinearPoints[0],collinearPoints[3]);
                               this.totalSegments++;
                           }
                       }
                   }
               }
           }
        }
        return segments;
    }

}
