package ui;

import model.Order;
import ui.graphical.manage.AddDrinksPanel;
import ui.graphical.manage.SelectDrinksPanel;
import ui.graphical.history.OrderHistoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

//Represent the graphical user interface for the order app
public class OrderAppGUI extends JFrame {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 500;
    private static final boolean RESIZABLE = false;
    private OrderApp app;
    private JMenuBar menuBar;
    private JMenu menu;
    private JButton menu1;
    private JButton menu2;
    private JMenuItem save;
    private JMenuItem load;
    private OrderHistoryPanel orderHistoryPanel;
    private SelectDrinksPanel selectDrinksPanel;
    private JSplitPane manageOrderSplitPanel;
    private AddDrinksPanel orderAddDrinksPanel;

    public OrderAppGUI() {
        super("OrderApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setUpMenuBar();
        try {
            app = new OrderApp(false);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Errors: Files not found!");
        }

        setSize((new Dimension(FRAME_WIDTH, FRAME_HEIGHT)));
        setResizable(RESIZABLE);
        centreOnScreen();

        setUpSplitPane();

        setVisible(true);
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

        menu.add(save);
        menu.addSeparator();
        menu.add(load);

        setJMenuBar(menuBar);
    }

    private void setUpMenuItems() {
        save = new JMenuItem("save");
        save.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, InputEvent.ALT_MASK));
        save.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(save);

        load = new JMenuItem("load");
        load.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, InputEvent.ALT_MASK));
        save.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
    }

    private void setUpMenuItemsActionListener() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.saveOrder();
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.loadOrder();
                orderHistoryPanel.updateOrderListScrollPane();
            }
        });
    }

    private void setUpMenuActionListener() {
        menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(manageOrderSplitPanel);
                add(orderHistoryPanel);
                repaint();
                setVisible(true);
            }
        });

        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(orderHistoryPanel);
                add(manageOrderSplitPanel);
                repaint();
                setVisible(true);
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
        list.setBounds(100, 200, 75, 75);
        add(list);
        setLayout(null);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: set up the split pane and have it displayed
    private void setUpSplitPane() {
        orderHistoryPanel = new OrderHistoryPanel();
        orderAddDrinksPanel = new AddDrinksPanel(orderHistoryPanel);
        selectDrinksPanel = new SelectDrinksPanel(orderAddDrinksPanel);
        orderHistoryPanel.setOrderPanel(selectDrinksPanel);
        orderHistoryPanel.setAddDrinksPanel(orderAddDrinksPanel);

        manageOrderSplitPanel = new JSplitPane(SwingConstants.VERTICAL, selectDrinksPanel, orderAddDrinksPanel);
        manageOrderSplitPanel.setDividerLocation(FRAME_WIDTH / 5);
        manageOrderSplitPanel.setContinuousLayout(true);

        add(manageOrderSplitPanel);
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
