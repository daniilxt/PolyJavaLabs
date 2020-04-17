package ru.spbstu.main;

import java.awt.*;
import java.util.logging.Logger;

import org.jetbrains.annotations.NotNull;
import ru.spbstu.main.shapes.Circle;
import ru.spbstu.main.shapes.Rectangle;
import ru.spbstu.main.shapes.Shape;
import ru.spbstu.main.shapes.Triangle;


public class Main {
  private final static Logger logger = Logger.getLogger(Main.class.getName());

  public static void maxAreaOfArrayShapes(@NotNull Shape[] shapes) {
    float maxArea = 0;
    for (Shape i : shapes) {
      System.out.println(i.getArea());
      if (i.getArea() > maxArea) maxArea = i.getArea();
    }
    System.out.println("Max Area is: " + maxArea);
  }

  public static void main(String[] args) {

    Circle circle1 = new Circle(45, 14, 6);
    Circle circle2 = new Circle(53, 4, 3);
    Circle circle3 = new Circle(5, 4, 8);

    //Triangle tr1 = new Triangle(1,4, 44,3,4); // для ошибки
    Triangle triangle1 = new Triangle(17, 3, 5, 3, 7);
    Triangle triangle2 = new Triangle(21, 33, 15, 16, 14);
    Triangle triangle3 = new Triangle(-1, 3, 5, 6, 4);

    Rectangle rect1 = new Rectangle(1, -3, 20, 10);
    Rectangle rect2 = new Rectangle(2, 4, 44, 67);
    Rectangle rect3 = new Rectangle(1, 4, 8, 14);
    Rectangle square = new Rectangle(0, 0, 121, 121);



    Shape[] shapes = {circle1, circle2, circle3, rect1, rect2, rect3, triangle1, triangle2, triangle3, square};
    maxAreaOfArrayShapes(shapes);

  }

  /*
   * TODO: Выполнить действия над массивом 'shapes'
   *
   * 1. Проинициализировать переменную 'shapes' массивом
   *    содержащим 10 произвольных фигур. Массив должен
   *    содержать экземпляры классов Circle, Rectangle
   *    и Triangle.
   *
   * 2. Найти в массиве 'shapes' фигуру с максимальной
   *    площадью. Для поиска фигуры необходимо создать
   *    статический метод в текущем классе (Main).
   */
}

