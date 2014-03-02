/**
 * 
 */
package translatedLabels;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 * @author Christoph Guse, info@flexguse.de
 *
 */
@Configuration
@EnableAspectJAutoProxy
public class TestapplicationConfiguration {

	@Scope(value="prototype",proxyMode=ScopedProxyMode.TARGET_CLASS)
	@Bean
	public Label label(){
		return new Label();
	}
	
	@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
	@Bean
	public Panel panel(){
		return new Panel();
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/locale/translatedLabels");
		messageSource.setCacheSeconds(10);
		return messageSource;
		
	}
	
	@Bean
	public TranslationAspect translationAspect(){
		
		TranslationAspect aspect = new TranslationAspect();
		aspect.setMessageSource(messageSource());
		
		return aspect;
		
	}
}
