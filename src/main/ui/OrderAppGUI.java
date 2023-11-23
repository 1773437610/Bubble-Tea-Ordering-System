package ui;

import model.Order;
import ui.panel.OrderPanel;
import ui.panel.OrderHistoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

public class OrderAppGUI extends JFrame {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 500;
    private static final boolean RESIZABLE = false;
    private OrderApp app;
    private JMenuBar menuBar;
    private JMenu menu;
    private JButton menu1;
    private JButton menu2;
    private JMenuItem menuItem;
    private JMenuItem menuItem1;
    private final OrderHistoryPanel orderHistoryPanel;
    private final OrderPanel orderPanel;

    public OrderAppGUI() {
        super("OrderApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setUpMenuBar();
        try {
            app = new OrderApp(false);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Eggs are not supposed to be green.");
        }

        setSize((new Dimension(FRAME_WIDTH, FRAME_HEIGHT)));
        setResizable(RESIZABLE);
        centreOnScreen();
        setVisible(true);

        orderHistoryPanel = new OrderHistoryPanel();
        orderHistoryPanel.setVisible(false);
        add(orderHistoryPanel);

        orderPanel = new OrderPanel();
        orderPanel.setVisible(false);
        add(orderPanel, BorderLayout.WEST);
    }

    private void setUpMenuBar() {
        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");

        menu1 = new JButton("Show All Orders");
        menu1.setMnemonic(KeyEvent.VK_A);
        menu1.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");

        menu2 = new JButton("Manage Order");
        menu2.setMnemonic(KeyEvent.VK_A);
        menu2.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");

        menuBar.add(menu);
        menuBar.add(menu1);
        menuBar.add(menu2);

        setUpMenuItems();
        setUpMenuItemsActionListener();
        setUpMenuActionListener();

        menu.add(menuItem);
        menu.addSeparator();
        menu.add(menuItem1);

        setJMenuBar(menuBar);
    }

    private void setUpMenuItems() {
        menuItem = new JMenuItem("save");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem);

        menuItem1 = new JMenuItem("load");
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
    }

    private void setUpMenuItemsActionListener() {
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.saveOrder();
            }
        });

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.loadOrder();
            }
        });
    }

    private void setUpMenuActionListener() {
        menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderPanel.setVisible(false);
                orderHistoryPanel.setVisible(true);
                showOrderHistory();
            }
        });

        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderPanel.setVisible(true);
                orderHistoryPanel.setVisible(false);
            }
        });
    }

    private void showOrderHistory() {
        DefaultListModel<String> l1 = new DefaultListModel<>();
        System.out.println(Order.getOrdersHistory().size());
        for (int i = 0; i < Order.getOrdersHistory().size(); i++) {
            l1.addElement("Order " + i);
        }
        JList<String> list = new JList<>(l1);
        list.setBounds(100,200, 75,75);
        add(list);
        setLayout(null);
        setVisible(true);
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public static void main(String[] args) {
        new OrderAppGUI();
    }
}
