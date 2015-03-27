package org.mule.docs.model;

import java.util.*;

/**
 * Created by sean.osterberg on 2/20/15.
 */
public class TocNode implements IPageElement {

    private String url;
    private String title;
    private TocNode parent;
    private List<TocNode> children;

    public TocNode(String url, String title, TocNode parent) {
        this.url = url;
        this.title = title;
        this.parent = parent;
        children = new ArrayList<TocNode>();
    }

    public TocNode(String url, String title) {
        this(url,title,null);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TocNode getParent() {
        return parent;
    }

    public void setParent(TocNode parent) {
        this.parent = parent;
    }

    public List<TocNode> getChildren() {
        return children;
    }

    public void addChild(TocNode node) {
        this.children.add(node);
    }

    @Override
    public void accept(IPageElementVisitor visitor) {
        if (visitor.visit(this)) {
            for (TocNode tocNode : children) {
                visitor.visit(tocNode);
            }
        }
    }
}
