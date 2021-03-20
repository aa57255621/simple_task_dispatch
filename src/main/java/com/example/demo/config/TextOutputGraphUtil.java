package com.example.demo.config;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;

public class TextOutputGraphUtil {
    public  void outputGraph(String input){
        try {
            Font font = new Font("黑体", Font.PLAIN, 20);

            AffineTransform at = new AffineTransform();

            FontRenderContext frc = new FontRenderContext(at, true, true);

            GlyphVector gv = font.createGlyphVector(frc, input); // 要显示的文字

            Shape shape = gv.getOutline(10, 30);

            int weith = 300;

            int height = 30;

            boolean[][] view = new boolean[weith][height];

            for (int i = 0; i < weith; i++) {
                for (int j = 0; j < height; j++) {
                    if (shape.contains(i, j)) {
                        view[i][j] = true;

                    } else {
                        view[i][j] = false;

                    }

                }

            }

            for (int j = 0; j < height; j++) {
                for (int i = 0; i < weith; i++) {
                    if (view[i][j]) {
                        System.out.print("0");// 替换成你喜欢的图案

                    } else {
                        System.out.print(" ");

                    }

                }

                System.out.println();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
