package ru.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "ContactCount" )
    public int count;
    @Parameter(names = "-f", description = "Target file" )
    public String file;
    @Parameter(names = "-d", description = "Data format" )
    public String format;

    public static void main(String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander( generator );
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if(format.equals("csv"))
            saveAsCsv(contacts, new File(file));
        else if(format.equals("xml"))
            saveAsXml(contacts, new File(file));
        else if(format.equals("json"))
            saveAsJson(contacts, new File(file));
        else    System.out.println("Unrecognize format" + format);
    }
    private  List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i=0;i<count;i++){
            contacts.add( new ContactData().withLastname(String.format("Фамилия%s", i)).withFirstname(String.format("Имя%s", i)).withMobilePhone(String.format("8903333220%s", i))
            .withPhoneHome(String.format("8495111220%s", i)).withWorkPhone(String.format("8916444550%s", i)).withEmail(String.format("%s%s%s@mail.ru", i,i,i))
            .withPhoto("src/test/resources/stru.png"));
        }
        return contacts;
    }

    private void saveAsJson(List<ContactData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> groups, File file) throws IOException {
        XStream xstream = new XStream();
//  Переименование тега
//        xstream.alias("group", GroupData.class);
        xstream.processAnnotations( ContactData.class );
        String xml = xstream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }



    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {

        Writer writer = new FileWriter(file);
        for(ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s\n",contact.getLastname(),contact.getFirstname(),contact.getMobilePhone(),contact.getHomePhone(),contact.getWorkPhone()));
        }
        writer.close();
    }
}


