package org.mule.docs.writer;

import org.junit.Test;
import org.mule.docs.processor.DocBuildException;
import org.mule.docs.model.AsciiDocPage;
import org.mule.docs.model.RootNodeFromHtmlToc;
import org.mule.docs.model.TocNode;
import org.mule.docs.utils.TestUtilities;
import org.mule.docs.utils.Utilities;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.File;

/**
 * Created by sean.osterberg on 2/20/15.
 */
public class RootNodeInHtmlTocTest {

    @Test
    public void fromAsciiDocTocPage_ReturnsNewObject() {
        RootNodeFromHtmlToc htmlToc = RootNodeFromHtmlToc.fromTocAsciiDocPage(getValidPage());
        assertThat(htmlToc, instanceOf(RootNodeFromHtmlToc.class));
    }

    @Test
    public void fromAsciiDocTocPage_RootNodeIsNotNull() {
        RootNodeFromHtmlToc htmlToc = RootNodeFromHtmlToc.fromTocAsciiDocPage(getValidPage());
        assertNotNull(htmlToc.getRootNode());
    }

    @Test
    public void getRootNodeFromRawTocHtml_FromValidTocDoc_HasProperStructure() {
        RootNodeFromHtmlToc htmlToc = RootNodeFromHtmlToc.fromTocAsciiDocPage(getValidPage());
        TocNode result = htmlToc.getRootNode();
        assertTrue(result.getTitle().contentEquals("CloudHub"));
        assertEquals(12,result.getChildren().size());
    }

    @Test(expected = DocBuildException.class)
    public void getRootNodeFromRawTocHtml_FromInvalidTocDoc_ThrowsException() {
        RootNodeFromHtmlToc htmlToc = RootNodeFromHtmlToc.fromTocAsciiDocPage(getInvalidPage());
    }

    @Test(expected = DocBuildException.class)
    public void getRootNodeFromRawTocHtml_FromTocDocWithMoreThanOneRootNode_ThrowsException() {
        String path = Utilities.getConcatPath(new String[] { TestUtilities.getTestResourcesPath(), "bad-files", "toc.ad" });
        AsciiDocPage page = AsciiDocPage.fromFile(new File(path));
        RootNodeFromHtmlToc htmlToc = RootNodeFromHtmlToc.fromTocAsciiDocPage(page);
    }

    @Test
    public void cleanupLink_WithHtmlInLink_RemovesHtmlExtension() {
        String link = RootNodeFromHtmlToc.cleanupLink("cloudhub.html");
        assertTrue(link.contentEquals("cloudhub"));
    }

    @Test
    public void cleanupLink_WithIndexInLink_RemovesIndex() {
        String link = RootNodeFromHtmlToc.cleanupLink("index.html");
        assertTrue(link.contentEquals(""));
    }

    private AsciiDocPage getValidPage() {
        String path = Utilities.getConcatPath(new String[] { TestUtilities.getPathToMasterFolder(), "cloudhub", "toc.ad" });
        AsciiDocPage page = AsciiDocPage.fromFile(new File(path));
        return page;
    }

    private AsciiDocPage getInvalidPage() {
        String path = Utilities.getConcatPath(new String[] { TestUtilities.getPathToMasterFolder(), "cloudhub", "cloudhub.ad" });
        AsciiDocPage page = AsciiDocPage.fromFile(new File(path));
        return page;
    }
}
