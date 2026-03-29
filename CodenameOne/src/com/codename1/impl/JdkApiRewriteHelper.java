package com.codename1.impl;

/**
 * Bridge methods used by bytecode rewrite rules for JDK APIs that are risky/unsupported on some targets.
 */
public final class JdkApiRewriteHelper {
    private JdkApiRewriteHelper() {
    }

    public static String[] split(String source, String regex) {
        return split(source, regex, 0);
    }

    public static String[] split(String source, String regex, int limit) {
        if (source == null) {
            throw new NullPointerException("source is null");
        }
        if (regex == null) {
            throw new NullPointerException("regex is null");
        }
        if (regex.length() == 0) {
            return new String[]{source};
        }
        java.util.Vector<String> out = new java.util.Vector<String>();
        int start = 0;
        int match;
        while ((match = source.indexOf(regex, start)) >= 0) {
            if (limit > 0 && out.size() == limit - 1) {
                break;
            }
            out.addElement(source.substring(start, match));
            start = match + regex.length();
        }
        out.addElement(source.substring(start));
        int resultSize = out.size();
        if (limit == 0) {
            while (resultSize > 0 && out.elementAt(resultSize - 1).length() == 0) {
                resultSize--;
            }
        }
        String[] result = new String[resultSize];
        for (int i = 0; i < resultSize; i++) {
            result[i] = out.elementAt(i);
        }
        return result;
    }
}
