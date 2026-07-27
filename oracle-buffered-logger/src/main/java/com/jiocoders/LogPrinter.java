package com.jiocoders;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class LogPrinter {

    public LogPrinter() {
        System.out.println("************ ************ Application has been started ************ ************");
    }

    public void appStart(String msg) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println(msg);
    }
}