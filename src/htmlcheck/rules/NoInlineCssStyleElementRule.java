/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package htmlcheck.rules;

import htmlcheck.HtmlCheckError;
import htmlcheck.Page;
import htmlcheck.Rule;
import htmlcheck.Selector;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import java.util.List;

public class NoInlineCssStyleElementRule implements Rule {

    private final Page page;

    public NoInlineCssStyleElementRule(Page page) {
        this.page = page;
    }

    @SuppressWarnings("unchecked")
    public void addErrorsTo(List<HtmlCheckError> errors) throws Exception {
        List<Element> styles = XPath.selectNodes(page.getRoot(), "//style");
        for (Element style : styles) {
            errors.add(new HtmlCheckError(String.format("BANNED ELEMENT: inline style element found: %s, containing: %s", Selector.from(style), StringUtils.abbreviate(style.getText(), 60))));
        }
    }
}
