import java.util.*;

/**
 * Created by Mariano on 8/7/17.
 */
public class FastCollinearPoints {


    private Point [] points;
    private int totalSegments;
    private LineSegment[] segments;
    private Stack<Point> headers;


    public FastCollinearPoints(Point[] points){

        if(points == null){
            throw new IllegalArgumentException("The input array can't be null");
        }

        for(int  i = 0; i < points.length; i++){
            if(points[i] == null){
                throw new IllegalArgumentException("Is not possible add null values");
            }

            for(int j = i+1; j< points.length; j++){
                if(points[i].compareTo(points[j]) == 0 ){
                    throw new IllegalArgumentException("Is not possible add duplicated points");
                }
            }

        }

        this.totalSegments = 0;
        this.points = points;
        this.segments = new LineSegment [this.points.length/4];
        this.headers = new Stack<Point>();
    }

    public int numberOfSegments() {
        return totalSegments;
    }

    public LineSegment[] segments() {
        Point [] copyPoints = Arrays.copyOf(points,points.length);
        LinkedList<Point> collinearPoints;
        for(Point p : points){
            Arrays.sort(copyPoints,p.slopeOrder());
            collinearPoints = new LinkedList<Point>();
            double previousSlope = copyPoints[1].slopeTo(p);
            collinearPoints.add(copyPoints[1]);
            for(int i = 2; i < copyPoints.length-1;i++){
               Point q = copyPoints[i];
               double currentSlope = q.slopeTo(p);
                if(previousSlope  != currentSlope){
                    if(collinearPoints.size()>=3){
                          collinearPoints.add(p);
                          Collections.sort(collinearPoints);
                          if(headers.isEmpty() || headers.pop().compareTo(collinearPoints.getFirst())!=0) {
                              this.segments[this.totalSegments] = new LineSegment(collinearPoints.getFirst(), collinearPoints.getLast());
                              this.totalSegments++;
                          }
                          headers.push(collinearPoints.getFirst());
                     }
                    previousSlope = currentSlope;
                    collinearPoints.clear();
               }
                collinearPoints.add(q);
            }
        }

        return this.segments;

    }




}
