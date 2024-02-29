package net.walksanator.uxncraft.math;

import net.minecraft.util.math.Vec2f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Objects;

public final class Vec2 {

    public static final Vec2 ORIGIN = new Vec2(0, 0);

    public final float x;
    public final float y;

    float length = Float.NaN;
    float lengthSq = Float.NaN;
    Vec2 normalized;
    private Vec2 negated;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2i other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    public Vec2 sub(Vec2i other) {
        return new Vec2(this.x - other.x, this.y - other.y);
    }

    public Vec2 sub(Vec2 other) {
        return new Vec2(this.x - other.x, this.y - other.y);
    }

    public Vec2 mul(Vec2i other) {
        return new Vec2(this.x * other.x, this.y * other.y);
    }

    public Vec2 mul(Vec2 other) {
        return new Vec2(this.x * other.x, this.y * other.y);
    }

    public Vec2 mul(int other) {
        return new Vec2(this.x * other, this.y * other);
    }

    public Vec2 mul(float other) {
        return new Vec2(this.x * other, this.y * other);
    }

    public Vec2 div(Vec2i other) {
        return new Vec2(x / other.x, this.y / other.y);
    }

    public Vec2 div(Vec2 other) {
        return new Vec2(x / other.x, this.y / other.y);
    }

    public Vec2 div(int other) {
        return new Vec2(this.x / other, this.y / other);
    }

    public Vec2 div(float other) {
        return new Vec2(this.x / other, this.y / other);
    }

    public float dot(Vec2 other) {
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

    public Vec2 negate() {
        if (negated == null) {
            negated = new Vec2(-x, -y);
            negated.length = length;
            negated.lengthSq = lengthSq;
            negated.negated = this;
        }

        return negated;
    }

    public Vec2i toVec2i() {
        return new Vec2i((int) x, (int) y);
    }

    public Vec2f toVec2f() {
        return new Vec2f(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2 vec2 = (Vec2) o;
        return vec2.x == x &&
            vec2.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("Vec2(%f, %f)", x, y);
    }

    public static Vec2 from(Vec2f vec) {
        return new Vec2(vec.x, vec.y);
    }

}
