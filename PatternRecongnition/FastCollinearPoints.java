import java.util.Arrays;

/**
 * Created by Mariano on 8/7/17.
 */
public class FastCollinearPoints {


    private Point [] points;
    private int totalSegments;
    private LineSegment[] segments;


    public FastCollinearPoints(Point[] points){

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

        this.totalSegments = 0;
        this.points = points;
        this.segments = new LineSegment [this.points.length/4];

    }

    public int numberOfSegments() {

        return totalSegments;

    }

    public LineSegment[] segments() {

        Point [] slopes;
        for(int i=0; i< this.points.length ; i++){
            slopes = new Point [this.points.length];
            Arrays.sort(slopes,points[i].slopeOrder());
            for(int j = 0; j < slopes.length-1; j++){
                if(slopes[j].slopeTo(points[i]) != slopes[j+1].slopeTo(points[i])){
                    this.segments[this.totalSegments] = new LineSegment(points[i],points[j]);
                    this.totalSegments++;
                    break;
                }
            }
        }

        return null;

    }


}
