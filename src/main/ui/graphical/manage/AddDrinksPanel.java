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
    private final JPanel drinkPanel;
    private final JComboBox<String> types;
    private final JComboBox<Integer> iceLevel;
    private final JComboBox<Integer> sweetness;
    private final JComboBox<String> size;

    private final String[] typesOfDrinks = {"MilkTea", "Drinks"};
    private final Integer[] sweetnessList = {100, 80, 50, 20, 0};
    private final Integer[] iceLevelList = {100, 80, 50, 20, 0};
    private final String[] sizeList = {"Medium", "Small", "Large"};
    private final ArrayList<JCheckBox> checkBoxList;
    private final OrderHistoryPanel orderHistoryPanel;
    private final JScrollPane textPaneScrollContainer;
    private final JEditorPane textPane;

    public AddDrinksPanel(OrderHistoryPanel orderHistoryPanel) {
        this.orderHistoryPanel = orderHistoryPanel;
        drinkPanel = new JPanel();
        types = new JComboBox<>(typesOfDrinks);
        sweetness = new JComboBox<>(sweetnessList);
        iceLevel = new JComboBox<>(iceLevelList);
        size = new JComboBox<>(sizeList);
        checkBoxList = new ArrayList<>();
        textPane = new JEditorPane();
        textPaneScrollContainer = new JScrollPane();

        //drinkPanel.setBackground(new Color(255,226,226));

        setUpTopPane();

        setUpCheckBoxes();

        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: set up graphical components in the top of the addDrinksPanel
    public void setUpTopPane() {
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setTopComponent(drinkPanel);
        setDividerLocation(85);
        drinkPanel.add(new JLabel("Drinks:"));
        drinkPanel.add(types);

        JLabel sweetnessLabel = new JLabel("Sweetness:");
        drinkPanel.add(sweetnessLabel);
        drinkPanel.add(sweetness);

        drinkPanel.add(new JLabel("Size:"));
        drinkPanel.add(size);

        drinkPanel.add(new JLabel("Ice Levels:"));
        drinkPanel.add(iceLevel);
    }

    //MODIFIES: this
    //EFFECTS: set up graphical components in the bottom of the addDrinksPanel
    public void setUpCheckBoxes() {
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        splitPane.setDividerLocation(OrderAppGUI.FRAME_WIDTH / 3);
        //left.setBackground(new Color(255,226,226));
        //right.setBackground(new Color(255,226,226));
        splitPane.setVisible(true);
        JLabel text = new JLabel("Select Toppings:");
        left.add(text, BorderLayout.WEST);

        for (Ingredients ingredients : Ingredients.values()) {
            JCheckBox checkBox = new JCheckBox(ingredients.name());
            checkBoxList.add(checkBox);
            left.add(checkBox, BorderLayout.CENTER);
        }

        right.add(textPaneScrollContainer);
        textPaneScrollContainer.setViewportView(textPane);
        textPaneScrollContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textPane.setContentType("test/plain");
        textPane.setPreferredSize(new Dimension(300, 300));
        textPane.setText(updateOrderDetail());

        textPane.setEditable(false);
        setBottomComponent(splitPane);
    }

    //EFFECTS: return an arrayList of Ingredients selected in check boxes
    public ArrayList<Ingredients> getSelectedToppings() {
        ArrayList<Ingredients> array = new ArrayList<>();

        for (int i = 0; i < checkBoxList.size(); i++) {
            if (checkBoxList.get(i).isSelected()) {
                array.add(Ingredients.values()[i]);
            }
        }

        return array;
    }

    //EFFECTS: return a new drink that is customized by user
    public Drinks getNewDrinksCreated() {
        int iceLevelValue = ((Integer) iceLevel.getSelectedItem());
        int sweetnessValue = ((Integer) sweetness.getSelectedItem());
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

    //EFFECTS:
    public String updateOrderDetail() {
        if (orderHistoryPanel.getSelectedOrder() != null) {
            return orderHistoryPanel.getSelectedOrder().showOrderDetails();
        }

        return "Order details:";
    }

    //MODIFIES: this
    //EFFECTS: add Ingredients selected by user to drink
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
