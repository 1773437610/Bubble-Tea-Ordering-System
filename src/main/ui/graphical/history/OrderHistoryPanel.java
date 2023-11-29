package ui.graphical.history;

import model.Order;
import ui.OrderAppGUI;
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
    private final OrderAppGUI mainFrame;
    private SelectDrinksPanel selectDrinksPanel;
    private AddDrinksPanel addDrinksPanel;
    private final JPanel leftPane;
    private final JEditorPane rightTextPane;
    private final JScrollPane rightTextPaneContainer;
    private JList<String> list;
    private final JScrollPane scrollPane;

    private final JButton showAllOrder;
    private final JButton addOrder;
    private final JButton deleteOrder;
    private String[] orderList;
    private int selectedOrderNum;
    private Order selectedOrder;

    public OrderHistoryPanel(OrderAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        rightTextPaneContainer = new JScrollPane();
        rightTextPane = new JEditorPane();
        leftPane = new JPanel();
        showAllOrder = new JButton("Show Orders");
        addOrder = new JButton("Add Order");
        deleteOrder = new JButton("Delete Order");
        leftPane.add(showAllOrder);
        leftPane.add(addOrder);
        leftPane.add(deleteOrder);
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(200, 200));
        setLeftComponent(leftPane);
        setRightComponent(rightTextPaneContainer);
        rightTextPaneContainer.setViewportView(rightTextPane);
        rightTextPaneContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightTextPaneContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setOrientation(JSplitPane.VERTICAL_SPLIT);

        setUpOrderActionListener();
        updateOrderListScrollPane();
        leftPane.add(scrollPane);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: update the order list scroll pane
    public void updateOrderListScrollPane() {
        orderList = new String[Order.getOrderHistory().size()];

        for (int i = 0; i < Order.getOrderHistory().size(); i++) {
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
    //EFFECTS: show all order details in the text pane
    public void showOrdersOnTextPane() {

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
        mainFrame.getDeleteOrderButton().doClick(250);

    }

    //MODIFIES: this
    //EFFECTS: initialize selectedOrder with the order selected on the orders list
    public void updateSelectedOrder(int selectedIndex) {
        selectedOrder = Order.getOrderHistory().get(selectedIndex);
    }

    //MODIFIES: this, Order
    //EFFECTS: if corresponding buttons is clicked:
    // add new order to Order and update the order list scroll pane
    //delete order in Order and update the order list scroll pane
    //show all order for on the text pane
    public void setUpOrderActionListener() {
        addOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order.addToOrderHistory(new Order());
                updateOrderListScrollPane();
            }
        });

        deleteOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order.deleteOrderHistory(selectedOrderNum);
                updateOrderListScrollPane();
            }
        });

        showAllOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOrdersOnTextPane();
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
