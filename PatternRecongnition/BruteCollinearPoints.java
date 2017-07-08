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
        this.segments = new LineSegment[points.length];
        this.totalSegments = 0;
    }

    public int numberOfSegments(){
        return this.totalSegments;
    }

    public LineSegment[] segments(){
       for(int i=0;i<points.length-4;i++){
           for(int j=i+1; j<points.length-3;j++){
               for(int k=j+1; k<points.length-2;k++){
                   for(int h = k+1; h<points.length-1;h++){
                       double aSegment = points[i].slopeTo(points[j]);
                       double bSegment = points[j].slopeTo(points[k]);
                       double cSegment = points[k].slopeTo(points[h]);
                       if((aSegment == bSegment) && ( bSegment == cSegment)){
                            segments[this.totalSegments] = new LineSegment(points[i],points[k]);
                            this.totalSegments++;
                       }
                   }
               }
           }
        }
        return segments;
    }
}
