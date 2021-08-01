package mario_break;

import java.awt.Image;
import java.awt.Rectangle;

//모든 이미지를 사각형으로 받아들이기 위해 만든 클래스 - 충돌처리를 위해
public class RectangleShape {
	 
	int x;
	int y;
	int shapeW;
	int shapeH;
	Image shape;
	
	protected void setX(int x) {
		this.x=x; }
	int finalX() {
		return x; }
	
	protected void setY(int y) {
		this.x=y; }
	int finalY() {
		return y; }
	
	int shapewidth() {
		return shapeW; }
	int shapeheight() {
		return shapeH; }
	Image shapepic() {
		return shape; }
	
	Rectangle getRect() {
		return new Rectangle(x,y,shape.getWidth(null), shape.getHeight(null));
	}
	
	void shapesize() {
		shapeW= shape.getWidth(null);
		shapeH= shape.getHeight(null);
	}
	

}
