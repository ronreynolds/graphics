package com.ronreynolds.graphics.util;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Windows {
    /**
     * Adds a shutdown hook to a Window to ensure proper disposal on close.
     *
     * @param window The Window to which the shutdown hook will be added.
     * @return The same Window instance for method chaining.
     */
    public static <T extends Window> T addShutdownHook(T window) {
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                window.dispose();
                System.exit(0);
            }
        });
        return window;
    }
}
