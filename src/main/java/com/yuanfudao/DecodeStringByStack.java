package com.yuanfudao;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Stack;

@Component
public class DecodeStringByStack {

    public String decodeString(String s) {
        // 返回结果
        StringBuilder res = new StringBuilder();
        // 重复次数
        int multi = 0;
        // 辅助栈
//        Stack<String> stack_res = new Stack<>();
//        Stack<Integer> stack_multi = new Stack<>();
        LinkedList<String> stack_res = new LinkedList<>();
        LinkedList<Integer> stack_multi = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                stack_res.push(res.toString());
                stack_multi.push(multi);
                res = new StringBuilder();
                multi = 0;
            }
            else if (c == ']') {
                StringBuilder temp = new StringBuilder(stack_res.pop());
                int cur_multi = stack_multi.pop();
                for (Integer i = 0; i < cur_multi; i++) {
                    temp.append(res);
                }
                res = temp;
            }
            else if (c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }

        return res.toString();
    }
}
