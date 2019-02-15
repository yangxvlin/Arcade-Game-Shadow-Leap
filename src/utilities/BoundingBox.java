/**
 * BoundingBox complete class for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 */
package utilities;

import org.newdawn.slick.Image;

public final class BoundingBox {
	private float left;
	private float top;
	private float width;
	private float height;
	
	public BoundingBox(float x, float y, float width, float height) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
	}
	public BoundingBox(Image img, float x, float y) {
		setWidth(img.getWidth());
		setHeight(img.getHeight());
		setX(x);
		setY(y);
	}
	public BoundingBox(BoundingBox bb) {
		width = bb.width;
		height = bb.height;
		left = bb.left;
		top = bb.top;
	}

	/*
	 * Sets the x and y position at the centre of the bounding box.
	 */
	public void setX(float x) {
		left = x - width / 2;
	}
	public void setY(float y) {
		top = y - height / 2;
	}
	
	public void setWidth(float w) {
		width = w;
	}
	public void setHeight(float h) {
		height = h;
	}
	
	public float getLeft() {
		return left;
	}
	public float getTop() {
		return top;
	}
	public float getRight() {
		return left + width;
	}
	public float getBottom() {
		return top + height;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}

    /* ************************* customized methods ************************* */
    /**
     * return true if two boundingBox intersects
     * @param other the other boundingBox to check with
     * @param edgesInclusive include edge to check or not
	 * @return true if two boundingBox intersects
     * */
    public boolean intersects(BoundingBox other, boolean edgesInclusive) {
        if (edgesInclusive) {
            return !(other.left          > getRight()
                    || other.getRight()  < left
                    || other.top         > getBottom()
                    || other.getBottom() < top);
        } else {
            return !(other.left          >= getRight()
                    || other.getRight()  <= left
                    || other.top         >= getBottom()
                    || other.getBottom() <= top);
        }
    }

    /**
     * return true if one BoundingBox contains another
     * @param other the other BoundingBox to check with in current Box
     * @param edgesInclusive include edge to check or not
	 * @return true if one BoundingBox contains another
     * */
	public boolean contains(BoundingBox other, boolean edgesInclusive) {
	    if (edgesInclusive) {
            return ((other.left        >= left) &&
                    (other.getRight()  <= getRight()) &&
                    (other.top         >= top) &&
                    (other.getBottom() <= getBottom()));
        } else {
            return ((other.left        >= left) &&
                    (other.getRight()  <= getRight()) &&
                    (other.top         >= top) &&
                    (other.getBottom() <= getBottom()));
        }
    }

    /**
     * return true if current boundingBox's right is at left of the other boundingBox's left
     * @param other the other boundingBox to check with
     * @param edgesInclusive include edge to check or not
	 * @return true if current boundingBox's right is at left of the other boundingBox's left
     * */
    public boolean atRight(BoundingBox other, boolean edgesInclusive) {
        if (edgesInclusive) {
            return (this.getRight() >  other.left);
        } else {
            return (this.getRight() >= other.left);
        }
    }

    /**
     * return true if current boundingBox's left is at right of the other boundingBox's right
     * @param other the other boundingBox to check with
     * @param edgesInclusive include edge to check or not
	 * @return true if current boundingBox's left is at right of the other boundingBox's right
     * */
    public boolean atLeft(BoundingBox other, boolean edgesInclusive) {
        if (edgesInclusive) {
            return (this.left <  other.getRight());
        } else {
            return (this.left <= other.getRight());
        }
    }

    /**
	 * return the copy of current BoundingBox
	 * @return the copy of current BoundingBox
	 * */
    public BoundingBox copyOf() {
	    return new BoundingBox(this);
    }
}

/* java is fun! */