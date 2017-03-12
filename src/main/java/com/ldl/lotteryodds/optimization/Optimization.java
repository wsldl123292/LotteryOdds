package com.ldl.lotteryodds.optimization;

/**
 * 功能: 彩票投注优化，输入赔率，投注金额，计算出投注数量，保证本金
 * 作者: ldl
 * 时间: 2017-03-12 12:43
 */
public class Optimization {


    public static void main(String[] args) {
        System.out.println(optimize(1.85, 2.03, 20));
    }

    /**
     * @param firstOdds  第一项赔率
     * @param secondOdds 第二项赔率
     * @param money      投注总金额
     * @return
     */
    private static Result optimize(double firstOdds, double secondOdds, int money) {
        Result result = new Result();
        result.setFirstOdds(firstOdds);
        result.setSecondOdds(secondOdds);

        //只支持大于10的正偶数
        if (money < 10 || money % 2 != 0) {
            return null;
        }

        int firstAmount = 1;
        int secondAmount = 1;

        //投注总数 = 投注金额/2
        int totalAmount = money / 2;


        while (totalAmount > 0) {

            if (firstAmount * firstOdds * 2 < money) {
                firstAmount++;
            } else {
                result.setFirstAmount(firstAmount);
                result.setFirstMoney(firstAmount * firstOdds * 2);
                totalAmount = totalAmount - firstAmount;
                while (totalAmount > 0) {
                    if (secondAmount * secondOdds * 2 < money) {
                        if(secondAmount < totalAmount){
                            secondAmount++;
                        }else{
                            totalAmount = totalAmount - secondAmount;
                            result.setSecondAmount(secondAmount + totalAmount);
                            result.setSecondMoney((secondAmount + totalAmount) * secondOdds * 2);
                        }
                    } else {
                        totalAmount = totalAmount - secondAmount;
                        if (totalAmount > 0) {
                            result.setSecondAmount(secondAmount + totalAmount);
                            result.setSecondMoney((secondAmount + totalAmount) * secondOdds * 2);
                            totalAmount = 0;
                        } else {
                            result.setSecondAmount(secondAmount);
                            result.setSecondMoney(secondAmount * secondOdds * 2);
                        }
                    }
                }
            }
        }
        return result;
    }

    private static class Result {
        private double firstOdds;
        private double secondOdds;
        private int firstAmount;
        private int secondAmount;
        private double firstMoney;
        private double secondMoney;


        public double getFirstOdds() {
            return firstOdds;
        }

        public void setFirstOdds(double firstOdds) {
            this.firstOdds = firstOdds;
        }

        public double getSecondOdds() {
            return secondOdds;
        }

        public void setSecondOdds(double secondOdds) {
            this.secondOdds = secondOdds;
        }

        public int getFirstAmount() {
            return firstAmount;
        }

        public void setFirstAmount(int firstAmount) {
            this.firstAmount = firstAmount;
        }

        public int getSecondAmount() {
            return secondAmount;
        }

        public void setSecondAmount(int secondAmount) {
            this.secondAmount = secondAmount;
        }

        public double getFirstMoney() {
            return firstMoney;
        }

        public void setFirstMoney(double firstMoney) {
            this.firstMoney = firstMoney;
        }

        public double getSecondMoney() {
            return secondMoney;
        }

        public void setSecondMoney(double secondMoney) {
            this.secondMoney = secondMoney;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "firstOdds=" + firstOdds +
                    ", secondOdds=" + secondOdds +
                    ", firstAmount=" + firstAmount +
                    ", secondAmount=" + secondAmount +
                    ", firstMoney=" + firstMoney +
                    ", secondMoney=" + secondMoney +
                    '}';
        }
    }
}
