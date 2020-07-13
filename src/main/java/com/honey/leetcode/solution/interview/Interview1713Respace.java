package com.honey.leetcode.solution.interview;

/**
 * @author hualin.su
 * @date 2020-07-09 14:26
 */
public class Interview1713Respace {

    public static void main(String[] args) {
        String[] dic = {"looked", "just", "like", "her", "brother"};
        String sentence = "jesslookedjustliketimherbrother";
        System.out.println(new Interview1713Respace().respace(dic, sentence));
    }

    public int respace(String[] dictionary, String sentence) {
        int senLen = sentence.length();
        int[] dp = new int[senLen + 1];
        for (int i = 1; i <= senLen; i++) {
            dp[i] = dp[i - 1];
            for (String word : dictionary) {
                int wordLen = word.length();
                if (i < wordLen) {
                    continue;
                }
                if (word.equals(sentence.substring(i - wordLen, i))) {
                    dp[i] = Math.max(dp[i], dp[i - wordLen] + wordLen);
                }
            }
        }
        return senLen - dp[senLen];
    }
}
