package ui.panel;

import model.Order;

import javax.swing.*;
import java.awt.*;

public class OrderHistoryPanel extends JPanel {
    public OrderHistoryPanel() {
        setBounds(40,80,200,200);
        setBackground(Color.DARK_GRAY);
        DefaultListModel<String> l1 = new DefaultListModel<>();
        JList<String> list = new JList<>(l1);
        list.setBounds(100,100, 75,75);
        for (int i = 0; i < Order.getOrdersHistory().size(); i++) {
            l1.addElement("Order " + i);
        }
        add(list);
    }
}
