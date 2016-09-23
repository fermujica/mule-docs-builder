package com.mulesoft.documentation.builder.SitemapGenerator;

/**
 * Created by fernandomujica on 9/23/16.
 */
public class sitemapper {

    public static void main(String[] args) {
        Generator sitemapper = new Generator("https://docs.mulesoft.com","/Users/fernandomujica/github/mulesoft-docs");
        sitemapper.generate();
    }

}
