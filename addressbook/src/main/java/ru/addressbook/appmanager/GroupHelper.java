package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Сергей on 11.04.2018.
 */
public class GroupHelper extends HelperBase {


    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
    public void initGroupCreation() {click(By.name("new"));}
    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }
    public void initGroupModification() {click(By.name("edit"));}
    public void submitGroupModification() {click(By.name("update"));}
    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
        groupCash = null;
    }

    public void modify( GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
        groupCash = null;
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
         for(WebElement element : elements){
             String name = element.getText();
             int id = Integer.parseInt( element.findElement(By.tagName("input")).getAttribute("value") );
             groups.add(new GroupData().withId(id).withName(name));
         }
        return groups;
    }
    public Groups groupCash = null;
//Создание множества с кешированием
    public Groups all() {
        if (groupCash != null){
            return new Groups(groupCash);
        }
        groupCash = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for(WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt( element.findElement(By.tagName("input")).getAttribute("value") );
            groupCash.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCash);
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        groupCash = null;
        returnToGroupPage();
    }

    private void selectGroupById(int id) {
            wd.findElement( By.cssSelector("input[value='"+id+"']")).click();
    }
}
