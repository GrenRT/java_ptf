package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;

/**
 * Created by razgonyaev on 01.02.2017.
 */
public class JamesHelper {

  private ApplicationManager app;

  private TelnetClient telnet;
  private InputStream in;
  private PrintStream out;

  private Session mailSession;
  private Store store;
  private String mailserver;

  public JamesHelper(ApplicationManager applicationManager) {
    this.app = app;
    telnet = new TelnetClient();
    mailSession = Session.getDefaultInstance(System.getProperties());
  }
  public boolean doesUserExist(String name) {
    initTelnetSession();
    write("verify " + name);
    String result = readUntil("exist");
    closeTelnetSession();
    return result.trim().equals("User " + name + " exist");
  }

  public void createUser(String name, String passwd) {
    initTelnetSession();
    write("adduser " + name + " " + passwd);
    String result = readUntil("User " + name + " added");
    closeTelnetSession();
  }

  public void deleteUser(String name) {
    initTelnetSession();
    write("deluser " + name);
    String result = readUntil("User " + name + " deleted");
    closeTelnetSession();
  }

  private void initTelnetSession() {
    mailserver = app.getProperties("mailserver.host");
    int port = Integer.parseInt(app.getProperties("mailserver.port"));
    String login = app.getProperties("mailserver.adminlogin");
    String password = app.getProperties("mailserver.adminpassword");

    try {
      telnet.connect(mailserver, port);
      in = telnet.getInputStream();
      out = new PrintStream(telnet.getOutputStream());

    } catch (Exception e) {
      e.printStackTrace();
    }

    readUntil("Login id:");
    write("");
    readUntil("Password");
    write("");

    readUntil("Login id:");
    write(login);
    readUntil("Password");
    write(password);

    readUntil("Welcome " + login + ". Help for a lost of commands");
  }

  private String readUntil(String pattern) {
    try {
      char lastChar = pattern.charAt(pattern.length()) - 1);
    }

  }

  private void write(String s) {

  }
}
