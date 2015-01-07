package cookbook;


import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.PropertyModel;

import java.util.*;

/**
 * Created by Ashot Karakhanyan on 05-01-2015
 */
public class HomePage extends WebPage {

    private static List<Contact> contacts = Arrays.asList(new Contact[]{
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
            new Contact("Ned Flanders", "green@fox.com", "555-9732")});

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }

    private String filter;

    public HomePage(final PageParameters parameters) {

        List<IColumn<Contact>> columns = new ArrayList<IColumn<Contact>>();
        columns.add(new PropertyColumn<Contact>(Model.of("Name"), "name", "name"));
        columns.add(new PropertyColumn<Contact>(Model.of("Email"), "email", "email"));
        columns.add(new PropertyColumn<Contact>(Model.of("Phone"), "phone"));

        Form<?> form = new Form<Void>("form");
        add(form);
        form.add(new TextField<String>("filter", new PropertyModel<String>(this, "filter")));

        add(new DefaultDataTable<Contact>("contacts", columns, new ContactsProvider(), 10));
    }

    private class ContactsProvider extends SortableDataProvider<Contact> {
        private transient List<Contact> filtered;

        private List<Contact> getFiltered() {
            if (filtered == null) {
                filtered = filter();
            }
            return filtered;
        }

        private List<Contact> filter() {
            List<Contact> filtered = new ArrayList<Contact>(contacts);
            if (filter != null) {
                String upper = filter.toUpperCase();

                Iterator<Contact> it = filtered.iterator();

                while (it.hasNext()) {
                    Contact contact = it.next();
                    if (contact.name.toUpperCase().indexOf(upper) < 0
                            && contact.email.toUpperCase().indexOf(upper) < 0) {
                        it.remove();
                    }
                }
            }
            return filtered;
        }

        @Override
        public void detach() {
            filtered = null;
            super.detach();
        }

        public Iterator<? extends Contact> iterator(int first, int count) {
            return getFiltered().subList(first, Math.min(first + count, getFiltered().size())).iterator();
        }


        @Override
        public int size() {
            return getFiltered().size();
        }

        @Override
        public IModel<Contact> model(Contact object) {
            return Model.of(object);
        }

	}

}