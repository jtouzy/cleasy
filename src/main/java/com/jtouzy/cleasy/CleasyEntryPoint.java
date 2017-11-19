package com.jtouzy.cleasy;

import com.jtouzy.cleasy.launch.Launcher;

public class CleasyEntryPoint {
    public static void main(String[] args) {
        boolean success = Launcher.launch(args);
        System.exit(success ? 0 : 1);
    }
}
