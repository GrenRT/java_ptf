package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import ru.stqa.pft.rest.application.RestHelper;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by razgonyaev on 01.12.2016.
 */
public class TestBase {
  RestHelper rest = new RestHelper();

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }


  public void skipIfNotFixed(int issueId) throws IOException {
    if (!isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
  private boolean isIssueOpen(int issueId) throws IOException {
    if (getIssueStatus(issueId).iterator().next().getState_name().equals("Closed")) {
      return true;
    } else {
      return false;
    }
  }

  private Set<Issue> getIssueStatus(int issueId) throws IOException {
    String json = rest.getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

}
