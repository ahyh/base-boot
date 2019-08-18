package com.yh.command;

/**
 * Command Test
 *
 * @author yanhuan
 */
public class SimpleRemoteControlMain {

    public static void main(String[] args) {
        SimpleRemoteControl control = new SimpleRemoteControl();
        control.setCommand(new LightOnCommand(new Light()));
        control.buttonWasPressed();
    }
}
