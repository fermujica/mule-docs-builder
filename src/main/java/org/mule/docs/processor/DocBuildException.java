package org.mule.docs.processor;

/**
 * Created by sean.osterberg on 2/20/15.
 */
public class DocBuildException extends RuntimeException {

    public DocBuildException(String m) {
        super(m);
    }

    public DocBuildException(String m,Throwable e) {
        super(m,e);
    }
}
