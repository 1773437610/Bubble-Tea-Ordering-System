package ui.graphical.manage;

import model.Drinks;
import model.MilkTea;
import model.ingredients.Ingredients;
import ui.OrderAppGUI;
import ui.graphical.history.OrderHistoryPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Represents the right split pane under the manage order panel
public class AddDrinksPanel extends JSplitPane {
    JPanel drinkPanel;
    JPanel ingredientPanel;
    JSplitPane splitPane;
    JComboBox<String> types;
    JComboBox<Integer> iceLevel;
    JComboBox<Integer> sweetness;
    JComboBox<String> size;
    JLabel sweetnessLabel;
    String[] typesOfDrinks = {"MilkTea", "Drinks"};
    Integer[] sweetnessList = {100, 80, 50, 20, 0};
    Integer[] iceLevelList = {100, 80, 50, 20, 0};
    String[] sizeList = {"Medium", "Small", "Large"};
    JButton addButton;
    ArrayList<JCheckBox> checkBoxList;
    OrderHistoryPanel orderHistoryPanel;
    JEditorPane textPane;

    //Represents the top panel under
    public AddDrinksPanel(OrderHistoryPanel orderHistoryPanel) {
        this.orderHistoryPanel = orderHistoryPanel;
        drinkPanel = new JPanel();
        ingredientPanel = new JPanel();
        types = new JComboBox<>(typesOfDrinks);
        sweetness = new JComboBox<>(sweetnessList);
        iceLevel = new JComboBox<>(iceLevelList);
        size = new JComboBox<>(sizeList);
        addButton = new JButton("add drink");
        checkBoxList = new ArrayList<>();
        textPane = new JEditorPane();

        setUpTopPane();

        setUpCheckBoxes();

        setVisible(true);
    }

    public void setUpTopPane() {
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setTopComponent(drinkPanel);
        setDividerLocation(85);
        drinkPanel.add(new JLabel("Drinks:"));
        drinkPanel.add(types);

        sweetnessLabel = new JLabel("Sweetness:");
        drinkPanel.add(sweetnessLabel);
        drinkPanel.add(sweetness);

        drinkPanel.add(new JLabel("Size:"));
        drinkPanel.add(size);

        drinkPanel.add(new JLabel("Ice Levels:"));
        drinkPanel.add(iceLevel);
    }

    public void setUpCheckBoxes() {
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        splitPane.setDividerLocation(OrderAppGUI.FRAME_WIDTH / 3);
        splitPane.setVisible(true);
        JLabel text = new JLabel("Select Toppings:");
        left.add(text, BorderLayout.WEST);

        for (Ingredients ingredients : Ingredients.values()) {
            JCheckBox checkBox = new JCheckBox(ingredients.name());
            checkBoxList.add(checkBox);
            left.add(checkBox, BorderLayout.CENTER);
        }
        drinkPanel.add(addButton, BorderLayout.SOUTH);
        //ingredientPanel.add(new JLabel("Order details:"), BorderLayout.SOUTH);
        right.add(textPane);
        textPane.setContentType("test/plain");
        textPane.setPreferredSize(new Dimension(300, 300));
        textPane.setText(updateOrderDetail());

        textPane.setEditable(false);
        setBottomComponent(splitPane);
    }

    public String updateOrderDetail() {

        String text = "Order detail:\n";

        if (orderHistoryPanel.getSelectedOrder() != null) {
            ArrayList<Drinks> order = orderHistoryPanel.getSelectedOrder().getItemsOrdered();

            for (int i = 0; i < order.size(); i++) {
                Drinks drink = order.get(i);
                text += (i + 1) + "." + drink.getClass().toString().substring(12);
                text += "\n   Sweetness: " + drink.getSweetness();
                text += "\n   Size: " + drink.getSize();

                if (drink.getClass().equals(MilkTea.class)) {
                    text += "\n   IceLevel:" + ((MilkTea) drink).getIceLevel();
                }

                text += "\n";
            }
        }

        return text;
    }

    public ArrayList<Ingredients> getSelectedToppings() {
        ArrayList<Ingredients> array = new ArrayList<>();

        for (int i = 0; i < checkBoxList.size(); i++) {
            if (checkBoxList.get(i).isSelected()) {
                array.add(Ingredients.values()[i]);
            }
        }

        return array;
    }

    public Drinks getNewDrinksCreated() {
        int iceLevelValue = ((Integer) iceLevel.getSelectedItem());
        int sweetnessValue = ((Integer)sweetness.getSelectedItem());
        String sizeValue = (String) size.getSelectedItem();

        if (types.getSelectedItem().equals("MilkTea")) {
            Drinks drink = new MilkTea(iceLevelValue, sweetnessValue, sizeValue);
            addSelectedToppingToDrink(drink);
            return drink;
        } else {
            Drinks drink = new Drinks(sweetnessValue, sizeValue);
            addSelectedToppingToDrink(drink);
            return drink;
        }
    }

    public void addSelectedToppingToDrink(Drinks drink) {
        ArrayList<Ingredients> array = getSelectedToppings();

        for (Ingredients ingredients : array) {
            drink.addIngredient(ingredients);
        }
    }

    public JEditorPane getTextPane() {
        return textPane;
    }
}
