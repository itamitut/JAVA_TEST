package ru.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase{

    //  Данные из файла csv
       @DataProvider
       public Iterator<Object[]> validGroupsFromCsv() throws IOException {
           List<Object[]> list = new ArrayList<>();
           try (BufferedReader reader = new BufferedReader( new FileReader( new File( "src/test/resources/groups.csv" ) ) )) {
               String line = reader.readLine();
               while (line != null) {
                   String[] split = line.split( ";" );
                   list.add( new Object[]{new GroupData().withName( split[0] ).withHeader( split[1] ).withFooter( split[2] )} );
                   line = reader.readLine();
               }
               return list.iterator();
           }
       }
    //  Данные из файла xml
    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader( new FileReader( new File( "src/test/resources/groups.xml" ) ) )) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations( GroupData.class );
            List<GroupData> groups = (List<GroupData>) xstream.fromXML( xml );
            return groups.stream().map( (g) -> new Object[]{g} ).collect( Collectors.toList() ).iterator();
        }
    }
    //  Данные из файла json
    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader( new FileReader( new File( "src/test/resources/groups.json" ) ) )) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson( json, new TypeToken<List<GroupData>>() {
            }.getType() );
            return groups.stream().map( (g) -> new Object[]{g} ).collect( Collectors.toList() ).iterator();
        }
    }
    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create( group );
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream()
                        .mapToInt((g) -> g.getId()).max().getAsInt()))));

        /*      Cравнение упорядоченных списков:
        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt( GroupData::getId );
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
*/

    }

}
