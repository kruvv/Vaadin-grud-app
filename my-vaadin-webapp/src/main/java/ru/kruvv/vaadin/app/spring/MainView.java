package ru.kruvv.vaadin.app.spring;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

	private CustomerService service = CustomerService.getInstance();
	private Grid<Customer> grid = new Grid<>(Customer.class);
	private TextField filterText = new TextField();
	private CustomerForm form = new CustomerForm(this);

	public MainView() {

		// Adding a TextField Component
		filterText.setPlaceholder("Filter by name...");
		filterText.setClearButtonVisible(true);

		// Filtering the Data
		filterText.setValueChangeMode(ValueChangeMode.EAGER);
		filterText.addValueChangeListener(e -> updateList());

		grid.setColumns("firstName", "lastName", "status");

		HorizontalLayout mainContent = new HorizontalLayout(grid, form);
		mainContent.setSizeFull();
		grid.setSizeFull();

		add(filterText, mainContent);

		setSizeFull();

		updateList();
	}

	public void updateList() {
		grid.setItems(service.findAll(filterText.getValue()));
	}

}
