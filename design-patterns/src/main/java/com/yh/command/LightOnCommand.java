package com.yh.command;

/**
 * Light On Command
 *
 * @author yanhuan
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
