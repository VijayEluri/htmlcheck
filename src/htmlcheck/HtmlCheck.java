package htmlcheck;

import htmlcheck.rules.*;

import java.util.ArrayList;
import java.util.List;

public class HtmlCheck {

    private final Page page;

    public HtmlCheck(String document) {
        page = new Page(document);
    }

    public List<HtmlCheckError> getVerificationErrors() {
        List<HtmlCheckError> errors = new ArrayList<HtmlCheckError>();

        try {
            new NoInvalidElementsInHeadRule(page).addErrorsTo(errors);
            new NoXmlDeclarationAtBeginningOfFileRule(page).addErrorsTo(errors);
            new NoBannedInlineCssStyleAttributeRule(page).addErrorsTo(errors);
            new NoInlineCssStyleElementRule(page).addErrorsTo(errors);
            new NoEmptyImageSrcAttributeRule(page).addErrorsTo(errors);
            new NoEmptyImageAltAttributeRule(page).addErrorsTo(errors);
            new NoDeveloperCommentsRule(page).addErrorsTo(errors);
            new NoInlineScriptElementRule(page).addErrorsTo(errors);
            new NoUpperCaseHrefOrSrcAttributesRule(page).addErrorsTo(errors);
            new MetaKeywordsWordLimitRule(page, 20).addErrorsTo(errors);
            new HyphenLimitInURLSegmentRule(page, 3).addErrorsTo(errors);
            // new NoUnderscoresInUrlsRule().addErrorsTo(errors);
            new HeaderWordLimitRule(page, "h1", 7).addErrorsTo(errors);
            new TitleLengthLimitRule(page, 66).addErrorsTo(errors);
            new MaximumNumberOfElementsRule(page, 1700).addErrorsTo(errors);
            new NoEventHandlerAttributesRule(page).addErrorsTo(errors);
            new NoDivsWithSingleBlockLevelChildRule(page).addErrorsTo(errors);
            new NoDuplicatedIdAttributesRule(page).addErrorsTo(errors);
            new NoInvalidIdAttributesRule(page).addErrorsTo(errors);
            new NoInvalidClassAttributesRule(page).addErrorsTo(errors);
            new BodyIdAttributeRequiredRule(page).addErrorsTo(errors);
            new NoEmptyListsRule(page).addErrorsTo(errors);
            new NoInvalidAttributesInElementsRule(page).addErrorsTo(errors);
            new W3CStandardsComplianceRule(page).addErrorsTo(errors);
            new NoExcessivelyNestedIdsRule(page).addErrorsTo(errors);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return errors;
    }

}
