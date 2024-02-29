package net.walksanator.uxncraft.math;

import net.minecraft.util.math.Vec2f;

import java.util.Objects;

public final class Vec2i {

    public static final Vec2i ORIGIN = new Vec2i(0, 0);

    public final int x;
    public final int y;

    private float length = Float.NaN;
    private float lengthSq = Float.NaN;
    private Vec2 normalized;
    private Vec2i negated;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i add(Vec2i other) {
        return new Vec2i(this.x + other.x, this.y + other.y);
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    public Vec2i sub(Vec2i other) {
        return new Vec2i(this.x - other.x, this.y - other.y);
    }

    public Vec2 sub(Vec2 other) {
        return new Vec2(this.x - other.x, this.y - other.y);
    }

    public Vec2i mul(Vec2i other) {
        return new Vec2i(this.x * other.x, this.y * other.y);
    }

    public Vec2 mul(Vec2 other) {
        return new Vec2(this.x * other.x, this.y * other.y);
    }

    public Vec2i mul(int other) {
        return new Vec2i(this.x * other, this.y * other);
    }

    public Vec2 mul(float other) {
        return new Vec2(this.x * other, this.y * other);
    }

    public Vec2i div(Vec2i other) {
        return new Vec2i(x / other.x, this.y / other.y);
    }

    public Vec2 div(Vec2 other) {
        return new Vec2(x / other.x, this.y / other.y);
    }

    public Vec2i div(int other) {
        return new Vec2i(this.x / other, this.y / other);
    }

    public Vec2 div(float other) {
        return new Vec2(this.x / other, this.y / other);
    }

    public float dot(Vec2i other) {
        return this.x * other.x + this.y * other.y;
    }

    public float getLength() {
        if (Float.isNaN(length)) {
            length = (float) Math.sqrt(getLengthSq());
        }

        return length;
    }

    public float getLengthSq() {
        if (Float.isNaN(lengthSq)) {
            lengthSq = x * x + y * y;
        }

        return lengthSq;
    }

    public Vec2 getNormalized() {
        if (normalized == null) {
            normalized = new Vec2(x / getLength(), y / getLength());
            normalized.length = 1;
            normalized.lengthSq = 1;
            normalized.normalized = normalized;
        }

        return normalized;
    }

    public Vec2i negate() {
        if (negated == null) {
            negated = new Vec2i(-x, -y);
            negated.length = length;
            negated.lengthSq = lengthSq;
            negated.negated = this;
        }

        return negated;
    }

    public Vec2 toVec2() {
        return new Vec2(x, y);
    }

    public Vec2f toVec2f() {
        return new Vec2f(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2i vec2 = (Vec2i) o;
        return vec2.x == x &&
            vec2.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("Vec2i(%d, %d)", x, y);
    }

}
