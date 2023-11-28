package ui.graphical.history;

import model.Order;
import ui.graphical.manage.AddDrinksPanel;
import ui.graphical.manage.SelectDrinksPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderHistoryPanel extends JSplitPane implements ListSelectionListener {
    SelectDrinksPanel selectDrinksPanel;
    AddDrinksPanel addDrinksPanel;
    JPanel leftPane;
    JEditorPane rightTextPane;
    JList<String> list;
    JScrollPane scrollPane;

    JButton addOrder;
    JButton deleteOrder;
    String[] orderList;
    int selectedOrderNum;
    Order selectedOrder;

    public OrderHistoryPanel() {
        rightTextPane = new JEditorPane();
        leftPane = new JPanel();
        addOrder = new JButton("Add Order");
        deleteOrder = new JButton("Delete Order");
        leftPane.add(addOrder);
        leftPane.add(deleteOrder);
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(200, 200));
        setLeftComponent(leftPane);
        setRightComponent(rightTextPane);
        setOrientation(JSplitPane.VERTICAL_SPLIT);

        setUpOrderActionListener();
        updateOrderListScrollPane();
        leftPane.add(scrollPane);
        setVisible(true);
    }

    public void updateOrderListScrollPane() {
        orderList = new String[Order.getOrdersHistory().size()];

        for (int i = 0; i < Order.getOrdersHistory().size(); i++) {
            orderList[i] = "Order" + (i + 1);
        }

        list = new JList<>(orderList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectionMode(0);
        list.addListSelectionListener(this);
        list.setFont(new Font(Font.DIALOG, Font.BOLD, 12));

        scrollPane.setColumnHeaderView(list);
        //scrollPane.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        selectedOrderNum = list.getSelectedIndex();
        updateSelectedOrder(selectedOrderNum);
        selectDrinksPanel.setSelectedOrder(selectedOrder);
        selectDrinksPanel.updateScrollPane();
        addDrinksPanel.getTextPane().setText(addDrinksPanel.updateOrderDetail());
    }

    public void updateSelectedOrder(int selectedIndex) {
        selectedOrder = Order.getOrdersHistory().get(selectedIndex);
    }

    public void setUpOrderActionListener() {
        addOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order.addToOrdersHistory(new Order());
                updateOrderListScrollPane();
            }
        });

        deleteOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order.deleteOrdersHistory(selectedOrderNum);
                updateOrderListScrollPane();
            }
        });
    }

    public void updateTextPane() {

    }

    public void setOrderPanel(SelectDrinksPanel selectDrinksPanel) {
        this.selectDrinksPanel = selectDrinksPanel;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setAddDrinksPanel(AddDrinksPanel orderAddDrinksPanel) {
        this.addDrinksPanel = orderAddDrinksPanel;
    }
}
