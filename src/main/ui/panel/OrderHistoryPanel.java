package ui.panel;

import model.Order;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class OrderHistoryPanel extends JSplitPane implements ListSelectionListener {
    JPanel leftPane;
    JList list;
    JScrollPane scrollPane;
    JEditorPane rightTextPane;
    JButton addOrder;
    String[] orderList;

    public OrderHistoryPanel() {
        rightTextPane = new JEditorPane();
        rightTextPane.setVisible(true);
        leftPane = new JPanel();
        addOrder = new JButton("Add Order");
        leftPane.add(addOrder);
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setLeftComponent(leftPane);
        setRightComponent(rightTextPane);

        updateOrderListScrollPane();
        leftPane.add(scrollPane);
        setVisible(true);
    }

    public void updateOrderListScrollPane() {
        orderList = new String[Order.getOrdersHistory().size()];

        for (int i = 0; i < Order.getOrdersHistory().size(); i++) {
            orderList[i] = "Order" + i;
        }

        list = new JList<>(orderList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectionMode(0);
        list.addListSelectionListener(this);
        list.setFont(new Font(Font.DIALOG, Font.BOLD, 12));

        scrollPane = new JScrollPane(list);
        scrollPane.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
