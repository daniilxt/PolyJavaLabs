package ru.spbstu.main.shapes;

/**
 * Представление об окружности.
 * <p>
 * Окру́жность — замкнутая плоская кривая, которая состоит из
 * всех точек на плоскости, равноудалённых от заданной точки.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9E%D0%BA%D1%80%D1%83%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D1%8C">Окружность</a>
 */
public class Circle implements Ellipse, Point {
  float radius_;
  float x_, y_ = 0;

  Circle() {
    this.radius_ = 1;
  }

  public Circle(float x, float y, float r) {
    if (r < 0) {
      throw new IllegalArgumentException("Incorrect circle");
    }
    this.radius_ = r;
    this.x_ = x;
    this.y_ = y;
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
    return (float) (Math.PI * radius_ * radius_);
  }

  @Override
  public float getLength() {
    return (float) (2 * Math.PI * radius_);
  }

  /*
   * TODO: Реализовать класс 'Circle'
   *
   * |\__|\
   * (O_O)
   * E[][][]3
   * (_)(_)
   *
   * 1. Используйте наследование.
   * 2. Реализуйте все абстрактные методы.
   */
}
