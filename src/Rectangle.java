public class Rectangle {
  private double width;
  private double height;

  public Rectangle(double width, double height) {
    this.width = width;
    this.height = (int) height;
  }

 // Override the compareTo method defined in Comparable
  public int compareTo(Rectangle o) {
    if (getHeight() > o.getHeight())
      return 1;
    else if (getHeight() < o.getHeight())
      return -1;
    else
      return 0;
  }
  
  @Override // Implement the toString method in GeometricObject
  public String toString() {
    return "Width: " + getWidth() + " Height: " + getHeight();
  }
  public double getWidth() {
    return width;
  }
  public double getHeight() {
    return height;
  }
}