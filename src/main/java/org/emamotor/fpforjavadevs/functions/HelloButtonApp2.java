package org.emamotor.fpforjavadevs.functions;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HelloButtonApp2 {
    private final Button button = new Button();
    
    public HelloButtonApp2() {
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello There: event received: " + e);
            }
            
        });
    }
}
