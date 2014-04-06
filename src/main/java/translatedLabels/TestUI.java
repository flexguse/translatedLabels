/**
 * 
 */
package translatedLabels;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.vaadin.spring.VaadinUI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Christoph Guse, info@flexguse.de
 * 
 */
@VaadinUI
public class TestUI extends UI implements ApplicationContextAware {

	private static final long serialVersionUID = 5617968757380469990L;

	private ApplicationContext springContext;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.springContext = applicationContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(VaadinRequest request) {
		
		setSizeFull();
		
		Panel panel = springContext.getBean(Panel.class);
		panel.setSizeFull();
		panel.setCaption("panel.caption");
		panel.setSizeFull();
		
		VerticalLayout panelLayout = new VerticalLayout();
		panelLayout.setSizeFull();
		
		Label label = springContext.getBean(Label.class);
		label.setValue("label.caption");
		panelLayout.addComponent(label);
				
		Label label2 = springContext.getBean(Label.class);
		label.setValue("Hello World!");
		panelLayout.addComponent(label2);
		
		panel.setContent(panelLayout);
		
		setContent(panel);

	}
	
	
	
}
