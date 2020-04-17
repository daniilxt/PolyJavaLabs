package ru.spbstu.main.shapes;

/**
 * Представление о прямоугольнике.
 * <p>
 * Прямоугольник — четырехугольник, у которого все углы
 * прямые (равны 90 градусам).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D1%8F%D0%BC%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Прямоугольник</a>
 */
public class Rectangle implements Polygon {
  private float x_, y_ = 0;
  private float width_, height_ = 0;
  private float angle_ = 0;

  public Rectangle() {
    this.width_ = 1;
    this.height_ = 1;
  }

  public Rectangle(float x, float y, float width, float height) {
    if (!(width > 0 && height > 0)) {
      throw new IllegalArgumentException("Incorrect triangle");
    }
    this.width_ = width;
    this.height_ = height;

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
  public float getPerimeter() {
    return 2 * (width_ + height_);
  }

  @Override
  public float getArea() {
    return width_ * height_;
  }

  @Override
  public int getRotation() {
    return (int) angle_;
  }

  @Override
  public void rotate(int angle) {
    angle_ += angle;
  }
}
/*
 * TODO: Реализовать класс 'Rectangle'
 * 1. Используйте наследование.
 * 2. Реализуйте все абстрактные методы.
 */
