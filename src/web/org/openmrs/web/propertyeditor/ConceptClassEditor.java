package org.openmrs.web.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.ConceptClass;
import org.openmrs.api.ConceptService;
import org.openmrs.context.Context;
import org.springframework.util.StringUtils;

public class ConceptClassEditor extends PropertyEditorSupport {

	private Log log = LogFactory.getLog(this.getClass());
	
	Context context;
	
	public ConceptClassEditor(Context c) {
		this.context = c;
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (context != null) {
			ConceptService cs = context.getConceptService(); 
			if (StringUtils.hasText(text)) {
				try {
					setValue(cs.getConceptClass(Integer.valueOf(text)));
				}
				catch (Exception ex) {
					throw new IllegalArgumentException("ConceptClass not found: " + ex.getMessage());
				}
			}
			else {
				setValue(null);
			}
		}
	}

	public String getAsText() {
		ConceptClass t = (ConceptClass) getValue();
		if (t == null) {
			return "";
		}
		else {
			return t.getConceptClassId().toString();
		}
	}

}
