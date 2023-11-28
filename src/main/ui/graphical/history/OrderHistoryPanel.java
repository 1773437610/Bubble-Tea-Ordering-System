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

//Represents the order history panel for the button show all orders
public class OrderHistoryPanel extends JSplitPane implements ListSelectionListener {
    private SelectDrinksPanel selectDrinksPanel;
    private AddDrinksPanel addDrinksPanel;
    private JPanel leftPane;
    private JEditorPane rightTextPane;
    private JList<String> list;
    private JScrollPane scrollPane;

    private JButton addOrder;
    private JButton deleteOrder;
    private String[] orderList;
    private int selectedOrderNum;
    private Order selectedOrder;

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

    //MODIFIES: this
    //EFFECTS: update the order list scroll pane
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
    }

    //MODIFIES: this
    //EFFECTS: initialize fields and update the text that is shown on the right panel
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        selectedOrderNum = list.getSelectedIndex();
        updateSelectedOrder(selectedOrderNum);
        selectDrinksPanel.setSelectedOrder(selectedOrder);
        selectDrinksPanel.updateScrollPane();
        addDrinksPanel.getTextPane().setText(addDrinksPanel.updateOrderDetail());
    }

    //MODIFIES: this
    //EFFECTS: initialize selectedOrder with the order selected on the orders list
    public void updateSelectedOrder(int selectedIndex) {
        selectedOrder = Order.getOrdersHistory().get(selectedIndex);
    }

    //MODIFIES: this, Order
    //EFFECTS: add new order to Order and update the order list scroll pane
    //delete order in Order and update the order list scroll pane
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
