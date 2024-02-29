package net.walksanator.uxncraft.math;

import java.util.Deque;
import java.util.LinkedList;

public class MatStack {

    private Deque<Mat4> stack = new LinkedList<>();

    public MatStack() {
        stack.add(Mat4.IDENTITY);
    }

    public Mat4 mat() {
        return stack.getLast();
    }

    public void push() {
        stack.add(mat());
    }

    public void pop() {
        if (stack.size() < 2) {
            throw new IllegalStateException("stack underflow");
        }
        stack.removeLast();
    }

    public void loadIdentity() {
        stack.removeLast();
        stack.add(Mat4.IDENTITY);
    }

    public void load(Mat4 mat) {
        stack.removeLast();
        stack.add(mat);
    }

    public void translate(float x, float y, float z) {
        stack.add(stack.removeLast().translate(x, y, z));
    }

    public void scale(float x, float y, float z) {
        stack.add(stack.removeLast().scale(x, y, z));
    }

    public void rotate(float x, float y, float z, float angle) {
        stack.add(stack.removeLast().rotate(x, y, z, angle));
    }

    public void translate(Vec3 xyz) {
        stack.add(stack.removeLast().translate(xyz));
    }

    public void mul(Mat4 other) {
        stack.add(stack.removeLast().mul(other));
    }

    public int depth() {
        return stack.size();
    }
}
