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
        this.points = points;
        this.segments = new LineSegment[points.length/4];
        this.totalSegments = 0;
    }

    public int numberOfSegments(){
        return this.totalSegments;
    }

    public LineSegment[] segments(){
       for(int i=0;i<points.length-1;i++){
           for(int j=i+1; j<points.length-2;j++){
               for(int k=j+1; k<points.length-3;k++){
                   for(int h = k+1; h<points.length-4;k++){
                       if(points[i] == null || points[j] == null || points[k] == null || points[h] == null){
                           throw new IllegalArgumentException("You can't have null's points");
                       }
                       double aSegment = points[i].slopeTo(points[j]);
                       double bSegment = points[j].slopeTo(points[k]);
                       double cSegment = points[k].slopeTo(points[h]);
                       if((aSegment == bSegment) && ( bSegment == cSegment)){
                            segments[this.totalSegments] = new LineSegment(points[i],points[k]);

                       }

                   }
               }
           }
       }
    }
}
