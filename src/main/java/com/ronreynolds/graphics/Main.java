package com.ronreynolds.graphics;

import com.ronreynolds.graphics.util.Windows;

import java.awt.Frame;

public class Main {
    public static void main(String[] args) {
        Frame frame = Windows.addShutdownHook(new Frame("Graphics Example"));
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}