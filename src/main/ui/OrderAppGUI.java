package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

public class OrderAppGUI extends JFrame {
    OrderApp app;
    JMenuBar menuBar;
    JMenu menu;
    JMenu menu1;
    JMenu menu2;
    JMenuItem menuItem;
    JMenuItem menuItem1;

    public OrderAppGUI() {
        super("OrderApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setUpMenu();
        try {
            app = new OrderApp(false);
        } catch (FileNotFoundException e) {
            add(new JFrame("Error"));
        }

        AppPanel app = new AppPanel();
        add(app);
        JPanel j = new JPanel();
        setSize(new Dimension(1000, 1000));
        setBackground(Color.RED);
        add(j);
        centreOnScreen();
        setVisible(true);
    }

    private void setUpMenu() {
        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");

        menu1 = new JMenu("Show All Orders");
        menu1.setMnemonic(KeyEvent.VK_A);
        menu1.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");

        menu2 = new JMenu("Manage Order");
        menu2.setMnemonic(KeyEvent.VK_A);
        menu2.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");

        menuBar.add(menu);
        menuBar.add(menu1);
        menuBar.add(menu2);

        setUpMenuItems();
        setUpActionListener();

        menu.add(menuItem);
        menu.addSeparator();
        menu.add(menuItem1);

        setJMenuBar(menuBar);
    }

    private void setUpMenuItems() {
        menuItem = new JMenuItem("save");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem);

        menuItem1 = new JMenuItem("load");
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
    }

    private void setUpActionListener() {
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

        menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
