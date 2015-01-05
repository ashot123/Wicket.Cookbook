package cookbook;


import org.apache.wicket.model.IModel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;

import java.util.*;

/**
 * Created by Ashot Karakhanyan on 05-01-2015
 */
public class HomePage extends WebPage {

    private static List<Contact> contacts = Arrays.asList(
            new Contact("Homer Simpson", "homer@fox.com", "555-1211"),
            new Contact("Charles Burns1", "cmb1@fox.com", "555-5322"),
            new Contact("Charles Burns2", "cmb2@fox.com", "555-5322"),
            new Contact("Charles Burns3", "cmb3@fox.com", "555-5322"),
            new Contact("Charles Burns4", "cmb4@fox.com", "555-5322"),
            new Contact("Charles Burns5", "cmb5@fox.com", "555-5322"),
            new Contact("Charles Burns6", "cmb6@fox.com", "555-5322"),
            new Contact("Charles Burns7", "cmb7@fox.com", "555-5322"),
            new Contact("Charles Burns8", "cmb8@fox.com", "555-5322"),
            new Contact("Charles Burns9", "cmb9@fox.com", "555-5322"),
            new Contact("Charles Burns10", "cmb10@fox.com", "555-5322"),
            new Contact("Ned Flanders", "green@fox.com", "555-9732"));

    public HomePage() {

        List<IColumn<Contact>> columns = new ArrayList<IColumn<Contact>>();
        columns.add(new PropertyColumn<Contact>(Model.of("Name"), "name", "name"));
        columns.add(new PropertyColumn<Contact>(Model.of("Email"), "email", "email"));
        columns.add(new PropertyColumn<Contact>(Model.of("Phone"), "phone"));

        add(new DefaultDataTable<Contact>("contacts", columns, new ContactsProvider(), 10));
    }

    private static class ContactsProvider extends SortableDataProvider<Contact> {
        public ContactsProvider() {
            setSort("name", true);
        }

        @Override
        public Iterator<? extends Contact> iterator(int first, int count) {
            List<Contact> data = new ArrayList<Contact>(contacts);
            Collections.sort(data, new Comparator<Contact>() {

                public int compare(Contact o1, Contact o2) {
                    int dir = getSort().isAscending() ? 1 : -1;

                    if ("name".equals(getSort().getProperty())) {
                        return dir * (o1.name.compareTo(o2.name));
                    } else {
                        return dir * (o1.email.compareTo(o2.email));
                    }
                }
            });
            return data.subList(first, Math.min(first + count, data.size())).iterator();
        }

        @Override
        public int size() {
            return contacts.size();
        }

        @Override
        public IModel<Contact> model(Contact object) {
            return Model.of(object);
        }

    }
}


