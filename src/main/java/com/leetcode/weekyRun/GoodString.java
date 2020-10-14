package com.leetcode.weekyRun;

public class GoodString {

    public int findGoodStrings(int n, String s1, String s2, String evil) {

        if (s1.compareTo(s2) > 0) return 0;

        // 0、公共部分是否包含evil，包含则直接返回0
        // 1、计算总共有多少个字符串
        // 2、计算包含evil的字符串
        // 3、相减
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int distinctLoc = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                distinctLoc = i;
                break;
            }
        }
        // 0
        if (s1.substring(0, distinctLoc).contains(evil)) return 0;
        // 1
        long mode = (long)Math.pow(10, 9) + 7;
        long totalCount = chars2[distinctLoc] - chars1[distinctLoc] - 1;
        for (int i = distinctLoc + 1;i < n; i++) {
            totalCount = totalCount * 26 + ('z' - chars1[i]) + (chars2[i] - 'a');
            totalCount %= mode;
        }
        totalCount = (totalCount + 2) % mode;
        // 2
        long evilCount = 0;


        return 0;
    }
}

/**
 * 题目：
 *
 * 给你两个长度为 n 的字符串 s1 和 s2 ，以及一个字符串 evil 。请你返回 好字符串 的数目。
 *
 * 好字符串 的定义为：它的长度为 n ，字典序大于等于 s1 ，字典序小于等于 s2 ，且不包含 evil 为子字符串。
 *
 * 由于答案可能很大，请你返回答案对 10^9 + 7 取余的结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2, s1 = "aa", s2 = "da", evil = "b"
 * 输出：51
 * 解释：总共有 25 个以 'a' 开头的好字符串："aa"，"ac"，"ad"，...，"az"。还有 25 个以 'c' 开头的好字符串："ca"，"cc"，"cd"，...，"cz"。最后，还有一个以 'd' 开头的好字符串："da"。
 * 示例 2：
 *
 * 输入：n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
 * 输出：0
 * 解释：所有字典序大于等于 s1 且小于等于 s2 的字符串都以 evil 字符串 "leet" 开头。所以没有好字符串。
 * 示例 3：
 *
 * 输入：n = 2, s1 = "gx", s2 = "gz", evil = "x"
 * 输出：2
 *  
 *
 * 提示：
 *
 * s1.length == n
 * s2.length == n
 * 1 <= n <= 500
 * 1 <= evil.length <= 50
 * 所有字符串都只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-good-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */