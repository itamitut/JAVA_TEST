package ru.mantis.test;

import org.testng.annotations.Test;
import ru.mantis.model.Issue;
import ru.mantis.model.Project;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class SoapTest extends TestBase {

  @Test
  public void testGetProgects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }


  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test Issue")
            .withDescription("Test Issue Description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(created.getSummary(), issue.getSummary());

  }

  @Test
  public void testGetIssues() throws RemoteException, ServiceException, MalformedURLException {
    int id = 0000003;
    skipIfNotFixed(id);
    System.out.println("catch");
  }
}