package ru.spbstu.main.shapes;

/**
 * Представление о треугольнике.
 * <p>
 * Треуго́льник (в евклидовом пространстве) — геометрическая
 * фигура, образованная тремя отрезками, которые соединяют
 * три точки, не лежащие на одной прямой. Указанные три
 * точки называются вершинами треугольника, а отрезки —
 * сторонами треугольника. Часть плоскости, ограниченная
 * сторонами, называется внутренностью треугольника: нередко
 * треугольник рассматривается вместе со своей внутренностью
 * (например, для определения понятия площади).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%A2%D1%80%D0%B5%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Треугольник</a>
 */
public class Triangle implements Polygon {

  private float a_, b_, c_ = 0;
  private float x_, y_ = 0;

  private int angle_ = 0;

  public Triangle() {
    this.a_ = 1;
    this.b_ = 1;
    this.c_ = 1;
  }

  public Triangle(float x, float y, float a, float b, float c) {
    if ((a < 0 && b < 0 && c < 0) || ((a > b + c) || (b > a + c) || (c > a + b))) {
      throw new IllegalArgumentException("Incorrect triangle");
    }

    this.a_ = a;
    this.b_ = b;
    this.c_ = c;

    this.y_ = y;
    this.x_ = x;
  }

  @Override
  public float getX() {
    return x_;
  }

  @Override
  public float getY() {
    return y_;
  }

  @Override
  public float getArea() {
    float p = getPerimeter() / 2;
    return (float) Math.sqrt(p * (p - a_) * (p - b_) * (p - c_));
  }

  @Override
  public void rotate(int angle) {
    angle_ += angle;
  }

  @Override
  public int getRotation() {
    return angle_;
  }

  @Override
  public float getPerimeter() {
    return a_ + b_ + c_;
  }
}
/*
 * TODO: Реализовать класс 'Triangle'
 * 1. Используйте наследование.
 * 2. Реализуйте все абстрактные методы.
 */
