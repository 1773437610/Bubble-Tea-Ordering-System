package ui;

import model.Event;
import model.EventLog;
import ui.graphical.manage.AddDrinksPanel;
import ui.graphical.manage.SelectDrinksPanel;
import ui.graphical.history.OrderHistoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

//Represent the graphical user interface for the order app
public class OrderAppGUI extends JFrame {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 500;
    private static final boolean RESIZABLE = false;
    private OrderApp app;
    private JMenuBar menuBar;
    private JMenu menu;
    private JButton addOrderButton;
    private JButton deleteOrderButton;
    private JMenuItem save;
    private JMenuItem load;
    private OrderHistoryPanel orderHistoryPanel;
    private SelectDrinksPanel selectDrinksPanel;
    private JSplitPane manageOrderSplitPanel;
    private AddDrinksPanel orderAddDrinksPanel;

    public OrderAppGUI() {
        super("OrderApp");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener closeAction = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                printLog();
                System.exit(0);
            }
        };
        addWindowListener(closeAction);

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

    //MODIFIES: this
    //EFFECTS: set up the menu bar
    private void setUpMenuBar() {
        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);

        addOrderButton = new JButton("Show All Orders");
        addOrderButton.setMnemonic(KeyEvent.VK_A);

        deleteOrderButton = new JButton("Manage Order");
        deleteOrderButton.setMnemonic(KeyEvent.VK_A);

        menuBar.add(menu);
        menuBar.add(addOrderButton);
        menuBar.add(deleteOrderButton);

        setUpMenuItems();
        setUpMenuItemsActionListener();
        setUpMenuActionListener();

        menu.add(save);
        menu.addSeparator();
        menu.add(load);

        setJMenuBar(menuBar);
    }

    //MODIFIES: this
    //EFFECTS: set up the menu items under Menu
    private void setUpMenuItems() {
        save = new JMenuItem("save");
        save.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, InputEvent.ALT_MASK));
        menu.add(save);

        load = new JMenuItem("load");
        load.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, InputEvent.ALT_MASK));
    }

    //MODIFIES: this
    //EFFECTS: save and load the status of the application
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

    //MODIFIES: this
    //EFFECTS: set up the menu action listener
    private void setUpMenuActionListener() {
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(manageOrderSplitPanel);
                add(orderHistoryPanel);
                repaint();
                setVisible(true);
            }
        });

        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(orderHistoryPanel);
                add(manageOrderSplitPanel);
                repaint();
                setVisible(true);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: set up the split pane and have it displayed
    private void setUpSplitPane() {
        orderHistoryPanel = new OrderHistoryPanel(this);
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

    public JButton getDeleteOrderButton() {
        return deleteOrderButton;
    }

    //EFFECTS: print to the console all the events that have been logged since app started
    public void printLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.print(next.toString() + "\n\n");
        }

        repaint();
    }

    public static void main(String[] args) {
        new OrderAppGUI();
    }
}
