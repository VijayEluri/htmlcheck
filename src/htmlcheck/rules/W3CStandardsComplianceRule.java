package htmlcheck.rules;

import htmlcheck.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.w3c.tidy.*;
import org.w3c.tidy.TidyMessage.Level;

public class W3CStandardsComplianceRule implements Rule {

	private final HtmlCheck htmlCheck;

	public W3CStandardsComplianceRule(HtmlCheck htmlCheck) {
		this.htmlCheck = htmlCheck;
	}

	public void addErrorsTo(final List<HtmlCheckError> errors) throws Exception {
		Tidy tidy = new Tidy();
		tidy.setErrout(new PrintWriter(new StringWriter()));
		tidy.setDocType("\"-//W3C//DTD XHTML 1.0 Strict//EN\"");
		tidy.setXHTML(true);
		
		tidy.setMessageListener(new TidyMessageListener() {
		
			private List<Integer> ignored = Arrays.asList(
					44, // doc type declaration not present
					49, // <script> lacks type attribute
					23, // empty spans
					66  // element id already defined (we have a specific check for that)
			);

			public void messageReceived(TidyMessage message) {
				if(shouldReport(message)) {
					errors.add(new HtmlCheckError(String.format("TIDY: %s (code %d, line %d, column %d)", message.getMessage(), message.getErrorCode(), message.getLine(), message.getColumn())));
				}
			}

			private boolean shouldReport(TidyMessage message) {
				return !ignored .contains(message.getErrorCode()) && (message.getLevel() == Level.ERROR || message.getLevel() == Level.WARNING);
			}
		});
		tidy.parse(new StringReader(this.htmlCheck.page.getSource()), new StringWriter());
	}
}