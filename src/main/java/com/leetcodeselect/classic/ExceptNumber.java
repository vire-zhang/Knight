package com.leetcodeselect.classic;

public class ExceptNumber {

    public int expectNumber(int[] scores) {

        for (int score : scores) {

        }
        return 0;
    }

    public int splitArray(int[] nums) {
        if (nums.length == 1) return 1;
        if (divisor(nums[0], nums[nums.length - 1]) != 1) return 1;
        if (nums.length == 2) return 2;
        int i = 0, j = nums.length - 2;
        int count = 1;
        boolean flag = true;
        while (i < nums.length - 1 && j > 0) {
            int base1 = flag ? nums[0] : nums[nums.length - 1];
            int base2 = flag ? nums[j] : nums[i];
            if (divisor(base1, base2) != 1) {
                if (flag) {
                    if (j == nums.length - 2) return count+1;
                    int[] subNums = new int[nums.length - j - 1];
                    int k = 0;
                    for (int p = j + 1; p < nums.length; ++p)
                        subNums[k++] = nums[p];
                    return count + splitArray(subNums);
                } else {
                    if (i == 1) return count+1;
                    int[] subNums = new int[i];
                    int k = 0;
                    for (int p = 0; p < i; ++p)
                        subNums[k++] = nums[p];
                    return count + splitArray(subNums);
                }
            }
            if (flag) {
                ++i;
                flag = false;
            } else {
                --j;
                flag = true;
            }
        }
        int[] sub = new int[nums.length - 2];
        int j1 = 0;
        for (int i1 = 1; i1 < nums.length - 1; i1++) {
            sub[j1++] = nums[i1];
        }
        return 2 + splitArray(sub);
    }

    // 最大公约数
    private int divisor(int a,int b) {
        int temp;
        if (a < b) {
            temp = a;
            a = b;
            b = temp;
        }
        while (b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

//    public static void main(String[] args) {
////        int[] nums = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59};
//        ExceptNumber exceptNumber = new ExceptNumber();
//        int[] nums = exceptNumber.getPrime();
//        System.out.println(nums[nums.length-1]);
//        System.out.println(exceptNumber.splitArray(nums));
//        System.out.println(nums.length);
//    }

    private int[] getPrime() {
        int[] result = new int[10000]; // 最多存50个
        int count = 0;
        for(int i=2; i<Integer.MAX_VALUE; i++) {
            // 判断 i 是否为质数
            boolean isPrime = true;
            for (int k = 2; k < i; k++) {
                if (i % k == 0) {
                    isPrime = false;
                    break;
                }
            }

            // 如果是质数，则存到数组result里
            if (isPrime) {
                result[count] = i;
                count++;
                if (count >= result.length) {
                    break; // 已经存满50个，则退出查找
                }
            }
        }
        return result;
    }

    public int minTime(int[] time, int m) {
        if (m >= time.length) return 0;

        int sum = 0;
        for (int i : time) {
            sum += i;
        }
        int average = sum / m + ((sum % m) == 0 ? 0 : 1);

        int maxTime1 = 0;
        int j = 0;
        for (int i = 0; i < m; i++) {
            if ((m - i) >= time.length - j) break;
            if (j >= time.length) break;
            int maxTemp = 0, sumTemp = 0, count = 0;
            if (i == m - 1) {
                while (j < time.length) {
                    sumTemp += time[j];
                    if (time[j] > maxTemp) maxTemp = time[j];
                    ++j;
                }
            } else {
                while (time[j] + sumTemp < average) {
                    sumTemp += time[j];
                    if (time[j] > maxTemp) maxTemp = time[j];
                    ++j;
                    ++count;
                }
                if ((m - i - 1) < time.length - j && (sum - sumTemp) / (m - i) > sumTemp) {
                    sumTemp += time[j];
                    if (time[j] > maxTemp) maxTemp = time[j];
                    ++j;
                }
            }
            sumTemp -= maxTemp;
            if (sumTemp > maxTime1) maxTime1 = sumTemp;
        }
        return maxTime1;
    }

    public static void main(String[] args) {
        ExceptNumber exceptNumber = new ExceptNumber();
        int[] times = {1,2,3,4,5,6,7,8,9,10,999,666};
        System.out.println(exceptNumber.minTime(times,3));
    }
}
