package TP12;
public class ExempleSchema1 {
	public static void main(String[] args) {
		// 1. Définition des trois points (sommets du triangle) selon la Figure 1
		// Les coordonnées sont extraites des axes : (3,2), (11,4) et (6,9)
		Point p1 = new Point(3.0, 2.0);
		Point p2 = new Point(11.0, 4.0);
		Point p3 = new Point(6.0, 9.0);
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p2, p3);
		Segment s3 = new Segment(p3, p1);
	}
}