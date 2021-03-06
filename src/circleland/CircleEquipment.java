/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeff
 */
public class CircleEquipment extends CircleItem{
    protected String type;
    public void type(String i){type = i;}
    public String type(){return type;}
    
    protected List<CircleAffix> affixes;
    public void affixes(List<CircleAffix> aff){affixes = aff;}
    public List<CircleAffix> affixes(){return affixes;}
    
    protected int itemLevel;
    public void itemLevel(int i){itemLevel = i;}
    public int itemLevel(){return itemLevel;}
    
    protected int defense;
    public void defense(int i){defense = i;}
    public int defense(){return defense;}
    
    public CircleEquipment()
    {
        super();
        type = "None";
        affixes = new ArrayList<>();
        itemLevel = 1;
        defense = 0;
    }
    public void addBonus(CircleEntity entity){
        entity.attackDefense(entity.attackDefense() + defense);
    }
    public void draw(Graphics2D graphics){
        draw(graphics, (int)position.x, (int)position.y);
    }
    public void draw(Graphics2D graphics, int x, int y)
    {
        graphics.setColor(rarityColor);
        graphics.fillOval((int)(x - (size+4)/ 2), (int)(y - (size+4) / 2),
            (int)size+4, (int)size+4);
        graphics.setColor(itemColor);
        graphics.fillOval((int)(x - size / 2), (int)(y - size / 2),
            (int)size, (int)size);
        graphics.setColor(specialColor);
        graphics.fillOval((int)(x - 4), (int)(y - 4),
            (int)8, (int)8);
    }
    public void drawDetails(Graphics2D graphics,int x, int y){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x-200, y - 200, 200, 200);
        graphics.setColor(Color.WHITE);
        graphics.drawString(name(), x - 190, y - 190);
        graphics.drawString("Armor: " + defense(), x - 190, y - 170);
        int yOff = -140;
        for(CircleAffix affix : affixes){
            graphics.drawString(affix.getDetails(), x-190, y+yOff);
            yOff+=10;
        }
    }
}
