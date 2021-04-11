package com.example.demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkBlur extends RecursiveAction {

    private int[] mSource;
    private int mStart;
    private int mLength;
    private int[] mDestination;
    private int mBlurWidth = 15;

    public ForkBlur(int[] mSource, int mStart, int mLength, int[] mDestination) {
        this.mSource = mSource;
        this.mStart = mStart;
        this.mLength = mLength;
        this.mDestination = mDestination;
    }

    protected void computeDirectly() {
        int sidePixels = (mBlurWidth -1)/2;
        for (int index = mStart; index < mStart +mLength; index++) {
            float rt = 0,gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels ; mi++) {
                int mindex = Math.min(Math.max(mi + index,0),mSource.length-1);
                int pixel = mSource[index];
                rt += (float) ((pixel & 0x00ff0000) >> 16)/mBlurWidth;
                gt += (float) ((pixel & 0x0000ff00) >> 8)/mBlurWidth;
                bt += (float) ((pixel & 0x000000ff) >> 0)/mBlurWidth;
            }
            int dpixel = (0xff000000) | (((int)rt) << 16)|
                    (((int) gt)<< 8)|
                    (((int)bt)<<0);
            mDestination[index] = dpixel;
        }
    }
    protected static int mThreshold = 100000;

    @Override
    protected void compute() {
        if(mLength < mThreshold){
            computeDirectly();
            return;
        }
        int split = mLength/2;
        invokeAll(new ForkBlur(mSource, mStart,split,mDestination),
                new ForkBlur(mSource,mStart+split, mLength-split,mDestination));
    }
    static class Terst{
        public static void main(String[] args) {
            int[] src = {12,34,5,62,6,887,23,87,23,87,239,24589,2678,98765,534,21};
            int[] dst = new int[20];
            ForkBlur fb = new ForkBlur(src,0,src.length,dst);
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(fb);
            for (int i = 0; i < dst.length; i++) {
                System.out.println(dst[i]);
            }
        }
    }
}
