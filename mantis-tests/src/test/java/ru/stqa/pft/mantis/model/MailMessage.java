package ru.stqa.pft.mantis.model;

/**
 * Created by gren on 28.01.2017.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }

}
