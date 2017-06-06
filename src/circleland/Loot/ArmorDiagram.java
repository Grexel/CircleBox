/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import circleland.CircleAffix;
import circleland.CircleEquipment;
import circleland.CircleItem;
import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class ArmorDiagram extends ItemDiagram{
    private static Random rand = new Random();

    public static Random getRand() {
        return rand;
    }

    public static void setRand(Random aRand) {
        rand = aRand;
    }
    private String armorType;
    private Color color;
    private int minDefense;
    private int maxDefense;
    private List<CircleAffix> affixes;
    
    public ArmorDiagram(String name, int rarity, int itemLevel, String armorType,
            Color color, int minDefense, int maxDefense, List<CircleAffix> affixes) {
        super(itemLevel,name,rarity);
        this.armorType = armorType;
        this.color = color;
        this.minDefense = minDefense;
        this.maxDefense = maxDefense;
        this.affixes = affixes;
    }
    @Override
    public CircleItem buildItem() {
        CircleEquipment armor = new CircleEquipment();
        armor.type(armorType);
        armor.itemLevel(getItemLevel());
        armor.name(getName());
        armor.specialColor(getColor());
        armor.defense(rand.nextInt(maxDefense - minDefense+1)+minDefense);
        armor.affixes().addAll(affixes);
        return armor;
    }
    
    @Override
    public String codeItem(CircleItem item) {
        
        return "";
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getMinDefense() {
        return minDefense;
    }

    public void setMinDefense(int minDefense) {
        this.minDefense = minDefense;
    }

    public int getMaxDefense() {
        return maxDefense;
    }

    public void setMaxDefense(int maxDefense) {
        this.maxDefense = maxDefense;
    }


    
}
