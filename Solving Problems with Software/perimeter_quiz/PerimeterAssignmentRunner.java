import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int i = 0;
        for(Point currPt : s.getPoints()) {
        i++;
        }
        return i;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double averageLength = getPerimeter(s)/getNumPoints(s);
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double currDist = 0.0;
        for(Point currPt : s.getPoints()){
            double newDist = prevPt.distance(currPt);
            if(newDist > currDist){
                currDist = newDist;
            }
            prevPt = currPt;
        }
        return currDist;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double maxX = 0.0;
        for(Point currPt : s.getPoints()){
            if(currPt.getX() > maxX){
                maxX = currPt.getX();
            }
            prevPt = currPt;
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largest = 0.0;
        for(int i = 0; i < 4; i++){
            FileResource fr = new FileResource();
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            System.out.println("Perimeter = " + length);
            if(length > largest){
                largest = length;
            }
        }
        return largest;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int points = getNumPoints(s);
        double average = getAverageLength(s);
        double largest = getLargestSide(s);
        double x = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + points);
        System.out.println("average length =" + average);
        System.out.println("largest side = " + largest);
        System.out.println("largest X = " + x);
        double perimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + perimeter);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
