package htmlcheck.rules;


import htmlcheck.*;

import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

public class NoEmptyImageAltAttributeRule implements Rule {

	private final Page page;

	public NoEmptyImageAltAttributeRule(Page page) {
		this.page = page;
	}

	public void addErrorsTo(List<HtmlCheckError> errors) throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<Element> imgs = XPath.selectNodes(this.page.getRoot(), "//img[not(@alt) or @alt = '']");

			for (Element img : imgs) {
				errors.add(new HtmlCheckError(String.format("MISSING ALT: missing or empty alt attribute in %s: %s", HtmlCheck.toSelector(img), img.getAttributeValue("src"))));
			}
		} catch (JDOMException e) {
			throw new RuntimeException(e);
		}
	}
}