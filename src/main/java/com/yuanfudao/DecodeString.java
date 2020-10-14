package com.yuanfudao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static java.lang.Character.isDigit;

@Component
public class DecodeString {

    public String decodeString(String s) {

        // 输入的字符数组
        char[] chars = s.toCharArray();
        ArrayList<Character> input = charToArray(chars);
        // 返回结果
        ArrayList<Character> result = decodeCharList(input);
        return charsToString(result);
    }

    private ArrayList<Character> decodeCharList(ArrayList<Character> input) {
        // 输入的字符数组
        ArrayList<Character> chars = input;
        // 返回结果
        ArrayList<Character> result = new ArrayList<>();
        // 是否为重复子字符串
        boolean repeatFlag = false;
        // 重复次数
        ArrayList<Character> repaetDigitList = new ArrayList<>();
        int repeatCount = 0;
        // 重复内容
        ArrayList<Character> repeatString = new ArrayList<>();
        // 方括号闭合标志
        int circleflag = 0;
        // 方括号叠加层数
        int cicleCount = 0;
        for (char c : chars) {
            if (isDigit(c)) {
                if (circleflag == 0) {
                    repaetDigitList.add(c);
                } else {
                    repeatString.add(c);
                }
                continue;
            } else if (c == '[') {
                repeatFlag = true;
                if (circleflag != 0) {
                    repeatString.add(c);
                } else {
                    // 计算重复次数
                    repeatCount = charArrayListToNum(repaetDigitList);
                    // 清空数字数组
                    repaetDigitList = new ArrayList<>();
                }
                ++circleflag;
                ++cicleCount;
                continue;
            } else if (c == ']') {
                --circleflag;
                if (circleflag != 0) {
                    repeatString.add(c);
                } else {
                    repeatFlag = false;
                    --cicleCount;
                    while (cicleCount != 0) {
                        repeatString = decodeCharList(repeatString);
                        --cicleCount;
                    }
                    // 添加重复内容
                    addCharList(result, repeatString, repeatCount);
                    // 清空字符数组
                    repeatString = new ArrayList<>();
                }
                continue;
            } else if (repeatFlag == true) {
                repeatString.add(c);
                continue;
            } else {
                result.add(c);
            }
        }
        return result;
    }

    private ArrayList<Character> charToArray(char[] input) {
        ArrayList<Character> output = new ArrayList<>();
        for (char c : input) {
            output.add(c);
        }
        return output;
    }

    private int charArrayListToNum(ArrayList<Character> nums) {
        int result = 0;
        int bits = nums.size() - 1;
        for (Character num : nums) {
            result += Integer.parseInt(num.toString()) * Math.pow(10, bits);
            --bits;
        }
        return result;
    }

    private void addCharList(ArrayList<Character> result, ArrayList<Character> s, int count) {
        for (int i = 0; i < count; ++i) {
            result.addAll(s);
        }
    }

    private String charsToString(ArrayList<Character> chars) {
        StringBuffer s = new StringBuffer();
        for (Character c : chars) {
            s.append(c);
        }
        return s.toString();
    }
}
