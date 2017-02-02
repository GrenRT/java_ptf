package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by razgonyaev on 02.02.2017.
 */
public class RestAssuredTests {


  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssue();
    Issue newIssue = new Issue().withSubject("Test issue 2").withDescription("New test description");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssue();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  private Set<Issue> getIssue() throws IOException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private int createIssue(Issue newIssue) throws IOException {
    String json = RestAssured.given().parameter("subject", newIssue.getSubject())
                        .parameter("description", newIssue.getDescription()).post("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  //private Executor getExecutor() {
    //return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  //}
}
