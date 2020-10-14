package com.leetcode.weekyRun;

public class EntityParser {

    public static String entityParser(String text) {

        if (text.contains("&quot;")) text = text.replaceAll("&quot;", "\"");
        if (text.contains("&apos;")) text = text.replaceAll("&apos;", "'");
        if (text.contains("&amp;")) text = text.replaceAll("&amp;", "&");
        if (text.contains("&gt;")) text = text.replaceAll("&gt;", ">");
        if (text.contains("&lt;")) text = text.replaceAll("&lt;", "<");
        if (text.contains("&frasl;")) text = text.replaceAll("&frasl;", "/");
        return text;
    }

    public static void main(String[] args) {
        System.out.println(EntityParser.entityParser("&amp; is an HTML entity but &ambassador; is not."));
    }
}
