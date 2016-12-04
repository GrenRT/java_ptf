package ru.stqa.pft.sandbox;

/**
 * Created by gren on 04.12.2016.
 */
public class Equality {

  public static void main(String[] args) {
    String s1 = "firefox";
    String s2 = "firefox";

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
  }
}
