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
/*
 * This annotation enables @Aspect annotations. The property
 * "proxyTargetClass=true" must be set otherwise classes matching any Pointcut
 * are proxy classes and not the expected target classes.
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TestapplicationConfiguration {

	@Scope(value = "ui", proxyMode = ScopedProxyMode.TARGET_CLASS)
	@Bean
	public Label label() {
		return new Label();
	}

	@Scope(value = "ui", proxyMode = ScopedProxyMode.TARGET_CLASS)
	@Bean
	public Panel panel() {
		return new Panel();
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/locale/translatedLabels");
		messageSource.setCacheSeconds(10);
		return messageSource;

	}

	@Bean
	public org.vaadin.spring.i18n.TranslationAspect translationAspect() {

		org.vaadin.spring.i18n.TranslationAspect aspect = new org.vaadin.spring.i18n.TranslationAspect();
		aspect.setMessageSource(messageSource());

		return aspect;

	}
}
