package org.multipoly.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class Freemarker {

	public final static Freemarker INSTANCE = new Freemarker();
	private Configuration cfg;
	private StringTemplateLoader stringTemplateLoader;

	private Freemarker() {
		super();
		this.stringTemplateLoader = new StringTemplateLoader();
		MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(new TemplateLoader[] { this.stringTemplateLoader, new ClassTemplateLoader(Freemarker.class, "/") });
		cfg = new Configuration();
		cfg.setTemplateLoader(multiTemplateLoader);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
	}

	public Configuration getCfg() {
		return cfg;
	}

	public StringTemplateLoader getStringTemplateLoader() {
		return stringTemplateLoader;
	}

}
