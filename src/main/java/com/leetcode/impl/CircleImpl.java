package com.leetcode.impl;

import com.leetcode.Circle;

import static java.lang.StrictMath.random;

public class CircleImpl implements Circle {

    /**
     * 半径
     */
    double radius;

    /**
     * 圆心坐标 x
     */
    double x_center;

    /**
     * 圆心坐标 y
     */
    double y_center;

    public CircleImpl(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }

    /**
     * 返回圆中随机点的坐标
     * @return
     */
    @Override
    public double[] randPoint() {
        //随机半径
        double d = radius * Math.sqrt(Math.random());
        //随机弧度
        //弧度 = 角度 * 2pai
        double theta = Math.random() * 2 * Math.PI;
        //生成随机点
        return new double[] {d * Math.cos(theta) + x_center, d * Math.sin(theta) + y_center};
    }
}

/**
 * 方法二：计算分布函数
 * 不失一般性，我们只考虑在原点且半径为 1 的单位圆，对于非一般性的情况，我们只需要把生成的点的坐标根据半径等比例放大，再根据圆心坐标进行平移即可。
 *
 * 对于两条线段，我们在它们中均匀随机生成点。如果一条线段的长度是另一条的两倍，那么生成的点在第一条线段上的概率也应当是在第二条线段上的概率的两倍。因此我们考虑单位圆内部的每一个圆环，生成的点落在半径为 R_1R
 * 1
 * ​
 *   的圆环上的概率应当与圆环的周长成正比，同时也与 R_1R
 * 1
 * ​
 *   成正比，即 f(R_1) = k * R_1f(R
 * 1
 * ​
 *  )=k∗R
 * 1
 * ​
 *  ，其中 f(x)f(x) 为概率密度函数（PDF）。由于 f(x)f(x) 在定义域上的积分为 1，因此可以求出 f(x)f(x) 的表达式 f(x) = 2xf(x)=2x。
 *
 * 得到了概率密度函数后，我们计算累计分布函数（CDF），即 F(x) = \int f(x) = \int 2x = x^2F(x)=∫f(x)=∫2x=x
 * 2
 *  。累计分布函数 F(x)F(x) 告诉我们，在单位圆中随机生成一个点，它离圆心的距离小于等于 xx 的概率为 F(x) = x^2F(x)=x
 * 2
 *  。对于一个给定的累计分布函数，如果我们想要根据其生成随机变量，我们可以通过 [0, 1][0,1] 的均匀分布生成随机变量 UU，找到满足 F(X) = UF(X)=U 的 XX，此时 XX 即为满足累计分布函数的随机变量。
 *
 * 对于 F(X) = UF(X)=U，由于 F(X)F(X) 单调递增，因此有 X = F^{-1}(U)X=F
 * −1
 *  (U)。由于 F(x) = x^2F(x)=x
 * 2
 *  ，因此有 F^{-1}(x) = \sqrt{x}F
 * −1
 *  (x)=
 * x
 * ​
 *  ，即用 X = \sqrt{U}X=
 * U
 * ​
 *   来生成随机变量 XX。
 *
 * 除了 XX（代表到圆心的距离）之外，我们还需要随机生成其与水平轴正方向的夹角 \thetaθ，随后我们就可以根据
 *
 * \text{x\_coord} = X \cdot \cos \theta\\ \text{y\_coord} = X \cdot \sin \theta
 * x_coord=X⋅cosθ
 * y_coord=X⋅sinθ
 *
 * 得到点在单位圆内的坐标。再经过我们等比例放大坐标和平移两个步骤，就可以得到任意圆内的一个均匀随机生成的点了。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/generate-random-point-in-a-circle/solution/zai-yuan-nei-sui-ji-sheng-cheng-dian-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * **/
